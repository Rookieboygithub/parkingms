package com.parkingms.service;

import org.apache.ibatis.annotations.Select;

import com.parkingms.bean.LoginBean;

/**
 * 登录接口，判断登录是否成功
 * @author 杨吉竹
 *
 */
public interface ILoginService {

	public LoginBean loginJudge(LoginBean bean);
	public LoginBean getUser(Integer id);
}
