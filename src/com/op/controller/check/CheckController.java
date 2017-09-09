package com.op.controller.check;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.controller.BaseController;
import com.op.service.users.UsersService;
/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：CheckController   
* 类描述：   验证处理 Controller
* 创建人：Win Zhong   
* 创建时间：2015年11月18日 下午3:24:10   
* 修改人：Win Zhong   
* 修改时间：2015年11月18日 下午3:24:10   
* 修改备注：   
* @version    
*
 */
@Controller
@RequestMapping(value="check")
public class CheckController extends BaseController{

	
    @Resource(name="usersServiceImpl")
    UsersService usersService;
	
	
	
	/**
	 * 验证用户名邮箱手机号是否有重复
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="user")
	@ResponseBody
	public boolean checkUser() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();;
		boolean bl = true; 
		map.clear();
		map.put("uId", this.getParameter("uId"));
		if(!StringUtils.isEmpty(this.getParameter("uName"))){//验证用户名是否重复
			map.put("uName", this.getParameter("uName"));
			if(usersService.checkUserInfo(map) > 0){
				bl = false;
			}
		}else if(!StringUtils.isEmpty(this.getParameter("uEmail"))){//验证邮箱是否重复
			map.put("uEmail", this.getParameter("uEmail"));
			if(usersService.checkUserInfo(map) > 0){
				bl = false;
			}
		}else if(!StringUtils.isEmpty(this.getParameter("uPhone"))){//验证手机号码是否重复
			map.put("uPhone", this.getParameter("uPhone"));
			if(usersService.checkUserInfo(map) > 0){
				bl = false;
			}
		}else{
			bl = true;
		}
		
		
		return bl;
	}
	
	
}
