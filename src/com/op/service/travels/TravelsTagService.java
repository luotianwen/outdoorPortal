package com.op.service.travels;

import org.springframework.stereotype.Service;


@Service("travelsTagService")
public interface TravelsTagService {
	
	/**
	 * 方法描述：添加标签
	 * 返回类型：void
	 * @param tag
	 * @throws Exception
	 */
	public void insert(com.op.entity.travels.TravelsTag tag) throws Exception;
	
	/**
	 * 方法描述：删除标签
	 * 返回类型：void
	 * @param tag
	 * @throws Exception
	 */
	public void delete(java.util.Map<String,String> map) throws Exception;
}
