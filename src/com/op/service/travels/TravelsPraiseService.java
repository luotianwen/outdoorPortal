package com.op.service.travels;

import java.util.Map;

import org.springframework.stereotype.Service;


@Service("travelsPraiseService")
public interface TravelsPraiseService {
	
	/**
	 * 方法描述：游记点赞
	 * 返回类型：void
	 * @param map
	 * @throws Exception
	 */
	void updatePraise(java.util.Map<String,Object> map) throws Exception;
	
	/**
	 * 方法描述：查询用户是否点赞
	 * 返回类型：boolean
	 * @param map
	 * @return
	 * @throws Exception
	 */
	boolean currentUserIsPraise(Map<String,Object> map) throws Exception;
}
