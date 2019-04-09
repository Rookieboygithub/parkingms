package com.parkingms.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 招租信息表对应的实体Bean
 * @author 张怡
 *
 */
public class LandlordLetBean {
	/**
	 * 招租信息id
	 */
	private int id;
	/**
	 * 租借开始时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
/*	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")*/
	private Date beginTime;
	/**
	 * 租借结束时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	/*@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")*/
	private Date endTime;
	/**
	 * 租借价格
	 */
	private Double price;
	/**
	 * 出租人：包租婆
	 */
	private UserBean landlord;
	/**
	 * 出租车位
	 */
	private LandlordCarBean landlordCar;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public UserBean getLandlord() {
		return landlord;
	}
	public void setLandlord(UserBean landlord) {
		this.landlord = landlord;
	}
	public LandlordCarBean getLandlordCar() {
		return landlordCar;
	}
	public void setLandlordCar(LandlordCarBean landlordCar) {
		this.landlordCar = landlordCar;
	}
	
	
//	@Override
//	public String toString() {
//		return "LetBean [id=" + id + ", beginTime=" + beginTime + ", endTime=" + endTime + ", price=" + price 
//				+"车位地址"+ landlordCar.getLocation()+",车位编号"+landlordCar.getCarNo()+"包租婆姓名"+
//				landlord.getName()+"包租婆职业"+landlord.getCareer()+"包租婆信誉度"+landlord.getCredit()+"]";
//	}
	
}
