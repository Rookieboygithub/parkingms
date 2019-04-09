package com.parkingms.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ComplainBean {
	// 主键id
	private int id;
	// 投诉编号
	private String no;
	// 订单对象
	private DealBean deal;
	// 投诉人
	private UserBean complaint;
	// 被投诉人
	private UserBean defendant;
	// 投诉理由
	private String reasons;
	// 投诉时间
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date date;
	// 投诉状态
	private int status;
	// 处理理由
	private String dealreason;

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

	public DealBean getDeal() {
		return deal;
	}

	public void setDeal(DealBean deal) {
		this.deal = deal;
	}

	public UserBean getComplaint() {
		return complaint;
	}

	public void setComplaint(UserBean complaint) {
		this.complaint = complaint;
	}

	public UserBean getDefendant() {
		return defendant;
	}

	public void setDefendant(UserBean defendant) {
		this.defendant = defendant;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
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

	public String getDealreason() {
		return dealreason;
	}

	public void setDealreason(String dealreason) {
		this.dealreason = dealreason;
	}

	@Override
	public String toString() {
		return "ComplainBean [id=" + id + ", no=" + no + ", deal=" + deal + ", complaint=" + complaint + ", defendant="
				+ defendant + ", reasons=" + reasons + ", date=" + date + ", status=" + status + ", dealreason="
				+ dealreason + "]";
	}

}
