package com.op.service.suitablecrowd.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.entity.suitablecrowd.SuitableCrowd;
import com.op.service.suitablecrowd.SuitableCrowdService;

/**
 * 数据库中(适合人群)实现类
 * @author PYW
 * Date:2015年12月18日 09:19:11
 */
@Service("suitableCrowdServiceImpl")
public class SuitableCrowdServiceImpl implements SuitableCrowdService {
	
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 查询出所有的适合人群列表
	 */
	@Override
	public List<SuitableCrowd> selectList() throws Exception {
		return (List<SuitableCrowd>) dao.findForList("suitablecrowdMapper.selectList", null);
	}

}
