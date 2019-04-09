package com.parkingms.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingms.aop.ParkingmsLog;
import com.parkingms.bean.UserBean;
import com.parkingms.service.IMessageCodeService;
import com.parkingms.service.IUserService;

@Controller
public class LoginAction {
	@Autowired
	private IMessageCodeService mes;
	@Autowired
	private IUserService service;

	@RequestMapping("login.action")
	@ParkingmsLog(module = "公共业务", method = "登陆", plantform = 0, type = 0)
	public @ResponseBody Map<String, Object> login(UserBean user, HttpSession session, String code) {
		String realcode = (String) session.getAttribute("messageCode");
		Map<String, Object> map = new HashMap<String, Object>();
		String result = "fail";
		if (code.equalsIgnoreCase(realcode)) {
			System.out.println("进入login认证");
			Subject currentUser = SecurityUtils.getSubject();
			if (!currentUser.isAuthenticated()) {
				System.out.println("--------------->进入login认证");
				// 把用户名和密码封装为 UsernamePasswordToken 对象
				UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPwd());
				// rememberme
				token.setRememberMe(true);
				try {
					// 执行登录.
					currentUser.login(token);
				}
				// 所有认证时异常的父类.
				catch (AuthenticationException ae) {
					// unexpected condition? error?
					result = "fail";
					map.put("result", result);
					return map;
				}
			}
			Object character = session.getAttribute("character");
			map.put("character", character);
			// 若是包租婆或者招租客成功登录，则存入userId
			if ((int) character == 2 || (int) character == 3) {
				int userId = service.findUserByLoginId((int) session.getAttribute("loginId"));
				session.setAttribute("userId", userId);
			}
			result = "success";
		} else {
			result = "failcode";
		}
		map.put("result", result);
		return map;
	}

	@RequestMapping("mlogin.action")
	public @ResponseBody String mlogin(HttpSession session, String code, String Tel) {
		String realcode = (String) session.getAttribute("messageCode");
		System.out.println(realcode);
		if (realcode.length() != 6) {

			return "请获取验证码";
		}
		if (realcode.equalsIgnoreCase(code)) {
			Subject currentUser = SecurityUtils.getSubject();
			if (!currentUser.isAuthenticated()) {
				// 把用户名和密码封装为 UsernamePasswordToken 对象
				// 添加一个 -表示短信登陆，密码固定即可。
				UsernamePasswordToken token = new UsernamePasswordToken(mes.findUserNamebyTel(Tel) + "*", "123");
				// rememberme
				token.setRememberMe(true);
				try {
					// 执行登录.
					currentUser.login(token);
				}
				// 所有认证时异常的父类.
				catch (AuthenticationException ae) {
					// unexpected condition? error?
					return "登陆失败";
				}
			}
			return "success";
		} else {
			return "验证码错误";
		}
	}
}