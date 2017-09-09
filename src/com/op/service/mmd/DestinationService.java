package com.op.service.mmd;

import com.op.entity.mmd.Destination;

/** 
 * 旅游目的地(destination)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-24 14:02:55 
 */  
public interface DestinationService {

	/**
	 * 获取省ID
	 * @param name
	 * @return
	 */
	String getProvinceId(String name)throws Exception;

	/**
	 * 添加目的地
	 * @param destination
	 */
	void insertDestination(Destination destination)throws Exception;
	
	/**
	 * 修改目的地信息
	 * @param destination
	 */
	void updateDestination(Destination destination)throws Exception;

    

	
}
