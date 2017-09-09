package com.op.service.classification;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.entity.classification.Classification;

/** 
 * 活动类型信息表(activity)接口
 * @author 潘永威
 * @version Revision: 1.00 
 *  Date: 2015年12月14日 15:18:53
 */ 
@Service("classificationService")
public interface ClassificationService {
	
	/**
	 * 列表(全部)
	 */
	
	public List<Classification> selectList() throws Exception;
	
	/**
	 * 方法描述：根据类型级别查询活动类型
	 * 返回类型：List<Classification>
	 * @return
	 * @throws Exception
	 */
	public List<Classification> findTypesByLevel(int level) throws Exception;

}
