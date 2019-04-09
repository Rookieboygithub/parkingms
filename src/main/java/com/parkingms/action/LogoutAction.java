package com.parkingms.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parkingms.aop.ParkingmsLog;

/**
 * 
 * 实现页面的注销功能，将session域中数据销毁
 * 加入aop日志功能
 * @author 杨吉竹
 *
 */
@Controller
public class LogoutAction {
@RequestMapping("logout.action")
@ParkingmsLog(module="注销模块",method="注销",plantform=0,type=1)
public String logout(HttpServletRequest request){
	//将页面session强制销毁
	request.getSession().invalidate();
	//销魂后返回登陆页面
	return "jsp/login.jsp";
	
	
}
}
