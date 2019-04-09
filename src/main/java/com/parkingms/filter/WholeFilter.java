package com.parkingms.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 过滤器，实现权限管理和单态登录
 * @author 杨吉竹
 *
 */
//@WebFilter("/*")
public class WholeFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		//转换为httpservlet
	HttpServletRequest http_req=(HttpServletRequest)req;
	HttpServletResponse http_res=(HttpServletResponse) res;
	 HttpSession session=http_req.getSession(true);
	String uri=http_req.getRequestURI();
	System.out.println("拦截器执行了，uri为"+uri);
	//将js资源等静态资源和登录注册页面放过
	if(uri.endsWith("js")||uri.endsWith("jpg")||uri.endsWith("css")
			||uri.endsWith("login1.jsp")||uri.endsWith("register.jsp")||
					uri.endsWith("ttf")||uri.endsWith("woff")||uri.endsWith("png")){
		chain.doFilter(req, res);
	}else if(uri.endsWith("jsp")||uri.endsWith("html")){
   
 String user_name=   (String) session.getAttribute("username");
    	if(user_name!=null){
         chain.doFilter(req, res);
    	}else{
    	http_res.sendRedirect("login1.jsp");
    	}
    }else if(uri.endsWith(".action")){
    	if(uri.contains("login")||uri.contains("register")||uri.contains("Login")){
    		chain.doFilter(req, res);
		}else{
			//对session中是否存值进行判定，判定是否登录
		String user_name=	(String) http_req.getSession(true).getAttribute("username");
			if(user_name!=null){
				chain.doFilter(req, res);
			}else{
			http_res.sendRedirect("jsp/login1.jsp");
			
			}
		}
    	
    }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
