package com.parkingms.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.parkingms.bean.PropertyBean;

/**
 * 持久层：操作性能表
 * @author 张怡
 *
 */
public interface IPropertyDao {
	/**
	 * 根据URI查询该URI对应的统计结果
	 * @param uri
	 * @return
	 */
	public PropertyBean getByURI(String uri);
	/**
	 * 更新统计
	 * @param bean
	 * @return
	 */
	@Update("UPDATE t_property SET property_count = #{count},property_totaltime=#{totalTime},"
			+ "property_averagetime=#{averageTime} WHERE property_uri = #{uri}" )
	public int update(PropertyBean bean);
	/**
	 * 插入统计
	 * @param bean
	 * @return
	 */
	@Insert("INSERT INTO t_property(property_uri,property_count,property_totaltime,property_averagetime)"
			+ " VALUES(#{uri},#{count},#{totalTime},#{totalTime})")
	public int insert(PropertyBean bean);

}
