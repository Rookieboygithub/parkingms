
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
public class UpdateAdminLoginAction {
	
	@Autowired
	private ISuperAdminService service;
	
	@RequestMapping(value="/updateAdminLoginAction.action")
	@ResponseBody
	public int registerAdminAction(AdminBean bean){
		

		System.out.println(bean.getLoginid());
		System.out.println("------------------------");
//		ModelAndView model = new ModelAndView();
		//把从页面获取到的参数，存到bean中
		//int id = bean.getLoginid();
		//bean.setLoginid(id);
		//service.alterAdminLogin(id);
		int row = service.alterAdminLogin(bean);
		System.out.println(row);
		
		
//		model.setViewName("transit");
		
//		return model;
		return row;
	}

}