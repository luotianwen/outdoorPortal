package com.op.service.usercenter.impl;

import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.op.dao.BaseDao;
import com.op.service.usercenter.ComplaintImageService;

/**
 * 投诉领队图(实现类)
 * @author PYW
 * Date: 2015年12月21日 15:06:12
 */
@Service("complaintImageServiceImpl")
public class ComplaintImageServiceImpl implements ComplaintImageService{
	
	@Resource(name="baseDaoImpl")
	private BaseDao dao;

	/**
	 * 新增投诉领队图片
	 */
	@Override
	public void insertCI(Map<String, Object> map) throws Exception {
		dao.save("complaintImageMapper.insertCI", map);
	}

	
}
