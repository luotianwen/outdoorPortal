package com.op.service.usercenter.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.entity.usercenter.ActiveApplicantLog;
import com.op.service.usercenter.ActiveApplicantLogService;

@Service("activeApplicantLogServiceImpl")
public class ActiveApplicantLogServiceImpl implements ActiveApplicantLogService{
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

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
	@Override
	public void saveActiveApplicantLog(String[] aa_ids,String asu_id,String activity_id,int aal_type,String remark,String user_Id,String orderState) throws Exception{
		ActiveApplicantLog activeApplicantLog = null;
		List<ActiveApplicantLog> list = new ArrayList<ActiveApplicantLog>();
		for(int i=0;i<aa_ids.length;i++){
			activeApplicantLog = new ActiveApplicantLog();
			activeApplicantLog.setAal_create_time(new Date());
			activeApplicantLog.setAa_id(aa_ids[i]);
			activeApplicantLog.setAsu_id(asu_id);
			activeApplicantLog.setActivity_id(activity_id);
			activeApplicantLog.setAal_type(aal_type);
			activeApplicantLog.setAal_remark(remark);
			activeApplicantLog.setUser_Id(user_Id);
			activeApplicantLog.setOrderState(orderState);
			list.add(activeApplicantLog);
		}
		
		
		dao.save("ActiveApplicantLogMapper.saveActiveApplicantLog", list);
	}
	
	/**
	 * 根据报名人ID查询所有操作日志
	 */
	@Override
	public List<ActiveApplicantLog> findAllByActiveApplicantId(Map<String,Object> map) throws Exception{
		List<ActiveApplicantLog> list = (List<ActiveApplicantLog>) dao.findForList("ActiveApplicantLogMapper.findAllByActiveApplicantId", map);
		return list;
	}
}
