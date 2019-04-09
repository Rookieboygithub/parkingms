package com.parkingms.service;



import java.util.List;

import com.parkingms.bean.CbdcarBean;
import com.parkingms.bean.OuttreatyBean;

public interface IOuttreatyService {
	/**
	 * 接收企业资料做出格式验证以及逻辑判断
	 */
	public boolean checkCompany(OuttreatyBean bean,CbdcarBean bean1);
	
	/**
	 * 显示企业合同信息
	 */
	public List<OuttreatyBean> showAll();
	
	/**
	 * 修改企业信息
	 * @param bean 
	 * @return
	 */
	public boolean changeMeans(OuttreatyBean bean);
	/**
	 * 删除合约
	 * @param id
	 * @return
	 */
	public boolean delThis(Integer id);
		
	
}
