package com.parkingms.bean;

import java.util.Date;
/**
 * 支付信息表
 * @author 王青杰
 *
 */
public class PayinfoBean {
	
	private int id;
	private Date date;  // 支付时间
	private int status;  // 支付状态，1：已支付，2：未支付
	private int orderId;  // 出租信息表 id

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "PayBean [id=" + id + ", date=" + date + ", status=" + status + ", orderId=" + orderId + "]";
	}

}
