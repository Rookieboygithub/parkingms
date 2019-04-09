package com.parkingms.bean;
/**
 * 注册页面的信息bean
 * @author 杨吉竹
 *
 */
public class UserMessageBean {
//个人用户id
private int id;
//个人用户密码
private String pwd;
//个人用户登录账户
private String account;
//个人用户信息地址
private String address;
//个人用户电话
private String tel;
//个人用户职业生涯描述
private String career;
//个人用户邮箱
private String email;
//个人用户信息表的外键
private int login_id;

public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getCareer() {
	return career;
}
public void setCareer(String career) {
	this.career = career;
}
public int getLogin_id() {
	return login_id;
}
public void setLogin_id(int login_id) {
	this.login_id = login_id;
}
@Override
public String toString() {
	return "UserMessageBean [id=" + id + ", pwd=" + pwd + ", account=" + account + ", address=" + address + ", tel="
			+ tel + ", career=" + career + ", email=" + email + ", login_id=" + login_id + "]";
}


}
