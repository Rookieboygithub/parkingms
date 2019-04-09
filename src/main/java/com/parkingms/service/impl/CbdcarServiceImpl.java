package com.parkingms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkingms.bean.CbdcarBean;
import com.parkingms.dao.ICbdcarDao;
import com.parkingms.service.ICbdcarService;
import com.parkingms.util.ConvertUtil;

/**
 * 
 * @author lLty 李同遥
 *         关于车位操作(添加,更新,软删除)都需传入一个CbdcarBean,若需用批量的话车位编号格式应为CF40-60,或者CF60-40
 *         批量支持一次添加(1-9999)，单个车位编号格式为CF50;
 */

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class CbdcarServiceImpl implements ICbdcarService {
	@Autowired
	private ICbdcarDao dao;

	@Override
	/**
	 * 添加车位
	 */
	public String insertCbdcar(CbdcarBean bean) {
		String result = "添加成功";
		List<CbdcarBean> list = ConvertUtil.conv(bean);
		if (list != null) {
			try {
				 dao.insertCbdcar(list);
			} catch (Exception e) {
				// TODO: handle exception
				return "部分车位已存在";
			}
		
			
		} else {
			result = "数据格式错误";
		}
		return result;
	}

	
	/**
	 * 查询所有车位
	 */
	@Override
	public List<CbdcarBean> showCbdcar() {
		
		List<CbdcarBean> list = dao.showCbdcar();
		return list;
	}

	/**
	 * 更新车位状态，包括出租状态，外部合约
	 */
	@Override
	public String updateCbdcar(CbdcarBean bean) {
		String result = "更新成功";
		List<CbdcarBean> list = ConvertUtil.conv(bean);
		if (list != null) {
			int a = dao.updateCbdcar(list);
			if (a == 0) {
				result = "更新失败";
			}
		} else {
			result = "数据格式错误";
		}
		return result;
	}

	@Override
	/**
	 * 软删除
	 */
	public String updateFlag(CbdcarBean bean) {
		String result = "更新成功";
		List<CbdcarBean> list = ConvertUtil.conv(bean);
		if (list != null) {
			int a = dao.updateFlag(list);
			if (a == 0) {
				result = "更新失败";
			}
		} else {
			result = "数据格式错误";
		}
		return result;
	}

	@Override
	public List<CbdcarBean> showCbdcarByStatus(int page, int limit, int status) {
		// 从第 几 条开始
		int start = (page - 1) * limit;
		return dao.findCbdcarByStatus(start, limit, status);
	}

	@Override
	public int getRecordCount() {

		return dao.findTotalRecords();
	}

	@Override
	public List<CbdcarBean> showCbdcarByPage(Integer page,Integer limit,CbdcarBean bean) {
		// TODO Auto-generated method stub
		List<CbdcarBean> list =dao.showCbdcarByPage(page,limit,bean);
		return list;
	}

	@Override
	public int findCbdcarNumber(CbdcarBean bean) {
		// TODO Auto-generated method stub
		int number=dao.findCbdcarNumber(bean);
		return number;
	}


	@Override
	public void deletecbdcar(int id) {
		// TODO Auto-generated method stub
		dao.deleteByFlag(id);
		
	}
	


	

}
