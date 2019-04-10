package com.parkingms.service;

import java.util.List;

import com.parkingms.bean.ComplainBean;
import com.parkingms.bean.ComplainMessageBean;

public interface IComplainService {
	
		//查询所有的投诉的大概信息	
		public List<ComplainBean> showAllComplain();
		
		//查询单个投诉记录的详细信息
		public List<ComplainMessageBean> showOneComplain(ComplainBean bean);
		
		//处理投诉>修改记录的状态
		public String dealComplain(ComplainBean bean);
		
		//发起投诉
		public String GoComplaint(ComplainBean bean);
}
