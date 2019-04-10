package com.parkingms.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.parkingms.bean.JoinBean;
/**
 * 注册的dao接口,
 * 同时向两张表插入数据t_login和t_user
 * @author 杨吉竹
 *
 */
public interface IRegisterDao {
	/**
	 * 注册时，向登录表插入的语句
	 * @param loginInfo
	 * @return
	 */
	@Insert("insert into t_login(login_account,login_pwd,login_character,flag) values(#{account},#{pwd},#{character},0)")
	@SelectKey(statement="SELECT LAST_INSERT_ID() AS id", before=false, resultType=int.class, keyProperty = "id")   
	public int  InsertLoginInfo(JoinBean loginInfo);
	/**
	 * 注册时，向个人信息表插入的数据
	 * @param User
	 * @return
	 */
	@Insert("Insert into t_user(user_name,user_alias,user_addr,user_tel,user_cardno,user_career,user_email,user_credit,user_login_id,user_role,flag) "
			+ "values(#{name},#{account},#{address},#{phone},#{cardno},#{career},#{email},0,#{id},#{character},0)")
    public int InsertRegisterInfo(JoinBean User);
	/**
	 * 删除登录表的数据
	 * @param loginAccount
	 * @return
	 */
	@Update("delete from t_login  where login_account=#{loginAccount}")
    public int DeleteLoginInfo(String loginAccount);
	//判断用户名是否重复
	@Select("select login_id from t_login where login_account=#{account}")
	public  Integer judgeRepeat(String account);
}
