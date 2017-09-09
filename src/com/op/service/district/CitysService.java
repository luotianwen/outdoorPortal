package com.op.service.district;

import java.util.List;
import java.util.Map;

import com.op.entity.district.Citys;

/**
 * 数据库中(省)接口
 * @author PYW
 * Date: 2015年12月18日 09:26:14
 */
public interface CitysService {
	/**
	 * 根据省id查询出对应的市
	 */
	List<Citys> selectCitys(String parent_code) throws Exception;
	/**
	 * 根据城市id查询出对应的区县
	 */
	List<com.op.entity.district.Countys> selectCounty(String ccId) throws Exception;
	
	/**
	 * 景点根据IP查询用户所在城市
	 * @param map 返回map集合
	 * @param city 高德地图返回城市名
	 * @throws Exception
	 */
	void selectNowCity(Map<String,String> map,String city) throws Exception;
	
}
