package com.parkingms.bean;

public class CompanyMessageBean {
//	企业用户主键
private Integer cid;
//	企业用户名称
private String cname;
//	企业用户地址
private String caddress;
//	企业用户联系人
private String cperson;
//	企业用户联系电话
private String ctel;
//	企业用户登录账户
private String caccount;
public String getCaccount() {
	return caccount;
}
public void setCaccount(String caccount) {
	this.caccount = caccount;
}
@Override
public String toString() {
	return "CompanyMessageBean [cid=" + cid + ", cname=" + cname + ", caddress=" + caddress + ", cperson=" + cperson
			+ ", ctel=" + ctel + "]";
}
public Integer getCid() {
	return cid;
}
public void setCid(Integer cid) {
	this.cid = cid;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
public String getCaddress() {
	return caddress;
}
public void setCaddress(String caddress) {
	this.caddress = caddress;
}
public String getCperson() {
	return cperson;
}
public void setCperson(String cperson) {
	this.cperson = cperson;
}
public String getCtel() {
	return ctel;
}
public void setCtel(String ctel) {
	this.ctel = ctel;
}
}
