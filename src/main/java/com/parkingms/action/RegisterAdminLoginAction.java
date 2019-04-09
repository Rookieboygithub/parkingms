
/**
 * 
 * @auther:杨向东
 * 
 */

package com.parkingms.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingms.bean.AdminBean;
import com.parkingms.service.ISuperAdminService;

@Controller
public class RegisterAdminLoginAction {

	@Autowired
	private ISuperAdminService service;

	@RequestMapping(value = "/registerAdminLoginAction.action")
	@ResponseBody
	public String registerAdminAction(AdminBean bean) {

		/*
		 * System.out.println(bean.getAuthorityCar());
		 * System.out.println(bean.getAuthorityComplain());
		 * System.out.println(bean.getAuthorityPact());
		 * System.out.println(bean.getAuthorityUser());
		 */

		// ModelAndView model = new ModelAndView();
		service.insertLoginAdmin(bean);

		// model.setViewName("transit");

		return "用不用是一回事，返不返回又是另外一回事";
	}

}
