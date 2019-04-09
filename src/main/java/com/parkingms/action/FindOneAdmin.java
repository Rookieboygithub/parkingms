package com.parkingms.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingms.bean.AdminBean;
import com.parkingms.service.ISuperAdminService;


	
	@Controller
	public class FindOneAdmin {
		
		
		@Autowired
		private ISuperAdminService service;
		
		@RequestMapping(value="/findAdminAction.action")
		public @ResponseBody AdminBean findadmin(HttpSession session) {
			String account = (String) session.getAttribute("username");
			//AdminBean admin = service.findadmin(account);
			AdminBean admin = service.findAdminBean(account);
			//当admin是一个null时，执行下一句时，为什么要报一个空指针错误
			//admin.setAccount(account);
			
			
			if(admin != null){
				admin.setAccount(account);
			    System.out.println(admin.getAccount());
				return admin;
			}else{
				//这样会不会造成耦合？
			    AdminBean bean = new AdminBean();
			 
			    admin = bean;
			    admin.setAccount(account);
			    //System.out.println(admin.getAccount());
			    //System.out.println("11111111111111"+admin+"--------------------");
			    return admin;
			}
		}
	}


