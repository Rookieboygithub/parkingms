package com.parkingms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.parkingms.aop.ParkingmsLog;
import com.parkingms.bean.DealBean;
import com.parkingms.dao.IDealDao;
import com.parkingms.dao.IPayinfoDao;
import com.parkingms.service.IPayService;
import com.parkingms.util.AlipayConfig;
import com.parkingms.util.PayUtil;

/**
 * 支付功能业务实现类
 * 
 * @author wqj24
 *
 */

@Service
public class PayServiceImpl implements IPayService {

	@Autowired
	private IPayinfoDao pdao;
	@Autowired
	private IDealDao ddao;

	@Override
	public String getPayBodyContent(int dealId) {

		// 设置订单id
		DealBean bean = new DealBean();
		bean.setId(dealId);

		// 获取支付金额
		String price = String.valueOf(ddao.get(bean).getPrice());
		// 获取商户订单编号
		String dealNo = ddao.get(bean).getNo();

		// 调式信息
		System.out.println("订单编号：" + dealNo + "\n支付金额：" + price);

		// 获取支付界面Body内容
		String BodyContent = PayUtil.pay(dealNo, price);

		return BodyContent;
	}

	public void setPdao(IPayinfoDao pdao) {
		this.pdao = pdao;
	}

	public void setDdao(IDealDao ddao) {
		this.ddao = ddao;
	}
	
	@ParkingmsLog(module="支付", method="创建支付信息", plantform=0,type=1)
	@Override
	public void createPayinfo(int dealId) {
		// 检查支付信息是否已存在
		int count = pdao.findPayinfoCountByDealId(dealId);
		if (count != 1) {
			pdao.addPayinfo(dealId);
		}

	}

	@Override
	public boolean payReturnCheck(HttpServletRequest request) {

		boolean signVerified = false;

		// 获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 解决乱码
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		// 调用SDK验证签名
		try {
			signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,
					AlipayConfig.sign_type);
		} catch (AlipayApiException e) {

			e.printStackTrace();
		}

		return signVerified;
	}

	@ParkingmsLog(module="支付", method="修改支付状态为已支付", plantform=0,type=1)
	@Override
	public String updatePaystStatus(HttpServletRequest request) {
		DealBean bean = new DealBean();
		String result = null;

		// 商户订单号
		String out_trade_no = request.getParameter("out_trade_no");
		// 支付宝交易号
		String trade_no = request.getParameter("trade_no");
		// 付款金额
		String total_amount = request.getParameter("total_amount");

		// 为了安全可以再验证金额

		bean.setNo(out_trade_no);

		// 获取订单id
		int dealId = ddao.get(bean).getId();

		// 更新订单状态
		pdao.updatePaystatusPayed(dealId, new Date());
		
		// 更新订单Deal表的订单状态
		ddao.updateStatusPayed(bean);
		
		
		result = out_trade_no;

		return out_trade_no;
	}

	@ParkingmsLog(module="支付", method="修改支付状态为已退款", plantform=0,type=1)
	@Override
	public boolean refund(String out_trade_no, String trade_no, String refund_amount, String refund_reason,
			String out_request_no) {
		String result = "未知错误，退款是否成功以支付宝为准";
		
		DealBean bean = new DealBean();
		bean.setNo(out_trade_no);
		int dealId = ddao.get(bean).getId();
		// 安全起见，验证退款金额(标记)
		refund_amount = ddao.get(bean).getPrice()+"";

		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);

		// 设置请求参数
		AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\"" + trade_no + "\","
				+ "\"refund_amount\":\"" + refund_amount + "\"," + "\"refund_reason\":\"" + refund_reason + "\","
				+ "\"out_request_no\":\"" + out_request_no + "\"}");

		// 请求
		try {
			result = alipayClient.execute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}

		boolean boo = result.contains("Success");

		if (boo) {
			// 更新支付状态为已退款
			pdao.updatePaystatusRefund(dealId);
			
		}

		return boo;
	}

	@Override
	public boolean refund(String out_trade_no, String refund_amount) {

		return this.refund(out_trade_no, "", refund_amount, "", "");
	}

}