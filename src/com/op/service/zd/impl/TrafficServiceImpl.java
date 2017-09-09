package com.op.service.zd.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.op.dao.BaseDao;
import com.op.entity.zd.Traffic;
import com.op.service.zd.TrafficService;


@Service("trafficServiceImpl")
public class TrafficServiceImpl implements TrafficService{
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 方法描述：查询所有的交通工具
	 * 实现接口：@see com.op.service.zd.TrafficService#allTraffic()
	 * @return
	 */
	@Override
	public List<Traffic> allTraffic()throws Exception {
		// TODO Auto-generated method stub
		
		return (List<Traffic>) dao.findForList("trafficMapper.allTraffic");
	}
}
