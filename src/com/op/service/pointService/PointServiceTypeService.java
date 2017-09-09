package com.op.service.pointService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.entity.pointService.PointServiceType;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：场馆
 * 创建人：Yan
 * 创建时间： 2016-6-7
 * modification list：
 * =============================================================
 */
@Service("pointServiceTypeService")
public interface PointServiceTypeService {
	
	/**
	 * 方法描述：根据父ID查询场馆类型
	 * 返回类型：List<VenueType>
	 * @param parentId
	 * @return
	 */
	public List<PointServiceType> selectVenueOneTypeByParentId()throws Exception;
	
	/**
	 * 方法描述：获取一级类型对应的全部子类型集合
	 * 返回类型：List<VenueType>
	 * @param oneTypeId
	 * @return
	 * @throws Exception
	 */
	List<PointServiceType> getChildrens(int oneTypeId)throws Exception;
	
	/**
	 * 根据类型ID查询类型数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	PointServiceType findPointServiceTypeById(String id) throws Exception;
	
	
}
