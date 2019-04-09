package com.parkingms.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.parkingms.bean.CbdcarBean;
import com.parkingms.bean.CompanytreatyBean;
import com.parkingms.service.ICompanytreatyService;
import com.parkingms.util.FileUploadUtil;

/**
 * 租户合约的表示层类
 * 
 * @author LC
 *
 */
@Controller
public class CompanytreatyAction {
	@Autowired
	private ICompanytreatyService service;

	/**
	 * 查询所有租户合约
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findCompanytreatyList.action")
	public @ResponseBody Map<String, Object> findCompanytreatyList(CompanytreatyBean bean, int page, int num) {
		Map<String, Object> map = service.getCompanytreaty(bean, page, num);
		return map;
	}

	/**
	 * 根据前台传来的数据，查询相应的租户合约
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findCompanytreaty.action")
	public @ResponseBody CompanytreatyBean findCompanytreaty(CompanytreatyBean bean) {
		CompanytreatyBean comtreaty = service.getOldCompanytreaty(bean);
		return comtreaty;
	}

	/**
	 * 更新租户合约(软删除旧的，插入新的)
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/updataCompanytreaty.action")
	public @ResponseBody String updateCompanytreaty(CompanytreatyBean bean, String cbdcar_no, String cbdcar_addr,
			String oldcar_no) {
		String str = "";
		CbdcarBean newcbdcar = new CbdcarBean();
		CbdcarBean oldcbdcar = new CbdcarBean();
		newcbdcar.setNo(cbdcar_no);
		newcbdcar.setAddr(cbdcar_addr);
		oldcbdcar.setNo(oldcar_no);
		oldcbdcar.setAddr(cbdcar_addr);
		try {
			str = service.Companyupdata(bean, newcbdcar, oldcbdcar);
		} catch (Exception e) {
			str = e.getMessage();
		}
		return str;
	}

	/**
	 * 租户合约解约
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/removeCompanytreaty.action")
	public @ResponseBody String removeCompanytreaty(int cid) {
		String str = "";
		try {
			str = service.Companyunwind(cid);
		} catch (Exception e) {
			str = e.getMessage();
		}
		return str;
	}

	/**
	 * 签订租户合同
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/signCompanytreaty.action")
	public @ResponseBody String signCompanytreaty(CompanytreatyBean bean, String cbdcar_no, String cbdcar_addr) {
		String str = "";
		CbdcarBean cbdcar = new CbdcarBean();
		cbdcar.setAddr(cbdcar_addr);
		cbdcar.setNo(cbdcar_no);
		try {
			str = service.Companysigned(bean, cbdcar);
		} catch (Exception e) {
			str = e.getMessage();
		}
		return str;
	}

	/**
	 * 续约合同
	 * 
	 * @param bean
	 * @param cbdcar_no
	 * @param cbdcar_addr
	 * @return
	 */
	@RequestMapping(value = "/renewCompanytreaty.action")
	public @ResponseBody String renewCompanytreaty(CompanytreatyBean bean, String cbdcar_no, String cbdcar_addr) {
		String str = "";
		CbdcarBean cbdcar = new CbdcarBean();
		cbdcar.setAddr(cbdcar_addr);
		cbdcar.setNo(cbdcar_no);
		try {
			str = service.Companyrenewal(bean, cbdcar);
		} catch (Exception e) {
			str = e.getMessage();
		}
		return str;
	}

	/**
	 * 文件上传
	 * 
	 * @param uploadImg
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/companytreatyUpload.action")
	public @ResponseBody Map<String, String> companytreatyUpload(@RequestParam(value = "file") MultipartFile uploadImg,
			HttpServletRequest req) {
		String str = "";
		str = FileUploadUtil.saveFile(req, uploadImg);
		Map<String, String> res = new HashMap<String, String>();
		res.put("uploadImg", str);
		return res;
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

	public ICompanytreatyService getService() {
		return service;
	}

	public void setService(ICompanytreatyService service) {
		this.service = service;
	}
}
