package com.op.service.insurance;

import java.util.List;
import java.util.Map;

import com.op.dto.insurance.BaoXianren;
import com.op.dto.insurance.InsurancesDTO;
/** 
 * 保险信息(Insurances)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-16 11:19:59 
 */  
public interface InsurancesService {

	/**
	 * 获取保险列表
	 * @return
	 * @throws Exception
	 */
    List<InsurancesDTO> getInsurancesList()throws Exception;

/*	*//**
	 * 根据保险id获取保险计划列表
	 * @param id
	 * @return
	 * @throws Exception
	 *//*
    Insurances findById(String id)throws Exception;*/

    /**
     * 购买保险
     * @param lpId 保险价格ID
     * @param buy_user_id 购买保险的用户id
     * @param startDate 活动开始时间 yyyy-MM-dd
     * @param endDate   活动结束时间 yyyy-MM-dd
     * @param toubaorenList
     * @return
     */
	void buy(int aId,String lpId,String buy_user_id,String startDate,String endDate,List<BaoXianren> baoXianrenList)throws Exception;
    
	/**
	 * 保险退单
	 * @param asu_id 订单ID
	 * @param aa_id 联系人ID
	 * @throws Exception
	 */
	void orderCancel(String asu_id,String aa_id) throws Exception;
}
