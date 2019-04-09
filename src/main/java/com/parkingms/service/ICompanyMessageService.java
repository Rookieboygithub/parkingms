package com.parkingms.service;

import com.parkingms.bean.CompanyBean;
/**
 * 公司用户接口，查找公司账户信息
 * @author 杨吉竹
 *
 */
public interface ICompanyMessageService {
public CompanyBean selectCompany(String account);
}
