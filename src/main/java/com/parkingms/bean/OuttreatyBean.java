package com.parkingms.bean;


import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.SerializableString;


/**
 * 此实体Bean用于外部合约的数据持久化
 * @author 徐浩力
 *
 */
public class OuttreatyBean  implements Serializable{
	//序列化ID
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//设置企业资料私有属性
	//主键id
	private int id;
	//原合同编号
	private String formerno;
	//新合同编号
	private String newno;
	//合同单位
	private String company;
	//联系人
	private String contact;
	//电话
	private String tel;
	//单位地址
	private String addr;
	//合同开始时间
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date begintime;
	//合同结束时间
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endtime;
	//合同原件路径
	private String img;
	//车位编号
	private String carNum;
	
	
	
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFormerno() {
		return formerno;
	}
	public void setFormerno(String formerno) {
		this.formerno = formerno;
	}
	public String getNewno() {
		return newno;
	}
	public void setNewno(String newno) {
		this.newno = newno;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "OuttreatyBean [id=" + id + ", formerno=" + formerno + ", newno=" + newno + ", company=" + company
				+ ", contact=" + contact + ", tel=" + tel + ", addr=" + addr + ", begintime=" + begintime + ", endtime="
				+ endtime + ", img=" + img + ", carNum=" + carNum + "]";
	}

	
	
	
	
	

	}


	
	
	
	
	

	
	



	

