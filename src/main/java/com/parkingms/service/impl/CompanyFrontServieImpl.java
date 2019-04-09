package com.parkingms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.parkingms.bean.CbdcarBean;
import com.parkingms.bean.CompanyBean;
import com.parkingms.bean.CompanytreatyBean;
import com.parkingms.dao.ICompanyFrontDao;
import com.parkingms.service.ICompanyFrontService;
/**
 * 企业前台业务层
 * 企业合同查询：合同，数量
 * 企业车查询：车位，数量
 * @author BHH
 *
 */
@Service
public class CompanyFrontServieImpl implements ICompanyFrontService {
	@Autowired
	private ICompanyFrontDao dao;

	@Override
	public CompanytreatyBean selectCompanyTreatyById(Integer id) {
		CompanytreatyBean result = dao.selectCompanyTreatyById(id);
		return result;
	}

	@Cacheable(value = "sampleCache1")
	@Override
	public List<CompanytreatyBean> selectCompanytreaty(CompanyBean bean, Integer page, Integer limit,CompanytreatyBean treaty) {
		List<CompanytreatyBean> list = dao.selectCompanytreaty(bean, page, limit,treaty);
		return list;
	}

	@Cacheable(value = "sampleCache1")
	@Override
	public int selectCompanytreatyNumber(CompanyBean bean) {
		int treatyNumber = dao.selectCompanytreatyNumber(bean);
		return treatyNumber;
	}

	@Cacheable(value = "sampleCache1")
	@Override
	public List<CbdcarBean> selectCompanyCbdcar(CompanyBean bean, Integer page, Integer limit, Integer id,CbdcarBean cbdcar) {
		List<CbdcarBean> list = dao.selectCompanyCbdcar(bean, page, limit, id,cbdcar);
		return list;
	}

	@Cacheable(value = "sampleCache1")
	@Override
	public int selectCompanyCbdcarNumber(CompanyBean bean, Integer id) {
		int cbdNumber = dao.selectCompanyCbdcarNumber(bean, id);
		return cbdNumber;
	}

	@Cacheable(value = "sampleCache1")
	@Override
	public Integer selectCompanyBeanID(Integer id) {
		Integer ID = dao.selectCompanyBeanID(id);
		return ID;
	}

}
