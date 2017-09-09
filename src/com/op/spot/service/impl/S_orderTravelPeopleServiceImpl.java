package com.op.spot.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.spot.service.S_orderTravelPeopleService;

/** 
 * 订单出游人(s_orderTravelPeople)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-09-23 10:22:24 
 */  
@Service("s_orderTravelPeopleServiceImpl")
public class S_orderTravelPeopleServiceImpl implements S_orderTravelPeopleService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	
	
	
}
