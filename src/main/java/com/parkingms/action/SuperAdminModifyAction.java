package com.parkingms.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingms.bean.AdminBean;
import com.parkingms.service.ISuperAdminModifyService;

/**
 * 
 * 超级管理员权限，可以修改自己登陆的密码， 
 * 可以修改普通管理员的登录权限 包括用户管理权限、
 * 车位管理权限、合同签约管理权限和投诉管理权限
 * 
 * @author 杨吉竹
 *
 */
@Controller
public class SuperAdminModifyAction {
	@Autowired
	private ISuperAdminModifyService service;
//查找所有的普通管理员
	@RequestMapping("findall.action")
	public @ResponseBody List<AdminBean> findall(Integer page) {
		List<AdminBean>  list=new ArrayList<AdminBean>();
		if(page==null){
	return list;
	
}else if(page<=0){
	return null;
}else{
	 list= service.findall(page);
	 
}
		return list;

	}

	@RequestMapping("updateAdmin.action")
	public @ResponseBody Map<String,String> updateAdmin(AdminBean bean) {
		boolean rs = service.modifyadmin(bean);
		Map<String,String> map=new HashMap<String,String>();
		if (rs) {
			map.put("judge", "success");
			return map;
		} else {
			return map;
		}

	}
	@RequestMapping("searchAdmin.action")
	public @ResponseBody List<AdminBean> search(String keywords,Integer page){
		
		return service.search(keywords,page);
		
	}
	@RequestMapping("findone.action")
	public @ResponseBody AdminBean findAdmin(Integer id){
		
		return service.findone(id);	
	}
	//找到数据条数
	@RequestMapping("findcount.action")
	public @ResponseBody Map<String, Integer> findcount(){
	  int tolPage=service.getcount();
	  Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("pages", tolPage);
	  return map;
	}
	
	@RequestMapping("searchcount.action")
	public @ResponseBody Map<String, Integer> searchcount(String keywords){
		  int tolPage=service.searchcount(keywords);
		  Map<String, Integer> map=new HashMap<String, Integer>();
			map.put("pages", tolPage);
		  return map;
		}
}
