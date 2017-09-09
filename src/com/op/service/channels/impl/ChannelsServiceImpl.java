package com.op.service.channels.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.entity.channels.Channels;
import com.op.service.channels.ChannelsService;

/** 
 * 频道信息表(channels)实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2015年12月14日 15:18:53
 */ 
@Service("channelsServiceImpl")
public class ChannelsServiceImpl implements ChannelsService {
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	/**
	 * 列表(全部)
	 */
	@Override
	public List<Channels> selectList() throws Exception {
		return (List<Channels>) dao.findForList("channelsMapper.selectList", null);
	}

}
