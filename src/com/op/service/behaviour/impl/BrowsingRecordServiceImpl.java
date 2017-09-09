package com.op.service.behaviour.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.op.dao.BaseDao;
import com.op.service.behaviour.BrowsingRecordService;
import com.op.util.DateUtil;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;
/**
 * 
 * ClassName: BrowsingRecordServiceImpl 
 * @Description: 用户浏览记录
 * @author WinZhong
 * @date 2016年1月5日 下午4:06:28
 */
@Service("browsingRecordServiceImpl")
public class BrowsingRecordServiceImpl implements BrowsingRecordService {

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
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
	@Override
	public void addBrowsingRecord(String userId,String recordId,int recordType)throws Exception{

		String key = "BrowsingRecord_"+userId;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("recordId", recordId);
		map.put("recordType", recordType);
		RedisUtil.sadd(key.getBytes(),SerializationUtil.serialize(map));
		RedisUtil.expire(key.getBytes(), 86400);//86400秒过期 即一天后自动删除缓存
	}
	/**
	 * 
	 * @Description: 保存用户的浏览记录到数据库
	 * @param userId
	 * @throws Exception   
	 * @return void  
	 * @author WinZhong
	 * @date 2016年1月4日 下午5:28:53
	 */
	@Override
	public void saveBrowsingRecord(String userId)throws Exception{

		String key = "BrowsingRecord_"+userId;
		// 获取缓存里的用户浏览记录
		//byte[] bs = RedisUtil.get(key.getBytes());
		Set<byte[]> byteList = RedisUtil.smembers(key.getBytes());
		if(!StringUtils.isEmpty(byteList) && byteList.size()>0){
			List<Map<String, Object>> newByteSet = new ArrayList<Map<String, Object>>();
				//只获取最新的99条浏览记录
				Iterator<byte[]> it = byteList.iterator();//先迭代出来
				int i = 0;
				String ymd = DateUtil.getDay();
		        while(it.hasNext()){//遍历  
		        	Map<String, Object> map = (Map<String, Object>)SerializationUtil.deserialize(it.next());
		        	map.put("userId", userId);
		        	map.put("careteTime", ymd);
		        	newByteSet.add(map);  
		        	System.err.println("===========浏览记录==========="+map);
		            i++;
		            if(i == 100){
		            	break;
		            }
		        }  
			if(newByteSet.size()>0){
			  //保存浏览记录
			   dao.save("browsingRecordMapper.saveBrowsingRecord", newByteSet);
			   //删除旧的相同的浏览记录
			   dao.delete("browsingRecordMapper.deleteSameOldBrowsingRecord", userId);
			   //保留最新的99条记录，其余删除
			   dao.delete("browsingRecordMapper.deleteSurplusBrowsingRecord", userId);
			   RedisUtil.del(key.getBytes());
			}
		}
 
	}
	 
	 
}
