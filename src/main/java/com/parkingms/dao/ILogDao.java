package com.parkingms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.parkingms.bean.LogBean;
/**
 * 持久层：操作日志表
 * @author 张怡
 *
 */
public interface ILogDao {
	/**
	 * 插入日志
	 * @param log
	 * @return
	 */
	@Insert("INSERT INTO t_log(log_username,log_time,log_ip,log_module,log_method,log_responseTime,log_plantformtype,log_type)"
			+ " VALUES(#{username},#{time},#{ip},#{module},#{method},#{responseTime},#{plantformType},#{type}) ")
	public int insert(LogBean log);
	
	/**
	 * 动态分页查询日志
	 * @param curr
	 * @param limit
	 * @param log
	 * @return
	 */
	public List<LogBean> getListByPage(@Param(value="begin")int begin, @Param(value="limit")int limit,@Param(value="log")LogBean log);
	
	/**
	 * 查询日志总条数（带条件搜索)
	 * @param bean
	 * @return
	 */
	public int count(LogBean bean);

}
