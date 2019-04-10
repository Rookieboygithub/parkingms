package com.parkingms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkingms.aop.ParkingmsLog;
import com.parkingms.bean.UserBean;
import com.parkingms.dao.IUserModifyDao;
import com.parkingms.service.IUserModfiyService;
import com.parkingms.util.LoginEncoding5;

/**
 * 
 * 实现修改个人信息的业务层实现类 开启申明式事务
 * 加入日志功能
 * @author 杨吉竹
 *
 */

@Service
//设置事务的隔离级别，和传播方式
@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
public class UserModifyServiceImpl implements IUserModfiyService {
	@Autowired
	private IUserModifyDao dao;

	@ParkingmsLog(module="个人信息", method="修改密码",plantform=0,type=1)
	@Override
	public boolean updateUserPwd(UserBean user) {
	int i=	dao.modifyPwd(user);
	if(i>=1){
		return true;
	}
	
		return false;
	}
	@ParkingmsLog(module="个人信息", method="修改信息",plantform=0,type=1)
	@Override
	public boolean updateUserMessage(UserBean user) {
		dao.modifyMessage(user);
		return true;
	}

	@Override
	public boolean verifyPwd(UserBean user) {
	boolean res=false;
	String pwd=LoginEncoding5.upperMD5(user.getPwd());
	String vpwd=dao.selectPwd(user);
	
	if(vpwd!=null){
		if(vpwd.equalsIgnoreCase(pwd)){
			res=true;
			}
		}
		return res;
	}

	@Override
	public UserBean findUser(String account) {
		UserBean user=dao.findUser(account);
		return user;
	}

}
