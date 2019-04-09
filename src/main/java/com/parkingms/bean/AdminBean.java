package com.parkingms.bean;


/**
 * 
 * @author 杨向东
 *
 */
public class AdminBean {
	
	private int id;//主键--我的其他业务没用到
	private String account;//工号-------改成account
	private String name;//姓名------改成name
	private String pwd;//密码
	private String tel;//电话
	private int authorityUser;//用户管理权限--默认为0,0为无权限，1有权限
	private int authorityPact;//合约管理权限--默认为0,0为无权限，1有权限
	private int authorityComplain;//投诉管理权限--默认为0,0为无权限，1有权限
	private int loginid;//登陆的外键
	private int authorityCar;//车位管理权限--默认为0,0为无权限，1有权限
	private int character;//登录身份----0:超管;1:管理员;2:包租婆;3:散户租客;4:企业租客
	
	
/*	private int id;//主键--我的其他业务没用到
	private String name;//真实姓名
	private String tel;//电话
	private int authorityUser;//用户管理权限--默认为0,0为无权限，1有权限
	private int authorityCar;//车位管理权限--默认为0,0为无权限，1有权限
	private int authorityPact;//合约管理权限--默认为0,0为无权限，1有权限
	private int authorityComplain;//投诉管理权限--默认为0,0为无权限，1有权限
	private int loginid;//登陆的外键--不直接获取，在登录表插入时，映射数据
	
	private String account;//登录账号
	private String pwd;//登录密码
	private int character;*///登录身份----0:超管;1:管理员;2:包租婆;3:散户租客;4:企业租客
	
	
	public int getId() {
		return id;
	}
	public int getAuthorityCar() {
		return authorityCar;
	}
	public void setAuthorityCar(int authorityCar) {
		this.authorityCar = authorityCar;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getAuthorityUser() {
		return authorityUser;
	}
	public void setAuthorityUser(int authorityUser) {
		this.authorityUser = authorityUser;
	}
	public int getAuthorityPact() {
		return authorityPact;
	}
	public void setAuthorityPact(int authorityPact) {
		this.authorityPact = authorityPact;
	}
	public int getAuthorityComplain() {
		return authorityComplain;
	}
	public void setAuthorityComplain(int authorityComplain) {
		this.authorityComplain = authorityComplain;
	}
	public int getLoginid() {
		return loginid;
	}
	public void setLoginid(int loginid) {
		this.loginid = loginid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getCharacter() {
		return character;
	}
	public void setCharacter(int character) {
		this.character = character;
	}
	@Override
	public String toString() {
		return "AdminBean [id=" + id + ", account=" + account + ", name=" + name + ", pwd=" + pwd + ", tel=" + tel
				+ ", authorityUser=" + authorityUser + ", authorityPact=" + authorityPact + ", authorityComplain="
				+ authorityComplain + ", loginid=" + loginid + ", authorityCar=" + authorityCar + ", character="
				+ character + "]";
	}
	
}

//package com.parkingms.bean;
///**
// * 
// * @author Xtacy
// * 对应t_admin表                         对应的数据库里的
// * id:主键                           -->admin_id
// * name:姓名                      -->admin_name
// * phone:电话                    -->admin_phone
// * authority :管理权限-->admin_authority
// * loginId:登录编号       -->admin_l_id
// */
//public class AdminBean {
//	private Integer id;
//	private String name;
//    private String phone;
//    private String authority;
//    private Integer loginId;
//    public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getPhone() {
//		return phone;
//	}
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//	public String getAuthority() {
//		return authority;
//	}
//	public void setAuthority(String authority) {
//		this.authority = authority;
//	}
//	public Integer getLoginId() {
//		return loginId;
//	}
//	public void setLoginId(Integer loginId) {
//		this.loginId = loginId;
//	}
//	@Override
//	public String toString() {
//		return "AdminBean [id=" + id + ", name=" + name + ", phone=" + phone + ", authority=" + authority + ", loginId="
//				+ loginId + "]";
//	}
//}
