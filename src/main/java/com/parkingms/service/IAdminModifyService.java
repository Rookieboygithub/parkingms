package com.parkingms.service;

import com.parkingms.bean.AdminBean;
/**
 * 普通管理员修改信息接口
 * @author 杨吉竹
 *
 */

public interface IAdminModifyService {
public AdminBean findadmin(String account);
public boolean updateTel(AdminBean bean);

}
