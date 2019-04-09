package com.parkingms.bean;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 租户合同实体Bean
 * @author LC
 *
 */
public class CompanytreatyBean {
	/**
	 * id
	 */
	private int id;
	/**
	 * 旧合同号
	 */
	private String formerno;
	/**
	 * 续约合同号
	 */
	private String newno;
	/**
	 * 公司名字
	 */
	private String companyname;
	/**
	 * 对方联系人
	 */
	private String contact;
	/**
	 * 对方联系电话
	 */
	private String tel;
	/**
	 * 合同生效时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date begintime;
	/**
	 * 合同结束时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endtime;
	/**
	 * 合同图片
	 */
	private String img;
	/**
	 * 图片文件
	 */
	private MultipartFile fimg;
	/**
	 * 平台车位实体List集合
	 */
	private List<CbdcarBean> cbdcarlist;
	/**
	 * 企业实体Bean
	 */
	private CompanyBean company;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFormerno() {
		return formerno;
	}
	public void setFormerno(String formerno) {
		this.formerno = formerno;
	}
	public String getNewno() {
		return newno;
	}
	public void setNewno(String newno) {
		this.newno = newno;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public CompanyBean getCompany() {
		return company;
	}
	public void setCompany(CompanyBean company) {
		this.company = company;
	}
	public List<CbdcarBean> getCbdcarlist() {
		return cbdcarlist;
	}
	public void setCbdcarlist(List<CbdcarBean> cbdcarlist) {
		this.cbdcarlist = cbdcarlist;
	}
	public MultipartFile getFimg() {
		return fimg;
	}
	public void setFimg(MultipartFile fimg) {
		this.fimg = fimg;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	@Override
	public String toString() {
		return "CompanytreatyBean [id=" + id + ", formerno=" + formerno + ", newno=" + newno + ", companyname="
				+ companyname + ", contact=" + contact + ", tel=" + tel + ", begintime=" + begintime + ", endtime="
				+ endtime + ", img=" + img + ", fimg=" + fimg + ", cbdcarlist=" + ", company=" + company
				+ "]";
	}
}
