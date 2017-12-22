package com.webmagic.dao;

import org.apache.ibatis.annotations.Param;

import com.webmagic.entity.Company;

public interface ICompanyDao {

	/**
	 * 
	 * Description: 公司信息新增
	 * 
	 * @param company
	 * @return
	 * @Note
	 * @Author: Louis
	 * @Date: 2017年12月21日 上午10:48:01
	 */
	public int addCompany(@Param("company") Company company);

}
