package com.parkingms.service;

import com.parkingms.bean.PropertyBean;

/**
 * 业务层接口：处理与性能表有关的业务逻辑
 * @author 张怡
 *
 */
public interface IPropertyService {
	/**
	 * 更新性能表数据
	 * @param property
	 */
	public void updateProperty(PropertyBean property);

}
