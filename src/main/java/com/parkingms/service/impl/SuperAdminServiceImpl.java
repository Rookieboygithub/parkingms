package com.parkingms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parkingms.bean.AdminBean;
import com.parkingms.dao.IAdminDao;
import com.parkingms.service.ISuperAdminService;

/**
 * 
 * 杨向东
 * @author Administrator
 *
 */
@Service
public class SuperAdminServiceImpl implements ISuperAdminService{
	
	@Autowired
	private IAdminDao dao;

	/**
	 * 注册一个用户权限管理
	 */
	@Override
	public void checkUserManager(AdminBean bean) {
		dao.insertUserManger(bean);		
	}
	
	
	/**
	 * 删除
	 * 
	 */
	@Override
	public int removeAdmin(int id) {
		int row = dao.deleteAdmin(id);
		return row;	
	}
	
	
	/**
	 * 查看
	 * 
	 * */
	@Override
	public AdminBean examineAdmin(int id) {
		AdminBean admin = dao.selectUserManager(id);
		return admin;
	}
	
	//查找所有
	@Override
	public List<AdminBean> examineAll(int page) {
//		AdminBean admin = (AdminBean) dao.selectAll(bean);
//		return admin;
		page = 1;
		//定义limit下标
		int index = (page-1)*6;
		List<AdminBean> list = dao.selectAll(index);
		return list;
	}
	//查找总页码
	@Override
	public int examineNum(AdminBean bean) {
		int row = 0;
        row = dao.selectCount(bean);
        if(row%6 == 0){
        	return (row/6);
        }else{
        	return (row/6)+1 ;
        }

	}
	
	/**
	 * 修改
	 * 
	 */
	@Override
	public int alterAdmin(int id) {
		//int row = dao.updateAdmin(id);
		//return row;
		return 0;
	}

	
	/**
	 * 
	 * 对view表操作
	 */
	@Override
	public List<AdminBean> findAll(int page) {
//		page = 1;
		//定义limit下标
		int index = (page-1)*2;
		List<AdminBean> list = dao.selectAllView(index);
		return list;
	}

    //查找总页码
	@Override
	public int findNum(AdminBean bean) {
		int row = 0;
        row = dao.selectCountView(bean);
        if(row%2 == 0){
        	return (row/2);
        }else{
        	return (row/2)+1 ;
        }
	}
	//查找数据库总页数
	@Override
	public int findAllNum(AdminBean bean) {
		int row = 0;
		row = dao.selectCountView(bean);
		return row;
	}
	//根据账号，查密码
	@Override
	public String findPassword(String account) {
		String pass = dao.selectPassword(account);
		return pass;
	}
	//对两张单表操作
	@Override
	public AdminBean findAdminBean(String account) {
		AdminBean bean = null;
		try {
		    int loginid = dao.selectLoginid(account);
		    bean = dao.selectOneRowData(loginid);
		}finally{
			return bean;
		}
	}
	
	
	
	//对两张单表分别操作--获取前端数据，分别插入两张表
	@Transactional
	@Override
	public void insertLoginAdmin(AdminBean  bean) {
						
		try {
			dao.insertLogin(bean);
			//bean.setLoginid(bean.getLogin_id());
			dao.insertUserManger(bean);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}


	//软删除
	@Transactional
	@Override
	public void removeflag(int ids) {
		
		try {
			dao.updateAdminflag(ids);
		    dao.updateLoginflag(ids);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	//修改
	@Transactional
	@Override
	public int alterAdminLogin(AdminBean  bean) {
		//先进行单表操作，试验一下把其他数据砍掉不用
		int row = 0;
	    int row1 = 0;
	    int row2 = 0;
		try {
			row = dao.updateAdmin(bean);
		    //row1 = dao.updateLogin(bean);
		    //row2 = row +row1;
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		return row;
		
	}



	
	





	
	

}
