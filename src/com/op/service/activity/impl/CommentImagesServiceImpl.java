package com.op.service.activity.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.service.activity.CommentImagesService;

@Service("commentImagesServiceImpl")
public class CommentImagesServiceImpl implements CommentImagesService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
}
