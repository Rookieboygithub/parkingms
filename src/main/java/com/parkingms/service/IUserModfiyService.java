package com.parkingms.service;

import com.parkingms.bean.UserBean;

/**
 * 
 * 设计修改个人用户信息的service层接口
 * 
 * @author 杨吉竹
 *
 */



public interface IUserModfiyService {
	//验证密码
	public boolean  verifyPwd(UserBean user);
	
public boolean updateUserPwd(UserBean user); 	

public boolean updateUserMessage(UserBean user); 	
public UserBean findUser(String account);
	
}
