package com.op.service.insurance;

/** 
 * 保险被保人(Insured)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-16 11:19:59 
 */  
public interface InsuredService {



	/**
	 * 更新保单信息
	 * @param insureNum  投保单号
	 */
    void updatePolicy(String insureNum);

	
}
