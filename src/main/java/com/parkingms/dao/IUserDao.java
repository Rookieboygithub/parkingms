package com.parkingms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.parkingms.bean.UserBean;

/**
 * 持久层：操作用户表
 * @author 张怡
 *
 */
public interface IUserDao {
	/**
	 * 查询所有的包租婆信息
	 * @return
	 */
//	public List<UserBean> getListOfLandlord();
	
	/**
	 * 分页查询包租婆信息(带条件搜索)
	 * @param curr
	 * @param limit
	 * @return
	 */
	public List<UserBean> getListLandlords(@Param(value="begin")int begin, @Param(value="limit")int limit,@Param(value="bean")UserBean bean);
	
	/**
	 * 统计包租婆数量(带条件搜索)
	 * @return
	 */
	public int countLandlords(UserBean bean);
	
	/**
	 * 根据用户名查找包租婆
	 * @param username
	 * @return
	 */
	public UserBean getByUsername(String username);
	/**
	 * 根据用户名查该用户的信息
	 */
	public UserBean getUserByAccount(String account);
	
	/**
	 * 根据账号和密码查找用户(临时测试用)
	 * @param bean
	 * @return
	 */
	public UserBean getByLogin(UserBean bean);
	/**
	 * 判断该手机用户存在不
	 * @param tel
	 * @return
	 */
	public String getByTel(String tel);
	
	/**
	 * 根据登录Id获取用户id
	 * @param loginId
	 * @return
	 */
	public int getByLoginId(int loginId);
}