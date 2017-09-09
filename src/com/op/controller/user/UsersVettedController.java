package com.op.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.op.controller.BaseController;
import com.op.service.users.UsersService;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：UsersVettedController   
* 类描述：   会员资质审核
* 创建人：Win Zhong   
* 创建时间：2015年11月16日 上午10:40:25   
* 修改人：Win Zhong   
* 修改时间：2015年11月16日 上午10:40:25   
* 修改备注：   
* @version    
*
 */
@Controller
@RequestMapping(value="usersVetted")
public class UsersVettedController extends BaseController{

    
    @Resource(name="usersServiceImpl")
    UsersService usersService;

  
   
	
}
