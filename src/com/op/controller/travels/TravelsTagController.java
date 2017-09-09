package com.op.controller.travels;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.entity.travels.TravelsTag;
import com.op.entity.users.Users;
import com.op.service.travels.TravelsTagService;
import com.op.util.Const;
/** 
 * 游记标签(travelsTag)Controller
 * @author 世峰户外商城 
 * @version Revision: 1.00 
 *  Date: 2016-04-27 15:19:27 
 */ 
@Controller
@RequestMapping(value="/travelsTag")
public class TravelsTagController {
	
	@Resource(name="travelsTagServiceImpl")
	private TravelsTagService travelsTagServiceImpl;
	
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 方法描述：添加游记对应的标签
	 * 返回类型：Map<String,Object>
	 * @param tag
	 * @return
	 */
	@RequestMapping(value="/insert")
	@ResponseBody
	public Map<String,String> insert(TravelsTag tag){
		Users user = (Users) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USER);
		tag.setTt_create_user(user.getuId());
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			travelsTagServiceImpl.insert(tag);
			map.put("ttId", tag.getTt_id()+"");
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			log.error("添加标签异常！", e);
			map.put(Const.RESPONSE_STATE, 500+"");
			map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
		}
		return map;
	}
	
	/**
	 * 方法描述：删除标签
	 * 返回类型：Map<String,Object>
	 * @param tag
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String,String> delete(@RequestParam(value="tagId") String tagId){
		Users user = (Users) SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USER);
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", user.getuId());
		map.put("tt_id", tagId);
		try {
			travelsTagServiceImpl.delete(map);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			log.error("删除标签异常！", e);
			map.put(Const.RESPONSE_STATE, 500+"");
			map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
		}
		return map;
	}
}
