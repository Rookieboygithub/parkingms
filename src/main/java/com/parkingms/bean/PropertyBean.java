package com.parkingms.bean;
/**
 * 性能表对应的实体Bean
 * @author 张怡
 *
 */
public class PropertyBean {
	/**
	 * 性能id,主键
	 */
	private int id;
	/**
	 * 请求URI
	 */
	private String uri;
	/**
	 * 统计次数
	 */
	private long count;
	/**
	 * 总响应时间
	 */
	private long totalTime;
	/**
	 * 平均响应时间
	 */
	private int averageTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}
	public int getAverageTime() {
		return averageTime;
	}
	public void setAverageTime(int averageTime) {
		this.averageTime = averageTime;
	}
	@Override
	public String toString() {
		return "PropertyBean [id=" + id + ", uri=" + uri + ", count=" + count + ", totalTime=" + totalTime
				+ ", averageTime=" + averageTime + "]";
	}
	
}
