package com.op.service.travels;

import java.util.Map;

import org.springframework.stereotype.Service;


@Service("travelsFollowService")
public interface TravelsFollowService {
	
	/**
	 * 方法描述：收藏游记
	 * 返回类型：void
	 * @param map
	 * @throws Exception
	 */
	void updateFollowTravels(Map<String,Object> map) throws Exception;
	
	/**
	 * 方法描述：查询用户是否收藏
	 * 返回类型：boolean
	 * @param map
	 * @return
	 * @throws Exception
	 */
	boolean currenUsertIsFollow(Map<String,Object> map) throws Exception;

	
	/**
	 * 取消收藏游记
	 */
	void deleteCollection(Map<String,String> map) throws Exception;
	
}
