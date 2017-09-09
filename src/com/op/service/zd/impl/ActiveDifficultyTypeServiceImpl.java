package com.op.service.zd.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.op.dao.BaseDao;
import com.op.entity.zd.ActiveDifficultyType;
import com.op.service.zd.ActiveDifficultyTypeService;

/**
 * 项目名：outdoorPortal
 * 类描述：活动难度等级
 * 创建人：Yan
 * 创建时间： 2015-12-11 下午5:50:22
 * 最后修改时间：2015-12-11下午5:50:22
 */
@Service("activeDifficultyTypeServiceImpl")
public class ActiveDifficultyTypeServiceImpl implements ActiveDifficultyTypeService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 方法描述：查询所有
	 * 实现接口：@see com.op.service.zd.ActiveDifficultyTypeService#selectList()
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<ActiveDifficultyType> selectList() throws Exception {
		// TODO Auto-generated method stub
		return (List<ActiveDifficultyType>) dao.findForList("activeDifficultyTypeMapper.selectList", null);
	}
}
