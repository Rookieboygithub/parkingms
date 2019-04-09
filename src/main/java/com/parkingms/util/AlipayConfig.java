package com.parkingms.util;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 支付宝接口配置信息
 * @author 王青杰
 *
 */

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016091700531846";
		
	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC5JjmITt7fPUZonDdpyXFZ8hpkHP0GLRi8/smH0ilIxn9f54hIlrDKBR0th40qUaLZT/dmVB+GLsjkeAKQNpQsxrA1sVZd3aSqbbq66VgHfy+wFuoY4fndeJ4yEhK/eFB9eS18plvN0jniOZMxadLDZ8QGKGv7DDFowP5m6luoSKqm5yeqoGclM7CJkDHB6hCOQoepFLoBtv9LUwnys3Z9bUYz6r/uIA6ym8zWmdYV+tdpfaf96m4w6+A2N5UACjhTqTjgkosrmj0PZtjaz7cBy+bikbyljeyAhPLWMF2WPi6s7wPiD73eeqFQ9X40qXUxvmTrVRRAK0rvTl/1G13lAgMBAAECggEAR8tnwdB7oes70zLIVhAUEUHNNAb9oph4IElYUn+PHLZEDCdW9HbihOPoxY/rkH5pfIUCtT5nnvujUNysecmAOcCt8ZQbnnhk8uVr5DC5vuZ10n2ANzfhUR0FkvrpJC/+XjlQBybnMG+VnICg8CdN4BHrtEuWW5oFys4TmErGUgF6dAr1Q2kN0iKfTHpLJUtisdxsMBUzP9G+s6yQByuAkBiE7PTHfVAB7XUzqwEW5UykcJH66fPWer7Nquh2spkModiH1NnrSyi1gpe+6W7+SLg/SL9Q/SV4eiExf7KH7tUnZfNvKL3W2wuN+liDx55lEcw2VXAIxQ935dJ/wimTIQKBgQDmiQ2Exj417FRZi9shUJwwlrFlMmCq/jqGds9FRxjruh6rcJ/IToCFORD+miDbaPiZXIDrZ7e9WQmIvRv1ZTzVru5KVbd1NyFYPgp+xDN4wH2J5Lqp8F5cIDqyhoDLH5nq6hmoevjEzM+JvCohTh7e15ZtHPMrDPpCRnSRQaUvjQKBgQDNmcVviIT0MclGN69TefO5+iJvfcVm6l9x2uDpXv+xj3KJa+MnjX7tKIThjibR7y/RYoSQYm6vST1Fk0HyhQ5T0POb6j6+BZjpN4jXg0FAV92W5QNEv6hexIuxZf+e3EDGkMT3WZ8SNCqNjdGJZ4Bqh7dS57a9DnsismWFrldFuQKBgQCf0RlPF4OfLEPZhCwYb2KoCH/YUNFVGUOHXiwYsc4j9PY4Ea6jVVChissuRblLgfz9jHRPParSGDyUz8znFRTUah4F6GBzYRG7s97gvLGWH5OoGaoSD3HK2gj3JGl652Bzy3CYLQ/ylq21pX1rc1XRVJGS+QPWr3t6oI9E98ZFLQKBgQC108NXIw4qBAmz7rVAhv60P/HKVhmJLcdhIqh720cYWft6aw6GQU26IXZ7H8NXMEhCzfTc2CB5JrVoXZYaC/Ai0g1PmzcHyF12C2Ya6np/k/VlaH2ehD08Kmg8aC9L6A/lS8zVz82tRLNJYt2nvgBEtme7iy8CPddKVSkRs6B9CQKBgQDUYOLghyQyZ5YGMc5tC4J8Tiv4713s8AV8jvtJL5xPieJY9DM3DjKmYpvCCdPyBHq1IW+zPA48JVF8mILb5nGnyhwLH6nIDO1CvXFVH4hZ7g8fiyps4nGSt28ivKClbmnpTDSSw7tRr7dtA9+QGvyEOZSt+8sFTiHKQxQvTKsDHQ==";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0fucfsMbcDPwzQrxhp3gYbmLMcI5GFjIY9D7mNUP2oEKQOLZl2cMaNLdRbgKlf2FOiVELG3y4+M/p2QyT+eymSPlF4OU+Tn9uDKRrbsyl5j7Dp/ZrUQlYJSM/kXrgPxi9U+PH6hO0r0X4a+Xg2Lne92XTIyD9Lm+h/ocT+fVNROmatUOn25JIuytLFqTaH9YBgOtNRfSFjCmvvq3szRSkjFkBxWkaJkGdEvwOeHb4uQpAlIXqeWfR4ZtWqL52fEzhCvcaiwV3zKv2oQ0xUdLvN1OKIkVbG1nOrc+Dr+F2zR0rOuceG5vP/ipA5nkVf6G2VDI9ZAfa7TkLjeCL3YegQIDAQAB";

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:80/parkingms/notify_url.jsp";
	

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:80/parkingms/pay_result.action";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	// 支付宝网关
	public static String log_path = "C:\\";
	

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	static {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
		notify_url = url+"/notify_url.jsp";
		return_url = url+"/pay_result.action";
	}

	/**
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 * 
	 * @param sWord 要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
