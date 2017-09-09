package com.op.service.log.order.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.op.dao.BaseDao;
import com.op.service.log.order.ActiveOrderLogService;

/**
 * 项目名：outdoorPortal
 * 类描述：订单日志ServiceImpl
 * 创建人：Yan
 * 创建时间： 2016-1-7 下午5:39:41
 * 最后修改时间：2016-1-7下午5:39:41
 */
@Service("orderLogServiceImpl")
public class ActiveOrderLogServiceImpl implements ActiveOrderLogService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
}
