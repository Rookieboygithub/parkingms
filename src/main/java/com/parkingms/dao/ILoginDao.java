package com.parkingms.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.parkingms.bean.LoginBean;
/**
 * 
 *登录的dao接口 
 * @author 杨吉竹
 *
 */
public interface ILoginDao {
	@Select("select * from t_login  where login_account=#{account} And login_pwd=#{pwd}")
   @Results({
		   @Result(property="loginAccount",column="login_account"),
		   @Result(property="loginId",column="login_id"),
		   @Result(property="loginCharacter",column="login_character")
   })
	public LoginBean getBeanByAccount(@Param("account")String account,@Param("pwd")String pwd);
   @Select("select * from t_user where user_login_id =#{id}")
   @Results({
	   @Result(property="userId",column="user_id"),
	   @Result(property="alias",column="user_alias"),
   })
	public LoginBean getUserMes(Integer id);
}
