package com.parkingms.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.parkingms.bean.CbdcarBean;
import com.parkingms.bean.CompanyBean;
import com.parkingms.bean.CompanytreatyBean;
import com.parkingms.service.ICompanyFrontService;

/**
 * 服务端企业相关action
 * @author BHH
 *
 */
@Controller
@RequestMapping(value = "/companyFront")
public class CompanyFront {
	@Autowired
	private ICompanyFrontService service;

	/**
	 * 获取企业的信息
	 * 1：查询指定条件的的车位
	 * 2：查询指定id车位bean，返回相应的合同
	 * @param page当前分页页码
	 * @param limit当前每页显示条数
	 * @param treaty，查询合同的信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "streaty.action")
	public @ResponseBody Map<String, Object> selectCompanyTreary(Integer page, Integer limit,
			CompanytreatyBean treaty, HttpServletRequest request) {
		/* 企业的信息从登录状态获取 */
		CompanyBean bean = new CompanyBean();
		Integer loginid = (Integer) request.getSession(true).getAttribute("loginId");
		Integer ID = service.selectCompanyBeanID(loginid);
		bean.setId(ID);
		// 获取企业的合同信息
		List<CompanytreatyBean> list = service.selectCompanytreaty(bean, page, limit, treaty);
		// 获取企业合同总数
		int streatyNumber = service.selectCompanytreatyNumber(bean);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("streatyNumber", streatyNumber);
		return map;
	}

	/**
	 * 查询车位信息
	 * 1：查询指定条件的车位
	 * 2：查询指定合同的车位
	 * @param page当前车位页码
	 * @param limit当前页面显示条数
	 * @param treatyID当前合同的id
	 * @param cbdcar车位信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "cbdcar.action")
	public @ResponseBody Map<String, Object> selectCompanyCbdcar(Integer page, Integer limit, Integer treatyID,
			CbdcarBean cbdcar, HttpServletRequest request) {
		/* 企业的信息从登录状态获取 */
		CompanyBean bean = new CompanyBean();
		Integer loginid = (Integer) request.getSession(true).getAttribute("loginId");
		Integer ID = service.selectCompanyBeanID(loginid);
		bean.setId(ID);
		// 获取企业车位信息
		List<CbdcarBean> list = service.selectCompanyCbdcar(bean, page, limit, treatyID, cbdcar);
		// 获取企业车位总数
		int cbdnumber = service.selectCompanyCbdcarNumber(bean, treatyID);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("cbdnumber", cbdnumber);
		return map;
	}

	/**
	 * 日期格式转换
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}
}
