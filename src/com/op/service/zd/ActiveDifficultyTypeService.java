package com.op.service.zd;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.entity.zd.ActiveDifficultyType;

/**
 * 项目名：outdoorPortal
 * 类描述：活动难度等级
 * 创建人：Yan
 * 创建时间： 2015-12-11 下午5:49:58
 * 最后修改时间：2015-12-11下午5:49:58
 */
@Service("activeDifficultyTypeService")
public interface ActiveDifficultyTypeService {
	
	/**
	 * 方法描述：查询所有
	 * 返回类型：ActiveDifficultyType
	 * @return
	 */
	public List<ActiveDifficultyType> selectList() throws Exception;
}
