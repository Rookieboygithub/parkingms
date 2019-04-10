package com.parkingms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkingms.bean.JoinBean;
import com.parkingms.dao.IRegisterDao;
import com.parkingms.service.IRegisterService;
import com.parkingms.util.MD5_Encoding;
/**
 * 注册信息的操作，操作t_login和t_user两张表，开启事务
 * @author 杨吉竹
 *
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class RegisterServiceImpl implements IRegisterService {
	@Autowired
	private IRegisterDao dao;

	public IRegisterDao getDao() {
		return dao;
	}

	public void setDao(IRegisterDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean insert(JoinBean bean) {
		/*
		 * 对密码进行加密后存入数据库中
		 */
		String pwd=bean.getPwd();
		String Ipwd=MD5_Encoding.lowerMD5(pwd);
		bean.setPwd(Ipwd);
		dao.InsertLoginInfo(bean);
		int jud = dao.InsertRegisterInfo(bean);
		if (jud > 0) {
			return true;
		}
		return false;
	}
/**
 * 判断是否有重复的账号
 */
	@Override
	public boolean isRepeat(JoinBean bean) {
	Integer rs=	dao.judgeRepeat(bean.getAccount());
	if(rs==null){
		
		return false;
	}
		return true;
	}

}
