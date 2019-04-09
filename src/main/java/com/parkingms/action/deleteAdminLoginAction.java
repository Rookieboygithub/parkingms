
/**
 * 
 * @auther:杨向东
 * 
 * 
 */

package com.parkingms.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.parkingms.service.ISuperAdminService;

@Controller
public class deleteAdminLoginAction {

	@Autowired
	private ISuperAdminService service;

	@RequestMapping(value = "/deleteAdminLoginAction.action")
	public ModelAndView registerAdminAction(int loginid) {

		ModelAndView model = new ModelAndView();
		service.removeflag(loginid);

		// model.setViewName("jsp/transit.jsp");

		return model;
	}

}
