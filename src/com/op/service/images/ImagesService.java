package com.op.service.images;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.entity.images.Images;

/** 
 * 图片信息表(images)接口
 * @author 潘永威
 * @version Revision: 1.00 
 *  Date: 2015年12月14日 15:18:53
 */ 
@Service("imagesService")
public interface ImagesService {
	/*
	 * 新增活动图片信息
	 */
	public void insertImages(Images images) throws Exception;
	
	/**
	 * 获取没有上传阿里云OSS云存储的图片
	 * @return
	 * @throws Exception
	 */
	List<Images> notUploadOSSImages() throws Exception;

	
}
