package com.op.service.pointService.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



import com.op.dao.BaseDao; 
import com.op.service.pointService.PojectPraiseService;
import  com.op.util.jedis.RedisUtil;

/** 
 * 项目点赞(pojectPraise)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-06-27 15:25:43 
 */  
@Service("pojectPraiseServiceImpl")
public class PojectPraiseServiceImpl implements PojectPraiseService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 项目点赞数量
	 * @param id 项目ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public String projectLike(String id) throws Exception{
		return (String) dao.findForObject("pojectPraiseMapper.projectLike", id);
	}
	
	/**
	 * 点赞
	 * @param id 项目ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	@Override
	public void addLike(String id,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		dao.save("pojectPraiseMapper.addLike", map);
		

		if(StringUtils.isEmpty(RedisUtil.get("projectLike_"+id))){
			String count = projectLike(id);
			RedisUtil.set("projectLike_"+id, count);
			RedisUtil.expire(("projectLike_"+id), (int) (1.5*24*60*60));
		}else{
			RedisUtil.set("projectLike_"+id, Integer.valueOf(RedisUtil.get("projectLike_"+id))+1+"");
			RedisUtil.expire(("projectLike_"+id), (int) (1.5*24*60*60));
		}
	}
	
	
	/**
	 * 取消点赞
	 * @param id 项目ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	@Override
	public void cancellike(String id,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		dao.delete("pojectPraiseMapper.cancellike", map);
		

		if(StringUtils.isEmpty(RedisUtil.get("projectLike_"+id))){
			String count = projectLike(id);
			RedisUtil.set("projectLike_"+id, count);
			RedisUtil.expire(("projectLike_"+id), (int) (1.5*24*60*60));
		}else{
			RedisUtil.set("projectLike_"+id, Integer.valueOf(RedisUtil.get("projectLike_"+id))-1+"");
			RedisUtil.expire(("projectLike_"+id), (int) (1.5*24*60*60));
		}
	}
	
}
