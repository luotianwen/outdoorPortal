package com.op.service.travels.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao; 
import com.op.service.travels.TravelsPhotosService;

/** 
 * 游记图片(travelsPhotos)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-18 14:30:32 
 */  
@Service("travelsPhotosServiceImpl")
public class TravelsPhotosServiceImpl implements TravelsPhotosService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 添加游记图片
	 * @param map （包含：游记id 图片路径）
	 * @return 返回图片id
	 * @throws Exception
	 */
    @Override
	public String addPhoto(Map<String, Object> map)throws Exception{
		
    	return null;
    }

	/**
	 * 修改游记图片
	 * @param map （包含：游记id 图片路径）
	 * @throws Exception
	 */
    @Override
	public void updatePhoto(Map<String, Object> map)throws Exception{
    	 
     }

	/**
	 * 删除游记图片
	 * @param map （包含：游记id 图片路径）
	 * @throws Exception
	 */
    @Override
	public void delPhoto(Map<String, Object> map)throws Exception{
    	
    }
    
    /**
     * 查询关联地点的图片数量
     * @param travelsId 游记ID
     * @return
     * @throws Exception
     */
    @Override
	public int findTravelsPhotosCountByTagId(String travelsId)throws Exception{
    	return (int) dao.findForObject("travelsPhotosMapper.findTravelsPhotosCountByTagId", travelsId);
    }

    /**
     * 方法描述：批量修改图片标签
     * 实现接口：@see com.op.service.travels.TravelsPhotosService#updateImgTag(java.util.Map)
     * @param map
     * @throws Exception
     */
	@Override
	public void updateImgTag(Map<String, Object> map) throws Exception {
		dao.update("travelsPhotosMapper.batchUpdateImgTag", map);
	}
}
