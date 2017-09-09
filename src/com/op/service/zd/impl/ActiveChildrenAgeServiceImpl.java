package com.op.service.zd.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.entity.zd.ActiveChildrenAge;
import com.op.service.zd.ActiveChildrenAgeService;

/**
 * 项目名：outdoorPortal
 * 类描述：儿童年龄段
 * 创建人：Yan
 * 创建时间： 2015-12-11 下午5:50:10
 * 最后修改时间：2015-12-11下午5:50:10
 */
@Service("activeChildrenAgeServiceImpl")
public class ActiveChildrenAgeServiceImpl implements ActiveChildrenAgeService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 方法描述：查询所有
	 * 实现接口：@see com.op.service.zd.ActiveChildrenAgeService#selectList()
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<ActiveChildrenAge> selectList() throws Exception {
		// TODO Auto-generated method stub
		return (List<ActiveChildrenAge>) dao.findForList("activeChildrenAgeMapper.selectList", null);
	}
	

	
}
