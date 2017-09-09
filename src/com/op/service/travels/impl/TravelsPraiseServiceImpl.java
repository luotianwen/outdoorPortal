package com.op.service.travels.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



import com.op.dao.BaseDao;
import com.op.dto.travels.TravelsHotDataDTO;
import com.op.service.travels.TravelsPraiseService;
import com.op.util.Const;
import com.op.util.Tools;
import  com.op.util.jedis.RedisUtil;


@Service("travelsPraiseServiceImpl")
public class TravelsPraiseServiceImpl implements TravelsPraiseService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	/**
	 * 方法描述：游记点赞
	 * 实现接口：@see com.op.service.travels.TravelsPraiseService#praise(java.util.Map)
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updatePraise(Map<String, Object> map) throws Exception {
		

		
		String key = map.get("id")+"_"+map.get("userId")+"_praise";
		
    	// 获取该用户是否已经查询过数据库   缓存key为"游记ID+用户ID"
		if(RedisUtil.get(key) == null){
			// 查询是否点过赞
			int i = (int) dao.findForObject("travelsPraiseMapper.countByTravelsIdAndUserId", map);
			
			// 没有点过赞
			if(i==0){
				// 1、insert点赞表
				dao.save("travelsPraiseMapper.insertTravelsPraise", map);
				
				// 2、更新游记点赞量
				dao.update("travelsPraiseMapper.updateTravelsPraiseNum", map);
				
				// 3、更新缓存中游记的点赞量
				updateRedis(map.get("id")+"");
				
				map.clear();
				map.put("alert", "点赞成功");
				map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			}else{
				map.clear();
				map.put("alert", "您已经点过赞了");
				map.put(Const.RESPONSE_STATE, "300");
			}
			
			// 标识该用户已经查询过数据库，再次点赞经发现已经查询过数据库就不在进行操作了
			RedisUtil.set(key, "yes");
			
			//单位（秒）过期 即一天后自动删除缓存
			RedisUtil.expire(key, 24*60*60);
			
		}else{
			map.clear();
			// 因为没有取消点赞操作，所以不再进行更新操作
			map.put("alert", "您已经点过赞了");
			map.put(Const.RESPONSE_STATE, "300");
		}
		
	}
	
	/**
	 * 方法描述：更新缓存中游记的点赞量
	 * 返回类型：void
	 * @param travelsId
	 * @param jedis
	 */
	void updateRedis(String id)throws Exception{
		// 缓存不存在数据
		if(RedisUtil.hkeys(Const.TRAVELSHOTDATAKEY+id).isEmpty()){
			// 获取该游记数据
			TravelsHotDataDTO th = (TravelsHotDataDTO)dao.findForObject("travelsMapper.findHotData", id);
			th.setLike_count((Integer.valueOf(th.getLike_count())+1)+"");
			
			//存入热数据到缓存
			RedisUtil.hmset(Const.TRAVELSHOTDATAKEY+id,Tools.transBean2MapString(th));
			//108000秒过期 即一天半后自动删除缓存
			RedisUtil.expire(Const.TRAVELSHOTDATAKEY+id, (int) (1.5*24*60*60));
			
		}else{
			List<String> hotData = RedisUtil.hmget(Const.TRAVELSHOTDATAKEY+id,"like_count");
			RedisUtil.hset(Const.TRAVELSHOTDATAKEY+id, "like_count",""+(Integer.valueOf(hotData.get(0))+1));
		}
	}

	/**
	 * 方法描述：查询用户是否点赞
	 * 实现接口：@see com.op.service.travels.TravelsPraiseService#currentUserIsPraise(java.util.Map)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean currentUserIsPraise(Map<String, Object> map) throws Exception {
		int i = (int) dao.findForObject("travelsPraiseMapper.countByTravelsIdAndUserId", map);
		return i>0?true:false;
	}
}
