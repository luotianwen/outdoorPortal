package com.op.service.images.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.entity.images.Images;
import com.op.service.images.ImagesService;

/** 
 * 图片信息表(images)实现类
 * @author 潘永威
 * @version Revision: 1.00 
 *  Date: 2015年12月14日 15:18:53
 */ 
@Service("imagesServiceImpl")
public class ImagesServiceImpl implements ImagesService {
	
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 新增图片
	 */
	@Override
	public void insertImages(Images images) throws Exception {
		dao.save("imagesMapper.insertImages",images);
	}
	
	/**
	 * 获取没有上传阿里云OSS云存储的图片
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Images> notUploadOSSImages() throws Exception{
		return (List<Images>)dao.findForList("imagesMapper.notUploadOSSImages", null);
	}
	
}
