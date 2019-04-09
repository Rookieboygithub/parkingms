package com.parkingms.service;

import java.util.List;

import com.parkingms.bean.AdminBean;

/**
 * 
 * 杨向东
 * @author Administrator
 *
 */
public interface ISuperAdminService {
	
	/**
	 * 增删查改-->对普通管理员
	 * 
	 */
	/**
	 * 增
	 * 
	 * */
	//超管注册管理权限
	public void checkUserManager(AdminBean  bean );	
	
	
	/**
	 * 删
	 * 
	 */
	//超管删除一个普通管理
	public int removeAdmin(int id);
	
	
	/**
	 * 查
	 * 
	 * */
	//超管查看一个用户管理权限
	public AdminBean examineAdmin(int id);
	
	//查找所有,limit--对不同场景下的参数的再理解
	//public List<AdminBean> examineAll(AdminBean  bean);
	public List<AdminBean> examineAll(int page);
	
	//查找总条数
	public int examineNum(AdminBean  bean);

	/**
	 * 改
	 * 
	 */
	public int alterAdmin(int id);
	
	
	
	//连表操作
	public void insertLoginAdmin(AdminBean  bean);
	
	
	
	/**
	 * 
	 * view
	 * 
	 */
	//查找所有
	public List<AdminBean> findAll(int page);
	//查找总页码
	public int findNum(AdminBean  bean);
	//查找数据库总条数
	public int findAllNum(AdminBean bean);
	//根据账号，查密码
	public String findPassword(String account);
	//对两张单表操作
	public AdminBean findAdminBean(String account);
	
	
	//软删除
	public void removeflag(int ids);
	
	//修改
	public int alterAdminLogin(AdminBean bean);
	


}
