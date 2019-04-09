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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.parkingms.bean.LandlordCarBean;
import com.parkingms.bean.LandlordLetBean;
import com.parkingms.service.ILandlordLetService;

/**
 * 处理招租表有关的Action请求
 * @author 张怡
 *
 */
//定义类为处理器
@Controller
public class LandlordLetAction {
	/**
	 * 自动注入ILandlordLetService接口
	 */
	@Autowired
	private ILandlordLetService service;
	
	/**
	 * 根据包租婆id查看车位及招租信息，第一次请求进入页面，获取第一页的车位列表及车位数量(动态搜索)
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/showFirstLetByLandlordId.action")
	@ResponseBody
	public ModelAndView showFirstLetByLandlordId(LandlordCarBean bean){
		//根据招租婆ID获取第一页的车位及招租信息以及总条数，显示条数为10条
		Map<String,Object> map = service.findLetByLandlordIdUsingPage(1,10,bean);
		ModelAndView model = new ModelAndView();
		//将车位信息集合及车位数量存入model
		model.addAllObjects(map);
		//将查询的包租婆id存入model
		if(bean != null){
			model.addObject("landlordId", bean.getLandlord().getId());
		}
		//设置跳转视图
		model.setViewName("/jsp/landlordlet-list.jsp");
		return model;
	}
	
	/**
	 * 根据包租婆Id查询车位及招租信息，分页显示(动态搜索)
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/showLetByLandlordIdUsingPage.action")
	@ResponseBody
	public Map<String,Object> showLetByLandlordIdUsingPage(@RequestParam(value="curr") int curr,@RequestParam(value="limit")int limit,LandlordCarBean bean){
		// 根据请求的页数curr及每页显示的条数limit，返回分页的集合
		Map<String,Object> map = service.findLetByLandlordIdUsingPage(curr, limit, bean);
		return map;
	}
	
	/**
	 * 发布招租信息
	 */
	@RequestMapping(value="/addLandlordLet.action")
	@ResponseBody
	public boolean addLandlordLet(LandlordLetBean bean){	
		boolean result=false;
		try {
			// 添加招租信息成功返回true,否则返回false,并捕获可能抛出的运行时异常
			if(service.insertLet(bean)){
				result = true;
			}
		} catch (Exception e) {

		}
		return result;
	}
	
	/**
	 * 根据车位id删除招租信息
	 */
	@RequestMapping(value="/removeLandlordLet.action")
	@ResponseBody
	public boolean removeLandlordLet(int id){
		boolean result=false;
		try {
			// 删除招租信息成功返回true,否则返回false,并捕获可能抛出的运行时异常
			if(service.deleteLet(id)){
				result = true;
			}
		} catch (Exception e) {

		}
		return result;
	}
	
	/**
	 * 根据车位id删除车位信息和招租信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteLandlordCar.action")
	@ResponseBody
	public boolean deleteLandlordCar(int id){
		boolean result = service.deleteCar(id);
		return result;
	}
	
	/**
	 * 第一次请求进入页面，获取第一页的可用招租信息列表及车位数量
	 * @return
	 */
	@RequestMapping(value="/showFirstAvailableLet.action")
	@ResponseBody
	public ModelAndView showFirstAvailableLet(LandlordLetBean bean){
		//获取第一页的招租信息（车位待出租及已预约）及总条数，显示条数为5条
		Map<String,Object> map = service.findAvailableLetByPage(1,5,bean);
		ModelAndView model = new ModelAndView();
		//将车位信息集合及车位数量存入model
		model.addAllObjects(map);
		//设置跳转视图
		model.setViewName("/jsp/landlordletAvailable-list.jsp");
		return model;
	}
	
	/**
	 * 分页显示可显示的招租信息
	 * @param curr
	 * @param limit
	 * @param bean
	 * @return
	 */
	@RequestMapping(value="/showAvailableLetByPage.action")
	@ResponseBody
	public Map<String,Object> showAvailableLetByPage(int curr,int limit,LandlordLetBean bean){
		// 根据请求的页数curr及每页显示的条数limit，返回分页的招租信息集合
		Map<String,Object> map =service.findAvailableLetByPage(curr, limit, bean);
		return map;
	}
	
	/**
	 * 根据车位id查找车位招租信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/makeLetOrder.action")
	@ResponseBody
	public ModelAndView makeLetOrder(int id){
		// 根据车位id查找车位招租信息
		LandlordLetBean bean = service.findLetByCarId(id);
		ModelAndView model = new ModelAndView();
		//将车位信息存入model
		model.addObject("data", bean);
		//设置跳转视图
		model.setViewName("/jsp/deal-add.jsp");
		return model;
	}
	
	/**
	 * 对前台发送的日期进行格式转换
	 * @param binder
	 */
	@InitBinder
    public void initBind(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
    }
}
