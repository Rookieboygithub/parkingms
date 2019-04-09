package com.parkingms.service;

import javax.servlet.http.HttpServletRequest;

public interface IPayService {
	
	/**
	 * 根据订单生成扫码支付页面boay内容
	 * @param dealId 订单id
	 * @return 支付页面boay内容
	 */
	public String getPayBodyContent(int dealId);
	
	/**
	 * 添加支付信息
	 * @param dealId
	 */
	public void createPayinfo(int dealId);
	
	/**
	 * 效验支付宝支付返回信息
	 * @param request
	 * @return
	 */
	public boolean payReturnCheck(HttpServletRequest request);
	
	/**
	 * 设置订单支付状态为已支付
	 * @param request
	 * @return
	 */
	public String updatePaystStatus(HttpServletRequest request);
	
	/**
	 * 退款
	 * @param out_trade_no 商户订单号
	 * @param trade_no 支付宝交易号
	 * @param refund_amount 退款的金额
	 * @param refund_reason 退款的原因
	 * @param out_request_no 标识一次退款请求
	 * @return 退款回执信息
	 */
	public boolean refund(String out_trade_no, String trade_no, String refund_amount, String refund_reason, String out_request_no);
	
	public boolean refund(String out_trade_no, String refund_amount);
	
}
