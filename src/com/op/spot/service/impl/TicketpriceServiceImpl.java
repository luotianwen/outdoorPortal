package com.op.spot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.op.dao.BaseDao;
import com.op.spot.entity.Suitablecrowd;
import com.op.spot.entity.Ticketprice;
import com.op.spot.service.TicketpriceService;
import com.op.util.Const;
import com.op.util.SerializationUtil;
import com.op.util.jedis.RedisUtil;

@Service("ticketpriceServiceImpl")
public class TicketpriceServiceImpl implements TicketpriceService{
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 查询所有门票价格
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Ticketprice> ticketpriceList() throws Exception {
		byte[] bs = RedisUtil.get("ticketprice".getBytes());
		List<Ticketprice> list = null;
    	
    	if(StringUtils.isEmpty(list)){
    		list = (List<Ticketprice>) dao.findForList("ticketpriceMapper.ticketpriceList");
			RedisUtil.set("ticketprice".getBytes(),SerializationUtil.serialize(list));
			//1296000秒过期 即15天后自动删除缓存
			RedisUtil.expire(("ticketprice").getBytes(), (int) (Const.SPOT_TYPE_UPDATE_TIME));
    	}
		
		return list;
	}

}
