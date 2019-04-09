package com.parkingms.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingms.bean.AdminBean;
import com.parkingms.service.IAdminModifyService;

/**
 * 
 * 
 * 普通管理员的权限管理，
 * 后台管理人员修改只允许修改登录密码和 电话号码，
 * 不允许修改工号、真实姓名和管理权限。
 * 
 * 
 * @author 杨吉竹
 *
 */
@Controller
public class AdminModifyAction {
	@Autowired
	private IAdminModifyService service;

	
	/**
	 * 
	 * @param session
	 * @return  AdminBean 返回当前管理员用户的所有详细信息
	 */
	@RequestMapping("findadmin.action")
	public @ResponseBody AdminBean findadmin(HttpSession session) {
		String account = (String) session.getAttribute("username");
		AdminBean admin = service.findadmin(account);

		return admin;
	}

	@RequestMapping("updateTel.action")
	public @ResponseBody Map<String,Boolean> updateTel(AdminBean admin, HttpSession session) {
		String account = (String) session.getAttribute("username");
		admin.setAccount(account);
		boolean rs = service.updateTel(admin);
		Map<String,Boolean>  result=new HashMap<String, Boolean>();
		result.put("judge", rs);
		return result;
   
	}
}
