package com.parkingms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.parkingms.bean.AdminBean;

/**
 * 超级管理员修改dao接口
 * @author 杨吉竹
 *
 */
public interface ISuperAdminModifyDao {
	/**
	 * 修改超级管理员的密码
	 * @param bean
	 */
/*	@Update("update t_login set login_pwd =#{pwd} where login_account =#{account}" )
public void updatePwd(SuperAdminMessageBean bean );*/
	/**
	 * 更新普通管理员的操作权限
	 * @param admin
	 */
	@Update("update t_admin set admin_authority_user =#{authorityUser},"
			+ "admin_authority_car =#{authorityCar},"
			+ "admin_authority_pact =#{authorityPact},"
			+ "admin_authority_complain =#{authorityComplain} "
			+ " where admin_id=#{id} AND flag = 0")
	public void updateAdmin(AdminBean admin);
	/**
	 *模糊查询，搜索功能
	 * @return 所有的管理员信息
	 */
	/*@Select("<script> \n"+"select * from "
			+ "(SELECT a.admin_authority_car,a.admin_authority_complain,a.admin_authority_pact,a.admin_authority_user,a.admin_name,a.admin_tel,b.login_account,a.admin_id "
			+ "FROM  t_admin as a INNER JOIN t_login as b WHERE a.admin_login_id = b.login_id AND b.flag = 0 AND a.flag=0 ) AS ms <if test='name != null' || test='name != '''> WHERE ms.admin_name like CONCAT(CONCAT('%', #{name}),'%') "
			+ "OR ms.login_account like CONCAT(CONCAT('%', #{name}),'%')"
			+" order by admin_id limit  #{uppage},#{downpage} </if> </script>")
	@Results({
	       //查询关联对象
	       @Result(property = "name",column = "admin_name"),
	       @Result(property = "tel",column = "admin_tel"),
	       @Result(property = "id",column = "admin_id"),
	       @Result(property = "account",column = "login_account"),
	       @Result(property = "authorityUser",column = "admin_authority_user"),
	       @Result(property = "authorityCar",column = "admin_authority_car"),
	       @Result(property = "authorityPact",column = "admin_authority_pact"),
	       @Result(property = "authorityComplain",column = "admin_authority_complain"),
	})*/
	public List<AdminBean> selectAccount(@Param("name")String name,@Param("uppage")Integer uppage,@Param("downpage")Integer down);
	/*@Select("<script> \n"+"select count(*) from "
			+ "(SELECT a.admin_name,b.login_account,a.admin_id "
			+ "FROM  t_admin as a INNER JOIN t_login as b WHERE a.admin_login_id = b.login_id AND b.flag = 0 AND a.flag = 0) AS ms <if test='name != null' ||test= 'name != '''> WHERE ms.admin_name like CONCAT(CONCAT('%', #{name}),'%') "
			+ "OR ms.login_account like CONCAT(CONCAT('%', #{name}),'%')"
			+"  </if></script>")*/
	public int searchcount(@Param("name")String name);
	@Select("select * from t_admin INNER JOIN t_login WHERE admin_login_id =login_id AND t_login.flag = 0 order by admin_id limit  #{uppage},#{downpage}")
	@Results({
	       //查询关联对象
	       @Result(property = "name",column = "admin_name"),
	       @Result(property = "id",column = "admin_id"),
	       @Result(property = "tel",column = "admin_tel"),
	       @Result(property = "account",column = "login_account"),
	       @Result(property = "authorityUser",column = "admin_authority_user"),
	       @Result(property = "authorityCar",column = "admin_authority_car"),
	       @Result(property = "authorityPact",column = "admin_authority_pact"),
	       @Result(property = "authorityComplain",column = "admin_authority_complain"),
	})
	public List<AdminBean> selectAll(@Param("uppage") Integer up,@Param("downpage") Integer down);
	@Select("select * from "
			+ "(SELECT a.admin_authority_car,a.admin_authority_complain,a.admin_authority_pact,a.admin_authority_user,a.admin_name,a.admin_tel,b.login_account,a.admin_id "
			+ "FROM  t_admin as a INNER JOIN t_login as b WHERE a.admin_login_id = b.login_id AND b.flag = 0 ) AS ms  WHERE ms.admin_id=#{id} ")
	@Results({
	       //查询关联对象
	       @Result(property = "name",column = "admin_name"),
	       @Result(property = "id",column = "admin_id"),
	       @Result(property = "tel",column = "admin_tel"),
	       @Result(property = "account",column = "login_account"),
	       @Result(property = "authorityUser",column = "admin_authority_user"),
	       @Result(property = "authorityCar",column = "admin_authority_car"),
	       @Result(property = "authorityPact",column = "admin_authority_pact"),
	       @Result(property = "authorityComplain",column = "admin_authority_complain"),
	})
	public AdminBean find(Integer id);
	@Select("select count(*) from t_admin where flag =0")
	public int getcount();
	
	
}
