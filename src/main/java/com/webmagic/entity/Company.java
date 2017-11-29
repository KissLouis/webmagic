package com.webmagic.entity;

import java.util.Date;

public class Company {
	private String companyid;

	private String companyname;

	private String companytel;

	private String companyemail;

	private String companywebsite;

	private String companyaddress;

	private Integer companysort;

	private Date createdate;

	private String companysynopsis;

	private Search search;

	private CompanyInformation companyInformation;

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getCompanytel() {
		return companytel;
	}

	public void setCompanytel(String companytel) {
		this.companytel = companytel;
	}

	public String getCompanyemail() {
		return companyemail;
	}

	public void setCompanyemail(String companyemail) {
		this.companyemail = companyemail;
	}

	public String getCompanywebsite() {
		return companywebsite;
	}

	public void setCompanywebsite(String companywebsite) {
		this.companywebsite = companywebsite;
	}

	public String getCompanyaddress() {
		return companyaddress;
	}

	public void setCompanyaddress(String companyaddress) {
		this.companyaddress = companyaddress;
	}

	public Integer getCompanysort() {
		return companysort;
	}

	public void setCompanysort(Integer companysort) {
		this.companysort = companysort;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getCompanysynopsis() {
		return companysynopsis;
	}

	public void setCompanysynopsis(String companysynopsis) {
		this.companysynopsis = companysynopsis;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public CompanyInformation getCompanyInformation() {
		return companyInformation;
	}

	public void setCompanyInformation(CompanyInformation companyInformation) {
		this.companyInformation = companyInformation;
	}

}