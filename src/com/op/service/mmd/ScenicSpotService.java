package com.op.service.mmd;

import java.util.List;

import com.op.entity.mmd.ScenicSpot;
import com.op.plugin.page.Page;
/** 
 * 景点(scenicSpot)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-24 14:02:55 
 */  
public interface ScenicSpotService {

	/**
	 * 添加景点
	 * @param scenicSpot
	 */
	void insertScenicSpot(ScenicSpot scenicSpot)throws Exception;

    /**
     * 获取景点信息
     * @param page
     * @return
     * @throws Exception
     */
	List<ScenicSpot> getScenicSpot(Page<Object> page)throws Exception;
	
	/**
	 * 更新景点信息
	 * @param scenicSpot
	 * @throws Exception
	 */
	void updateScenicSpot(ScenicSpot scenicSpot) throws Exception;
	
}
