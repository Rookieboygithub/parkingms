package com.parkingms.dao;

import org.apache.ibatis.annotations.Select;
/**
 * 
 * 选择身份id，判断身份
 * @author 杨吉竹
 *
 */
public interface ISelectIdentity {
	@Select("select login_character from t_login where login_account =#{account} AND flag = 0")
public Integer selecRole(String account);
}
