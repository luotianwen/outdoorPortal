package com.op.service.travels;

/** 
 * 游记标题(travelsTitle)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-18 14:30:32 
 */  
public interface TravelsTitleService {

	/**
     * 查询游记标题数量
     * @param travelsId 游记ID
     * @return
     * @throws Exception
     */
    public int findTravelsTitleCountByTagId(String travelsId)throws Exception;
	
}
