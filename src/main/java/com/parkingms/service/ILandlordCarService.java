package com.parkingms.service;

import java.util.List;
import java.util.Map;

import com.parkingms.bean.LandlordCarBean;
import com.parkingms.bean.UserBean;
/**
 * 业务层接口：处理与车位信息表有关的业务逻辑
 * @author 张怡
 *
 */
public interface ILandlordCarService {
	/**
	 * 包租婆申请添加车位
	 * @param bean
	 */
	public boolean insertCar(LandlordCarBean bean);
	/**
	 * 包租婆删除车位
	 * @param bean
	 */
	public boolean deleteCar(int id);
	/**
	 * 修改车位状态
	 * @param bean
	 */
	public boolean updateCarStatus(LandlordCarBean bean);
	/**
	 * 包租婆查看申请记录
	 */
//	public List<LandlordCarBean> findCarById(UserBean bean);
	/**
	 * 包租婆查看有效车位
	 * @param bean
	 * @return
	 */
//	public List<LandlordCarBean> findValidCar(UserBean bean);
	/**
	 * 管理员查看所有车位
	 */
//	public List<LandlordCarBean> findAllCar();
	/**
	 * 分页查询车位信息
	 * @param curr
	 * @param limit
	 * @return
	 */
	public Map<String,Object> findLandlordCarsByPage(int curr, int limit,LandlordCarBean bean);
	
	/**
	 * 统计车位信息数量
	 * @return
	 */
	public int countLandlordCars(LandlordCarBean bean);
	/**
	 * 根据车位id查找车位及包租婆信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findLandlordCarById(int id);
	/**
	 * 根据包租婆id查找车位信息
	 * @param i
	 * @param j
	 * @return
	 */
//	public List<LandlordCarBean> findCarsByLandlordIdUsingPage(int curr, int limit,int id);
}
