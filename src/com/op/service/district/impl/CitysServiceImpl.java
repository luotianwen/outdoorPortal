package com.op.service.district.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;






import com.op.dao.BaseDao;
import com.op.entity.district.Citys;
import com.op.entity.district.Countys;
import com.op.entity.district.Provinces;
import com.op.service.district.CitysService;
import com.op.util.Const;
import com.op.util.SerializationUtil;
import  com.op.util.jedis.RedisUtil;

/**
 * 市、区县实现类
 * @author PYW
 * Date: 2015年12月18日 09:26:14
 */
@Service("citysServiceImpl")
public class CitysServiceImpl implements CitysService{
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 根据省id查询出对应的市
	 */
	@Override
	public List<Citys> selectCitys(String parent_code) throws Exception {
		byte[] key = (parent_code+"_CITY_LIST_").getBytes();

		byte[] citys = RedisUtil.get(key);
		
		if(citys != null){
			return (List<Citys>) SerializationUtil.deserialize(citys);
		}
		
		List<Citys> list = (List<Citys>) dao.findForList("citysMapper.selectCitys", parent_code);
		RedisUtil.set(key,SerializationUtil.serialize(list));
		
		return list;
	}

	/**
	 * 方法描述：根据城市id查询出对应的区县
	 * 注意：新的构造已经改为[市]方法查询出对应的区县信息，利用js进行解析
	 * 实现接口：@see com.op.service.district.CitysService#selectCounty(java.lang.String)
	 * @param ccId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Countys> selectCounty(String ccId) throws Exception {
		byte[] key = (ccId+"_COUNTY_LIST_").getBytes();

		byte[] countys = RedisUtil.get(key);
		
		if(countys != null){
			return (List<Countys>) SerializationUtil.deserialize(countys);
		}
		
		List<Countys> list = (List<Countys>) dao.findForList("countysMapper.selectCountyByCityCode", ccId);
		RedisUtil.set(key, SerializationUtil.serialize(list));
		return list;
	}

	/**
	 * 景点根据IP查询用户所在城市
	 * @param map 返回map集合
	 * @param city 高德地图返回城市名
	 * @throws Exception
	 */
	public void selectNowCity(Map<String,String> map,String name) throws Exception{
		Citys citys = (Citys) dao.findForObject("citysMapper.findNowCity", name);
		
		if(citys==null){
			switch (name) {
			case "北京市":
				name = "北京";
				break;
			case "天津市":
				name = "天津";
				break;
			case "上海市":
				name = "上海";
				break;
			case "重庆市":
				name = "重庆";
				break;
			case "台湾省":
				name = "台湾";
				break;

			default:
				break;
			}
			
			Provinces province = (Provinces) dao.findForObject("provincesMapper.findNowProvince", name);
			if(province!=null){
				map.put("province", province.getCode());
				map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			}
		}else{
			map.put("city", citys.getCode());
			map.put("province", citys.getParent_code());
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		}
		
	}
	
	
}
