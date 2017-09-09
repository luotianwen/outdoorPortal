package com.op.service.classification.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.entity.classification.Classification;
import com.op.service.classification.ClassificationService;

/** 
 * 活动类型表(activity)实现类
 * @author 潘永威
 * @version Revision: 1.00 
 *  Date: 2015年12月14日 15:18:53
 */ 
@Service("classificationServiceImpl")
public class ClassificationServiceImpl implements ClassificationService {

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 列表(全部)
	 */
	@Override
	public List<Classification> selectList() throws Exception {
		return (List<Classification>) dao.findForList("classificationMapper.selectList", null);
	}

	/**
	 * 方法描述：根据类型级别查询活动类型
	 * 返回类型：List<Classification>
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Classification> findTypesByLevel(int level) throws Exception {
		return (List<Classification>) dao.findForList("classificationMapper.findTypesByLevel", level);
	}

}
