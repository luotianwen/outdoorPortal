package com.op.service.usercenter;


import java.util.Map;

import org.springframework.stereotype.Service;


/**
 * 项目名：outdoorPortal
 * 类描述：投诉领队接口
 * 创建人：panyongwei
 * 创建时间： 2016年1月13日 14:38:26
 */
@Service("complaintLeadService")
public interface ComplaintLeadService {
	
	/**
	 * 投诉领队
	 */
	int insertComplaintLead(Map<String, Object> map) throws Exception;
	/**
	 * 根据订单id和投诉用户,查询该订单的投诉信息
	 */
	Map<String, Object> selectOrderId(Map<String, Object> map) throws Exception;
}
