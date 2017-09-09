package com.op.service.travels.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;




import com.op.dao.BaseDao;
import com.op.dto.travels.TravelsHotDataDTO;
import com.op.service.travels.TravelsFollowService;
import com.op.util.Const;
import com.op.util.Tools;
import  com.op.util.jedis.RedisUtil;


@Service("travelsFollowServiceImpl")
public class TravelsFollowServiceImpl implements TravelsFollowService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	


	/**
	 * 方法描述：收藏游记
	 * 实现接口：@see com.op.service.travels.TravelsService#followTravels(java.util.Map)
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateFollowTravels(Map<String, Object> map) throws Exception {
		

		
		String key = map.get("id")+"_"+map.get("userId")+"_follow";
		
    	// 获取该用户是否已经查询过数据库   缓存key为"游记ID+用户ID"
		if(RedisUtil.get(key) == null){
			// 查询是否收藏
			int i = (int) dao.findForObject("travelsFollowMapper.countUserFollowTravels", map);
			
			// 没有收藏过
			if(i==0){
				// 1、insert收藏表
				dao.save("travelsFollowMapper.insertTravelsFollow", map);
				
				// 2、更新游记表收藏量
				dao.update("travelsFollowMapper.updateTravelsFollowNum", map);
				
				// 3、更新缓存中游记的收藏量
				updateRedis(map.get("id")+"",1);
				
				map.clear();
				map.put("alert", "收藏成功");
				map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			}else{
				map.clear();
				map.put("alert", "您已经收藏过了,如想取消收藏,请前往个人中心!");
				map.put(Const.RESPONSE_STATE, "300");
			}
			
			// 标识该用户已经查询过数据库，再次收藏经发现已经查询过数据库就不在进行操作了
			RedisUtil.set(key, "yes");
			
			//单位（秒）过期 即一天后自动删除缓存
			RedisUtil.expire(key, 24*60*60);
		}else{
			// 因为没有取消收藏操作，所以不再进行更新操作
			map.clear();
			map.put("alert", "您已经收藏过了,如想取消收藏,请前往个人中心!");
			map.put(Const.RESPONSE_STATE, "300");
		}
		
		
	}
	/**
	 * 方法描述：更新缓存中游记的收藏量
	 * 返回类型：void
	 * @param travelsId
	 * @param type 1:收藏 2：取消收藏
	 * @param jedis
	 */
	void updateRedis(String id,int type)throws Exception{
		// 缓存不存在数据
		if(RedisUtil.hkeys(Const.TRAVELSHOTDATAKEY+id).isEmpty()){
			// 获取该游记数据
			TravelsHotDataDTO th = (TravelsHotDataDTO)dao.findForObject("travelsMapper.findHotData", id);
			if(type==1){
				th.setCollection_count(Integer.valueOf(th.getCollection_count())+1+"");
			}else{
				th.setCollection_count(Integer.valueOf(th.getCollection_count())-1+"");
			}
			
			//存入热数据到缓存
			RedisUtil.hmset(Const.TRAVELSHOTDATAKEY+id,Tools.transBean2MapString(th));
			//108000秒过期 即一天半后自动删除缓存
			RedisUtil.expire(Const.TRAVELSHOTDATAKEY+id, (int) (1.5*24*60*60));
			
		}else{
			List<String> hotData = RedisUtil.hmget(Const.TRAVELSHOTDATAKEY+id,"collection_count");
			if(type==1){
				RedisUtil.hset(Const.TRAVELSHOTDATAKEY+id, "collection_count",Integer.valueOf(hotData.get(0))+1+"");
			}else{
				RedisUtil.hset(Const.TRAVELSHOTDATAKEY+id, "collection_count",Integer.valueOf(hotData.get(0))-1+"");
			}
			
		}
	}
	
	/**
	 * 方法描述：查询用户是否收藏
	 * 实现接口：@see com.op.service.travels.TravelsFollowService#currenUsertIsFollow(java.util.Map)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean currenUsertIsFollow(Map<String, Object> map) throws Exception {
		int i = (int) dao.findForObject("travelsFollowMapper.countUserFollowTravels", map);
		return i>0?true:false;
	}
	
	
	/**
	 * 取消收藏游记
	 */
	@Override
	public void deleteCollection(Map<String,String> map) throws Exception{
		int i = (int) dao.delete("travelsMapper.deleteCollection", map);
		
		if(i>0){
			// 删除缓存中收藏状态
			String key = map.get("id")+"_"+map.get("userId")+"_follow";
			RedisUtil.del(key);
			
			// 更新游记表收藏量
			dao.update("travelsFollowMapper.updateTravelsFollowNumReduce", map);
			
			// 更新缓存中游记的收藏量
			updateRedis(map.get("id")+"",2);
		}
	}
	
	
}
