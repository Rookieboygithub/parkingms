package com.parkingms.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingms.bean.DealBean;
import com.parkingms.service.IPayService;
/**
 * 支付功能action
 * @author 王青杰
 *
 */
@Controller
public class PayAction {
	
	@Autowired
	IPayService service;
	
	@RequestMapping("/pay.action")
	public String pay(HttpServletRequest request, int dealid) {
		
		// 生成支付记录
		service.createPayinfo(dealid);
		// 生成支付界面内容
		String bodyContent = service.getPayBodyContent(dealid);
		request.setAttribute("body", bodyContent);
		
		return "/jsp/pay.jsp";
	}
	
	@RequestMapping("/pay_result.action")
	public String payRetrn(HttpServletRequest request) {
		
		String PayResult = "支付失败";
		
		// 效验支付信息
		boolean boo = service.payReturnCheck(request);
		if (boo) {
			// 更新支付状态
			service.updatePaystStatus(request);
			PayResult = "支付成功";
		}
		
		request.setAttribute("PayResult", PayResult);
		
		System.out.println(PayResult);
		
		
		// 支付成功跳转的页面
		return "jsp/index2.jsp";
		
	}
	
	@RequestMapping("/pay_refund.action")
	@ResponseBody
	public String payRefund(DealBean bean) {
		
		String result = "fail";
		
		// 获取订单编号
		String out_trade_no = bean.getNo();
		// 获取订单金额
		String refund_amount = bean.getPrice()+"";
		// 处理退款
		if(service.refund(out_trade_no, refund_amount)){
			result = "success";
		}
		return result;
	}
	
}
