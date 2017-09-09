package com.op.service.zd;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.entity.zd.ActiveChildrenAge;

/**
 * 项目名：outdoorPortal
 * 类描述：儿童年龄段
 * 创建人：Yan
 * 创建时间： 2015-12-11 下午5:49:04
 * 最后修改时间：2015-12-11下午5:49:04
 */
@Service("activeChildrenAgeService")
public interface ActiveChildrenAgeService {
	/**
	 * 方法描述：查询所有
	 * 返回类型：ActiveDifficultyType
	 * @return
	 */
	public List<ActiveChildrenAge> selectList() throws Exception;
}
