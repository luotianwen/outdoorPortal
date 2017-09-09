package com.op.service.usercenter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.op.entity.usercenter.ActiveApplicantLog;

@Service("activeApplicantLogService")
public interface ActiveApplicantLogService {

	/**
	 * 新增订单操作日志
	 * @param aa_ids 报名人ID
	 * @param asu_id 订单ID
	 * @param activity_id 活动ID
	 * @param aal_type 操作日志类型
	 * @param remark 备注
	 * @param userId 操作人ID
	 * @param orderState 订单状态
	 * @throws Exception
	 */
	void saveActiveApplicantLog(String[] aa_ids,String asu_id,String activity_id,int aal_type,String remark,String user_Id,String orderState) throws Exception;
	
	/**
	 * 根据报名人ID查询所有操作日志
	 */
	List<ActiveApplicantLog> findAllByActiveApplicantId(Map<String,Object> map) throws Exception;
}
