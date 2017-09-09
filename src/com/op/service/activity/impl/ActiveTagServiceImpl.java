package com.op.service.activity.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.service.activity.ActiveTagService;


@Service("activeTagServiceImpl")
public class ActiveTagServiceImpl implements ActiveTagService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
}
