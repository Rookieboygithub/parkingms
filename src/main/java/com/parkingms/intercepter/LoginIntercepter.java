/*package com.parkingms.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
*//**
 * 
 * 对页面进行拦截，
 * 
 * @author Administrator
 *
 *//*
public class LoginIntercepter implements HandlerInterceptor {
*//**
 * 在方法前，取出uri，对uri进行判定，是否是登录或注册操作，如果是则放过，
 * 如果不是，则进行判定，看session中是否存有用户账户，如果没有则返回登录页面，
 * 如果有则放过
 *//*
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {
		//先获取到uri
		String path_uri=req.getRequestURI();
		System.out.println("拦截器执行了");
		//进行判定
		if(path_uri.contains("login")||path_uri.contains("sign")){
			return true;
		}else{
			//对session中是否存值进行判定，判定是否登录
		String user_name=	(String) req.getSession(true).getAttribute("myuser");
			if(user_name!=null){
				System.out.println("session中有值");
				return true;
			}else{
			res.sendRedirect("jsp/login.jsp");
			return false;
			}
		}
	}
	
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object obj, Exception arg3)
			throws Exception {
	
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}
*/