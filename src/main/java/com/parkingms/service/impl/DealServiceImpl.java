package com.parkingms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkingms.aop.ParkingmsLog;
import com.parkingms.bean.DealBean;
import com.parkingms.dao.IDealDao;
import com.parkingms.dao.ILandlordCarDao;
import com.parkingms.service.IDealService;
import com.parkingms.util.CreateDealNumberUtil;
/**
 * 业务层：处理与出租订单表有关的业务逻辑
 * @author 张怡
 *
 */
//该类为业务层
@Service
public class DealServiceImpl implements IDealService {
	// 自动注入IDealDao接口
	@Autowired
	private IDealDao dao;
	// 自动注入ILandlordCarDao接口
	@Autowired
	private ILandlordCarDao cdao;
	
	/**
	 * 新增出租信息订单
	 * 招租客预约车位
	 * 出租表：添加出租信息；车位表：修改车位状态为已预约
	 */
	@ParkingmsLog(module="个人车位业务", method="新增预约订单",plantform=0,type=1)
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public Map<String,Object> insertDeal(DealBean bean) {
		Map<String,Object> map = new HashMap<String, Object>();
		boolean result = false;
		// 获取当前时间
		Date date = new Date();
		// 产生交易单号
		bean.setNo(CreateDealNumberUtil.dealNumber(bean, date));
		// 设置交易时间
		bean.setOrderTime(date);
		// 往出租信息表添加数据，返回受影响的行数
		int row = dao.insert(bean);
		// 修改车位信息表车位状态为已预约，返回受影响的行数
		int crow = cdao.updateStatusReserved(bean.getLandlordCar());
		// 两张表都操作成功，返回true，否则回滚事务
		if(row > 0 && crow > 0){
			result = true;
			map.put("dealId", bean.getId());
		} else {
			// 两张表操作失败，抛出运行时异常，使事务回滚
			throw new RuntimeException("fail");
		}
		map.put("result", result);
		return map;
	}
	
	/**
	 * 包租婆处理出租信息订单
	 * 同意订单（出租表：添加成交时间，修改订单状态为已成交；车位表：修改车位状态为已租）
	 * 拒绝订单（出租表：修改订单状态为已失效；车位表：修改车位状态为待租）
	 */
	@ParkingmsLog(module="个人车位业务", method="处理预约订单",plantform=0,type=1)
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public Map<String,Object> dealwithDeal(DealBean bean) {
		Map<String,Object> map = new HashMap<String, Object>();
		String result = "fail";
		// 根据传入的订单ID查询订单信息
		DealBean deal = dao.get(bean);
		bean.setLandlordCar(deal.getLandlordCar());
		int crow = 0;
		int row = 0;
		// 包租婆同意预约
		if(bean.getStatus()==2){
			// 添加成交时间
			bean.setDoneTime(new Date());
			// 修改车位信息表车位状态为已出租，返回受影响的行数
			crow = cdao.updateStatusAgreed(bean.getLandlordCar());
			// 向出租信息表添加成交时间，修改订单状态，返回受影响的行数数
			row = dao.updateStatusAccepted(bean);
		} 
		// 包租婆不同意预约
		else if (bean.getStatus()==3){
			// 修改车位信息表车位状态为待出租，返回受影响的行数
			crow = cdao.updateStatusRefused(bean.getLandlordCar());
			// 查询招租客是否已经支付订单
			int status = deal.getStatus();
			// 若订单状态为已支付，则将订单ID和金额存入map
			if(status == 1){
				map.put("dealNo", deal.getNo());
				map.put("amount", deal.getPrice());
			}	
			// 向出租信息表修改订单状态，返回受影响的行数数
			row = dao.updateStatusRefused(bean);
		}
		// 两张表操作成功，返回true
		if(row > 0 && crow > 0){
			result = "success";
		} else {
			// 两张表操作失败，抛出运行时异常，使事务回滚
			throw new RuntimeException("fail");
		}
		map.put("result", result);
		return map;
	}
	/**
	 * 根据传入条件动态查询出租信息订单
	 * 若包租婆id有值，则根据包租婆id查找订单
	 * 若招租客id有值，则根据招租客id查找订单
	 * 若都没有传值，则查找所有订单
	 */
//	@Override
//	public List<DealBean> findDeal(DealBean bean) {
//		List<DealBean> list = dao.getList(bean);
//		return list;
//	}
	/**
	 * 根据传入条件动态查询出租信息订单,分页显示
	 * 若包租婆id有值，则根据包租婆id查找订单
	 * 若招租客id有值，则根据招租客id查找订单
	 * 若都没有传值，则查找所有订单
	 */
	@Override
	public Map<String,Object> findDealByPage(int curr, int limit, DealBean bean) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<DealBean> list = dao.getListByPage((curr-1)*limit,limit,bean);
		int total = dao.count(bean);
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	/**
	 * 根据传入条件动态统计总的订单数量
	 * 若包租婆id有值,则统计指定包租婆的订单数
	 * 若招租客id有值,则统计指定招租客的订单数
	 */
	@Override
	public int countDeal(DealBean bean) {
		return dao.count(bean);
	}
	/**
	 * 根据传入条件动态查询预约信息（即状态为0和1的订单信息），分页显示
	 */
	@Override
	public Map<String,Object> findBookingByPage(int curr, int limit, DealBean bean) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<DealBean> list = dao.getListBookingByPage((curr-1)*limit,limit,bean);
		int total = dao.countBooking(bean);
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	/**
	 * 根据传入条件动态统计总的预约数量（即状态为0和1的订单信息）
	 */
	@Override
	public int countBooking(DealBean bean) {
		return dao.countBooking(bean);
	}

}
