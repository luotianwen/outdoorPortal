package com.op.service.mmd.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.op.dao.BaseDao;
import com.op.entity.mmd.Destination;
import com.op.service.mmd.DestinationService;

/** 
 * 旅游目的地(destination)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-24 14:02:55 
 */  
@Service("destinationServiceImpl")
public class DestinationServiceImpl implements DestinationService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 获取省ID
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	@Override
	public String getProvinceId(String name)throws Exception{
		if("宁".equals(name)){
			name= "宁夏";
		}else if(StringUtils.isEmpty(name)){
			return null;
		}
		return (String)dao.findForObject("destinationMapper.getProvinceId", name);
	}

	/**
	 * 添加目的地
	 * @param destination
	 */
	@Override
	public void insertDestination(Destination destination)throws Exception{
		dao.save("destinationMapper.insertDestination", destination);
	}
	
	/**
	 * 修改目的地信息
	 * @param destination
	 */
	@Override
	public void updateDestination(Destination destination)throws Exception{
		dao.update("destinationMapper.updateDestination", destination);
	}
	
}
