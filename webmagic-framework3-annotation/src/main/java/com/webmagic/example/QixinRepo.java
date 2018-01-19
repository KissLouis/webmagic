package com.webmagic.example;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("http://www.qixin.com/company/*")
@HelpUrl("http://www.qixin.com/search?key=广州皓云原智信息科技有限公司")
@ExtractBy(value = "/html/body/div[5]/div/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/a/@href", multi = true)
public class QixinRepo {

	@ExtractBy("//*[@id='icinfo']/table/tbody/tr[1]/td[2]/text()")
	private String unifiedSocialCreditCode; // 统一社会信用代码

	@ExtractBy("/html/body/div[6]/div/div[2]/div/div[1]/h4/text()")
	private String companyName; // 公司名称

	@ExtractBy("/html/body/div[6]/div/div[2]/div/div[1]/div[1]/div[1]/div/div[2]/div/span/text()")
	private String companyTel; // 电话

	@ExtractBy("/html/body/div[6]/div/div[2]/div/div[1]/div[1]/div[2]/div/div[2]/a/@href")
	private String companyWebSite; // 网站

	@ExtractBy("/html/body/div[6]/div/div[2]/div/div[1]/div[1]/div[3]/div/div[2]/text()")
	private String companyAddress; // 地址

	@ExtractBy("/html/body/div[6]/div/div[2]/div/div[2]/div/text()")
	private String companyProfile; // 公司简介

	public String getUnifiedSocialCreditCode() {
		return unifiedSocialCreditCode;
	}

	public void setUnifiedSocialCreditCode(String unifiedSocialCreditCode) {
		this.unifiedSocialCreditCode = unifiedSocialCreditCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public String getCompanyWebSite() {
		return companyWebSite;
	}

	public void setCompanyWebSite(String companyWebSite) {
		this.companyWebSite = companyWebSite;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyProfile() {
		return companyProfile;
	}

	public void setCompanyProfile(String companyProfile) {
		this.companyProfile = companyProfile;
	}

	public static void main(String[] args) {
		OOSpider.create(Site.me().setSleepTime(1000),
				new ConsolePageModelPipeline(), QixinRepo.class)
				.addUrl("http://www.qixin.com/search?key=广州皓云原智信息科技有限公司&page=1")
				.thread(5).run();
	}

}
