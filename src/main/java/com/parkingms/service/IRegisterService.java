package com.parkingms.service;

import com.parkingms.bean.JoinBean;

/**
 * 插入数据注册数据，判断注册账户是否重复
 * 
 * @author 杨吉竹
 *
 */
public interface IRegisterService {
	public boolean insert(JoinBean loginInfo);
 
  public boolean isRepeat(JoinBean loginInfo);
}
