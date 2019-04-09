package com.parkingms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingms.bean.AuthorityBean;
import com.parkingms.dao.IAuthorityDao;
import com.parkingms.service.IAuthorityService;
@Service
public class AuthorityServiceImpl implements IAuthorityService {
	@Autowired
	private IAuthorityDao dao;
	@Override
	public List<AuthorityBean> findAll() {
		// TODO Auto-generated method stub
		List<AuthorityBean> list=dao.findAll();
		return list;
	}

}
