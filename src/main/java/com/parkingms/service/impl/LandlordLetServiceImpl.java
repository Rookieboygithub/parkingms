package com.parkingms.service.impl;

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
import com.parkingms.bean.LandlordLetBean;
import com.parkingms.dao.ILandlordCarDao;
import com.parkingms.dao.ILandlordLetDao;
import com.parkingms.service.ILandlordLetService;
/**
 * 业务层：处理与招租信息表有关的业务逻辑
 * @author 张怡
 *
 */
// 业务层
@Service
public class LandlordLetServiceImpl implements ILandlordLetService {
	/**
	 * 自动注入ILetDao接口
	 */
	@Autowired
	private ILandlordLetDao dao;
	/**
	 * 自动注入ILandlordCarDao接口
	 */
	@Autowired
	private ILandlordCarDao cdao;

	/**
	 * 包租婆发布招租信息
	 */
	@ParkingmsLog(module="个人车位业务", method="新增招租信息",plantform=0,type=1)
	//设置事务隔离级别及传播方式
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	@Override
	public boolean insertLet(LandlordLetBean bean) {
		boolean result = false;
		// 往招租表添加数据，返回受影响的行数
		int row = dao.insert(bean);
		// 修改车位信息表车位状态为待出租，返回受影响的行数
		LandlordCarBean car = bean.getLandlordCar();
		int crow = cdao.updateStatusAddLet(car);
		// 两张表都操作成功，返回true，否则回滚事务
		if (row > 0 && crow > 0) {
			result = true;
		} else {
			// 两张表操作失败，抛出运行时异常，使事务回滚
			throw new RuntimeException("fail");
		}
		return result;
	}

	/**
	 * 包租婆删除招租信息
	 */
	@Override
	@ParkingmsLog(module="个人车位业务", method="删除招租信息",plantform=0,type=1)
	//设置事务隔离级别及传播方式
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public boolean deleteLet(int id) {
		boolean result = false;
		// 软删除招租表信息，返回受影响的行数
		int row = dao.updateRemove(id);
		// 修改车位信息表车位状态为已审核，返回受影响的行数
		LandlordCarBean car = new LandlordCarBean();
		car.setId(id);
		int crow = cdao.updateStatusDeleteLet(car);
		// 两张表都操作成功，返回true，否则回滚事务
		if (row > 0 && crow > 0) {
			result = true;
		} else {
			// 两张表操作失败，抛出运行时异常，使事务回滚
			throw new RuntimeException("fail");
		}
		return result;
	}

	/**
	 * 包租婆修改招租信息
	 */
	@Override
	@ParkingmsLog(module="个人车位业务", method="修改招租信息",plantform=0,type=1)
	//设置事务隔离级别及传播方式
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public boolean updateLet(LandlordLetBean bean) {
		// 返回受影响的行数
		int row = dao.update(bean);
		if (row > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 招租客查看招租信息(可带时间条件筛选)
	 */
//	@Override
//	public List<LandlordLetBean> findAvailableCar(LandlordLetBean bean) {
//		List<LandlordLetBean> list = dao.getListAvailable(bean);
//		return list;
//	}

	/**
	 * 包租婆查看发布的招租信息
	 */
//	@Override
//	public List<LandlordLetBean> findLetByLandlordId(int id) {
//		 List<LandlordLetBean> list = dao.getListByLandlordId(id); 
//		 return list;
//	}

	/**
	 * 根据包租婆id查找车位信息
	 */
	@Override
	public Map<String,Object> findLetByLandlordIdUsingPage(int curr, int limit,LandlordCarBean bean) {
		if(bean == null){
			return null;	
		}
		Map<String,Object> map = new HashMap<String, Object>();
		List<LandlordLetBean> list = dao.getListByLandlordId((curr-1)*limit, limit, bean);
		int total = dao.countCarsByLandlordId(bean);
		map.put("list", list);
		map.put("total", total);
		return map;
	}

	/**
	 * 根据包租婆id统计车位数量
	 * 
	 * @return
	 */
	@Override
	public int countCarsByLandlordId(LandlordCarBean bean) {
		if(bean == null){
			return 0;
		}
		return dao.countCarsByLandlordId(bean);
	}
	/**
	 * 根据车位id删除车位信息及招租信息
	 * @param id
	 */
	@Override
	@ParkingmsLog(module="个人车位业务", method="删除车位及招租信息",plantform=0,type=1)
	//设置事务隔离级别及传播方式
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
	public boolean deleteCar(int id) {
		// 软删除招租信息(可能没有招租信息，所以受影响行数可能为0)
		dao.updateRemove(id);
		// 软删除车位信息，返回受影响的行数
		int row = cdao.updateRemove(id);
		if (row > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 分页查询可显示的招租信息
	 */
	@Override
	public Map<String,Object> findAvailableLetByPage(int curr, int limit,LandlordLetBean bean) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<LandlordLetBean> list = dao.getListAvailableByPage((curr-1)*limit,limit,bean);
		int total = dao.countAvailable(bean);
		map.put("list", list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 统计可显示的招租信息数量
	 */
	@Override
	public int countAvailableLet(LandlordLetBean bean) {
		return dao.countAvailable(bean);
	}
	
	/**
	 * 根据车位id查找车位招租信息
	 */
	@Override
	public LandlordLetBean findLetByCarId(int id) {
		LandlordLetBean bean = dao.getByCarId(id);
		return bean;
	}

}
