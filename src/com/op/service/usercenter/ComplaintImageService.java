package com.op.service.usercenter;


import java.util.Map;

import org.springframework.stereotype.Service;


/**
 * 投诉领队图片(接口)
 * @author PYW
 * Date: 2015年12月21日 15:06:12
 */
@Service("complaintImageService")
public interface ComplaintImageService {
	/**
	 * 新增投诉领队图片
	 * @param map
	 * @throws Exception
	 */
	void insertCI(Map<String, Object> map) throws Exception;
}
