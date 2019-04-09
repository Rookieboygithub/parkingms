package com.parkingms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingms.aop.ParkingmsLog;
import com.parkingms.bean.UserBean;
import com.parkingms.dao.IUserDao;
import com.parkingms.service.IUserService;
/**
 * 业务层：处理与用户信息有关的业务逻辑
 * @author 张怡
 *
 */
//业务层
@Service
public class UserServiceImpl implements IUserService {
	/**
	 * 自动注入IUserDao接口
	 */
	@Autowired
	private IUserDao dao;
	/**
	 * 查询所有包租婆
	 * @return
	 */
//	@Override
//	public List<UserBean> findAllLandlord() {
//		List<UserBean> list = dao.getListOfLandlord();
//		return list;
//	}
	
	/**
	 * 分页查询包租婆（带条件搜索）
	 */
	@Override
	public Map<String, Object> findLandlordsByPage(int curr, int limit,UserBean bean) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<UserBean> list = dao.getListLandlords((curr-1)*limit,limit,bean);
		int total = dao.countLandlords(bean);
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 统计包租婆数量（带条件搜索）
	 * @return
	 */
	@Override
	public int countLandlords(UserBean bean) {
		return dao.countLandlords(bean);
	}
	
	/**
	 * 第一次访问页面获取第一页包租婆信息及包租婆总数
	 * @return
	 */
//	@Override
//	public Map<String, Object> findLandlordsFirstly() {
//		// 获取到第一页的包租婆集合
//		List<UserBean> list = dao.getListLandlords(1,5);
//		// 获取到包租婆总数
//		int total = dao.countLandlords();
//		Map<String, Object> map = new HashMap<String, Object>();
//		// 将包租婆集合存入map
//		map.put("list", list);
//		// 将包租婆总数存入map
//		map.put("total", total);
//		return map;
//	}
	
	/**
	 * 根据用户名查找包租婆信息
	 */
//	@Override
//	public Map<String, Object> findLandlordByUsername(String username) {
//		String result = "notfound";
//		UserBean bean = dao.getByUsername(username);
//		Map<String, Object> map = new HashMap<String, Object>();
//		if(bean != null){
//			result = "find";
//			map.put("data", bean);
//		} 
//		map.put("result", result);
//		return map;
//	}
	
	/**
	 * 检查用户登陆
	 * @param bean
	 * @return
	 */
	@Override
	public UserBean checkLogin(UserBean bean) {
		UserBean user = dao.getByLogin(bean);
		return user;
	}
	
	/**
	 * 根据登录Id获取用户Id
	 */
	@Override
	public int findUserByLoginId(int loginId) {
		return dao.getByLoginId(loginId);
	}
	
}
