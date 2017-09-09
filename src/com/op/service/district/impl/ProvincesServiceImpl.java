package com.op.service.district.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



import com.op.dao.BaseDao;
import com.op.entity.district.Provinces;
import com.op.service.district.ProvincesService;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;

/**
 * 数据库中(省)实现类
 * @author PYW
 * Date: 2015年12月18日 09:26:14
 */
@Service("provincesServiceImpl")
public class ProvincesServiceImpl implements ProvincesService{
	@Resource(name="baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 查询出所有的省
	 */
	@Override
	public List<Provinces> selectProvinces() throws Exception {
		byte[] key = "CHINA_PROVINCE_LIST".getBytes();
		

		byte[] provinces = RedisUtil.get(key);
		
		if(provinces != null){
			return (List<Provinces>) SerializationUtil.deserialize(RedisUtil.get(key));
		}

		List<Provinces> list = (List<Provinces>) dao.findForList("provincesMapper.selectProvinces");
		
		// 封装市信息
		for(Iterator<Provinces> iterator = list.iterator();iterator.hasNext();){
			Provinces province = iterator.next();
			RedisUtil.set((province.getCode()+"_CITY_LIST_").getBytes(),SerializationUtil.serialize(province.getCitys()));
			province.setCitys(null);
		}
		
		RedisUtil.set(key, SerializationUtil.serialize(list));
		return list;
	}
}
