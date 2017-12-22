package com.webmagic.entity;

import java.util.Date;

public class Company {

	private String unifiedSocialCreditCode; // 统一社会信用代码
	private String companyName; // 公司名称
	private String companyTel; // 电话
	private String companyWebSite; // 网站
	private String companyAddress; // 地址
	private String companyProfile; // 公司简介
	private Integer companySort; // 搜索排序
	private Date createDate; // 创建时间
	private String searchName; // 搜索条件

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

	public Integer getCompanySort() {
		return companySort;
	}

	public void setCompanySort(Integer companySort) {
		this.companySort = companySort;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public Company() {
		super();
	}

	public Company(String searchName) {
		super();
		this.searchName = searchName;
	}

}
