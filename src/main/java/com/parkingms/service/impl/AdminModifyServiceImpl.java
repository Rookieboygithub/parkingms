package com.parkingms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkingms.aop.ParkingmsLog;
import com.parkingms.bean.AdminBean;
import com.parkingms.dao.IAdminModifyDao;
import com.parkingms.service.IAdminModifyService;
/**
 * 普通管理员个人信息展示及修改电话
 * 加入aop日志功能
 * @author 杨吉竹
 *
 */
@Service
//设置事务的隔离级别，和传播方式
@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
public class AdminModifyServiceImpl implements IAdminModifyService {
@Autowired
	private IAdminModifyDao dao;
	/*@Override
	public boolean updateAdminService(AdminMessageBean bean) {
	int a=	dao.updateAdminPwd(bean);
	int b=	dao.updateAdminTel(bean);
		System.out.println("执行了SQL语句a="+a+"b的值是"+b);
		return false;
	}*/
	@Override
	public AdminBean findadmin(String account) {
	 AdminBean admin=dao.selectAdmin(account);
		return admin;
	}
	@Override
	@ParkingmsLog(method="修改信息",module="管理员",plantform=1,type=1)
	public boolean updateTel(AdminBean bean) {
	Integer rs= dao.updateTel(bean);
	if(rs!=null){
		return true;
	}else{
		return false;	
	}
	}

}
