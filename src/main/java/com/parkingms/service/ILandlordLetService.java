package com.parkingms.service;

import java.util.Map;

import com.parkingms.bean.LandlordCarBean;
import com.parkingms.bean.LandlordLetBean;
/**
 * 业务层接口：处理与招租信息表有关的业务逻辑
 * @author 张怡
 *
 */
public interface ILandlordLetService {
	/**
	 * 包租婆发布招租信息
	 */
	public boolean insertLet(LandlordLetBean bean);
	/**
	 * 包租婆删除招租信息
	 */
	public boolean deleteLet(int id);
	/**
	 * 包租婆修改招租信息
	 */
	public boolean updateLet(LandlordLetBean bean);
	/**
	 * 招租客查看招租信息(可带时间条件筛选)
	 */
//	public List<LandlordLetBean> findAvailableCar(LandlordLetBean bean);
	/**
	 * 包租婆查看发布的招租信息
	 */
//	public List<LandlordLetBean> findLetByLandlordId(int id);
	
	/**
	 * 根据包租婆id查找车位及租赁信息，分页显示
	 * @param curr
	 * @param limit
	 * @param id
	 * @return
	 */
	public Map<String,Object> findLetByLandlordIdUsingPage(int curr, int limit,LandlordCarBean bean);
	/**
	 * 根据包租婆id统计车位数量
	 * @return
	 */
	public int countCarsByLandlordId(LandlordCarBean bean);
	/**
	 * 根据车位id删除车位信息及招租信息
	 * @param id
	 */
	public boolean deleteCar(int id);
	/**
	 * 分页查询可显示的招租信息
	 * @param curr
	 * @param limit
	 * @return
	 */
	public Map<String,Object> findAvailableLetByPage(int curr, int limit,LandlordLetBean bean);
	/**
	 * 统计可显示的招租信息数量
	 * @param bean
	 * @return
	 */
	public int countAvailableLet(LandlordLetBean bean);
	/**
	 * 根据车位id查找车位招租信息
	 * @param id
	 * @return
	 */
	public LandlordLetBean findLetByCarId(int id);
}
