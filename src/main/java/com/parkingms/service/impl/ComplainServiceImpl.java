package com.parkingms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingms.bean.ComplainBean;
import com.parkingms.bean.ComplainMessageBean;
import com.parkingms.dao.IComplainDao;
import com.parkingms.service.IComplainService;
import com.parkingms.util.CreateComplainNumberUtil;

@Service("IComplainService")
public class ComplainServiceImpl implements IComplainService {
	@Autowired
	private IComplainDao iDao;
	@Override
	public List<ComplainBean> showAllComplain() {
		List<ComplainBean> beans = iDao.showAllComplain();
		return beans;
	}

	@Override
	public List<ComplainMessageBean> showOneComplain(ComplainBean bean) {
		List<ComplainMessageBean> bean1 = iDao.showOneComplain(bean);
		return bean1;
	}

	@Override
	public String dealComplain(ComplainBean bean) {
		String result = "";
		if (iDao.dealComplain(bean)!=0) {
			result="处理成功";
		}
		return result;
	}

	@Override
	public String GoComplaint(ComplainBean bean) {
		String result= "fail";
		Date date = new Date();
		bean.setDate(date);
		bean.setNo(CreateComplainNumberUtil.dealNumber(bean, date));
		System.out.println(bean.getNo());
		if(iDao.insertComplain(bean)!=0){;
			result = "success";
		}
		return result;
		
	}

}
