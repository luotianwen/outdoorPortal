package com.op.service.behaviour;

public interface BrowsingRecordService {

	/**
	 * 
	 * @Description: 添加用户浏览记录
	 * @param userId  用户id
	 * @param recordId 活动或景点或线路的id
	 * @param recordType 浏览类型 (1:活动;2:景点;3:线路;)
	 * @throws Exception   
	 * @return void  
	 * @author WinZhong
	 * @date 2016年1月4日 下午4:59:08
	 */
	void addBrowsingRecord(String userId,String recordId,int recordType)throws Exception;
	
	/**
	 * 
	 * @Description: 保存用户的浏览记录到数据库
	 * @param userId
	 * @throws Exception   
	 * @return void  
	 * @author WinZhong
	 * @date 2016年1月4日 下午5:28:53
	 */
	void saveBrowsingRecord(String userId)throws Exception;
	
}
