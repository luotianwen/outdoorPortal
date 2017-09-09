package com.op.service.pointService;

import org.springframework.stereotype.Service;

/** 
 * 项目点赞(pojectPraise)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-06-27 15:25:43 
 */  
@Service("pojectPraiseService")
public interface PojectPraiseService {

	/**
	 * 项目点赞数量
	 * @param id 项目ID
	 * @return
	 * @throws Exception
	 */
	String projectLike(String id) throws Exception;

	/**
	 * 点赞
	 * @param id 项目ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	void addLike(String id,String userId) throws Exception;
	
	/**
	 * 取消点赞
	 * @param id 项目ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	void cancellike(String id,String userId) throws Exception;
	
	
}
