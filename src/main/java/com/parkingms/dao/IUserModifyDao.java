package com.parkingms.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.parkingms.bean.UserBean;
import com.parkingms.bean.UserMessageBean;
/**
 * 
 * sql语句修改个人登录密码和用户信息
 * @author 杨吉竹
 *
 */
public interface IUserModifyDao {
	//验证密码是否正确
	@Select("select login_pwd from t_login where login_account =#{account} and flag = 0")
	public String selectPwd(UserBean mes);
	//sql语句修改账户密码
	@Update("update t_login set login_pwd = #{pwd} where login_account =#{account} and flag = 0")
public int  modifyPwd(UserBean mes);
	//sql语句修改个人用户信息,地址，电话，职业描述
	@Update("update t_user set user_addr =#{addr},"
			+ "user_tel=#{tel},"+"user_career=#{career}, "+"user_email=#{email}"
			+"where user_login_id=(select login_id from t_login where login_account=#{account}) and flag =0")
public void  modifyMessage(UserBean mes);
	@Select("select * from t_user where user_login_id= (select login_id from t_login where login_account =#{account}) and flag = 0")
	@Results({
	       //查询关联对象
	       @Result(property = "name",column = "user_name"),
	       @Result(property = "cardNo",column = "user_cardno"),
	       @Result(property = "credit",column = "user_credit"),
	       @Result(property = "addr",column = "user_addr"),
	       @Result(property = "tel",column = "user_tel"),
	       @Result(property = "email",column = "user_email"),
	       @Result(property = "career",column = "user_career"),
	       
	})
	public UserBean findUser(String account);
}
