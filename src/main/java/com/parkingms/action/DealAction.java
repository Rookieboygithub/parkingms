package com.parkingms.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.parkingms.bean.DealBean;
import com.parkingms.service.IDealService;
/**
 * 处理出租订单有关的Action请求
 * @author 张怡
 *
 */
//定义类为处理器
@Controller
public class DealAction {
	/**
	 * 自动注入IDealService接口
	 */
	@Autowired
	private IDealService service;

	/**
	 * 预约车位
	 * 
	 * @param deal
	 * @return
	 */
	@RequestMapping(value = "/orderDeal.action")
	@ResponseBody
	public Map<String,Object> orderDeal(DealBean bean) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			// 新增出租订单
			map = service.insertDeal(bean);
			return map;
		} catch (Exception e) {
			map.put("result", "fail");
		}
		return map;
	}

	/**
	 * 动态查看出租订单信息,第一次请求进入页面，获取第一页的订单信息
	 * 若传有包租婆Id,则查找包租婆的订单
	 * 默认查全部订单
	 * @return
	 */
	@RequestMapping(value = "/showFirstDeal.action")
	@ResponseBody
	public ModelAndView showFirstDeal(DealBean bean) {
		// 获取第一页的订单信息及总条数，显示条数为5条
		Map<String,Object> map = service.findDealByPage(1, 5, bean);
		ModelAndView model = new ModelAndView();
		// 将车位信息集合及车位数量存入model
		model.addAllObjects(map);
		// 设置跳转视图
		model.setViewName("/jsp/landlorddeal-list.jsp");
		return model;
	}
	
	/**
	 * 动态分页查看出租订单信息
	 * @param curr
	 * @param limit
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/showDealByPage.action")
	@ResponseBody
	public Map<String,Object> showDealByPage(int curr,int limit,DealBean bean){
		// 根据请求的页数curr及每页显示的条数limit，返回分页的订单集合
		Map<String,Object> map = service.findDealByPage(curr, limit, bean);
		return map;
	}
	
	/**
	 * 查看预约信息,第一次请求进入页面，获取第一页的预约信息
	 * @return
	 */
	@RequestMapping(value = "/showFirstBooking.action")
	@ResponseBody
	public ModelAndView showFirstBooking(DealBean bean) {
		// 获取第一页的订单信息，显示条数为5条
		Map<String,Object> map = service.findBookingByPage(1, 5, bean);
		ModelAndView model = new ModelAndView();
		// 将车位信息集合及车位数量存入model
		model.addAllObjects(map);
		// 设置跳转视图
		model.setViewName("/jsp/landlorddeal-booking.jsp");
		return model;
	}
	
	/**
	 * 动态分页查看预约信息
	 * @param curr
	 * @param limit
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/showBookingByPage.action")
	@ResponseBody
	public Map<String,Object> showBookingByPage(int curr,int limit,DealBean bean){
		// 根据请求的页数curr及每页显示的条数limit，返回分页的订单集合
		Map<String,Object> map = service.findBookingByPage(curr, limit, bean);
		return map;
	}
	
	/**
	 * 包租婆处理预约订单
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/dealwithDeal.action")
	@ResponseBody
	public Map<String,Object> dealwithDeal(DealBean bean){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			// 处理预约订单
			map = service.dealwithDeal(bean);
		} catch (Exception e) {
			map.put("result", "fail");
		}		
		return map;
	}

	/**
	 * 对前台发送的日期进行格式转换
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBind(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
