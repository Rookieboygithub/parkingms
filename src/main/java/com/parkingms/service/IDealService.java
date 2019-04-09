package com.parkingms.service;

import java.util.List;
import java.util.Map;

import com.parkingms.bean.DealBean;

/**
 * 业务层接口：处理与出租订单表有关的业务逻辑
 * @author 张怡
 *
 */
public interface IDealService {
	/**
	 * 新增出租信息订单
	 * 招租客预约车位
	 * @param bean
	 * @return
	 */
	public Map<String,Object> insertDeal(DealBean bean);
	/**
	 * 处理出租信息订单
	 * 包租婆确认租出预约车位
	 * 包租婆取消租出预约车位
	 * @param bean
	 * @return
	 */
	public Map<String,Object> dealwithDeal(DealBean bean);
	/**
	 * 根据传入条件动态查询出租信息订单
	 * @param bean
	 * @return
	 */
//	public List<DealBean> findDeal(DealBean bean);
	/**
	 * 根据传入条件动态查询出租信息订单,分页显示
	 * @param bean
	 * @return
	 */
	public Map<String,Object> findDealByPage(int curr, int limit, DealBean bean);
	/**
	 * 根据传入条件动态统计总的订单数量
	 * @param bean
	 * @return
	 */
	public int countDeal(DealBean bean);
	/**
	 * 根据传入条件动态查询预约信息（即状态为1和2的订单信息），分页显示
	 * @param curr
	 * @param limit
	 * @param bean
	 * @return
	 */
	public Map<String,Object> findBookingByPage(int curr, int limit, DealBean bean);
	/**
	 * 根据传入条件动态统计总的预约数量（即状态为1和2的订单信息）
	 * @param bean
	 * @return
	 */
	public int countBooking(DealBean bean);
	 
}
