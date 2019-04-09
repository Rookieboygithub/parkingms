package com.parkingms.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;



public class PayUtil {
	
	private static String subject = "CBD停车系统";
	private static String goods_desc = "车位租金";
	
	
	/**
	 * 获取支付宝扫码页面 body 内容
	 * 
	 * @param out_trade_no 订单号
	 * @param total_amount 付款金额
	 * @return
	 */
	public static String pay(String out_trade_no, String total_amount) {
		
		return pay(out_trade_no, total_amount, subject, goods_desc);
	}
	
	
	/**
	 * 获取支付宝扫码页面 body 内容
	 * 
	 * @param out_trade_no 订单号
	 * @param total_amount 付款金额
	 * @param subject      订单名称
	 * @param body         商品描述
	 * @return 扫码界面 body 内容
	 */
	public static String pay(String out_trade_no, String total_amount, String subject, String goods_desc) {
		
		String bodyContent = "<h3>未知错误，获取付款页面失败<h3>";
		
		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);

		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + goods_desc + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		// 请求
		try {
			bodyContent = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}

		return bodyContent;
	}
}
