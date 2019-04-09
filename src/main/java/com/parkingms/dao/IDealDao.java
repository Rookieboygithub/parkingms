package com.parkingms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.parkingms.bean.DealBean;

/**
 * 持久层：操作出租订单表
 * @author 张怡
 *
 */
public interface IDealDao {
	/**
	 * 新增出租订单
	 * 招租客预约车位
	 */
	@Insert("INSERT INTO t_deal(deal_no,deal_begintime,deal_endtime,deal_price,landlordcar_id,landlord_id,tenement_id,deal_message,deal_ordertime)"
			+ " VALUES(#{no},#{beginTime},#{endTime},#{price},#{landlordCar.id},#{landlord.id},#{tenement.id},#{message},#{orderTime}) ")
	@Options(useGeneratedKeys = true, keyProperty = "id",keyColumn = "deal_id")
	public int insert(DealBean bean);
	/**
	 * 处理出租订单
	 * 包租婆确认租出预约车位
	 * 包租婆取消租出预约车位
	 */
	public int update(DealBean bean);
	
	/**
	 * 租婆确认租出预约车位修改订单状态
	 * @param bean
	 * @return
	 */
	@Update("UPDATE t_deal SET deal_status = 2,deal_donetime = #{doneTime} WHERE deal_id=#{id} AND flag=0 AND deal_status = 1" )
	public int updateStatusAccepted(DealBean bean);
	
	/**
	 * 租婆拒绝租出预约车位修改订单状态
	 * @param bean
	 * @return
	 */
	@Update("UPDATE t_deal SET deal_status = 3 WHERE deal_id=#{id} AND flag=0 AND deal_status IN (0,1)" )
	public int updateStatusRefused(DealBean bean);
	
	/**
	 * 根据传入条件动态查询出租信息订单
	 * @return
	 */
//	public List<DealBean> getList(DealBean bean);
	/**
	 * 根据传入条件动态查询一条出租订单
	 * 根据id或者订单编号
	 */
	public DealBean get(DealBean bean);
	/**
	 * 根据传入条件动态查询出租信息订单,分页显示
	 * @param curr
	 * @param limit
	 * @param bean
	 * @return
	 */
	public List<DealBean> getListByPage(@Param(value="begin")int begin, @Param(value="limit")int limit, @Param(value="bean")DealBean bean);
	/**
	 * 根据传入条件动态统计订单数
	 * @param bean
	 * @return
	 */
	public int count(DealBean bean);
	/**
	 * 根据传入条件动态查询预约信息（即状态为0和1的订单），分页显示
	 * @param curr
	 * @param limit
	 * @param bean
	 * @return
	 */
	public List<DealBean> getListBookingByPage(@Param(value="begin")int begin, @Param(value="limit")int limit, @Param(value="bean")DealBean bean);
	/**
	 * 根据传入条件动态统计预约数（即状态为0和1的订单）
	 * @param bean
	 * @return
	 */
	public int countBooking(DealBean bean);
	/**
	 * 招租客支付订单，将订单状态由未支付变为已支付
	 * @param bean
	 * @return
	 */
	@Update("UPDATE t_deal SET deal_status = 1 WHERE deal_no = #{no} AND deal_status = 0 AND flag = 0")
	public int updateStatusPayed(DealBean bean);
	
}
