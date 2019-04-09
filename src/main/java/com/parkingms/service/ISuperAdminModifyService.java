package com.parkingms.service;

import java.util.List;

import com.parkingms.bean.AdminBean;
/**
 * 超级管理员功能接口，修改普通管理员权限
 * @author 杨吉竹
 *
 */
public interface ISuperAdminModifyService {
/*public boolean modifySuperAdmin(SuperAdminMessageBean bean);*/
public boolean modifyadmin(AdminBean bean);
public List<AdminBean> search(String keywords,Integer page);
public List<AdminBean> findall(Integer page);
public AdminBean findone(Integer id);
public int getcount();
public int searchcount(String keywords);
}
