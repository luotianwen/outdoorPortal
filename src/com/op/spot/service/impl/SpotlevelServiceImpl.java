package com.op.spot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.op.dao.BaseDao;
import com.op.spot.entity.Playtheme;
import com.op.spot.entity.Spotlevel;
import com.op.spot.service.SpotlevelService;
import com.op.util.Const;
import com.op.util.SerializationUtil;
import com.op.util.jedis.RedisUtil;

@Service("spotlevelServiceImpl")
public class SpotlevelServiceImpl implements SpotlevelService{
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 查询所有景点级别
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Spotlevel> spotlevelList() throws Exception {
		byte[] bs = RedisUtil.get("spotlevel".getBytes());
		List<Spotlevel> list = (List<Spotlevel>) SerializationUtil.deserialize(bs);
    	
    	if(StringUtils.isEmpty(list)){
    		list = (List<Spotlevel>) dao.findForList("spotlevelMapper.spotlevelList");
			RedisUtil.set("spotlevel".getBytes(),SerializationUtil.serialize(list));
			//1296000秒过期 即15天后自动删除缓存
			RedisUtil.expire(("spotlevel").getBytes(), (int) (Const.SPOT_TYPE_UPDATE_TIME));
    	}
		
		return list;
	}

}
