package com.parkingms.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.parkingms.bean.ComplainBean;

/**
 * 生成投诉编号
 * @author 张怡
 *
 */
public class CreateComplainNumberUtil {
	public static String dealNumber(ComplainBean bean,Date date){
		// 设置交易日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd"); 
		String complainNumber= "C"+df.format(date)+String.format("%02d",bean.getDeal().getId())+String.format("%02d",bean.getComplaint().getId())+String.format("%02d",bean.getDefendant().getId())+
				String.format("%02d",(int)(Math.random()*100));
		return complainNumber;
	}
}
