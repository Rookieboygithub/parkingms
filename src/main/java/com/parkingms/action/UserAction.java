package com.parkingms.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.parkingms.bean.UserBean;
import com.parkingms.service.IUserService;
/**
 * 处理用户表有关的Action请求
 * @author 张怡
 *
 */
//定义类为处理器
@Controller
public class UserAction {
	/**
	 * 自动注入IUserService接口
	 */
	@Autowired
	private IUserService service;
	
	/**
	 * 显示所有包租婆信息
	 * @return
	 */
//	@RequestMapping(value="/showAllLandlords.action")
//	@ResponseBody
//	public List<UserBean> showAllLandlords(){
//		List<UserBean> list = service.findAllLandlord();
//		return list;
//	}
	
	/**
	 * 第一次请求进入页面，获取第一页的包租婆列表及总包租婆数量（带条件搜索）
	 * @return
	 */
	@RequestMapping(value="/showFirstLandlords.action")
	/*@ResponseBody
	public Map<String, Object> showFirstLandlords(){
		Map<String, Object> map = service.findLandlordsFirstly();
		return map;
	}*/
	public ModelAndView showFirstLandlords(UserBean bean){
		//获取第一页的包租婆信息，显示条数为10条
		Map<String, Object> map = service.findLandlordsByPage(1, 10,bean);
		ModelAndView model = new ModelAndView();
		//将包租婆信息集合及包租婆总数存入model
		model.addAllObjects(map);
		//设置跳转视图
		model.setViewName("/jsp/landlord-list.jsp");
		return model;
	}
	
	/**
	 * 分页显示包租婆列表（带条件搜索）
	 * @return
	 */
	@RequestMapping(value="/showLandlordsByPage.action")
	@ResponseBody
	public Map<String, Object> showLandlordsByPage(@RequestParam(value="curr") int curr,@RequestParam(value="limit")int limit,UserBean bean){
		// 根据请求的页数curr及每页显示的条数limit，返回分页的包租婆集合
		Map<String, Object> map = service.findLandlordsByPage(curr,limit,bean);
		return map;
	}
	
	/**
	 * 根据用户名查找包租婆
	 * @param alias
	 * @return
	 */
//	@RequestMapping(value="/searchLandlord.action")
//	@ResponseBody
//	public Map<String, Object> searchLandlord(String username){
//		Map<String, Object> map = service.findLandlordByUsername(username);
//		return map;
//	}
	
	/**
	 * 根据登录id获取用户id
	 * @param user
	 */
	@RequestMapping(value="/getUserIdByLoginId.action")
	@ResponseBody
	public int getUserIdByLoginId(int loginId,HttpSession session){
		int userId = 0;
		userId = service.findUserByLoginId(loginId);
		if(userId != 0){
			session.setAttribute("userId", userId);
		}
		return userId;
	}
}
