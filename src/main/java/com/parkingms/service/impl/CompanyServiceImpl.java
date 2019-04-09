package com.parkingms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.parkingms.bean.CbdcarBean;
import com.parkingms.bean.CompanyBean;
import com.parkingms.bean.CompanytreatyBean;
import com.parkingms.dao.ICompanyDao;
import com.parkingms.service.ICompanyService;
import com.parkingms.util.MD5_Encoding;

/**
 * 企业管理界面：业务层实现类
 * 企业的增加
 * 企业的删除
 * 企业的查询，企业数量及企业查询
 * @author BHH
 *
 */
@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
@CacheConfig(cacheNames = "sampleCache1")
public class CompanyServiceImpl implements ICompanyService {
	/* 企业dao层接口 */
	@Autowired
	private ICompanyDao dao;

	/**
	 * 需要检测登录名是否已经存在。调用注册接口,
	 * @CachePut：查询结果添加到缓存
	 */
	@CachePut(value = "sampleCache1")
	@Override
	public String insertCompanyBean(CompanyBean bean) {
		String result = "注册失败";
		String pwd = bean.getPwd();
		bean.setPwd(MD5_Encoding.lowerMD5(pwd));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bean", bean);
		dao.insertCompanyBean(map);
		/* 查看存储过程返回值，对返回值进行判断，确认是否存储成功 */
		if (((Integer) map.get("result")) == 1) {
			result = "注册失败";
		} else if (((Integer) map.get("result")) == 0) {
			result = "注册成功";
		} else {
			throw new RuntimeException();
		}
		return result;
	}

	/**
	 * @CacheEvict：数据删除后，清除缓存中的数据
	 */
	@CacheEvict(value = "sampleCache1", allEntries = true, beforeInvocation = true)
	@Override
	public boolean deleteCompanyBean(CompanyBean bean) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bean", bean);
		dao.deleteCompanyBean(map);
		if (((Integer) map.get("result")) == 1) {
			return true;
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public List<CompanyBean> selectCompanyBean(CompanyBean bean, Integer page, Integer limit) {
		List<CompanyBean> list = new ArrayList<CompanyBean>();
		if (bean != null) {
			list = dao.selectCompanyBean(bean, page, limit);
		}
		return list;
	}

	@Cacheable(value = "sampleCache1")
	@Override
	public int selectCompanyNumber(CompanyBean bean) {
		int number = dao.selectCompanyNumber(bean);
		return number;
	}

	@Override
	public String selectCompanyLoginName(String loginName) {
		String result = null;
		Integer id = dao.selectCompanyLoginName(loginName);
		if (id != null) {
			result = "用户名已存在";
		}
		return result;
	}
}
