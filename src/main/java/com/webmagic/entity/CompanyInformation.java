package com.webmagic.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CompanyInformation {
	private Integer id;

	private String legalperson;

	private String legalpersoninformation;

	private BigDecimal registeredcapital;

	private Date registrationtime;

	private String enterprisestate;

	private String businessregistrationnumber;

	private String organizationcode;

	private String uniformcreditcode;

	private String enterprisetype;

	private String taxpayeridentification;

	private String industry;

	private String businessterm;

	private String approvaldate;

	private String registrationauthority;

	private String englishname;

	private String registeredaddress;

	private String businessscope;

	private String ownershipstructure;

	private Company company;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLegalperson() {
		return legalperson;
	}

	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson == null ? null : legalperson.trim();
	}

	public String getLegalpersoninformation() {
		return legalpersoninformation;
	}

	public void setLegalpersoninformation(String legalpersoninformation) {
		this.legalpersoninformation = legalpersoninformation == null ? null
				: legalpersoninformation.trim();
	}

	public BigDecimal getRegisteredcapital() {
		return registeredcapital;
	}

	public void setRegisteredcapital(BigDecimal registeredcapital) {
		this.registeredcapital = registeredcapital;
	}

	public Date getRegistrationtime() {
		return registrationtime;
	}

	public void setRegistrationtime(Date registrationtime) {
		this.registrationtime = registrationtime;
	}

	public String getEnterprisestate() {
		return enterprisestate;
	}

	public void setEnterprisestate(String enterprisestate) {
		this.enterprisestate = enterprisestate == null ? null : enterprisestate
				.trim();
	}

	public String getBusinessregistrationnumber() {
		return businessregistrationnumber;
	}

	public void setBusinessregistrationnumber(String businessregistrationnumber) {
		this.businessregistrationnumber = businessregistrationnumber == null ? null
				: businessregistrationnumber.trim();
	}

	public String getOrganizationcode() {
		return organizationcode;
	}

	public void setOrganizationcode(String organizationcode) {
		this.organizationcode = organizationcode == null ? null
				: organizationcode.trim();
	}

	public String getUniformcreditcode() {
		return uniformcreditcode;
	}

	public void setUniformcreditcode(String uniformcreditcode) {
		this.uniformcreditcode = uniformcreditcode == null ? null
				: uniformcreditcode.trim();
	}

	public String getEnterprisetype() {
		return enterprisetype;
	}

	public void setEnterprisetype(String enterprisetype) {
		this.enterprisetype = enterprisetype == null ? null : enterprisetype
				.trim();
	}

	public String getTaxpayeridentification() {
		return taxpayeridentification;
	}

	public void setTaxpayeridentification(String taxpayeridentification) {
		this.taxpayeridentification = taxpayeridentification == null ? null
				: taxpayeridentification.trim();
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry == null ? null : industry.trim();
	}

	public String getBusinessterm() {
		return businessterm;
	}

	public void setBusinessterm(String businessterm) {
		this.businessterm = businessterm == null ? null : businessterm.trim();
	}

	public String getApprovaldate() {
		return approvaldate;
	}

	public void setApprovaldate(String approvaldate) {
		this.approvaldate = approvaldate == null ? null : approvaldate.trim();
	}

	public String getRegistrationauthority() {
		return registrationauthority;
	}

	public void setRegistrationauthority(String registrationauthority) {
		this.registrationauthority = registrationauthority == null ? null
				: registrationauthority.trim();
	}

	public String getEnglishname() {
		return englishname;
	}

	public void setEnglishname(String englishname) {
		this.englishname = englishname == null ? null : englishname.trim();
	}

	public String getRegisteredaddress() {
		return registeredaddress;
	}

	public void setRegisteredaddress(String registeredaddress) {
		this.registeredaddress = registeredaddress == null ? null
				: registeredaddress.trim();
	}

	public String getBusinessscope() {
		return businessscope;
	}

	public void setBusinessscope(String businessscope) {
		this.businessscope = businessscope == null ? null : businessscope
				.trim();
	}

	public String getOwnershipstructure() {
		return ownershipstructure;
	}

	public void setOwnershipstructure(String ownershipstructure) {
		this.ownershipstructure = ownershipstructure == null ? null
				: ownershipstructure.trim();
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}