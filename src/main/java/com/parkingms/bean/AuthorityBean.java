package com.parkingms.bean;

public class AuthorityBean {
	private String uri;
	private String role;
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "AuthorityBean [uri=" + uri + ", role=" + role + "]";
	}
	
	
	
}
