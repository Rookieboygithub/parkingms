package com.parkingms.service;

import java.util.List;

import com.parkingms.bean.CompanyBean;

/**
 * 企业用户业务层接口
 * 1：增加企业
 * 2：查询企业
 * 3：查询企业数量
 * 4：删除企业
 * @author BHH
 *
 */

public interface ICompanyService {
	/**
	 * 增加企业用户
	 * @param map
	 * @return
	 */
	public String insertCompanyBean(CompanyBean bean);

	/**
	 * 删除企业用户
	 * @param bean，不能为null
	 * @return
	 */
	public boolean deleteCompanyBean(CompanyBean bean);

	/**
	 * 查询企业用户，如果全为空，默认查询全部
	 * @param bean,查询的bean，可以为空
	 * @param page当前页码
	 * @param limit当前页面显示的条数
	 * @return
	 */
	public List<CompanyBean> selectCompanyBean(CompanyBean bean, Integer page, Integer limit);

	/**
	 * 查询企业用户数量
	 * @param bean，不能为null
	 * @return
	 */
	public int selectCompanyNumber(CompanyBean bean);
	/**
	 * 查询企业用户名，返回企业id
	 * @param loginName
	 * @return
	 */
	public String selectCompanyLoginName(String loginName);
}
