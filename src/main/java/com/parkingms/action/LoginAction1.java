package com.parkingms.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parkingms.aop.ParkingmsLog;
import com.parkingms.bean.UserBean;
import com.parkingms.service.IUserService;

/**
 * 处理用户登陆请求
 * 
 * @author zyi1313
 *
 */
@Controller
public class LoginAction1 {
	/**
	 * 自动注入IUserService接口
	 */
	@Autowired
	private IUserService service;

	/**
	 * 验证用户进行登陆操作
	 */
	@ParkingmsLog(module = "个人业务", method = "登陆", plantform = 0, type = 0)
	@RequestMapping(value = "/checkUserLogin.action")
	public String checkUserLogin(UserBean bean, HttpSession session) {
		UserBean user = service.checkLogin(bean);
		if (user != null) {
			session.setAttribute("username", user.getAccount());
			session.setAttribute("userId", user.getId());
			session.setAttribute("character", user.getCharacter());
			System.out.println("成功");
			return "redirect:/jsp/index2.jsp";

		} else {
			System.out.println("失败");
			return "/jsp/login1.jsp";
		}
	}
}
