package com.parkingms.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 车位信息表对应的实体Bean
 * @author 张怡
 *
 */
public class LandlordCarBean {
	/**
	 * 车位ID
	 */
	private int id;
	/**
	 * 车位产权证编号
	 */
	private String certificateNo;
	/**
	 * 车位所在小区位置
	 */
	private String location;
	/**
	 * 车位编号
	 */
	private String carNo;
	/**
	 * 车位产权证图片
	 */
	private String img;
	/**
	 * 车位状态
	 */
	private Integer status;
	/**
	 * 车位所有者：包租婆
	 */
	private UserBean landlord;
	/**
	 * 申请时间
	 * @return
	 */
	// 设置通过json返回Date日期的格式
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public UserBean getLandlord() {
		return landlord;
	}
	public void setLandlord(UserBean landlord) {
		this.landlord = landlord;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "LandlordCarBean [id=" + id + ", certificateNo=" + certificateNo + ", location=" + location + ", carNo="
				+ carNo + ", img=" + img + ", status=" + status + ", time=" + time + "]";
	}
}