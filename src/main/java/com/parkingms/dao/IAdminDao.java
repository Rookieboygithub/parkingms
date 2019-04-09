package com.parkingms.dao;


/**
 * 
 * @author 杨向东
 * 
 */
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;



import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.parkingms.bean.AdminBean;

public interface IAdminDao {
	/**
	 * 
	 * 对t_admin表的单表操作
	 * 
	 * 增--删--改--查-->顺序
	 * 
	 * */
	//注册一个普通管理员
	@Insert("insert into t_admin (admin_name,admin_tel, admin_authority_user,admin_authority_car,admin_authority_pact,admin_authority_complain,admin_login_id) values (#{name},#{tel},#{authorityUser},#{authorityCar},#{authorityPact},#{authorityComplain},#{loginid})   ")
	public void insertUserManger(AdminBean  bean );
	
	/**
	 * 删除
	 * 
	 * */
	@Delete("delete from t_admin where admin_id = #{id}")
	public int deleteAdmin(int id);
		
	/**
	 * 修改
	 * 
	 */

	
	/**
	 * 查找
	 * 
	 * */	
	@Select("select admin_id as id,admin_name as name,admin_tel as tel,admin_authority_user as authorityUser,admin_authority_car as authorityCar,admin_authority_pact as authorityPact,admin_authority_complain as authorityComplain,admin_login_id as loginid "
			+ "from t_admin WHERE  "
			+ " admin_id = #{id} ")
	public AdminBean selectUserManager(int id);
		
	//查找所有--limit
	@Select("SELECT admin_id as id,admin_name as name,admin_tel as tel,admin_authority_user as authorityUser,admin_authority_car as authorityCar,admin_authority_pact as authorityPact,admin_authority_complain as authorityComplain,admin_login_id as loginid,flag  "
			+ " FROM t_admin WHERE flag=0 limit #{index},3")
	public List<AdminBean> selectAll(int index);
	
	//查找总条数
	@Select("SELECT COUNT(admin_id) num FROM t_admin WHERE flag=0")
	public int selectCount(AdminBean  bean);
	
	
	
	

	/**
	 * 
	 * 对t_login表的操作
	 * 
	 * 
	 * 添加操作
	 */
		@Insert(value = {
				"INSERT INTO t_login (login_account,login_pwd,login_character) VALUES(#{account},#{pwd},#{character})" })
		/* keyProperty:对应实体Bean中的属性，表中对应的列名 
		 * keyProperty = "login_id"-----login表，实体bean里面的属性/---也可以写admin表的外键，这样可以直接将login表的主键值，直接赋给admin表的
		 * keyColumn = "login_id"-----login表，，数据库真实列名
		 * 
		 * */
		@Options(useGeneratedKeys = true, keyProperty = "loginid", keyColumn = "login_id")
		public void insertLogin(AdminBean bean);
		//public boolean insertLogin(LoginBean bean);


	/**
	 * 
	 * 对view_admin_login表操作(login和admin表的视图)
	 * 
	 * 增--删--改--查-->顺序
	 * 
	 */
	//查
//	@Select("SELECT admin_id as id,admin_name as 'name',admin_tel as tel,admin_authority_user as authorityUser,admin_authority_car as authorityCar,admin_authority_pact as authorityPact,admin_authority_complain as authorityComplain,admin_login_id as loginid,flag,  "
//			+ " login_id ,login_account as account,login_pwd as pwd,login_character as 'character' "
//			+ " FROM view_admin_login")	
//	public List<AdminBean> selectAllView(AdminBean bean);
		
	//查找所有，根据limit
	@Select("SELECT admin_id as id,admin_name as 'name',admin_tel as tel,admin_authority_user as authorityUser,admin_authority_car as authorityCar,admin_authority_pact as authorityPact,admin_authority_complain as authorityComplain,admin_login_id as loginid,flag,  "
			+ " login_id ,login_account as account,login_pwd as pwd,login_character as 'character' "
			+ " FROM view_admin_login WHERE flag=0 limit #{index}, 2")		
	public List<AdminBean> selectAllView(int index);
	
	//查找总页数
	@Select("SELECT COUNT(admin_id) num FROM view_admin_login WHERE flag=0")
	public int selectCountView(AdminBean  bean);
	
	//查找账号，校验是否为空
	@Select("SELECT login_pwd FROM view_admin_login WHERE login_account=#{account} AND flag=0")
	public String selectPassword(String account);
	
	//根据工号找loginid,然后查admin信息
	@Select("SELECT login_id as loginid FROM t_login WHERE login_account=#{account} AND flag=0")
	public int selectLoginid(String account);
	@Select("SELECT admin_id as id,admin_name as 'name',admin_tel as tel,admin_authority_user as authorityUser,admin_authority_car as authorityCar,admin_authority_pact as authorityPact,admin_authority_complain as authorityComplain,  "
			+ " login_character as 'character' "
			+ " FROM view_admin_login WHERE  flag=0 and admin_login_id=#{loginid}")
	public AdminBean selectOneRowData(int loginid);
	
	
	/**
	 * 增添
	 * 
	 */
	//login表增加---分别调用两个sql
	//admin表增加
	/**
	 * 删除
	 * 
	 */
	//因为有外键联系，先要删除外键列，才能删除主键列那一方
	//用软删除---flag直接改为1
	//两条修改sql语句
	@Update("update t_admin set "
			+ " flag = 1 "
			+ " where  admin_login_id = #{ids}")
	public int updateAdminflag(int ids);
	
	@Update("update t_login set "
			+ " flag = 1 "
			+ " where  login_id = #{ids}")
	public int updateLoginflag(int ids);
	
	/**
	 * 
	 * 修改
	 */

	//修改admin表已有，直接引用

/*	@Update("update t_admin set "
			+ " admin_name = #{name},admin_authority_user = #{authorityUser}, admin_authority_car = #{authorityCar},admin_authority_pact = #{authorityPact},admin_authority_complain = #{authorityComplain} "
			+ "where  admin_login_id = #{loginid}")
	public int updateAdmin(AdminBean bean);*/
	@Update("update t_admin set "
			+ " admin_authority_user = #{authorityUser}, admin_authority_car = #{authorityCar},admin_authority_pact = #{authorityPact},admin_authority_complain = #{authorityComplain} "
			+ "where  admin_login_id = #{loginid}")
	public int updateAdmin(AdminBean bean);
	//修改login表
	@Update("update t_login set "
			+ "login_account = #{account}, login_pwd = #{pwd},login_character = #{character} "
			+ "where  login_id = #{loginid}")
	public int updateLogin(AdminBean bean);
	

	
	
	
	
	
	/**
	 * 废弃代码
	 * 
	 * */
	
	/**
	 * 修改
	 * 
	 * id
	 * name
	 * tel
	 * 
	 * admin_authority_user = #{authorityUser}  
	 * admin_authority_car = #{authorityCar} 
	 * admin_authority_pact = #{authorityPact}    
	 * admin_authority_complain = #{authorityComplain} 
	 * 
	 */
	
	/*	
	//注册一个车位管理
	@Insert("insert into t_admin (admin_name,admin_tel, admin_authority_car,admin_login_id) values (#{name},#{tel},#{authorityCar},#{loginid}) ")
	public AdminBean insertCarManager(AdminBean  bean );
	
	//注册一个合同管理
	@Insert("insert into t_admin (admin_name,admin_tel, admin_authority_pact,admin_login_id) values (#{name},#{tel},#{authorityPact},#{loginid}) ")
	public AdminBean insertPactManager(AdminBean  bean );
	
	//注册一个投诉管理
	@Insert("insert into t_admin (admin_name,admin_tel, admin_authority_complain,admin_login_id) values (#{name},#{tel},#{authorityComplain},#{loginid}) ")
	public AdminBean insertComplainManager(AdminBean  bean );*/
	
	
	/*	
	//查
	@Select("select admin_id as id,admin_name as name,admin_tel as tel,admin_authority_user as authorityUser,admin_authority_car as authorityCar,admin_authority_pact as authorityPact,admin_authority_complain as authorityComplain,admin_login_id as loginid "
	        + "from t_admin WHERE  "
	        + " admin_id = #{id} and admin_name = #{name} and admin_tel = #{tel} and admin_authority_user = #{authorityUser} and admin_authority_car = #{authorityCar} and  admin_authority_pact = #{authorityPact} and   admin_authority_complain = #{authorityComplain} and admin_login_id = #{loginid} ")
    public AdminBean selectUserManager(AdminBean  bean );*/
	

}
