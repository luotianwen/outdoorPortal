package com.op.service.zd;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.entity.zd.Traffic;


@Service("trafficService")
public interface TrafficService {
	
	/**
	 * 方法描述：查询所有的交通工具
	 * 返回类型：List<Traffic>
	 * @return
	 */
	List<Traffic> allTraffic()throws Exception;
}
