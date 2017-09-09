package com.op.service.insurance;

import java.util.List;

import com.op.dto.insurance.InsurantDateLimitDTO;
/** 
 * 保险保障期限(InsurantDateLimit)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-16 11:19:59 
 */  
public interface InsurantDateLimitService {

	/**
	 * 根据保险id获取保险期限
	 * @param productId
	 * @return
	 */
	List<InsurantDateLimitDTO> findByProductId(String productId)throws Exception;

    

	
}
