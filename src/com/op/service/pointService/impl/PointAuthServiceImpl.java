package com.op.service.pointService.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.service.pointService.PointAuthService;


@Service("pointAuthServiceImpl")
public class PointAuthServiceImpl implements PointAuthService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	

	
}
