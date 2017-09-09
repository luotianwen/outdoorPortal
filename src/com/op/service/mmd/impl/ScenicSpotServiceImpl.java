package com.op.service.mmd.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.entity.mmd.ScenicSpot;
import com.op.plugin.page.Page;
import com.op.service.mmd.ScenicSpotService;

/** 
 * 景点(scenicSpot)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-24 14:02:55 
 */  
@Service("scenicSpotServiceImpl")
public class ScenicSpotServiceImpl implements ScenicSpotService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 添加景点
	 * @param scenicSpot
	 * @throws Exception 
	 */
	@Override
	public void insertScenicSpot(ScenicSpot scenicSpot) throws Exception{
		dao.save("scenicSpotMapper.insertScenicSpot", scenicSpot);
	}


    /**
     * 获取景点信息
     * @param page
     * @return
     * @throws Exception
     */
	@Override
	@SuppressWarnings("unchecked")
	public List<ScenicSpot> getScenicSpot(Page<Object> page)throws Exception{
		return (List<ScenicSpot>)dao.findForList("scenicSpotMapper.getScenicSpotListPage",page);
	}
	
	/**
	 * 更新景点信息
	 * @param scenicSpot
	 * @throws Exception
	 */
	@Override
	public void updateScenicSpot(ScenicSpot scenicSpot) throws Exception{
		System.out.println("开始更新【"+scenicSpot.getZh_name()+"("+scenicSpot.getId()+")】的景点信息");
		int reluest = (int)dao.update("scenicSpotMapper.updateScenicSpot", scenicSpot);
		System.out.println(reluest);
	}
	
	
}
