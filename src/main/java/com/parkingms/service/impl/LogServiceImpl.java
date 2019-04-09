package com.parkingms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingms.bean.LogBean;
import com.parkingms.dao.ILogDao;
import com.parkingms.service.ILogServcie;
/**
 * 业务层接口：处理与日志表有关的业务逻辑
 * @author 张怡
 *
 */
@Service
public class LogServiceImpl implements ILogServcie {
	/**
	 * 自动注入ILogDao接口
	 */
	@Autowired
	private ILogDao dao;
	/**
	 * 将日志写入数据库
	 */
	@Override
	public boolean saveLog(LogBean log) {
		int row = dao.insert(log);
		if(row > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 查询日志信息(可带条件搜索)
	 */
	@Override
	public Map<String, Object> findLogByPage(int curr, int limit, LogBean bean) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 根据分页参数获取指定页的日志集合信息
		List<LogBean> list = dao.getListByPage((curr-1)*limit, limit, bean);
		// 获取日志总条数
		int total = dao.count(bean);
		// 将日志集合和总条数存入map
		map.put("list", list);
		map.put("total", total);
		return map;
	}

}
