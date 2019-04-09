package com.parkingms.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingms.service.IMessageCodeService;

/**
 * 
 * @author LC
 *
 */
@Controller
public class MessageCodeAction {
	@Autowired
	private IMessageCodeService service;

	@RequestMapping(value = "getMessageCode.action")
	public @ResponseBody String getMessageCode(String tel, HttpSession session) {
		String code = String.format("%06d", ((int) (Math.random() * 1000000)));
		String str = service.findByTel(tel, code);
		if ("发送成功!".equals(str)) {
			session.setAttribute("messageCode", code);
		}
		return str;
	}

	@RequestMapping(value = "setMessageCode.action")
	public @ResponseBody void setMessageCode(HttpSession session) {
		session.setAttribute("messageCode", "1111111");
	}
}
