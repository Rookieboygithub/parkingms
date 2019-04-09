package com.parkingms.service;

/**
 * @author LC
 */
public interface IMessageCodeService {
	/**
	 * 根据tel查询user表和company表看是否有数据
	 * @param Tel
	 * @return
	 */
	public String findByTel(String Tel,String code);
	public String findUserNamebyTel(String Tel);
}
