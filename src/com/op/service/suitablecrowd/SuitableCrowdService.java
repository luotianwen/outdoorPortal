package com.op.service.suitablecrowd;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.entity.suitablecrowd.SuitableCrowd;

/**
 * 数据库中(适合人群)接口
 * @author PYW
 * Date:2015年12月18日 09:19:11
 */
@Service("suitableCrowdService")
public interface SuitableCrowdService {
	
	/**
	 * 列表(全部)
	 */
	public List<SuitableCrowd> selectList() throws Exception;

}
