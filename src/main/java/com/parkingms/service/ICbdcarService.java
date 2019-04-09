package com.parkingms.service;



import java.util.List;

import com.parkingms.bean.CbdcarBean;
/**
 * 
 * @author lLty
 *
 */
public interface ICbdcarService {
	public String insertCbdcar(CbdcarBean bean);
	public List<CbdcarBean> showCbdcar();
	public String updateCbdcar(CbdcarBean bean);
	public String updateFlag(CbdcarBean bean);
	/**
	 * 根据状态展示CBD平台车位信息
	 * @param currpage
	 * @param count
	 * @param status 1：已出租，0；未出租
	 * @return
	 */
	public List<CbdcarBean> showCbdcarByStatus(int currpage, int count, int status);

	/**
	 * 为租赁车位记录条数
	 * @return
	 */
	public int getRecordCount();
	

	public List<CbdcarBean> showCbdcarByPage(Integer page,Integer limit ,CbdcarBean bean);
	public int findCbdcarNumber(CbdcarBean bean);
	public void deletecbdcar(int id);
}
