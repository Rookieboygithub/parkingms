package com.parkingms.service;

import java.util.List;

import com.parkingms.bean.CbdcarBean;
import com.parkingms.bean.CompanyBean;
import com.parkingms.bean.CompanytreatyBean;

public interface ICompanyFrontService {
	/**
	 * 查询对用id，对应的企业合同
	 * @param id：暂未使用
	 * @return
	 */
	public CompanytreatyBean selectCompanyTreatyById(Integer id);

	/**
	 * 查询企业用户合同
	 * @param bean企业信息
	 * @param page当前页面页码
	 * @param limit当前每页显示条数
	 * @param treaty合同信息
	 * @return
	 */
	public List<CompanytreatyBean> selectCompanytreaty(CompanyBean bean, Integer page, Integer limit,
			CompanytreatyBean treaty);

	/**
	 * 查询企业合同数量
	 * @param bean，不能为null
	 * @return
	 */
	public int selectCompanytreatyNumber(CompanyBean bean);

	/**
	 * 查询企业车位
	 * @param cbdcar 
	 * @param bean企业
	 * @param page页码，大于1
	 * @param limit每页显示条数，默认5，取值，5,10,15
	 * @param id：合同id，可以为空
	 * @param cbdcar：车位信息，可以为空
	 * @return
	 */
	public List<CbdcarBean> selectCompanyCbdcar(CompanyBean bean, Integer page, Integer limit, Integer id,
			CbdcarBean cbdcar);

	/**
	 * 查询企业车位的数量
	 * @param bean不能为null
	 * @param id：合同的id，可以为空
	 * @return
	 */
	public int selectCompanyCbdcarNumber(CompanyBean bean, Integer id);

	/**
	 * 查询企业用户的id
	 * @param id企业用户登录id，不能为空
	 * @return
	 */
	public Integer selectCompanyBeanID(Integer id);
}
