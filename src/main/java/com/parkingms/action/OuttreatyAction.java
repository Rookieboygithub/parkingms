package com.parkingms.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.parkingms.aop.ParkingmsLog;
import com.parkingms.bean.CbdcarBean;
import com.parkingms.bean.OuttreatyBean;
import com.parkingms.service.IOuttreatyService;
import com.parkingms.util.FileUpLoad;

@Controller("OuttreatyAction")
public class OuttreatyAction {
	/**
	 * 将业务层接口注入controller
	 */
	@Autowired
	private IOuttreatyService service;
	// 接收文件上传的地址
	private String path;

	/**
	 * 
	 * 此方法用于企业用户资料的接收
	 * 
	 * @author 徐浩力
	 * @param bean
	 */
	@ParkingmsLog(method="新增外部合约",module="外部合约业务",type=1,plantform=1)
	@RequestMapping("companyUpLoad.action")
	@ResponseBody
	public String companyUpLoad(OuttreatyBean bean, CbdcarBean cbean,Map<String,String> hashMap) {
		bean.setImg(this.getPath());
		System.out.println(bean.getCarNum());
		// 调用service业务层处理
		boolean ret = service.checkCompany(bean, cbean);
		if (ret)
			return hashMap.put("mes", "上传成功") ;
		else {
			return hashMap.put("mes", "上传失败") ;
		}
	}
	/**
	 * 接收文件，
	 * @param multipartFile
	 * @param req
	 * @return
	 */
	@ParkingmsLog(method="上传文件",module="外部合约业务",type=1,plantform=1)
	@RequestMapping("/fileUpLoad.action")
	@ResponseBody
	public String fileUpLoad(@RequestParam("file") MultipartFile multipartFile,HttpServletRequest req) {
		System.out.println(multipartFile);
		String img = FileUpLoad.fileUpLoad(req, multipartFile);
		System.out.println(img);
		this.setPath(img);
		
		return img;
	}

	/**
	 * 将传入的时间格式于映射方法先行处理
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	/**
	 * 该方法为显示所有的信息
	 * 
	 */
	@Cacheable(value="showAll")
	@RequestMapping("/show.action")
	public @ResponseBody List<OuttreatyBean> show(HttpServletRequest request){
		List<OuttreatyBean> list=service.showAll();
		HttpSession session = request.getSession(true);
		session.setAttribute("list", list);
		return list;
	}
	
	/**
	 * 该方法为修改外部合约信息
	 */
	@ParkingmsLog(method="修改外部合约",module="外部合约业务",type=1,plantform=1)
	@RequestMapping("/change.action")
	@ResponseBody
	public String change(OuttreatyBean bean){
		boolean isOk=service.changeMeans(bean);
		if(isOk)
		return "success";
		else{
			return "error";
		}
		
		
	}
	/**
	 * 
	 * 该方法用于删除
	 * @param id
	 */
	@ParkingmsLog(method="删除外部合约",module="外部合约业务",type=1,plantform=1)
	@RequestMapping("/del.action")
	@ResponseBody
	public String del(Integer id){
		System.out.println(id);
		boolean isOk=service.delThis(id);
		if(isOk)
			return "ok";
		else{
			return "no";
		}
	}
	
	
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
