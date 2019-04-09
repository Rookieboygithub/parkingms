
/**
 * 
 * @auther:杨向东
 * 
 */

package com.parkingms.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingms.bean.AdminBean;
import com.parkingms.service.ISuperAdminService;


@Controller
public class ShowAllAdminLoginAction {
	
	
	@Autowired
	private ISuperAdminService service;
	
	@RequestMapping(value="/ShowAllAdminLoginAction.action")
	public @ResponseBody Map<String,Object> registerAdminAction(int page){
		
		AdminBean bean = new AdminBean();
		
		//当前页的数据
		List<AdminBean> list = service.findAll(page);
		//数据库总页码
		int totalPage = service.findNum(bean);
		//数据库总条数
		int totalData = service.findAllNum(bean);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("totalPage", totalPage);
		map.put("totalData", totalData);
		return map;
	}

}
