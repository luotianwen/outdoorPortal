package com.op.service.channels;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.entity.channels.Channels;

/** 
 * 频道信息表(channels)接口
 * @author 潘永威
 * @version Revision: 1.00 
 *  Date: 2015-11-30 11:16:49 
 */ 
@Service("channelsService")
public interface ChannelsService {
	
	/**
	 * 列表(全部)
	 */
	public List<Channels> selectList() throws Exception;

}
