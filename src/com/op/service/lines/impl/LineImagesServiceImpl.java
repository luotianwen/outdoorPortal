package com.op.service.lines.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.entity.lines.LineImages;
import com.op.service.lines.LineImagesService;


@Service("lineImagesServiceImpl")
public class LineImagesServiceImpl implements LineImagesService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	
	/**
	 * 获取没有上传阿里云OSS云存储的图片
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<LineImages> notUploadOSSImages() throws Exception{
		return (List<LineImages>)dao.findForList("lineImagesMapper.notUploadOSSImages", null);
	}
	
	/**
	 * 更改图片上传阿里云OSS云存储的状态
	 * @return
	 * @throws Exception
	 */
	@Override
	public void updateUploadOSSState(List<LineImages> lineImageList) throws Exception{
		dao.update("lineImagesMapper.updateUploadOSSState", lineImageList);
	}	
	
}
