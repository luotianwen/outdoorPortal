package com.op.service.travels.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.entity.travels.TravelsTag;
import com.op.service.travels.TravelsTagService;


@Service("travelsTagServiceImpl")
public class TravelsTagServiceImpl implements TravelsTagService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 方法描述：添加标签
	 * 实现接口：@see com.op.service.travels.TravelsTagService#insert(com.op.entity.travels.TravelsTag)
	 * @param tag
	 * @throws Exception
	 */
	@Override
	public void insert(TravelsTag tag) throws Exception {
		dao.save("travelsTagMapper.insertByTravelsId", tag);
	}

	/**
	 * 方法描述：删除标签
	 * 实现接口：@see com.op.service.travels.TravelsTagService#delete(java.util.Map)
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void delete(Map<String, String> map) throws Exception {
		dao.delete("travelsTagMapper.deleteByIdAndUserId", map);
	}
	
}
