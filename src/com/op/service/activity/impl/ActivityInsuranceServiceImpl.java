package com.op.service.activity.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.op.dao.BaseDao;
import com.op.entity.activity.Activity;
import com.op.service.activity.ActivityInsuranceService;
import com.op.util.Const;


@Service("activityInsuranceServiceImpl")
public class ActivityInsuranceServiceImpl implements ActivityInsuranceService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 方法描述：添加活动保险
	 * 实现接口：@see com.op.service.activity.ActivityInsuranceService#insertActiveInsurance(java.util.Map)
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void insertActiveInsurance(com.op.dto.activity.baoxian.BaoxianDTO dto,Map<String,String> map) throws Exception {
		String ai_activity_id =dto.getActivityId();
		
		// 费用说明，重组保险信息
		dto.setAcp_comment(dto.getChanpin()+"<br/>"+dto.getJihua()+"<br/>"+dto.getQixian());
		
		// 保存最新的数据并且生成活动ID
		if(StringUtils.isEmpty(ai_activity_id)){
			Activity activity = new Activity();
			activity.setCreateUser(dto.getUserId());
			dao.save("activityMapper.saveNullActivity", activity);
			dto.setActivityId(activity.getId()+"");
			
			// 将保险保存在费用包括内
			dao.save("activeCorrelationCostMapper.saveInsuranceGeneraActivityId", dto);
			
			// 保存草稿活动数据
			//dao.save("activityMapper.insertCaoGaoStateActiveByInsurance", dto);
			
			map.put("activityId",activity.getId()+"");
			// 保存活动保险数据
			dao.save("activityInsuranceMapper.insertGenerateActiveId", map);
		}else{
			
			// 删除历史保险信息
			dao.delete("activeCorrelationCostMapper.deleteInsuranceBaoxian", dto);
			
			// 查询活动价格
			Double price = (Double) dao.findForObject("activityMapper.selectPriceById", dto.getActivityId());
			
			// 包括费用的价格
			Double baokuofeiyong = (Double) dao.findForObject("activeCorrelationCostMapper.selectSumCostPrice", dto.getActivityId());
			
			
			// 如果活动价格不存在则不用考虑价格冲突
			if(price != null){
				// 1:包括费用不存在，只需要判断保险价格是否大于活动价格;
				// 2:包括费用存在，那么要判断保险价格和包括费用总和是否大于活动价格
				if((baokuofeiyong == null && dto.getJiage() > price) || (baokuofeiyong != null && (dto.getJiage()+baokuofeiyong) > price)){
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "保险价格+费用包括总额已经大于了活动价格，请修改活动价格!");
					throw new Exception();
				}
				
			}
			if(price != null 
					&& ((baokuofeiyong == null && dto.getJiage() > price) 
							|| (baokuofeiyong != null && (dto.getJiage()+baokuofeiyong) > price))){
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "保险价格+费用包括总额已经大于了活动价格，请修改活动价格!");
				throw new Exception();
			}
			
			// 将保险保存在费用包括内
			dao.save("activeCorrelationCostMapper.saveInsurance", dto);
			
			// 更新活动保险ID
			dao.update("activityMapper.updateInsureId", dto);
			
			// 保存最新的保险信息
			//dao.save("activityInsuranceMapper.insert", map);
		}
		
		map.put(Const.ACTIVITY_ID, dto.getActivityId());
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
	}

	/**
	 * 方法描述：根据活动ID和创建用户和保险ID删除活动保险
	 * 实现接口：@see com.op.service.activity.ActivityInsuranceService#deleteByActiveIdAndUserId(java.util.Map)
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void deleteByActiveIdAndUserId(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		//dao.delete("activityInsuranceMapper.deleteByActiveIdAndUserId", map);
		// 更改活动保险ID和强制保险信息
		// 更新活动保险ID
		dao.update("activityMapper.delInsureId", map);
		
		map.clear();
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
	}
}
