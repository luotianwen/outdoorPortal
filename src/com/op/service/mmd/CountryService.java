package com.op.service.mmd;

import com.op.entity.mmd.Country;
/** 
 * 国家(country)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-24 14:02:54 
 */  
public interface CountryService {

	/**
	 * 添加国家
	 * @param country
	 * @throws Exception
	 */
	void insertCountry(Country country)throws Exception;

	/**
	 * 根据国家名字获取国家id
	 * @param zh_name
	 * @return
	 */
	String getCountryId(String zh_name)throws Exception;

    

	
}
