package com.parkingms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingms.aop.ParkingmsLog;
import com.parkingms.bean.CompanyBean;
import com.parkingms.dao.ICompanyModifyDao;
import com.parkingms.service.ICompanyModifyService;
/**
 * 
 * 对企业用户信息修改的业务层
 * 加入日志功能
 * @author 杨吉竹
 *
 */
@Service
public class CompanyModfiyServiceImpl implements ICompanyModifyService {
    @Autowired
	private ICompanyModifyDao dao;

	@Override
	@ParkingmsLog(module="公司账户",method="修改信息",plantform=0,type=1)
	public boolean companyModify(CompanyBean company) {
		boolean result=false;
		int i=dao.updateCompany(company);
		if(i>=1){
		result=true;
		}
		return result;
	}
	

}
