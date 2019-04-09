package com.parkingms.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 李同遥
 * @author lLty
 *
 */
public class CbdcarBean {
	private int id;
	//单个车位编号CF20
	private String no;
	//车位地址多个车位cf40-60;单个车位cf20支持1-9999
	private String addr;
	//车位出租状态(0待租状态,1已出租状态)
	private int status;
	//外部合约表，与车位一对多
	private OuttreatyBean outtreaty;
	//租户合约表，与车位一对多
	private CompanytreatyBean companytreaty;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date maxtime;
	
	public Date getMaxtime() {
		return maxtime;
	}
	public void setMaxtime(Date maxtime) {
		this.maxtime = maxtime;
	}
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public OuttreatyBean getOuttreaty() {
		return outtreaty;
	}
	public void setOuttreaty(OuttreatyBean outtreaty) {
		this.outtreaty = outtreaty;
	}
	public CompanytreatyBean getCompanytreaty() {
		return companytreaty;
	}
	public void setCompanytreaty(CompanytreatyBean companytreaty) {
		this.companytreaty = companytreaty;
	}
	@Override
	public String toString() {
		return "CbdcarBean [id=" + id + ", no=" + no + ", addr=" + addr + ", status=" + status + ", outtreaty="
				+ outtreaty + ", companytreaty=" + companytreaty + "]";
	}


	
	
	
}
