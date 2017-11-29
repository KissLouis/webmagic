package com.webmagic.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Search {
	private Integer searchid;

	private String searchname;

	private Integer searchcount;

	private String searchurl;

	private Date startdate;

	private Date enddate;

	private List<Company> listCompany = new ArrayList<Company>();

	public Integer getSearchid() {
		return searchid;
	}

	public void setSearchid(Integer searchid) {
		this.searchid = searchid;
	}

	public String getSearchname() {
		return searchname;
	}

	public void setSearchname(String searchname) {
		this.searchname = searchname == null ? null : searchname.trim();
	}

	public Integer getSearchcount() {
		return searchcount;
	}

	public void setSearchcount(Integer searchcount) {
		this.searchcount = searchcount;
	}

	public String getSearchurl() {
		return searchurl;
	}

	public void setSearchurl(String searchurl) {
		this.searchurl = searchurl;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public List<Company> getListCompany() {
		return listCompany;
	}

	public void setListCompany(List<Company> listCompany) {
		this.listCompany = listCompany;
	}

}