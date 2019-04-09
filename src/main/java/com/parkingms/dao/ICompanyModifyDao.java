package com.parkingms.dao;

import org.apache.ibatis.annotations.Update;

import com.parkingms.bean.CompanyBean;
/**
 * 修改企业用户信息持久层接口
 * @author 杨吉竹
 *
 */
public interface ICompanyModifyDao {
	//修改公司信息
	@Update("update t_company set company_contact =#{contact},"
			+ "company_tel =#{tel} where company_login_id=(select login_id from t_login where login_account =#{account}) AND flag = 0")
public int updateCompany(CompanyBean company);
}
