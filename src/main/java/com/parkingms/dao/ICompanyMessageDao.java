package com.parkingms.dao;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.parkingms.bean.CompanyBean;
/**
 * 查询公司信息
 * @author 杨吉竹
 *
 */
public interface ICompanyMessageDao {
	//查询公司信息
	@Select("select * from t_company where company_login_id= (select login_id from t_login where login_account =#{account}) AND flag = 0")
	@Results({
	       //查询关联对象
	       @Result(property = "name",column = "company_name"),
	       @Result(property = "addr",column = "company_addr"),
	       @Result(property = "contact",column = "company_contact"),
	       @Result(property = "tel",column = "company_tel"),
	})
public CompanyBean companys(String account);
}
