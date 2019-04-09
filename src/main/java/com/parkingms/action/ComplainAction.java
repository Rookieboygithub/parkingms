package com.parkingms.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.parkingms.bean.ComplainBean;
import com.parkingms.bean.ComplainMessageBean;
import com.parkingms.service.IComplainService;

@Controller
public class ComplainAction {
	@Autowired
	private IComplainService iComplainService;

	@RequestMapping(value = "/showAllComplain.action")
	@ResponseBody
	public List<ComplainBean> showAllComplain() {
		List<ComplainBean> result = iComplainService.showAllComplain();
		return result;

	}

	@RequestMapping(value = "/insertComplain.action")
	@ResponseBody
	public String insertComplain(ComplainBean bean) {
		String result = iComplainService.GoComplaint(bean);
		return result;
	}

	@RequestMapping(value = "/showOneComplain.action")
	@ResponseBody
	public ModelAndView showOneComplain(String id, HttpServletRequest request) {
		ModelAndView modelvView = new ModelAndView();
		ComplainBean bean = new ComplainBean();
		int userID = Integer.parseInt(id);
		bean.setId(userID);
		List<ComplainMessageBean> list = iComplainService.showOneComplain(bean);
		if (request.getSession(true).getAttribute("Complain") == null) {
			request.getSession(true).setAttribute("Complain", list);
		}
		modelvView.addObject("Complain", list);
		modelvView.setViewName("jsp/complaint.jsp");
		return modelvView;

	}

	@RequestMapping(value = "/dealComplain.action")
	@ResponseBody
	public String dealComplain(String deal, String dealreason, String id) {
		int userID = Integer.parseInt(id);
		int truedeal = Integer.parseInt(deal);
		ComplainBean bean = new ComplainBean();
		bean.setStatus(truedeal);
		bean.setDealreason(dealreason);
		bean.setId(userID);
		return (iComplainService.dealComplain(bean));
	}

}
