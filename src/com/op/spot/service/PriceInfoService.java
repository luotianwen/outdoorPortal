package com.op.spot.service;

import java.util.List;

import com.op.spot.entity.PriceInfo;
/** 
 * 景点门票价格信息(s_priceInfo)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-09-21 13:40:39 
 */  
public interface PriceInfoService {

	
	/**
	 * 根据门票产品编号 游玩日期查询当天价格
	 * @param productSn
	 * @param inDate
	 * @return
	 * @throws Exception
	 */
	PriceInfo getPriceInfo(String productSn, String inDate)throws Exception;

    

	
}
