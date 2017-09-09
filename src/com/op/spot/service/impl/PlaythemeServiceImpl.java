package com.op.spot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.op.dao.BaseDao;
import com.op.dto.screening.ScreeningDTO;
import com.op.spot.entity.Playtheme;
import com.op.spot.service.PlaythemeService;
import com.op.util.Const;
import com.op.util.SerializationUtil;
import com.op.util.jedis.RedisUtil;

@Service("playthemeServiceImpl")
public class PlaythemeServiceImpl implements PlaythemeService{
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 查询所有游玩主题
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Playtheme> playthemeByPidList() throws Exception {
		byte[] bs = RedisUtil.get("playtheme".getBytes());
		List<Playtheme> list = (List<Playtheme>) SerializationUtil.deserialize(bs);
    	
    	if(StringUtils.isEmpty(list)){
    		list = (List<Playtheme>) dao.findForList("playthemeMapper.playthemeByPidList");
			RedisUtil.set("playtheme".getBytes(),SerializationUtil.serialize(list));
			//1296000秒过期 即15天后自动删除缓存
			RedisUtil.expire(("playtheme").getBytes(), (int) (Const.SPOT_TYPE_UPDATE_TIME));
    	}
		 
		return list;
	}

	@Override
	public List<Playtheme> playthemeList() throws Exception {
		return (List<Playtheme>) dao.findForList("playthemeMapper.playthemeList");
	}

}
