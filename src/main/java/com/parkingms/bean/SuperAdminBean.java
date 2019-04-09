package com.parkingms.bean;
/**
 * 
 * 超级管理员bean
 * @author Administrator
 *
 */
public class SuperAdminBean {
	private Integer superadminId;
	private Integer superadminLId;
	
	public Integer getSuperadminId() {
		return superadminId;
	}
	public void setSuperadminId(Integer superadminId) {
		this.superadminId = superadminId;
	}
	public Integer getSuperadminLId() {
		return superadminLId;
	}
	public void setSuperadminLId(Integer superadminLId) {
		this.superadminLId = superadminLId;
	}
	@Override
	public String toString() {
		return "SuperAdminBean [superadminId=" + superadminId + ", superadminLId=" + superadminLId + "]";
	}
}
