package com.parkingms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingms.bean.PropertyBean;
import com.parkingms.dao.IPropertyDao;
import com.parkingms.service.IPropertyService;
/**
 * 业务层实现类：处理与性能表有关的业务逻辑
 * @author 张怡
 *
 */
@Service
public class PropertyServiceImpl implements IPropertyService {
	/**
	 * 自动注入IPropertyDao接口，用于操作性能表
	 */
	@Autowired
	private IPropertyDao dao;
	
	/**
	 * 更新性能表数据
	 * @param property
	 */
	@Override
	public void updateProperty(PropertyBean property) {
		// 根据URI获取到上一次统计结果
		PropertyBean bean = dao.getByURI(property.getUri());
		if(bean == null){
			// 没有查询到上一次统计结果，则插入统计
			dao.insert(property);
		} else {
			// 更新统计次数，总响应时间和平均响应次数
			bean.setCount(bean.getCount()+1);
			bean.setTotalTime(bean.getTotalTime()+property.getAverageTime());
			int averageTime = (int) (bean.getTotalTime()/bean.getCount());
			bean.setAverageTime(averageTime);
			// 写入数据库
			dao.update(bean);
		}
	}

}
