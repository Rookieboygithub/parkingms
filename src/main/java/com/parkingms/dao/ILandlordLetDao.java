package com.parkingms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.parkingms.bean.LandlordCarBean;
import com.parkingms.bean.LandlordLetBean;
/**
 * 持久层：操作招租信息表
 * @author 张怡
 *
 */
public interface ILandlordLetDao {
	/**
	 * 新增
	 * 包租婆发布招租信息
	 * @param bean
	 * @return
	 */
	@Insert("insert into t_landlordlet(landlordlet_begintime,landlordlet_endtime,landlordlet_price,landlordlet_user_id,landlordlet_l_id)"
			+ "values(#{beginTime},#{endTime},#{price},#{landlord.id},#{landlordCar.id})")
	public int insert(LandlordLetBean bean);
	/**
	 * 软删除
	 * 包租婆删除招租信息
	 * @param bean
	 * @return
	 */
	@Update("update t_landlordlet set flag = 1 where landlordlet_l_id = #{id} and flag = 0")
	public int updateRemove(int id);
	/**
	 * 包租婆修改招租信息
	 */
	public int update(LandlordLetBean bean);
	/**
	 * 招租客查看招租信息(可带时间条件筛选)
	 * @return
	 */
//	public List<LandlordLetBean> getListAvailable(LandlordLetBean bean);
	/**
	 * 根据包租婆id查看车位及租赁信息(可带条件筛选)，分页显示
	 */
	public List<LandlordLetBean> getListByLandlordId(@Param(value="begin")int begin, @Param(value="limit")int limit,@Param(value="bean")LandlordCarBean bean);
	/**
	 * 根据包租婆id统计车位数量
	 * @return
	 */
	public int countCarsByLandlordId(LandlordCarBean bean);
	/**
	 * 分页显示可显示的招租信息(可带条件筛选)
	 * @param curr
	 * @param limit
	 * @param bean
	 * @return
	 */
	public List<LandlordLetBean> getListAvailableByPage(@Param(value="begin")int begin, @Param(value="limit")int limit, @Param(value="bean")LandlordLetBean bean);
	/**
	 * 统计可显示的招租信息数量(可带条件筛选)
	 * @param bean
	 * @return
	 */
	public int countAvailable(LandlordLetBean bean);
	/**
	 * 根据车位id查找车位招租信息
	 * @param id
	 * @return
	 */
	public LandlordLetBean getByCarId(int id);
}
