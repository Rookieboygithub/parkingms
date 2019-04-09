package com.parkingms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingms.bean.CompanyBean;
import com.parkingms.dao.ICompanyMessageDao;
import com.parkingms.service.ICompanyMessageService;
/**
 * 企业用户信息查询
 * @author 杨吉竹
 *
 */
@Service
public class CompanyMessageServiceImpl implements ICompanyMessageService{
    @Autowired
	private ICompanyMessageDao dao;
	@Override
	public CompanyBean selectCompany(String account) {
		CompanyBean company=dao.companys(account);
		return company;
	}

}
