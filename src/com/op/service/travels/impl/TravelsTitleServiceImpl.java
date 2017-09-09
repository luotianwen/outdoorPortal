package com.op.service.travels.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao; 
import com.op.service.travels.TravelsTitleService;

/** 
 * 游记标题(travelsTitle)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-18 14:30:32 
 */  
@Service("travelsTitleServiceImpl")
public class TravelsTitleServiceImpl implements TravelsTitleService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 查询游记所有标题
	 * @param travelsId 游记ID
	 */
	@Override
	public int findTravelsTitleCountByTagId(String travelsId)throws Exception{
    	return (int) dao.findForObject("travelsTitleMapper.findTravelsTitleCountByTagId", travelsId);
    }
}
