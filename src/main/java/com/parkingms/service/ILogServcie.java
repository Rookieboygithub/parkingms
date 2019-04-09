package com.parkingms.service;

import java.util.Map;

import com.parkingms.bean.LogBean;
/**
 * 业务层接口：处理与日志表有关的业务逻辑
 * @author 张怡
 *
 */
public interface ILogServcie {
	/**
	 * 将日志写入数据库
	 * @param log
	 */
	public boolean saveLog(LogBean log);
	
	/**
	 * 查询日志信息(可带条件搜索)
	 * @param curr
	 * @param limit
	 * @param bean
	 * @return
	 */
	public Map<String, Object> findLogByPage(int curr, int limit, LogBean bean);

}
