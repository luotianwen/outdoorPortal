package com.op.service.district;

import java.util.List;

import com.op.entity.district.Provinces;

/**
 * 数据库中(省)接口
 * @author PYW
 * Date: 2015年12月18日 09:26:14
 */
public interface ProvincesService {
	/**
	 * 查询出所有的省
	 */
	List<Provinces> selectProvinces() throws Exception;
}
