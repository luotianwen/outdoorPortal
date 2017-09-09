package com.op.service.activity.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.service.activity.ActiveScenicService;


@Service("activeScenicServiceImpl")
public class ActiveScenicServiceImpl implements ActiveScenicService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
}
