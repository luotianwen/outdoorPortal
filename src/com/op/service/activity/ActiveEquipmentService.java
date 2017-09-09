package com.op.service.activity;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.op.dto.activity.equipment.InsertEquipmentDTO;


@Service("activeEquipmentService")
public interface ActiveEquipmentService {
	
	/**
	 * 方法描述：添加装备
	 * 返回类型：void
	 * @param dto
	 * @param map
	 * @throws Exception
	 */
	void insertEquipment(InsertEquipmentDTO dto,Map<String,String> map)throws Exception;
	
}
