package com.op.service.pointService.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



import com.op.dao.BaseDao; 
import com.op.service.pointService.ProjectEnshrineService;
import  com.op.util.jedis.RedisUtil;

/** 
 * 项目收藏(projectEnshrine)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-06-27 15:25:43 
 */  
@Service("projectEnshrineServiceImpl")
public class ProjectEnshrineServiceImpl implements ProjectEnshrineService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 项目收藏数量
	 * @param id 项目ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public String projectCollection(String id) throws Exception{
		
		return (String) dao.findForObject("projectEnshrineMapper.projectCollection", id);
	}
	
	/**
	 * 收藏
	 * @param id 项目ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	@Override
	public void addConllection(String id,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		
		dao.save("projectEnshrineMapper.addConllection", map);
		

		if(StringUtils.isEmpty(RedisUtil.get("projectCollection_"+id))){
			String count = projectCollection(id);
			RedisUtil.set("projectCollection_"+id, count);
			RedisUtil.expire(("projectCollection_"+id), (int) (1.5*24*60*60));
		}else{
			RedisUtil.set("projectCollection_"+id, Integer.valueOf(RedisUtil.get("projectCollection_"+id))+1+"");
			RedisUtil.expire(("projectCollection_"+id), (int) (1.5*24*60*60));
		}
	}
	
	/**
	 * 取消收藏
	 * @param id 项目ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	@Override
	public void cancelCollection(String id,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		dao.delete("projectEnshrineMapper.cancelCollection", map);
		

		if(StringUtils.isEmpty(RedisUtil.get("projectCollection_"+id))){
			String count = projectCollection(id);
			RedisUtil.set("projectCollection_"+id, count);
			RedisUtil.expire(("projectCollection_"+id), (int) (1.5*24*60*60));
		}else{
			RedisUtil.set("projectCollection_"+id, Integer.valueOf(RedisUtil.get("projectCollection_"+id))-1+"");
			RedisUtil.expire(("projectCollection_"+id), (int) (1.5*24*60*60));
		}
	}
	
	
	/**
	 * 是否收藏
	 * @param id 项目ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	@Override
	public boolean isCollection(String id,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		int count = (int)dao.findForObject("projectEnshrineMapper.isCollection", map);
		
		if(count>0){
			return true;
		}else{
			return false;
		}
	}
	
}
