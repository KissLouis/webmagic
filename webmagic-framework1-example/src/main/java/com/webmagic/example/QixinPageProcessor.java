package com.webmagic.example;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

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
			.addHeader("Accept-Language", "zh-CN,zh;q=0.9").setRetryTimes(3)
			.setTimeOut(3000).setSleepTime(1000);

	// 部分二：总共爬取的数量
	private static int count = 0;

	// 部分三：需要搜索的公司关键字
	private static String companyName = "广州皓云原智信息科技有限公司";

	// 部分四：爬取的url
	private static String url = "http://www.qixin.com/search?key="
			+ companyName + "&page=1";

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	@Override
	/**
	 * process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	 */
	public void process(Page page) {
		// TODO Auto-generated method stub
		// 判断链接是否符合格式http://www.cnblogs.com/任意个数字字母
		if (!page.getUrl().regex("/company/*").match()) {
			System.out.println("符合链接合适");
			// 加入满足条件的链接
			page.addTargetRequests(page.getHtml()
					.xpath("//*[@class=\"company-title\"]/a/@href").all());
		} else {
			// 获取页面需要的内容
			System.out
					.println("抓取的标题内容："
							+ page.getHtml()
									.xpath("/html/body/div[3]/div/div/div[2]/div/div[1]/h3/text()")
									.get());
			count++;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("开始爬取...");
		long startTime, endTime;
		QixinPageProcessor processor = new QixinPageProcessor();
		startTime = System.currentTimeMillis();
		Spider.create(processor).addUrl(url).thread(5).run();
		endTime = System.currentTimeMillis();
		System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000)
				+ "秒，抓取了" + count + "条记录");
	}

}
