package com.op.service.pointService.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



import com.op.dao.BaseDao;
import com.op.entity.pointService.PointServiceType;
import com.op.service.pointService.PointServiceTypeService;
import com.op.util.Const;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：场馆
 * 创建人：Yan
 * 创建时间： 2016-6-7
 * modification list：
 * =============================================================
 */
@Service("pointServiceTypeServiceImpl")
public class PointServiceTypeServiceImpl implements PointServiceTypeService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 方法描述：根据父ID查询地点服务类型
	 * 实现接口：@see com.op.service.venue.VenueTypeService#selectVenueOneType(long)
	 * @param parentId
	 * @return
	 */
	@Override
	public List<PointServiceType> selectVenueOneTypeByParentId()throws Exception {
		byte[] key = Const.POINT_SERVICE_TYPES.getBytes();

		byte[] types = RedisUtil.get(key);
		
		if(types != null){
			return (List<PointServiceType>) SerializationUtil.deserialize(types);
		}
		
		List<PointServiceType> list = (List<PointServiceType>) dao.findForList("pointServiceTypeMapper.oneTypeAll",null);
		
		for(Iterator<PointServiceType> it=list.iterator();it.hasNext();){
			PointServiceType pst = it.next();
			RedisUtil.set((Const.POINT_SERVICE_TYPES+pst.getOneTypeId()).getBytes(), SerializationUtil.serialize(pst.getList()));
			pst.setList(null);
		}
		
		if(list.size()>0){
			RedisUtil.set(key, SerializationUtil.serialize(list));
		}
		
		return list;
	}

	/**
	 * 方法描述：获取一级类型对应的全部子类型集合
	 * 实现接口：@see com.op.service.venue.VenueTypeService#getChildrens(int)
	 * @param oneTypeId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PointServiceType> getChildrens(int oneTypeId) throws Exception {
		// 子数据类型key
		byte[] key = (Const.POINT_SERVICE_TYPES+oneTypeId).getBytes();

		byte[] types = RedisUtil.get(key);
		return (List<PointServiceType>) SerializationUtil.deserialize(types);
	}
	
	/**
	 * 根据类型ID查询类型数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public PointServiceType findPointServiceTypeById(String id) throws Exception{
		return (PointServiceType) dao.findForObject("pointServiceTypeMapper.findPointServiceTypeById", id);
	}

	
}
