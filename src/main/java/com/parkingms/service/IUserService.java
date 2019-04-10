package com.parkingms.service;

import java.util.Map;

import com.parkingms.bean.UserBean;
/**
 * 业务层接口：处理与用户信息表有关的业务逻辑
 * @author 张怡
 *
 */
public interface IUserService {
	/**
	 * 查询所有包租婆
	 * @return
	 */
//	public List<UserBean> findAllLandlord();
	
	/**
	 * 分页查询包租婆（带条件搜索）
	 * @param curr
	 * @param limit
	 * @return
	 */
	public Map<String, Object> findLandlordsByPage(int curr, int limit,UserBean bean);
	
	/**
	 * 统计包租婆数量（带条件搜索）
	 * @return
	 */
	public int countLandlords(UserBean bean);
	
	/**
	 * 第一次访问页面获取第一页包租婆信息及包租婆总数
	 * @return
	 */
//	public Map<String, Object> findLandlordsFirstly();
	
	/**
	 * 根据用户名查找包租婆
	 * @param username
	 * @return
	 */
//	public Map<String, Object> findLandlordByUsername(String username);
	
	/**
	 * 检查用户登陆（临时测试用）
	 * @param bean
	 * @return
	 */
	public UserBean checkLogin(UserBean bean);
	
	/**
	 * 根据登录id获取用户id
	 * @param user
	 */
	public int findUserByLoginId(int loginId);
	
}
