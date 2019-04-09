package com.parkingms.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.parkingms.bean.CompanyBean;
import com.parkingms.service.ICompanyService;

/**
 * 企业管理模块action
 * @author BHH
 *
 */
@Controller
@RequestMapping(value = "/company")
public class CompanyManageAction {
	@Autowired
	private ICompanyService service;

	/**
	 * 查询企业
	 * @param bean企业信息
	 * @param page当前页数
	 * @param limit每页显示多少条，默认五条
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "selectCompany.action")
	public @ResponseBody Integer selectCompany(CompanyBean bean, Integer page, Integer limit,
			HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setAttribute("CompanyBean", bean);
		/* 计算初始总页码 */
		int number = service.selectCompanyNumber(bean);
		session.setAttribute("number", number);
		return number;
	}

	/**
	 * 增加企业
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "addCompany.action")
	public ModelAndView addCompany(CompanyBean bean) {
		// 插入企业
		ModelAndView model = new ModelAndView();
		String result = service.insertCompanyBean(bean);
		// 返回添加的结果
		model.addObject("result", result);
		if (result.equals("注册成功")) {
			model.setViewName("../jsp/company-add.jsp");
		} else {
			model.setViewName("../jsp/company-add.jsp");
		}
		return model;
	}

	/**
	 * 删除企业
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "deleteCompany.action")
	public @ResponseBody Integer deleteCompany(CompanyBean bean, HttpServletRequest request) {
		/* 移除企业 */
		service.deleteCompanyBean(bean);
		/* 当前查询页面CompanyBean */
		CompanyBean selectBean = (CompanyBean) request.getSession(true).getAttribute("CompanyBean");
		/* 获取删除企业后，新的总页码 */
		int pageNew = service.selectCompanyNumber(selectBean);
		return pageNew;
	}

	/**
	 * 分页
	 * @param pageNumber当前页数
	 * @param limit当前每页多少条
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "page.action")
	public @ResponseBody List<CompanyBean> pageAction(Integer pageNumber, Integer limit, HttpServletRequest request) {
		// 查询条件bean
		CompanyBean bean = (CompanyBean) request.getSession(true).getAttribute("CompanyBean");
		// 查询出将要显示的信息
		List<CompanyBean> list = service.selectCompanyBean(bean, pageNumber, limit);
		return list;
	}
	/**
	 * 检测用户名是否存在
	 * @param loginName
	 * @return
	 */
	@RequestMapping(value = "loginNameCheck.action")
	public @ResponseBody String loginNameCheck(String loginName) {
		String result = service.selectCompanyLoginName(loginName);
		return result;
	}
}
