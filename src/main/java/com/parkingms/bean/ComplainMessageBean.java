package com.parkingms.bean;

import java.util.Date;

public class ComplainMessageBean {
		//id
		private int id;
		//车位地址
		private String location;
		//车位编号
		private String carNumber;
		//出租时间
		private Date begintime;
		//结束时间
		private Date endtime;
		//投诉人账号
		private String alias;
		//投诉人姓名
		private String name;
		//投诉人身份证
		private String cardno;
		//投诉人职业
		private String career;
		//投诉人号码
		private String tel;
		//投诉理由
		private String reason;
		//被投诉人账号
		private String byalias;
		//被投诉人姓名
		private String byname;
		//被投诉人身份证
		private String bycardno;
		//被投诉人职业
		private String bycareer;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getCarNumber() {
			return carNumber;
		}
		public void setCarNumber(String carNumber) {
			this.carNumber = carNumber;
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
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCardno() {
			return cardno;
		}
		public void setCardno(String cardno) {
			this.cardno = cardno;
		}
		public String getCareer() {
			return career;
		}
		public void setCareer(String career) {
			this.career = career;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public String getByalias() {
			return byalias;
		}
		public void setByalias(String byalias) {
			this.byalias = byalias;
		}
		public String getByname() {
			return byname;
		}
		public void setByname(String byname) {
			this.byname = byname;
		}
		public String getBycardno() {
			return bycardno;
		}
		public void setBycardno(String bycardno) {
			this.bycardno = bycardno;
		}
		public String getBycareer() {
			return bycareer;
		}
		public void setBycareer(String bycareer) {
			this.bycareer = bycareer;
		}
		public String getBytel() {
			return bytel;
		}
		public void setBytel(String bytel) {
			this.bytel = bytel;
		}
		//被投诉人号码
		private String bytel;
		@Override
		public String toString() {
			return "ComplainMessageBean [id=" + id + ", location=" + location
					+ ", carNumber=" + carNumber + ", begintime=" + begintime
					+ ", endtime=" + endtime + ", alias=" + alias + ", name="
					+ name + ", cardno=" + cardno + ", career=" + career
					+ ", tel=" + tel + ", reason=" + reason + ", byalias="
					+ byalias + ", byname=" + byname + ", bycardno=" + bycardno
					+ ", bycareer=" + bycareer + ", bytel=" + bytel + "]";
		}
		
}
