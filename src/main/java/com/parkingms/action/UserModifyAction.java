package com.parkingms.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingms.bean.UserBean;
import com.parkingms.service.IUserModfiyService;
import com.parkingms.util.LoginEncoding5;

/**
 * 
 * 
 * 对个人信息进行修改，对密码、地址、电话、职业描述等进行修改
 * 
 * @author 杨吉竹
 *
 */
@Controller
public class UserModifyAction {
	@Autowired
	private IUserModfiyService service;
	
	@RequestMapping("verifyPwd.action")
	public @ResponseBody Map<String, String> verifyAction(UserBean user, HttpSession session) {
		// 通过session中存取账号值
		String account = (String) session.getAttribute("username");
		user.setAccount(account);

		// ModelAndView model=new ModelAndView();
		Map<String, String> map = new HashMap<String, String>();
		// 验证原始密码是否正确
		if (!service.verifyPwd(user)) {
			map.put("tips", "原密码不正确");
			return map;
		};
		map.put("tips", "正确");
		return map;
		
	}

	@RequestMapping("updatePwd.action")
	public @ResponseBody Map<String, String> loginAction(@RequestParam("pwd")String pwd,
			@RequestParam("newPwd")String newPwd,
			@RequestParam("account")String account,
			HttpSession session) {
		// 通过session中存取账号值
		boolean result = false;
		UserBean user =new UserBean();
//		String account = (String) session.getAttribute("username");
		user.setAccount(account);
        user.setPwd(pwd);
		// ModelAndView model=new ModelAndView();
		Map<String, String> map = new HashMap<String, String>();
		// 验证原始密码是否正确
		if (!service.verifyPwd(user)) {
			map.put("tips", "原密码不正确");
			return map;
		};
		// 获取到m密码加密md5的值
		System.out.println("新密码值"+newPwd);
		String md5_pwd = LoginEncoding5.upperMD5(newPwd);
		user.setPwd(md5_pwd);
		result = service.updateUserPwd(user);
		if (result) {
			map.put("tips", "成功");
			return map;
		} else {
			map.put("tips", "失败");
			return map;
		}
	}

	@RequestMapping("updateMes.action")
	public @ResponseBody Map<String, String> updateMes(UserBean user, HttpSession session) {
		String account = (String) session.getAttribute("username");
		user.setAccount(account);
		boolean rs = service.updateUserMessage(user);
		Map<String, String> map = new HashMap<String, String>();
		if (rs) {
			map.put("judge", "success");
			return map;
		}
		return map;
	}

	// 展示信息
	@RequestMapping("finduser.action")
	public @ResponseBody UserBean finduser(HttpSession session) {
		String account = (String) session.getAttribute("username");

		return service.findUser(account);

	}
}
