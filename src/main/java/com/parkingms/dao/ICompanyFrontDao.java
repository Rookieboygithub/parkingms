package com.parkingms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.parkingms.bean.CbdcarBean;
import com.parkingms.bean.CompanyBean;
import com.parkingms.bean.CompanytreatyBean;

@Repository
public interface ICompanyFrontDao {
	/**
	 * 查询合同信息
	 * @param id：合同id
	 * @return
	 */
	public CompanytreatyBean selectCompanyTreatyById(@Param(value = "id") Integer id);

	/**
	 * 查询企业合同
	 * @param bean 企业的bean
	 * @param page 当前页码
	 * @param limit 当前页面显示条数
	 * @param treaty 合同信息
	 * @return
	 */
	public List<CompanytreatyBean> selectCompanytreaty(@Param(value = "bean") CompanyBean bean,
			@Param(value = "page") Integer page, @Param(value = "limit") Integer limit,
			@Param(value = "treaty") CompanytreatyBean treaty);

	/**
	 * 查询企业合同数量
	 * @param bean企业bean
	 * @return
	 */
	public int selectCompanytreatyNumber(@Param(value = "bean") CompanyBean bean);

	/**
	 * 查询企业车位
	 * @param bean 企业bean
	 * @param page 当前页码
	 * @param limit 当前每页显示条数
	 * @param id 合同id，可以为空
	 * @param cbdcar 车位信息
	 * @return
	 */
	public List<CbdcarBean> selectCompanyCbdcar(@Param(value = "bean") CompanyBean bean,
			@Param(value = "page") Integer page, @Param(value = "limit") Integer limit,
			@Param(value = "id") Integer id, @Param(value = "cbdcar") CbdcarBean cbdcar);

	/**
	 * 查询企业车位数量
	 * @param bean
	 * @param id
	 * @return
	 */
	public int selectCompanyCbdcarNumber(@Param(value = "bean") CompanyBean bean, @Param(value = "id") Integer id);

	/**
	 * 查询企业
	 * @param id登录id
	 * @return企业的id
	 */
	@Select(value = { "SELECT company_id FROM t_company WHERE company_login_id = #{id}" })
	public Integer selectCompanyBeanID(@Param(value = "id") Integer id);

}
