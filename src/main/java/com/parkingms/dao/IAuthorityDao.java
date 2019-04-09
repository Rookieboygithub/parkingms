package com.parkingms.dao;

import java.util.List;

import com.parkingms.bean.AuthorityBean;

public interface IAuthorityDao {
	public List<AuthorityBean> findAll();
}
