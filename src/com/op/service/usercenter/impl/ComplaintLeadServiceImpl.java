package com.op.service.usercenter.impl;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.service.usercenter.ComplaintLeadService;

/**
 * 项目名：outdoorPortal
 * 类描述：投诉领队实现类
 * 创建人：panyongwei
 * 创建时间： 2016年1月13日 14:37:54
 */
@Service("complaintLeadServiceImpl")
public class ComplaintLeadServiceImpl implements ComplaintLeadService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 投诉领队
	 */
	@Override
	public int insertComplaintLead(Map<String, Object> map) throws Exception {
		return (int) dao.save("complaintLeadMapper.insertComplaintLead", map);
	}

	/**
	 * 根据订单id和投诉用户,查询该订单的投诉信息
	 */
	@Override
	public Map<String, Object> selectOrderId(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) dao.findForObject("complaintLeadMapper.selectOrderId", map);
	}
	
}
