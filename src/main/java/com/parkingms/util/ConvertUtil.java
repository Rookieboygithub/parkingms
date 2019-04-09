package com.parkingms.util;

import java.util.ArrayList;
import java.util.List;

import com.parkingms.bean.CbdcarBean;
/**
 * 
 * @author lLty
 *车位批量添加时格式转换
 */
public class ConvertUtil {
	public  static List<CbdcarBean> conv(CbdcarBean bean){
		// [a-zA-Z]{0,2}\d{0,4}[-]\d{0,4}车位编号格式验证
		String p = "[a-zA-Z][a-zA-Z][0-9]{1,4}[-][0-9]{1,4}";
		//单个车位格式验证
		List<CbdcarBean> list = new ArrayList<CbdcarBean>();
		String single = "[a-zA-Z]{0,2}[0-9]{1,4}";
		String number = bean.getNo();
		String addr = bean.getAddr();
		int m = number.indexOf("-");
		if(m==-1&&number.matches(single)){
			 list.add(bean);
			 return list;
		}
		if (number.matches(p)&&m!=2&&m!=number.length()&&bean.getAddr()!= null) {
			int s = Integer.valueOf(number.substring(2, m));
			int e = Integer.valueOf(number.substring(m + 1, number.length()));
			String na = number.substring(0, 2);
			int start = 0;
			int end = 0;
			if (s < e || s == e) {
				start = s;
				end = e;
			} else {
				start = e;
				end = s;
			}
			for (; start <= end; start++) {
				CbdcarBean carbean = new CbdcarBean();
				carbean.setOuttreaty(bean.getOuttreaty());
				carbean.setCompanytreaty(bean.getCompanytreaty());
				carbean.setStatus(bean.getStatus());
				carbean.setAddr(addr);
				carbean.setNo(na + start);
				list.add(carbean);
			}
		}
		else{
			list=null;
		}
		return list;
	}
}
