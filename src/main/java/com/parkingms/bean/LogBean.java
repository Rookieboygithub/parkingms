package com.parkingms.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 日志表对应的实体Bean
 * @author 张怡
 *
 */
public class LogBean {
	/**
	 * 日志id，主键
	 */
	private int id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 操作时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date time;
	/**
	 * 操作ip
	 */
	private String ip;
	/**
	 * 操作模块
	 */
	private String module;
	/**
	 * 操作方法
	 */
	private String method;
	/**
	 * 响应时间
	 */
	private int responseTime;
	/**
	 * 标记前台日志还是后台日志：0前台，1后台
	 */
	private Integer plantformType;
	/**
	 * 标记是登录日志还是操作日志：0登录，1操作
	 */
	private Integer type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(int responseTime) {
		this.responseTime = responseTime;
	}
	public Integer getPlantformType() {
		return plantformType;
	}
	public void setPlantformType(Integer plantformType) {
		this.plantformType = plantformType;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "LogBean [id=" + id + ", username=" + username + ", time=" + time + ", ip=" + ip + ", module=" + module
				+ ", method=" + method + ", responseTime=" + responseTime + ", plantformType=" + plantformType
				+ ", type=" + type + "]";
	}
	
	
	
}
