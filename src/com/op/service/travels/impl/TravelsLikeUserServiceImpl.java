package com.op.service.travels.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.service.travels.TravelsLikeUserService;

@Service("travelsLikeUserServiceImpl")
public class TravelsLikeUserServiceImpl implements TravelsLikeUserService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	

	
}
