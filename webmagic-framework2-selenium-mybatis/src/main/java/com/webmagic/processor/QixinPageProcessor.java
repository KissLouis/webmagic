package com.webmagic.processor;

import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import com.webmagic.dao.ICompanyDao;
import com.webmagic.entity.Company;

/**
 * 
 * Description: 企信网基于关键字爬取企业信息
 * 
 * @author Louis
 * @version 1.0
 * @Note
 * @Date 2017年12月20日 下午2:54:05
 */
public class QixinPageProcessor implements PageProcessor {

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site
			.me()
			.setUserAgent(
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36")
			.addHeader(
					"Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
			.addHeader("Accept-Encoding", "gzip, deflate")
			.addHeader("Accept-Language", "zh-CN,zh;q=0.9")
			.addHeader("Connection", "keep-alive")
			.addHeader("Host", "www.qixin.com").setRetryTimes(3)
			.setTimeOut(3000).setSleepTime(1000);

	// 部分二：总共爬取的数量
	private static int count = 0;

	// 部分三：需要搜索的公司关键字
	private static String companyName = "广州皓云原智信息科技有限公司";

	// 部分四：爬取的url
	private static String url = "http://www.qixin.com/search?key="
			+ companyName + "&page=1";

	// 部分五：cookie信息储存
	private Set<Cookie> cookies;

	// 部分七
	private static Company company = new Company(companyName);

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		for (Cookie cookie : cookies) {
			site.addCookie(cookie.getName(), cookie.getValue());
		}
		return site;
	}

	/**
	 * 
	 * Description:使用 selenium 来模拟用户登录获取cookie信息
	 * 
	 * @Note
	 * @Author: Louis
	 * @Date: 2017年12月21日 下午1:23:27
	 */
	public void login() {
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.qixin.com/auth/login");
		System.out.println(driver.getCurrentUrl());
		try {
			// 3秒时间等待页面全部渲染完成
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO
		}
		// 输入手机号码
		driver.findElement(
				By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div/div[1]/input"))
				.sendKeys("18601698461");
		// 输入密码
		driver.findElement(
				By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div/div[2]/input"))
				.sendKeys("liu19970824");
		// 模拟点击登录按钮
		driver.findElement(
				By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div/div[4]/a"))
				.click();
		// 获取cookie信息
		cookies = driver.manage().getCookies();
		driver.close();
	}

	/**
	 * 
	 * Description:爬取数据新增入库
	 * 
	 * @param comapny
	 * @throws Exception
	 * @Note
	 * @Author: Louis
	 * @Date: 2017年12月21日 下午2:13:04
	 */
	public void addCompany(Company comapny) throws Exception {
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(Resources
				.getResourceAsStream("MyBatisConfig.xml"));
		// 创建session
		SqlSession session = ssf.openSession();
		ICompanyDao companyDao = session.getMapper(ICompanyDao.class);
		companyDao.addCompany(comapny);
		// 提交事务
		session.commit();
		// 关闭连接
		session.close();
	}

	@Override
	/**
	 * process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	 */
	public void process(Page page) {
		// TODO Auto-generated method stub
		// 判断链接是否符合格式http://www.cnblogs.com/任意个数字字母
		if (!page.getUrl().regex("/company/*").match()) {
			System.out.println("符合链接");
			// 加入满足条件的链接
			page.addTargetRequests(page
					.getHtml()
					.xpath("/html/body/div[5]/div/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/a/@href")
					.all());
		} else {
			System.out.println("进来了" + count);
			// 获取页面需要的内容
			count++;
			// 统一社会信用代码
			company.setUnifiedSocialCreditCode(page.getHtml()
					.xpath("//*[@id='icinfo']/table/tbody/tr[1]/td[2]/text()")
					.get());
			// 企业名称
			company.setCompanyName(page.getHtml()
					.xpath("/html/body/div[6]/div/div[2]/div/div[1]/h4/text()")
					.get());
			// 电话
			company.setCompanyTel(page
					.getHtml()
					.xpath("/html/body/div[6]/div/div[2]/div/div[1]/div[1]/div[1]/div/div[2]/div/span/text()")
					.get());
			// 官网
			company.setCompanyWebSite(page
					.getHtml()
					.xpath("/html/body/div[6]/div/div[2]/div/div[1]/div[1]/div[2]/div/div[2]/a/@href")
					.get());
			// 地址
			company.setCompanyAddress(page
					.getHtml()
					.xpath("/html/body/div[6]/div/div[2]/div/div[1]/div[1]/div[3]/div/div[2]/text()")
					.get());
			// 简介
			company.setCompanyProfile(page
					.getHtml()
					.xpath("/html/body/div[6]/div/div[2]/div/div[2]/div/text()")
					.get());
			// 排序
			company.setCompanySort(count);
			try {
				addCompany(company);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("开始爬取...");
		long startTime, endTime;
		QixinPageProcessor processor = new QixinPageProcessor();
		processor.login();
		startTime = System.currentTimeMillis();
		Spider.create(processor).addUrl(url).thread(5).run();
		endTime = System.currentTimeMillis();
		System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000)
				+ "秒，抓取了" + count + "条记录");
		System.exit(0);
	}

}
