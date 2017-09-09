package com.op.service.activity.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.op.dao.BaseDao;
import com.op.dto.activity.equipment.InsertEquipmentDTO;
import com.op.entity.activity.Activity;
import com.op.entity.activity.equipment.ActiveEquipment;
import com.op.service.activity.ActiveEquipmentService;
import com.op.util.Const;


@Service("activeEquipmentServiceImpl")
public class ActiveEquipmentServiceImpl implements ActiveEquipmentService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 方法描述：添加装备
	 * 实现接口：@see com.op.service.activity.ActiveEquipmentService#insertEquipment(com.op.dto.activity.equipment.InsertEquipmentDTO, java.util.Map)
	 * @param dto
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void insertEquipment(InsertEquipmentDTO dto, Map<String, String> map) throws Exception {
		
		// 删除
		if("d".equals(dto.getCrud())){
			deleteEquipment(dto,map);
		}else{
			if(!createEquipment(dto,map)){
				return;
			}
		}
		
		map.clear();
		map.put(Const.DATA_NUM, dto.getDataNum()+"");
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		map.put(Const.ACTIVITY_ID, dto.getActivityId());
		
	}
	
	/**
	 * 方法描述：删除
	 * 返回类型：void
	 * @param dto
	 * @param map
	 * @throws Exception
	 */
	void deleteEquipment(InsertEquipmentDTO dto, Map<String, String> map) throws Exception {
		if(StringUtils.isEmpty(dto.getActivityId())){
			dto.setDataNum(0);
			dto.setActivityId("");
			return;
		}else{

			// 根据活动ID和用户ID删除历史数据
			dao.delete("activeEquipmentMapper.deleteByActivityAndUserId", dto);
			
			List<ActiveEquipment> aes = new ArrayList<ActiveEquipment>();
			for(ActiveEquipment item:dto.getAes()){
				if(!StringUtils.isEmpty(item.getAe_name()) && !StringUtils.isEmpty(item.getAe_description())){
					aes.add(item);
				}
			}
			if(aes.size()>0){
				dto.setAes(aes);
				
				// 生成新的装备数据
				dao.save("activeEquipmentMapper.insert", dto);
				dto.setDataNum(aes.size());
				
			}else{
				dto.setDataNum(0);
			}
		}
	}
	/**
	 * 方法描述：新增
	 * 返回类型：void
	 * @param dto
	 * @param map
	 * @throws Exception
	 */
	boolean createEquipment(InsertEquipmentDTO dto, Map<String, String> map) throws Exception {
		
		// TODO Auto-generated method stub
		if(!dataCheck(dto)){
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "数据不能为空哦！");
			return false;
		}
		
		if(StringUtils.isEmpty(dto.getActivityId())){
			Activity activity = new Activity();
			activity.setCreateUser(dto.getUserId());
			dao.save("activityMapper.saveNullActivity", activity);
			dto.setActivityId(activity.getId()+"");
			
			// 生成装备的同时生成活动ID
			dao.save("activeEquipmentMapper.insertGenerateActiveId", dto);
			
			// 生辰草稿活动数据
			//dao.save("activityMapper.insertCaoGaoStateActiveByEquipment", dto);
		}else{
			// 根据活动ID和用户ID删除历史数据
			dao.delete("activeEquipmentMapper.deleteByActivityAndUserId", dto);
			
			// 生成装备数据
			dao.save("activeEquipmentMapper.insert", dto);
			
			dto.setDataNum(dto.getAes().size());
		}
		return true;
	}
	
	/**
	 * 方法描述：数据验证
	 * 返回类型：boolean
	 * @param dto
	 * @return
	 */
	boolean dataCheck(InsertEquipmentDTO dto){
		
		List<ActiveEquipment> aes = new ArrayList<ActiveEquipment>();
		for(ActiveEquipment item:dto.getAes()){
			if(!StringUtils.isEmpty(item.getAe_name()) && !StringUtils.isEmpty(item.getAe_description())){
				aes.add(item);
			}
		}
		if(aes.size()<=0){
			return false;
		}
		dto.setAes(aes);
		return true;
	}
}
