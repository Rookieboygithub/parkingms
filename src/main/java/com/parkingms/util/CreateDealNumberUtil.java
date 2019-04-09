package com.parkingms.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.parkingms.bean.DealBean;
/**
 * 生成交易编号
 * @author 张怡
 *
 */
public class CreateDealNumberUtil {
	public static String dealNumber(DealBean bean,Date date){
		// 设置交易日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss"); 
		String dealNumber= df.format(date)+String.format("%02d",bean.getLandlordCar().getId())+
				String.format("%02d",bean.getLandlord().getId())+String.format("%02d",bean.getTenement().getId())+
				String.format("%02d",(int)(Math.random()*100));
		return dealNumber;
	}
}
