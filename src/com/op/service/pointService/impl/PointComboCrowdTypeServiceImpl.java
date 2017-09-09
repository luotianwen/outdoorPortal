package com.op.service.pointService.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



import com.op.dao.BaseDao;
import com.op.entity.pointService.PointComboCrowdType;
import com.op.service.pointService.PointComboCrowdTypeService;
import com.op.util.Const;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;

/**
 * 地点项目/套餐适合人群字典表(pointComboCrowdType)接口实现类
 * 
 * @author Win Zhong
 * @version Revision: 1.00 Date: 2016-06-24 13:42:52
 */
@Service("pointComboCrowdTypeServiceImpl")
public class PointComboCrowdTypeServiceImpl implements
		PointComboCrowdTypeService {

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 刷新字典缓存
	 * 
	 * @throws Exception
	 */
	@Override
	public Map<String, String> shuaxinhuancun() throws Exception {

		Map<String,String> map = (Map<String,String>)SerializationUtil.deserialize(RedisUtil.get(Const.POINT_SERVICE_CROWD.getBytes()));
		
		if(StringUtils.isEmpty(map)){
			// 获取全部字典数据
			List<PointComboCrowdType> pointComboCrowdTypeList = (List<PointComboCrowdType>) dao .findForList("pointComboCrowdTypeMapper.getAll", null);
			
			map = new HashMap<String,String>();
			for (PointComboCrowdType pointComboCrowdType : pointComboCrowdTypeList) {
				map.put(pointComboCrowdType.getId()+"", pointComboCrowdType.getPcct_describe());
			}
			// 删除就缓存
			RedisUtil.del("POINT_SERVICE_CROWD".getBytes());
			// 存入缓存
			RedisUtil.set("POINT_SERVICE_CROWD".getBytes(), SerializationUtil.serialize(map));
		}
		
		return map;
	}

}
