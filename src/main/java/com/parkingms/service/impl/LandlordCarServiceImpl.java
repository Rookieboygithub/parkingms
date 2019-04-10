package com.parkingms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkingms.aop.ParkingmsLog;
import com.parkingms.bean.LandlordCarBean;
import com.parkingms.dao.ILandlordCarDao;
import com.parkingms.service.ILandlordCarService;
/**
 * 业务层：处理与车位信息表有关的业务逻辑
 * @author 张怡
 *
 */
// 业务层
@Service
public class LandlordCarServiceImpl implements ILandlordCarService {
	// 自动注入ILandlordCarDao接口
	@Autowired
	private ILandlordCarDao dao; 
	
	/**
	 * 包租婆申请添加车位
	 */
	//自定义日志注解
	@ParkingmsLog(module="个人车位业务", method="申请车位",plantform=0,type=1)
	//设置事务隔离级别及传播方式
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public boolean insertCar(LandlordCarBean bean) {
		try {
			// 插入申请时间
			bean.setTime(new Date());
			// 返回受影响的行数
			int row  = dao.insert(bean);
			if(row > 0){
				return true;
			}
		} catch (Exception e) {
			
		}
		return false;
	}
	/**
	 * 包租婆删除车位
	 * @param bean
	 */
	//自定义日志注解
	@ParkingmsLog(module="个人车位业务", method="删除车位",plantform=0,type=1)
	//设置事务隔离级别及传播方式
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public boolean deleteCar(int id) {
		try {
			// 返回受影响的行数
			int row  = dao.updateRemove(id);
			if(row > 0){
				return true;
			}
		} catch (Exception e) {
			
		}
		return false;
	}
	/**
	 * 修改车位状态
	 * @param bean
	 */
	@Override
	//设置事务隔离级别及传播方式
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public boolean updateCarStatus(LandlordCarBean bean) {
		try {
			// 返回受影响的行数
			int row  = dao.updateChange(bean);
			if(row > 0){
				return true;
			}
		} catch (Exception e) {
			
		}
		return false;
	}
	/**
	 * 包租婆查看申请记录
	 */
//	@Override
//	public List<LandlordCarBean> findCarById(UserBean bean) {
//		List<LandlordCarBean> list = dao.getListByLandlordId(bean.getId());
//		return list;
//	}
	/**
	 * 管理员查看所有车位
	 */
//	@Override
//	public List<LandlordCarBean> findAllCar() {
//		List<LandlordCarBean> list = dao.getListAll();
//		return list;
//	}
	/**
	 * 包租婆查看有效车位
	 */
//	@Override
//	public List<LandlordCarBean> findValidCar(UserBean bean) {
//		List<LandlordCarBean> list = dao.getListValidByLandlordId(bean.getId());
//		return list;
//	}
	/**
	 * 分页查询车位信息 
	 */
	@Override
	public Map<String,Object> findLandlordCarsByPage(int curr, int limit,LandlordCarBean bean) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<LandlordCarBean> list = dao.getListCars((curr-1)*limit,limit,bean);
		int total = dao.count(bean);
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 统计车位信息数量
	 * @return
	 */
	@Override
	public int countLandlordCars(LandlordCarBean bean) {
		return dao.count(bean);
	}
	/**
	 * 根据车位id查找车位及包租婆信息
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> findLandlordCarById(int id) {
		String result = "notfound";
		LandlordCarBean bean = dao.getById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if(bean != null){
			result = "find";
			map.put("data", bean);
		} 
		map.put("result", result);
		return map;
	}
	/**
	 * 根据包租婆id查找车位信息
	 */
//	@Override
//	public List<LandlordCarBean> findCarsByLandlordIdUsingPage(int curr, int limit,int id) {
//		List<LandlordCarBean> list = dao.getListByLandlordId(curr,limit,id);
//		return list;
//	}
	
	
	

}
