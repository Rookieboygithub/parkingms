package com.parkingms.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.parkingms.service.ISuperAdminService;


@Controller
public class CheckAccountByPwdAction {
	
	
	@Autowired
	private ISuperAdminService service;
	
	@RequestMapping(value="/checkAccountByPwdAction.action")
	@ResponseBody
	public String checkAccountByPwdAction(String account){
		
		String pass = service.findPassword(account);
		//System.out.println(row);
		

		return pass;
	}
	

}
