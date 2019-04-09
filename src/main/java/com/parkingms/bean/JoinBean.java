package com.parkingms.bean;
/**
 * 
 * 个人信息注册bean
 * @author 杨吉竹
 *
 */
public class JoinBean {
	private String pwd;//密码
	private String verifyPwd;//确认密码
    private String name;//真实姓名
    private String address;//地址
    private String phone;//电话
    private String cardno;//身份证号
    private String career;//职业生涯描述
    private String email;//邮件地址
    private Integer id;//登录的主键，信息的外键
    private String character;//角色代号
	private String account;//登录账户
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
	public String getVerifyPwd() {
		return verifyPwd;
	}
	public void setVerifyPwd(String verifyPwd) {
		this.verifyPwd = verifyPwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "JoinBean [pwd=" + pwd + ", verifyPwd=" + verifyPwd + ", name=" + name + ", address=" + address
				+ ", phone=" + phone + ", cardno=" + cardno + ", career=" + career + ", email=" + email + ", userLId="
				 + ", character=" + character + ", account=" + account + "]";
	}
}
