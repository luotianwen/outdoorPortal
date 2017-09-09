package com.op.controller.activity.equipment;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.dto.activity.equipment.InsertEquipmentDTO;
import com.op.entity.users.Users;
import com.op.service.activity.ActiveEquipmentService;
import com.op.util.Const;

/** 
 * 活动装备(activeEquipment)Controller
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-03-28 15:25:10 
 */ 
@Controller
@RequestMapping(value="/activeEquipment")
public class ActiveEquipmentController {
	
	@Resource(name="activeEquipmentServiceImpl")
	private ActiveEquipmentService activeEquipmentServiceImpl;
	
	
	Logger log = Logger.getLogger(this.getClass());
 
	/**
	 * 方法描述：新增活动装备
	 * 返回类型：Map<String,String>
	 * @param dto
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/equipment")
	@ResponseBody
	public Map<String,String> insertEquipment(InsertEquipmentDTO dto,
			HttpSession session){
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		dto.setUserId(user.getuId());
		
		Map<String,String> map = new HashMap<String,String>();

		try {
			activeEquipmentServiceImpl.insertEquipment(dto, map);
		} catch (Exception e) {
			map.clear();
			log.error("添加活动线路异常！", e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, map.get(Const.ERROR_INFO)==null?"发生异常，请稍后操作或联系我们客服，给您带来的不变敬请谅解!":map.get(Const.ERROR_INFO));
		
		}
		
		
		return map;
	}
	
	
	
	
}
