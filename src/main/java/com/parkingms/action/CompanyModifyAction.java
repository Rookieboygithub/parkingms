package com.parkingms.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingms.bean.CompanyBean;
import com.parkingms.service.ICompanyMessageService;
import com.parkingms.service.ICompanyModifyService;

/**
 * 
 * 对公司信息进行修改，修改企业用户的企业联系人和企业联系电话
 * 
 * @author 杨吉竹
 *
 */
@Controller
public class CompanyModifyAction {
	@Autowired
	private ICompanyModifyService service;

	@RequestMapping("CompanyModify.action")
	public @ResponseBody Map<String, String> companyModfiy(HttpSession session, Model model, CompanyBean company) {
		/*
		 * 获取到session中存入的企业的账户名 String account =(String)
		 * session.getAttribute("username"); company.setAccount(account);;
		 */
		boolean rs = service.companyModify(company);
		Map<String, String> map = new HashMap<String, String>();
		if (rs) {
			map.put("rs", "success");
			return map;

		} else {
			map.put("rs", "fail");
			return map;
		}

	}

	@Autowired
	private ICompanyMessageService findone;

	@RequestMapping("CompanyMessage.action")
	public @ResponseBody CompanyBean findCompany(String account) {

		CompanyBean myCompany = findone.selectCompany(account);

		return myCompany;
	}
}
