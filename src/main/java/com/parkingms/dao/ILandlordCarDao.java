package com.parkingms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.parkingms.bean.LandlordCarBean;
/**
 * 持久层：操作车位信息表
 * @author 张怡
 *
 */
public interface ILandlordCarDao {
	/**
	 * 包租婆查看有效车位
	 */
//	public List<LandlordCarBean> getListValidByLandlordId(int id);

	/**
	 * 管理员查看所有车位
	 */
//	public List<LandlordCarBean> getListAll();
	/**
	 * 包租婆查看申请记录
	 * @param id
	 * @return
	 */
//	public List<LandlordCarBean> getListByLandlordId(@Param(value="curr")int curr, @Param(value="limit")int limit,@Param(value="id")int id);
	/**
	 * 添加车位
	 * 包租婆申请添加车位
	 * @param bean
	 */
	@Insert("insert into t_landlordcar(landlordcar_no,landlordcar_location,landlordcar_num,landlordcar_img,landlordcar_user_id,landlordcar_time)"
			+ " VALUES(#{certificateNo},#{location},#{carNo},#{img},#{landlord.id},#{time}) ")
	public int insert(LandlordCarBean bean);

	/**
	 * 包租婆删除车位
	 * @param bean
	 * @return 
	 */
	@Update("UPDATE t_landlordcar SET flag = 1 WHERE landlordcar_id = #{id} AND flag=0 AND landlordcar_status NOT IN (3,4)  ")
	public int updateRemove(int id);

	/**
	 * 修改车位状态
	 * 管理员审核车位通过
	 * 管理员审核车位拒绝
	 * @param bean
	 */
	@Update("UPDATE t_landlordcar SET landlordcar_status = #{status} WHERE landlordcar_id=#{id} AND flag=0 AND landlordcar_status=0" )
	public int updateChange(LandlordCarBean bean);
	
	/**
	 * 包租婆发布招租信息时修改车位状态
	 * @param bean
	 * @return
	 */
	@Update("UPDATE t_landlordcar SET landlordcar_status = 2 WHERE landlordcar_id=#{id} AND flag=0 AND landlordcar_status = 1 " )
	public int updateStatusAddLet(LandlordCarBean bean);
	
	/**
	 * 包租婆撤销招租信息时修改车位状态
	 * @param bean
	 * @return
	 */
	@Update("UPDATE t_landlordcar SET landlordcar_status = 1 WHERE landlordcar_id=#{id} AND flag=0 AND landlordcar_status = 2 " )
	public int updateStatusDeleteLet(LandlordCarBean bean);
	
	/**
	 * 招租客预约车位修改车位状态
	 * @param bean
	 * @return
	 */
	@Update("UPDATE t_landlordcar SET landlordcar_status = 3 WHERE landlordcar_id=#{id} AND flag=0 AND landlordcar_status = 2 " )
	public int updateStatusReserved(LandlordCarBean bean);
	
	/**
	 * 包租婆同意出租修改车位状态
	 * @param bean
	 * @return
	 */
	@Update("UPDATE t_landlordcar SET landlordcar_status = 4 WHERE landlordcar_id=#{id} AND flag=0 AND landlordcar_status = 3 " )
	public int updateStatusAgreed(LandlordCarBean bean);
	
	/**
	 * 包租婆拒绝出租修改车位状态
	 * @param bean
	 * @return
	 */
	@Update("UPDATE t_landlordcar SET landlordcar_status = 2 WHERE landlordcar_id=#{id} AND flag=0 AND landlordcar_status = 3 " )
	public int updateStatusRefused(LandlordCarBean bean);
	
	/**
	 * 分页查询车位信息（带条件筛选）
	 * @param curr
	 * @param limit
	 * @return
	 */
	public List<LandlordCarBean> getListCars(@Param(value="begin")int begin, @Param(value="limit")int limit,@Param(value="bean")LandlordCarBean bean);
	
	/**
	 * 统计车位信息数量（带条件筛选）
	 * @return
	 */
	public int count(LandlordCarBean bean);
	
	/**
	 * 根据车位id查找车位信息
	 * @param id
	 * @return
	 */
	public LandlordCarBean getById(int id);
}
