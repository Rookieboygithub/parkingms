package com.parkingms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkingms.dao.ISelectIdentity;
import com.parkingms.service.ISelectCharacterService;
/**
 * 最开始计划是通过查询角色来实现页面的变换
 * @author 杨吉竹
 *
 */
@Service
public class SelectCharacterServiceImpl implements ISelectCharacterService {
@Autowired
	private ISelectIdentity dao;
	@Override
	public Integer findcha(String role) {
	return	dao.selecRole(role);
	}

}
