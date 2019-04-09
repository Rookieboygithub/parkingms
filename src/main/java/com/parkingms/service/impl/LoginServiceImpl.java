package com.parkingms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingms.bean.LoginBean;
import com.parkingms.dao.ILoginDao;
import com.parkingms.service.ILoginService;
import com.parkingms.util.LoginEncoding5;
/**
 * 登录的页面实现，判断是否登录成功
 * @author 杨吉竹
 *
 */
@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
	private ILoginDao dao;
	public ILoginDao getDao() {
		return dao;
	}
	public void setDao(ILoginDao dao) {
		this.dao = dao;
	}
	@Override
	public  LoginBean loginJudge(LoginBean bean) {
		String pwd=LoginEncoding5.upperMD5(bean.getLoginPwd());
		LoginBean mybean=dao.getBeanByAccount(bean.getLoginAccount(),pwd);
		
		return mybean;
	}
	@Override
	public LoginBean getUser(Integer id) {
	
		return dao.getUserMes(id);
	}
	
}
