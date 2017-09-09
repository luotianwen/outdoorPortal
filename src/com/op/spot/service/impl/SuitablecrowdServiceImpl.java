package com.op.spot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.op.dao.BaseDao;
import com.op.spot.entity.Spotlevel;
import com.op.spot.entity.Suitablecrowd;
import com.op.spot.service.SuitablecrowdService;
import com.op.util.Const;
import com.op.util.SerializationUtil;
import com.op.util.jedis.RedisUtil;

@Service("suitablecrowdServiceImpl")
public class SuitablecrowdServiceImpl implements SuitablecrowdService{
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 查询所有适用人群
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Suitablecrowd> suitablecrowdList() throws Exception {
		byte[] bs = RedisUtil.get("suitablecrowd".getBytes());
		List<Suitablecrowd> list = (List<Suitablecrowd>) SerializationUtil.deserialize(bs);
    	
    	if(StringUtils.isEmpty(list)){
    		list = (List<Suitablecrowd>) dao.findForList("suitablecrowdMapper.suitablecrowdList");
			RedisUtil.set("suitablecrowd".getBytes(),SerializationUtil.serialize(list));
			//1296000秒过期 即15天后自动删除缓存
			RedisUtil.expire(("suitablecrowd").getBytes(), (int) (Const.SPOT_TYPE_UPDATE_TIME));
    	}
		
		return list;
	}

}
