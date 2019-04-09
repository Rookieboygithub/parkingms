package com.parkingms.dao;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.parkingms.bean.AdminBean;
/**
 * 
 * 查询管理员信息和更新管理员电话
 * @author 杨吉竹
 *
 */

public interface IAdminModifyDao {
//查找普通管理员个人信息
	@Select("select * from t_admin where admin_login_id=(select login_id from t_login where login_account=#{account}) AND  flag = 0")
	@Results({
	@Result(property="tel",column="admin_tel"),
	@Result(property="name",column="admin_name"),
	@Result(property="authorityCar",column="admin_authority_car"),
	@Result(property="authorityPact",column="admin_authority_pact"),
	@Result(property="authorityUser",column="admin_authority_user"),
	@Result(property="authorityComplain",column="admin_authority_complain")
	})
	public AdminBean selectAdmin(String account);
	//修改admin的电话号码
	@Update("update t_admin set admin_tel = #{tel} where admin_login_id =(select login_id from t_login where login_account=#{account}) AND flag = 0 ")
	public Integer updateTel(AdminBean admin);
	
}
