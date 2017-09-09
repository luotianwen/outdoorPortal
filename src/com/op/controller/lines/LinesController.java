package com.op.controller.lines;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.controller.BaseController;
import com.op.entity.users.Users;
import com.op.service.lines.LinesService;
import com.op.util.Const;

/** 
 * 活动线路表(Lines)实体类
 * @author 潘永威
 * @version Revision: 1.00 
 *  Date: 2015-12-02 16:15:08
 */
@Controller
@RequestMapping(value="lines")
public class LinesController extends BaseController {
	
	@Resource(name="linesServiceImpl")
	LinesService linesServiceImpl;
	
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 方法描述：根据id和创建用户删除线路数据
	 * 返回类型：Map<String,String>
	 * @param l_id
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/del")
	@ResponseBody
	public Map<String,String> delete(@RequestParam(value="id") String l_id,
			HttpSession session){
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			map.put("l_id", l_id);
			map.put("l_create_user", user.getuId());
			linesServiceImpl.delete(map);
			
			map.clear();
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			log.error("根据id和创建用户删除线路数据异常", e);
			map.clear();
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "删除失败，请稍后尝试或者联系客服，给您造成的不便敬请谅解!");
		}
		
		return map;
	}
}

