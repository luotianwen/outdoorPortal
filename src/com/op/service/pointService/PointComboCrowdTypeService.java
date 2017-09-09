package com.op.service.pointService;

import java.util.Map;

/** 
 * 地点项目/套餐适合人群字典表(pointComboCrowdType)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-06-24 13:42:52 
 */  
public interface PointComboCrowdTypeService {
	/**
	 * 刷新字典缓存
	 * @throws Exception
	 */
	Map<String, String> shuaxinhuancun()throws Exception;

	
}
