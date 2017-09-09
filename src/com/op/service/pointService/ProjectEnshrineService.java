package com.op.service.pointService;

import org.springframework.stereotype.Service;

/** 
 * 项目收藏(projectEnshrine)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-06-27 15:25:43 
 */  
@Service("projectEnshrineService")
public interface ProjectEnshrineService {

	/**
	 * 项目收藏数量
	 * @param id 项目ID
	 * @return
	 * @throws Exception
	 */
	String projectCollection(String id) throws Exception;

	/**
	 * 收藏
	 * @param id 项目ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	void addConllection(String id,String userId) throws Exception;
	
	/**
	 * 取消收藏
	 * @param id 项目ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	void cancelCollection(String id,String userId) throws Exception;
	
	/**
	 * 是否收藏
	 * @param id 项目ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	boolean isCollection(String id,String userId) throws Exception;
	
	
}
