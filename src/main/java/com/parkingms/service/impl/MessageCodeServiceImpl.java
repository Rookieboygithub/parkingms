package com.parkingms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.parkingms.dao.ICompanyDao;
import com.parkingms.dao.IUserDao;
import com.parkingms.service.IMessageCodeService;
import com.parkingms.util.SmsDemo;
/**
 * 
 * @author LC
 *
 */
@Service
public class MessageCodeServiceImpl implements IMessageCodeService {
	@Autowired
	private IUserDao udao;
	@Autowired
	private ICompanyDao cdao;
	
	@Override
	public String findByTel(String tel,String code) {
		String flag1 = udao.getByTel(tel);
		if(flag1 != null){
			try {
				SendSmsResponse sendSms = SmsDemo.sendSms(tel,code);
				if(sendSms.getCode() != null && sendSms.getCode().equals("OK")){
					return "发送成功!";
				}else{
					return "短信发送失败!";
				}
			} catch (ClientException e) {
				return "短信发送异常!";
			}
		}
		String flag2 = cdao.getByTel(tel);
		if(flag2 != null){
			try {
				SendSmsResponse sendSms = SmsDemo.sendSms(tel,code);
				if(sendSms.getCode() != null && sendSms.getCode().equals("OK")){
					return "发送成功!";
				}else{
					return "短信发送失败!";
				}
			} catch (ClientException e) {
				return "短信发送异常!";
			}
		}else{
			return "该手机号还没有注册!";
		}
	}

	@Override
	public String findUserNamebyTel(String Tel) {
		// TODO Auto-generated method stub
		String flag1 = udao.getByTel(Tel);
		if(flag1!=null){
			return flag1;
		}
		else{
			String flag2 = cdao.getByTel(Tel);
			return flag2;
		}	
	}

}
