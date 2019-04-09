package com.parkingms.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.parkingms.bean.UserBean;

public class SaveInSession {
	public static void save(UserBean user ){
		
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("username", user.getAccount());
		session.setAttribute("character", user.getCharacter());
		session.setAttribute("loginId", user.getLoginId());
		
		
	}
}
