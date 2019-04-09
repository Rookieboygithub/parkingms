package com.parkingms.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingms.service.ISelectCharacterService;

/**
 * 查询登录的角色代码
 * 
 * @author 杨吉竹
 *
 */
@Controller
public class FindCharacterAction {
	@Autowired
	private ISelectCharacterService service;

	@RequestMapping("findcha.action")
	public @ResponseBody Map<String, Integer> findcha(HttpSession session) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		String account = (String) session.getAttribute("myuser");
		Integer role = service.findcha(account);
		map.put("character", role);
		return map;

	}
}
