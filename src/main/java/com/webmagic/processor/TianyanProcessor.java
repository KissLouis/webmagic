package com.webmagic.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import com.webmagic.dao.ISearchDao;
import com.webmagic.entity.Company;
import com.webmagic.entity.CompanyInformation;
import com.webmagic.entity.Search;

public class TianyanProcessor implements PageProcessor {

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site
			.me()
			.setUserAgent(
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36")
			.addHeader("host", "www.tianyancha.com")
			.addHeader("Accept",
					"application/json, text/javascript, */*; q=0.01")
			.addHeader("Accept-Encoding", "gzip, deflate, br")
			.addHeader("Accept-Language", "zh-CN,zh;q=0.9")
			.addHeader("X-Requested-With", "XMLHttpRequest").setRetryTimes(3)
			.setTimeOut(10000).setSleepTime(1800);

	// 部分二：总共爬取的数量
	private static int count = 0;
	// 部分三：需要搜索的公司关键字
	private static String name = "宁波市民营企业贷款担保有限公司";
	// 部分四：爬取的url
	private static String url = "https://www.tianyancha.com/search?key=" + name
			+ "&checkFrom=searchBox";
	// 部分五：cookie信息储存
	private Set<Cookie> cookies;
	// 部分六：搜索条件封装
	private static Search search = new Search();
	// 部分七：搜索结果
	private static List<Company> listCompany = new ArrayList<Company>();

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		for (Cookie cookie : cookies) {
			System.out.println(cookie.getName().toString() + "\t"
					+ cookie.getValue().toString());
			site.addCookie(cookie.getName().toString(), cookie.getValue()
					.toString());
		}
		return site;
	}

	/**
	 * 
	 * Description:使用 selenium 来模拟用户的登录获取cookie信息
	 * 
	 * @Note Author:Louis Date: 2017年11月29日 下午2:37:35
	 */
	public void login() {
		WebDriver driver = new ChromeDriver();
		System.out.println(driver.getCurrentUrl());
		driver.get("https://www.tianyancha.com/login");
		System.out.println(driver.getCurrentUrl());
		try {
			// 3秒时间等待页面全部渲染完成
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO
		}
		// 输入手机号
		driver.findElement(
				By.xpath("//*[@id='web-content']/div/div/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/input"))
				.sendKeys("18601698461");
		// 输入密码
		driver.findElement(
				By.xpath("//*[@id='web-content']/div/div/div/div[2]/div/div[2]/div[2]/div[2]/div[3]/input"))
				.sendKeys("liulei123456");
		// 模拟点击登录按钮
		driver.findElement(
				By.xpath("//*[@id='web-content']/div/div/div/div[2]/div/div[2]/div[2]/div[2]/div[5]"))
				.click();
		// 获取cookie信息
		cookies = driver.manage().getCookies();
		driver.close();
	}

	/**
	 * 
	 * Description: 数据新增
	 * 
	 * @param search
	 * @Note Author:Louis Date: 2017年11月29日 下午2:37:22
	 */
	public void add(Search search) throws Exception {
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(Resources
				.getResourceAsStream("MyBatisConfig.xml"));
		SqlSession session = ssf.openSession();
		ISearchDao seachDao = session.getMapper(ISearchDao.class);
		seachDao.insertSearch(search);
		session.commit();
		session.close();
	}

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		// 判断链接是否符合
		if (!page.getUrl().regex("https://www.tianyancha.com/company/*")
				.match()) {
			// 加入满足条件的链接
			page.addTargetRequests(page.getHtml()
					.xpath("//div[@class='search_right_item']/div/a/@href")
					.all());
		} else {
			// 获取页面需要的内容
			count++;
			Company company = new Company();
			// 企业基本信息
			company.setCompanyid(page
					.getHtml()
					.xpath("//*[@id='_container_baseInfo']/div/div[3]/table/tbody/tr[1]/td[2]/text()")
					.get());
			company.setCompanyname(page
					.getHtml()
					.xpath("//span[@class='f18 in-block vertival-middle sec-c2']/text()")
					.get());
			company.setCompanytel(page
					.getHtml()
					.xpath("//*[@id='company_web_top']/div[2]/div[2]/div[1]/div[1]/div[1]/span[2]/text()")
					.get());
			company.setCompanyemail(page
					.getHtml()
					.xpath("//*[@id='company_web_top']/div[2]/div[2]/div[1]/div[1]/div[2]/span[2]/text()")
					.get());
			company.setCompanywebsite(page
					.getHtml()
					.xpath("//*[@id='company_web_top']/div[2]/div[2]/div/div[2]/div[1]/a/text()")
					.get());
			company.setCompanyaddress(page
					.getHtml()
					.xpath("//*[@id='company_web_top']/div[2]/div[2]/div[1]/div[2]/div[2]/span[2]/text()")
					.get());
			company.setCompanysynopsis(page
					.getHtml()
					.xpath("//*[@id='_modal_container']/div/div/div[2]/p/text()")
					.get());
			company.setCompanysort(count);
			company.setCreatedate(new Date());
			System.out.println(company.getCompanyid());
			// 企业工商信息
			CompanyInformation companyInformation = new CompanyInformation();
			companyInformation.setId(company.getCompanyid());
			companyInformation
					.setLegalperson(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[2]/table/tbody/tr/td[1]/div/div[1]/div[2]/div/a/text()")
							.get());
			companyInformation
					.setLegalpersoninformation(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[2]/table/tbody/tr/td[1]/div/div[1]/div[2]/div/a/@href")
							.get());
			companyInformation
					.setRegisteredcapital(page
							.getHtml()
							.xpath("//*[@id='_container_holder']/div/table/tbody/tr/td[3]/div/span/text()")
							.get());
			companyInformation
					.setRegistrationtime(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[2]/table/tbody/tr/td[2]/div[2]/div[2]/div/text/text()")
							.get());
			companyInformation
					.setEnterprisestate(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[2]/table/tbody/tr/td[2]/div[3]/div[2]/div/text()")
							.get());
			companyInformation
					.setOwnershipstructure(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[2]/table/tbody/tr/td[3]/div[1]/img/@src")
							.get());
			companyInformation
					.setBusinessregistrationnumber(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[3]/table/tbody/tr[1]/td[2]/text()")
							.get());
			companyInformation
					.setOrganizationcode(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[3]/table/tbody/tr[1]/td[4]/text()")
							.get());
			companyInformation
					.setUniformcreditcode(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[3]/table/tbody/tr[2]/td[2]/text()")
							.get());
			companyInformation
					.setEnterprisetype(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[3]/table/tbody/tr[2]/td[4]/text()")
							.get());
			companyInformation
					.setTaxpayeridentification(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[3]/table/tbody/tr[3]/td[2]/text()")
							.get());
			companyInformation
					.setIndustry(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[3]/table/tbody/tr[3]/td[4]/text()")
							.get());
			companyInformation
					.setBusinessterm(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[3]/table/tbody/tr[4]/td[2]/span/text()")
							.get());
			companyInformation
					.setApprovaldate(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[3]/table/tbody/tr[4]/td[4]/text/text()")
							.get());
			companyInformation
					.setRegistrationauthority(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[3]/table/tbody/tr[5]/td[2]/text()")
							.get());
			companyInformation
					.setEnglishname(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[3]/table/tbody/tr[5]/td[4]/text()")
							.get());
			companyInformation
					.setRegisteredaddress(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[3]/table/tbody/tr[6]/td[2]/text()")
							.get());
			companyInformation
					.setBusinessscope(page
							.getHtml()
							.xpath("//*[@id='_container_baseInfo']/div/div[3]/table/tbody/tr[7]/td[2]/span/span/span[1]/text()")
							.get());
			company.setCompanyInformation(companyInformation);
			listCompany.add(company);
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("开始爬取...");
		TianyanProcessor processor = new TianyanProcessor();
		processor.login(); // 模拟登录
		// 开始时间
		search.setStartdate(new Date(System.currentTimeMillis()));
		Spider.create(processor).addUrl(url).run();
		// 结束时间
		search.setEnddate(new Date(System.currentTimeMillis()));
		System.out.println("爬取结束，耗时约"
				+ ((search.getEnddate().getTime() - search.getStartdate()
						.getTime()) / 1000) + "秒，抓取了" + count + "条记录");
		search.setSearchid((int) System.currentTimeMillis());
		search.setSearchname(name);
		search.setSearchcount(count);
		search.setSearchurl(url);
		search.setListCompany(listCompany);

		processor.add(search);
		System.exit(0);
	}

}
