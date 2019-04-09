package com.parkingms.bean;

import java.util.List;

/**
 * 企业表对应的实体bean
 * @author BHH
 *
 */
public class CompanyBean {
	/**
	 * 企业id
	 */
	private int id;
	/**
	 * 企业登录名
	 */
	private String account;
	/**
	 * 企业登录密码
	 */
	private String pwd;
	/**
	 * 企业名
	 */
	private String name;
	/*
	 * 企业地址
	 */
	private String addr;
	/**
	 * 企业联系人
	 */
	private String contact;
	/**
	 * 联系电话
	 */
	private String tel;
	/**
	 * 角色
	 */
	private int character;
	/**
	 * 企业与平台合同
	 */
	private List<CompanytreatyBean> treaty;

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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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
	public int getCharacter() {
		return character;
	}

	public void setCharacter(int character) {
		this.character = character;
	}

	public List<CompanytreatyBean> getTreaty() {
		return treaty;
	}

	public void setTreaty(List<CompanytreatyBean> treaty) {
		this.treaty = treaty;
	}

	@Override
	public String toString() {
		return "CompanyBean [id=" + id + ", account=" + account + ", pwd=" + pwd + ", name=" + name + ", addr=" + addr
				+ ", contact=" + contact + ", tel=" + tel + ", character=" + character + ", treaty="
				+ treaty + "]";
	}
}
