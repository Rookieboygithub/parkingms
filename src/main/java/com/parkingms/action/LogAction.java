package com.parkingms.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingms.bean.LogBean;
import com.parkingms.service.ILogServcie;
/**
 * 日志查询有关的请求
 * @author 张怡
 *
 */
//定义类为处理器
@Controller
public class LogAction {
	/**
	 * 自动注入ILogServcie接口
	 */
	@Autowired
	private ILogServcie service;
	
	@RequestMapping(value = "/showLogByPage.action")
	@ResponseBody
	public Map<String,Object> showLogByPage(int curr,int limit,LogBean bean){
		// 根据请求的页数curr及每页显示的条数limit，返回分页的日志信息及总条数
		Map<String,Object> map = service.findLogByPage(curr,limit,bean);
		return map;
	}
	
	/**
	 * 对前台发送的日期进行格式转换
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBind(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
