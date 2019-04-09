package com.parkingms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingms.aop.ParkingmsLog;
import com.parkingms.bean.AdminBean;
import com.parkingms.dao.ISuperAdminModifyDao;
import com.parkingms.service.ISuperAdminModifyService;
/**
 * 超级管理员修改权限实现
 * 加入日志功能
 * @author 杨吉竹
 *
 */
@Service
public class SuperAdminModifyServiceImpl implements ISuperAdminModifyService {
	@Autowired
	private ISuperAdminModifyDao dao;

	
	/*public boolean modifySuperAdmin(SuperAdminMessageBean bean) {
		dao.updatePwd(bean);

		return false;
	}*/
	@ParkingmsLog(module="超级管理员",method="修改权限",plantform=1,type=1)
	@Override
	public boolean modifyadmin(AdminBean bean) {
		dao.updateAdmin(bean);
		return true;
	}

	@Override
	public List<AdminBean> search(String keywords, Integer page) {
		Integer up = (page - 1) * 6;
		Integer down = (page) * 6;
		List<AdminBean> list = dao.selectAccount(keywords, up, down);
		return list;
	}

	@Override
	public List<AdminBean> findall(Integer page) {
		Integer uppage = (page - 1) * 6;
		Integer downpage = (page) * 6;
		List<AdminBean> result = null;
		result = dao.selectAll(uppage, downpage);
        
		return result;
	}

	@Override
	public AdminBean findone(Integer id) {
		
		return dao.find(id);
	}
//获取t_admin表总页数
	@Override
	public int getcount() {
		int i=dao.getcount();
		if(i>0){
			return i;
		}
		return 0;
	}

	@Override
	public int searchcount(String name) {
		int i =dao.searchcount(name);
		if(i>0){
			return i;
		}
		return 0;
	}

}
