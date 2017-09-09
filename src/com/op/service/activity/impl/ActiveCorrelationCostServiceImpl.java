package com.op.service.activity.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.service.activity.ActiveCorrelationCostService;

@Service("activeCorrelationCostServiceImpl")
public class ActiveCorrelationCostServiceImpl implements ActiveCorrelationCostService {
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
}
