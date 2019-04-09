package com.parkingms.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingms.bean.CbdcarBean;
import com.parkingms.service.ICbdcarService;

@Controller
public class CbdcarAction {
	@Autowired
	private ICbdcarService service;

	@RequestMapping(value = "/insertCbdcar.action")
	/**
	 * 
	 * 添加自营车位
	 *
	 */
	public @ResponseBody String insertCbdcar(CbdcarBean bean) {
		String result = "添加失败";
		if (bean.getAddr() != "" && bean.getNo() != "") {
			result = service.insertCbdcar(bean);
		}

		return result;
	}

	/**
	 * 展示未租赁车位信息
	 * 
	 * @param currpage
	 *            当前页
	 * @param count
	 *            展示条数
	 * @return
	 */
	@RequestMapping(value = "showCbdcarNotLease.action")
	public @ResponseBody Map<String, Object> showCbdcarNotLease(int page, int limit) {

		// 获取数据总条数
		int total = service.getRecordCount();
		// 获取分页数据
		List<CbdcarBean> list = service.showCbdcarByStatus(page, limit, 0);
		// 封装必要信息
		Map<String, Object> map = new HashMap<>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", total); // 数据库记录总条数。
		map.put("data", list);
		return map;
	}

	/**
	 * 查询企业车位信息
	 * 
	 * @param page当前显示页码
	 * @param limit每页显示条数
	 * @return
	 */
	@RequestMapping(value = "showcbdcarByPage.action")
	public @ResponseBody Map<String, Object> selectCompanyCbdcar(Integer pageNumber, Integer limit,
			HttpServletRequest request, CbdcarBean bean) {
		List<CbdcarBean> list = service.showCbdcarByPage(pageNumber, limit, bean);
		// 获取企业车位总数
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		int number = service.findCbdcarNumber(bean);
		map.put("number", number);
		return map;
	}

	@RequestMapping(value = "allPage.action")
	public @ResponseBody int allPage(CbdcarBean bean) {

		int number = service.findCbdcarNumber(bean);

		return number;
	}
	@RequestMapping(value="deletecbdcar.action")
	public @ResponseBody int deletecbdcar(int id,CbdcarBean bean){
		service.deletecbdcar(id);
		int number = service.findCbdcarNumber(bean);
		return number;
	}
}
