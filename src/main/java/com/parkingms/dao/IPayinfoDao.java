package com.parkingms.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 维护支付信息DAO
 * @author 王青杰
 *
 */
public interface IPayinfoDao {
	
	/**
	 * 添加支付记录
	 * @param no 订单编号
	 */
	@Insert("INSERT INTO t_payinfo(payinfo_order_id) VALUES (#{dealId})")
	public int addPayinfo(int dealId);
	
	/**
	 * 将支付状态改变为已支付
	 * @param date 支付时间
	 */
	@Update("UPDATE t_payinfo SET payinfo_status = 1, payinfo_time = #{date} WHERE payinfo_order_id = #{dealId}")
	public int updatePaystatusPayed(@Param("dealId")int dealId, @Param("date")Date date);
	
	/**
	 * 将支付状态改变为已退款
	 * @param 订单号
	 */
	@Update("UPDATE t_payinfo SET payinfo_status = 2 WHERE payinfo_order_id = #{dealId}")
	public int updatePaystatusRefund(@Param("dealId")int dealId);
	
	/**
	 * 通过订单id查询支付信息数量
	 * @param dealId 订单id
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM t_payinfo WHERE payinfo_order_id = #{dealId}")
	public int findPayinfoCountByDealId(int dealId);
}
