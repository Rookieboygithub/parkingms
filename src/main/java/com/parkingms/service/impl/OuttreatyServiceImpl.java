package com.parkingms.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingms.bean.CbdcarBean;
import com.parkingms.bean.OuttreatyBean;
import com.parkingms.dao.IOuttreatyDao;
import com.parkingms.service.IOuttreatyService;

@Service
public class OuttreatyServiceImpl implements IOuttreatyService {
	@Autowired
	private IOuttreatyDao dao;

	@Override
	public boolean checkCompany(OuttreatyBean bean,CbdcarBean cbean) {
		int row = dao.insert(bean);
		if (row > 0){
			//取出插入数据返回的主键ID
			int cbdId = bean.getId();
			//取出车位编号
			String carNum = bean.getCarNum();
			//取出地址
			String addr = bean.getAddr();
			//调用CBDBean存入数据
			cbean.setId(cbdId);
			cbean.setNo(carNum);
			cbean.setAddr(addr);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<OuttreatyBean> showAll() {
		List<OuttreatyBean> list = null ;
		list = dao.select();
		return list;
	}

	@Override
	public boolean changeMeans(OuttreatyBean bean) {
		int row=dao.update(bean);
		if(row>=1)
		return true;
		else{
			return false;
		}
	}

	@Override
	public boolean delThis(Integer id) {
		int row = dao.delete(id);
		if(row>=1)
			return true;
			else{
				return false;
			}
	}

}
