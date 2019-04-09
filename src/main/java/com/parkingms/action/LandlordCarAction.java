package com.parkingms.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.parkingms.bean.LandlordCarBean;
import com.parkingms.service.ILandlordCarService;
import com.parkingms.util.UploadingUtil;
/**
 * 处理车位表有关的Action请求
 * @author 张怡
 *
 */
//定义类为处理器
@Controller
public class LandlordCarAction {
	/**
	 * 自动注入ILandlordCarService接口
	 */
	@Autowired
	private ILandlordCarService service;
	
	/**
	 * 接受上传图片，并保存到服务器upload文件夹中，返回处理后的新文件名（加上UUID）
	 * @param req
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/uploadImgFile.action")
	@ResponseBody
	public Map<String, Object> uploadImgFile(HttpServletRequest req,MultipartFile file){
		// 调用上传文件工具类，以键值对形式返回上传文件名
		Map<String, Object> res = UploadingUtil.upload(req, file);
		return res;
	}
	
	/**
	 * 包租婆申请招租车位
	 * @param bean
	 * @return
	 */
	@RequestMapping(value="/landlordCarApply.action")
	@ResponseBody
	public boolean landlordCarApply(LandlordCarBean bean){
		// 添加车位信息成功返回true,否则返回false
		boolean result = service.insertCar(bean);
		return result;
	}
	
	/**
	 * 第一次请求进入页面，获取第一页的车位列表及车位数量(动态搜索)
	 * @return
	 */
	@RequestMapping(value="/showFirstLandlordCars.action")
	@ResponseBody
	public ModelAndView showFirstLandlordCars(LandlordCarBean bean){
		//获取第一页的车位信息以及总条数，显示条数为10条
		Map<String,Object> map  = service.findLandlordCarsByPage(1,10,bean);
		ModelAndView model = new ModelAndView();
		//将车位信息集合及车位数量存入model
		model.addAllObjects(map);
		//设置跳转视图
		model.setViewName("/jsp/landlordcar-list.jsp");
		return model;
	}
	
	/**
	 * 分页显示车位列表(动态搜索)
	 * @param curr
	 * @param limit
	 * @return
	 */
	@RequestMapping(value="/showLandlordCarsByPage.action")
	@ResponseBody
	public Map<String,Object> showLandlordCarsByPage(@RequestParam(value="curr") int curr,@RequestParam(value="limit")int limit,LandlordCarBean bean){
		// 根据请求的页数curr及每页显示的条数limit，返回分页的车位集合
		Map<String,Object> map = service.findLandlordCarsByPage(curr, limit,bean);
		return map;
	}
	
	/**
	 * 显示审核车位及包租婆信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/showLandlordCarReview.action")
	@ResponseBody
	public ModelAndView showlandlordCarReview(int id){
		//根据车位id查询车位信息
		Map<String, Object> map = service.findLandlordCarById(id);
		ModelAndView model = new ModelAndView();
		//将车位信息存入model
		model.addObject("data", map.get("data"));
		//设置跳转视图
		model.setViewName("/jsp/landlordcar-review.jsp");
		return model;
	}
	
	/**
	 * 审核车位信息
	 * @param bean
	 * @return
	 */
	@RequestMapping(value="/submitLandlordCarReview.action")
	@ResponseBody
	public boolean submitLandlordCarReview(LandlordCarBean bean){
		boolean result = service.updateCarStatus(bean);
		return result;
	}
	
	/**
	 * 包租婆查看自己的车位，第一次请求进入页面，获取第一页的车位列表及车位数量
	 * @return
	 */
	/*@RequestMapping(value="/showFirstCarsByLandlordId.action")
	@ResponseBody
	public ModelAndView showFirstCarsByLandlordId(){
		List<LandlordCarBean> list = service.findCarsByLandlordIdUsingPage(1,5);
		int total = service.countLandlordCars();
		ModelAndView model = new ModelAndView();
		model.addObject("list", list);
		model.addObject("total", total);
		model.setViewName("/jsp/landlordcar-list.jsp");
		return model;
	}*/
	
}
