package com.parkingms.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 出租订单表对应的实体Bean
 * @author 张怡
 *
 */
public class DealBean {
	/**
	 * 交易ID
	 */
	private int id;
	/**
	 * 交易编号
	 */
	private String no;
	/**
	 * 出租开始时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date beginTime;
	/**
	 * 出租结束时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date endTime;
	/**
	 * 费用
	 */
	private double price;
	/**
	 * 出租人：包租婆
	 */
	private UserBean landlord;
	/**
	 * 租借人：抢租客
	 */
	private UserBean tenement;
	/**
	 * 车位
	 */
	private LandlordCarBean landlordCar;
	/**
	 * 留言
	 */
	private String message;
	/**
	 * 预定时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date orderTime;
	/**
	 * 成交时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date doneTime;
	/**
	 * 订单状态
	 */
	private Integer status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public UserBean getLandlord() {
		return landlord;
	}
	public void setLandlord(UserBean landlord) {
		this.landlord = landlord;
	}
	public UserBean getTenement() {
		return tenement;
	}
	public void setTenement(UserBean tenement) {
		this.tenement = tenement;
	}
	public LandlordCarBean getLandlordCar() {
		return landlordCar;
	}
	public void setLandlordCar(LandlordCarBean landlordCar) {
		this.landlordCar = landlordCar;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getDoneTime() {
		return doneTime;
	}
	public void setDoneTime(Date doneTime) {
		this.doneTime = doneTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
