package com.parkingms.bean;
/**
 * 
 * 登录bean页面
 * @author Administrator
 *
 */
public class LoginBean {
	private Integer loginId;//登录id
	private String loginAccount;//登录账户
	private String loginPwd;//登录密码
	private String  alias;//登录名
    private String userId;//如果用户为个人，此为个人信息id
	private Integer loginCharacter;//登录角色
	private Integer LoginFlag;//软删除键
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public Integer getLoginCharacter() {
		return loginCharacter;
	}
	public void setLoginCharacter(Integer loginCharacter) {
		this.loginCharacter = loginCharacter;
	}
	public Integer getLoginFlag() {
		return LoginFlag;
	}
	public void setLoginFlag(Integer loginFlag) {
		LoginFlag = loginFlag;
	}
	@Override
	public String toString() {
		return "LoginBean [loginId=" + loginId + ", loginAccount=" + loginAccount + ", loginPwd=" + loginPwd
				+ ", loginCharacter=" + loginCharacter + ", LoginFlag=" + LoginFlag + "]";
	}

}
