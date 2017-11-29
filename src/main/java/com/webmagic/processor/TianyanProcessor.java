package com.webmagic.processor;

import java.util.Date;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import com.webmagic.entity.Company;
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
	private static String name = "上海高颖信息技术有限公司";
	// 部分四：爬取的url
	private static String url = "https://www.tianyancha.com/search?key=" + name
			+ "&checkFrom=searchBox";
	// 部分五：cookie信息储存
	private Set<Cookie> cookies;
	// 部分六：搜索条件封装
	private static Search search = new Search();

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

	// 使用 selenium 来模拟用户的登录获取cookie信息
	public void login() {
		WebDriver driver = new ChromeDriver();
		System.out.println(driver.getCurrentUrl());
		driver.get("https://www.tianyancha.com/login");
		System.out.println(driver.getCurrentUrl());
		try {
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
		}
	}

	public static void main(String[] args) throws Exception {

		long startTime, endTime;
		System.out.println("开始爬取...");
		TianyanProcessor processor = new TianyanProcessor();
		processor.login();
		startTime = System.currentTimeMillis();
		Spider.create(processor).addUrl(url).run();
		endTime = System.currentTimeMillis();
		System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000)
				+ "秒，抓取了" + count + "条记录");

		search.setSearchname(name);
		search.setSearchcount(count);
		search.setSearchurl(url);
		search.setStartdate(new Date(startTime));
		search.setEnddate(new Date(endTime));

		System.exit(0);
	}

}
