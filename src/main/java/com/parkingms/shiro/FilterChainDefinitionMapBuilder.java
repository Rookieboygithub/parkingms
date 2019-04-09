package com.parkingms.shiro;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.parkingms.bean.AuthorityBean;
import com.parkingms.dao.IAuthorityDao;
/**
 * 
 * 
 * @author lLty
 *将数据库保存的页面和权限存到map中
 */
public class FilterChainDefinitionMapBuilder {
	@Autowired
	private IAuthorityDao dao;
	public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		List<AuthorityBean> list=dao.findAll();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getRole().equals("all"))
				map.put(list.get(i).getUri(), "anon");
			else{
				map.put(list.get(i).getUri(), "authc,roles["+list.get(i).getRole()+"]");
			}
		}
	
		
		map.put("/logout", "logout");
		map.put("/font/**", "anon");
		map.put("/css/**", "anon");
		map.put("/js/**", "anon");
		map.put("/images/**", "anon");
		map.put("/lay/**", "anon");
		map.put("/lib/**", "anon");
		map.put("/fonts/**", "anon");
		map.put("/fonts/**", "anon");
		map.put("ws://127.0.0.1:8124/", "anon");
		map.put("/**", "authc");
		map.put("/jsp/register.jsp","anon");
		map.put("/register.action","anon");
		return map;
	}
	
}