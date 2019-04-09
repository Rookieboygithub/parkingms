package com.parkingms.bean;

import java.util.List;

/**
 * 用户信息表对应的实体Bean
 * @author 张怡
 *
 */
public class UserBean {
	/**
	 * 用户id（非登录ID）
	 */
	private int id;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 角色
	 */
	private int character;
	/**
	 * 密码
	 */
	private String pwd;
	/**
	 * 真实姓名
	 */
	private String name;
	/**
	 * 用户名（别名）
	 */
	private String alias;
	/**
	 * 家庭地址
	 */
	private String addr;
	/**
	 * 电话号码
	 */
	private String tel;
	/**
	 * 身份证号
	 */
	private String cardNo;
	/**
	 * 职业
	 */
	private String career;
	/**
	 * 邮箱地址
	 */
	private String email;
	/**
	 * 信誉度
	 */
	private double credit;
	/**
	 * 角色
	 */
	private int role;
	/**
	 * 登录Id
	 */
	private int loginId;
	/**
	 * 拥有车位(针对包租婆)
	 */
	private List<LandlordCarBean> list;
	
	public int getCharacter() {
		return character;
	}
	public void setCharacter(int character) {
		this.character = character;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	public List<LandlordCarBean> getList() {
		return list;
	}
	public void setList(List<LandlordCarBean> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "UserBean [account=" + account + ", character=" + character + ", pwd=" + pwd + ", loginId=" + loginId
				+ "]";
	}
	
}
