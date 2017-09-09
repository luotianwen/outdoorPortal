package com.op.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



import com.op.controller.BaseController;
import com.op.dto.travels.MyTravelsResultsDTO;
import com.op.dto.usercenter.user.update.YzmDTO;
import com.op.dto.usercenter.userInfo.UsersInfo;
import com.op.dto.usercenter.usercenter.UserCenterInfo;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.recentVisit.RecentVisitService;
import com.op.service.travels.TravelsService;
import com.op.service.userFollow.UserFollowService;
import com.op.service.users.UsersService;
import com.op.service.yzm.YzmService;
import com.op.task.AvatarUploadOss;
import com.op.util.Const;
import com.op.util.MD5;
import  com.op.util.jedis.RedisUtil;
import com.op.util.mail.EmailUtil;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：TestUserController   
* 类描述：   会员管理
* 创建人：Win Zhong   
* 创建时间：2015年11月5日 下午4:46:13   
* 修改人：Win Zhong   
* 修改时间：2015年11月5日 下午4:46:13   
* 修改备注：   
* @version    
*
 */
@Controller
@RequestMapping(value="/users")
public class UsersController extends BaseController{

    /**
     * 用户
     */
    @Resource(name="usersServiceImpl")
    UsersService usersService;
    
    /**
     * 上传头像
     */
    @Resource(name="avatarUploadOssImpl")
    AvatarUploadOss avatarUploadOssImpl;
    
    /**
     * 邮箱
     */
    @Resource(name="emailUtil")
	EmailUtil emailUtil;
    
    /**
     * 手机验证码
     */
    @Resource(name="yzmServiceImpl")
	YzmService yzmServiceImpl;
    
    /**
     * 用户关注
     */
    @Resource(name="userFollowServiceImpl")
    UserFollowService userFollowServiceImpl;
    
    /**
     * 最近访问
     */
    @Resource(name="recentVisitServiceImpl")
    RecentVisitService recentVisitServiceImpl;
    
    /**
     * 游记
     */
    @Resource(name="travelsServiceImpl")
    TravelsService travelsServiceImpl;
    
    
    /**
     * 根据用户id查询用户部分信息
     * @param u_id
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectUsers")
    public ModelAndView selectUsersId() throws Exception{
    	ModelAndView mav=new ModelAndView();
    	mav.addObject("Users",usersService.selectId(this.getSessionUser().getuId()));
    	mav.setViewName("userinfo/userinfo");
    	return mav;
    }
    
    
    /**
     * 查询当前登录用户身份类型
     * @param u_id
     * @return
     * @throws Exception
     */
    @RequestMapping("/uType")
    @ResponseBody
    public int uType() throws Exception{
    	Users user = this.getSessionUser();
    	user.setuType(usersService.selectUtypeById(user.getuId()));
    	return user.getuType();
    }
    
    
    /** -----------------------------------------------我不是分割线----------------------------------------------------------- */
    
    /**
	 * 方法描述：设置个人中心首页需要的数据
	 * 返回类型：void
	 * @param mv
	 */
	public void setHeaderMap(ModelAndView mv){
		Map<String,Object> map = new HashMap<String,Object>();
		// 当前导航标签
		map.put("center", "我的玩嘛");
		mv.addObject("map", map);
	}
    
    /**
     * 根据用户ID查询用户基本信息
     * @throws Exception 
     */
    @RequestMapping("/userInfo")
    public ModelAndView userInfo(ModelAndView mv) throws Exception{
    	Users users = this.getSessionUser();
    	
    	UsersInfo usersInfo = usersService.findUserInfoById(users.getuId());
    	mv.addObject("usersInfo",usersInfo);
    	mv.addObject("left_menu_name","我的信息");
    	mv.setViewName("usercenter/user/userinfo/my-info");
    	
    	setHeaderMap(mv);
    	
    	return mv;
    }
    
    /**
   	 * 修改用户信息
   	 * @param users
   	 * @throws Exception
   	 */
    @RequestMapping(value="/updateUsers",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUsers(Users users) {
    	Users user = this.getSessionUser();
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put(Const.RESPONSE_STATE, "200");
    	map.put(Const.SUCCESS_INFO, "信息修改成功!");
    	try {
    		// 用户名非空判断
    		if(!StringUtils.isEmpty(users.getuName())){
    			// 验证用户名唯一性
    			if(usersService.validateName(users.getuName(),user.getuId())){
        			if(users.getuName().length()>10){
        				map.put(Const.RESPONSE_STATE, "500");
                		map.put(Const.ERROR_INFO, "昵称最长为10个字符");
        			}else{
        				users.setuId(user.getuId());
               			usersService.updateUsers(users);
               			
               			// 更新session用户名称
               			user.setuName(users.getuName());
        			}
        		}else{
        			map.put(Const.RESPONSE_STATE, "500");
            		map.put(Const.ERROR_INFO, "用户名已被占用!");
        		}
    		}else{
    			map.put(Const.RESPONSE_STATE, "500");
        		map.put(Const.ERROR_INFO, "用户名不能为空");
    		}
    	} catch (Exception e) {
    		logger.error("修改用户信息错误", e);
    		map.put(Const.RESPONSE_STATE, "500");
    		map.put(Const.ERROR_INFO, "修改用户失败!");
    	}
    	return map;
    }
    
    /**
     * 查询用户头像
     * @return
     * @throws Exception 
     */
    @RequestMapping(value="/selectHead")
    public ModelAndView selectHead(ModelAndView mav) throws Exception{
    	Users users = this.getSessionUser();
    	
    	mav.setViewName("usercenter/user/userportrait/my-head");
    	mav.addObject("uHeadImg",usersService.selectHead(users.getuId()));
    	mav.addObject("userName", this.getSessionUser().getuName());
    	mav.addObject("left_menu_name","我的头像");
    	
    	setHeaderMap(mav);
    	
    	return mav;
    }
    
    /**
	 * 修改用户头像
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value="/updateHead",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateHead(String uHeadImg){
		Map<String, Object> map=new HashMap<String,Object>();//返回操作信息Map
		Map<String, Object> headMap=new HashMap<String,Object>();//头像Map
		map.put(Const.RESPONSE_STATE, "200");
		map.put(Const.SUCCESS_INFO, "头像修改成功!");
		try {
			Users users = this.getSessionUser();
			//用户头像
			headMap.put("uHeadImg", uHeadImg);
			//用户id
			headMap.put("uId", users.getuId());
			users.setuHeadImg(uHeadImg);
			usersService.updateHead(headMap);
			//图片上传到阿里云
			avatarUploadOssImpl.upload(users,uHeadImg);
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "修改用户失败!");
		}
		return map;
	}
    
    /**
     * 方法描述：用户安全中心
     * @return ModelAndView
     */
    @RequestMapping(value="/userSecurity")
    @ResponseBody
    public ModelAndView userSecurity(ModelAndView mv){
    	Users users = this.getSessionUser();
    	
    	mv.addObject("users", users);
    	mv.addObject("userName", users.getuName());
    	mv.addObject("left_menu_name","账号安全");
    	mv.setViewName("usercenter/user/usersecurity/my-security");
    	
    	setHeaderMap(mv);
    	
    	return mv;
    }
    
    /**
     * 方法描述：用户绑定手机
     * @return ModelAndView
     */
    @RequestMapping(value="/bindingMobile")
    @ResponseBody
    public ModelAndView bindingMobile(ModelAndView mv){
    	Users users = this.getSessionUser();
    	
//    	if(null!=users.getuPhone()&&!"".equals(users.getuPhone())){
//    		mv.setViewName("redirect:userSecurity.html");
//    	}else{
    		mv.addObject("users", users);
        	mv.addObject("userName", users.getuName());
        	mv.addObject("left_menu_name","账号安全");
    		mv.setViewName("usercenter/user/usersecurity/bindingMobile");
//    	}
    	
    	setHeaderMap(mv);
    	
    	return mv;
    }
    
    /**
     * 方法描述：用户绑定手机号，发送验证码
     * @param session
     * @return Map<String,String>
     */
    @RequestMapping(value="/bindingMobileCaptcha")
	@ResponseBody
	public Map<String,String> bindingMobileCaptcha(HttpSession session,String phone){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			if(null!=phone&&!"".equals(phone)){
				if(!usersService.checkUserPhoneUnique(phone)){
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "该手机号码已绑定其他账号!");
				}else{
					//发送手机验证码
					yzmServiceImpl.validateYzmTime(session, map, phone, "bindingPhoneInfo",true);
				}
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "手机号输入有误！");
			}
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "验证码发送失败，请刷新后再试！");
			logger.error("bindingMobileCaptcha方法异常！！！！",e);
		}
		return map;
	}
    
    /**
     * 方法描述：用户绑定手机号，验证验证码
     * @param session
     * @param yzm
     * @return Map<String,String>
     */
	@RequestMapping(value="/bindingPhoneCaptchaVerification")
	@ResponseBody
	public Map<String,String> bindingPhoneCaptchaVerification(HttpSession session,String yzm,String phone){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			if(null!=phone&&!"".equals(phone)){
				boolean bool = false;
				//验证手机验证码
				bool = yzmServiceImpl.saveVerify(session, map, yzm, phone,"bindingPhoneInfo", true,true);
				if(bool){
					Users users = getSessionUser();
					users.setuPhone(phone);
					try {
						usersService.updateUserPhone(users);
						String html = "<div class='myinfo-list'>"+
							"<div class='bd-cel-success'>"+
							"	<i></i>恭喜！绑定成功，您已经绑定新的手机号码"+phone.substring(0, 3)+"****"+phone.substring(6, 11)+
							"</div>";
						if(null==users.getuPassword()||"".equals(users.getuPassword())){
							html += "<div class='bd-cel-success' style='margin-left: 92px;'>"+
									"	继续完善信息，<a href='users/password.html?by=mobile'>设置登录密码</a>"+
									"</div>";
						}
							
						html += "</div>";
							
						map.put("html", html);
						YzmDTO yzmdto = (YzmDTO) session.getAttribute(Const.BINDING_PHONE);
						if(yzmdto!=null){
							session.removeAttribute(Const.BINDING_PHONE);
						}
					} catch (Exception e) {
						map.put(Const.RESPONSE_STATE, "500");
						map.put(Const.ERROR_INFO, "绑定新手机号失败，请稍后再试");
					}
				}
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "请先登录！");
			}
		} catch (Exception e) {
			logger.error("bindingPhoneCaptchaVerification方法异常！！！！", e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "数据异常，请刷新后再试！");
		}
		return map;
	}
    
    /**
     * 方法描述：用户更换绑定手机
     * @return ModelAndView
     */
    @RequestMapping(value="/changeMobile")
    @ResponseBody
    public ModelAndView changeMobile(ModelAndView mv){
    	Users users = this.getSessionUser();
    	
    	String email = "";
    	if(!"".equals(users.getuEmail())&&null!=users.getuEmail()){
    		String[] url = users.getuEmail().split("@");
    		if(url.length>1){
    			email = users.getuEmail().substring(0, 2)+"****@"+url[1];
    		}else{
    			email = users.getuEmail().substring(0, 2)+"****@";
    		}
    	}
    	
    	mv.addObject("users", users);
    	mv.addObject("userName", users.getuName());
    	mv.addObject("email",email);
    	mv.addObject("left_menu_name","账号安全");
    	mv.setViewName("usercenter/user/usersecurity/changeMobile");
    	
    	setHeaderMap(mv);
    	
    	return mv;
    }
    
    /**
     * 方法描述：用户更改手机号，给当前绑定手机号发送验证码
     * @param session
     * @return Map<String,String>
     */
    @RequestMapping(value="/changeMobileCaptcha")
	@ResponseBody
	public Map<String,String> changeMobileCaptcha(HttpSession session,String type){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			Users users = (Users) session.getAttribute(Const.SESSION_USER);
	    	
			if(null!=users){
				if("1".equals(type)){
					//发送手机验证码
					yzmServiceImpl.validateYzmTime(session, map, users.getuPhone(), "oldPhoneInfo",true);
				}else if("2".equals(type)){
					String yzm = emailUtil.sendCheckEmail(users.getuEmail(),"ChangeMobile");

		    		
		    		//将邮箱验证码存入缓存
		    		String key = "changeMobile_"+users.getuEmail()+"_"+users.getuId();
		    		RedisUtil.set(key,yzm);
		    		RedisUtil.expire(key.getBytes(), 259200);//259200秒过期 即三天后自动删除缓存
		    		
		    		map.put(Const.RESPONSE_STATE, "200");
		    		map.put("url", emailUrl(users.getuEmail()));
		    		map.put(Const.SUCCESS_INFO, "验证码已经发送到你的邮箱，请注意查收");
				}else{
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "验证码发送失败，请刷新后再试！");
				}
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "请先登录！");
			}
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "验证码发送失败，请刷新后再试！");
			logger.error("changeMobileCaptcha方法异常！！！！",e);
		}
		return map;
	}
    
    /**
     * 方法描述：用户更换手机号，验证验证码
     * @param session
     * @param yzm
     * @return Map<String,String>
     */
	@RequestMapping(value="/captchaVerification")
	@ResponseBody
	public Map<String,String> captchaVerification(HttpSession session,String yzm,String type){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			Users users = (Users) session.getAttribute(Const.SESSION_USER);
	    	
			if(null!=users){
				boolean bool = false;
				String key = "";
				if("1".equals(type)){
					//验证手机验证码
					bool = yzmServiceImpl.saveVerify(session, map, yzm, users.getuPhone(),"oldPhoneInfo", true,true);
				}else if("2".equals(type)){
					//验证邮箱验证码

		    		
		    		key = "changeMobile_"+users.getuEmail()+"_"+users.getuId();
		    		String jedisyzm = RedisUtil.get(key);
					
					if(jedisyzm!=null&&jedisyzm.equals(yzm)){
						bool = true;
						map.put(Const.RESPONSE_STATE, "200");
					}else{
						map.put(Const.RESPONSE_STATE, "500");
						map.put(Const.ERROR_INFO, "验证码错误！");
					}
				}else{
					bool = false;
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "数据异常，请刷新后再试！");
				}
				if(bool){
					String html = "<div class='payPassword-box'>"+
					"	<div class='edit-payPassword'>"+
					"		<dl class='clearfix'>"+
					"			<dt>新的手机号码：</dt>"+
					"			<dd>"+
					"				<input type='text' name='newphone' id='newphone'>"+
					"				<input type='hidden' name='oldphone' id='oldphone'>"+
					"				<a style='cursor:pointer;' class='btn-code' id='getCaptchaNew'>获取验证码</a>"+
					"			</dd>"+
					"			<dd class='tips-error' id='phoneerro'></dd>"+
					"		</dl>"+
					"		<dl class='clearfix'>"+
					"			<dt>验证码：</dt>"+
					"			<dd>"+
					"				<input type='text' name='yzm' id='yzm'>"+
					"			</dd>"+
					"			<dd class='tips-error' id='yzmerro'></dd>"+
					"		</dl>"+
					"		<div class='btns'>"+
					"			<a class='btn-over' id='submittwo' href='javascript:void(0)'>下一步</a>"+
					"		</div>"+
					"	</div>"+
					"</div>";
					map.put("html", html);
					if("1".equals(type)){
						YzmDTO yzmdto = (YzmDTO) session.getAttribute(Const.UPDATE_PHONE_OBJ);
						if(yzmdto!=null){
							yzmdto.setOldYzm("");
							session.setAttribute(Const.UPDATE_PHONE_OBJ, yzmdto);
						}
					}else if("2".equals(type)){
						RedisUtil.del(key);
					}
				}
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "请先登录！");
			}
		} catch (Exception e) {
			logger.error("captchaVerification方法异常！！！！", e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "数据异常，请刷新后再试！");
		}
		return map;
	}
	
	/**
	 * 方法描述：用户更换手机号，给新手机号发送验证码
	 * @param session
	 * @param phone
	 * @return Map<String,String>
	 * @throws Exception 
	 */
	@RequestMapping(value="/newMobileCaptcha")
	@ResponseBody
	public Map<String,String> newMobileCaptcha(HttpSession session,String phone) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			Users users = (Users) session.getAttribute(Const.SESSION_USER);
	    	
			if(null!=users){
				if(users.getuPhone().equals(phone)){
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "新手机号不能与原手机号相同！");
				}else if(!usersService.checkUserPhoneUnique(phone)){
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "该手机号码已绑定其他账号!");
				}else{
					//发送手机验证码
					yzmServiceImpl.validateYzmTime(session, map	, phone, "newPhoneInfo",false);
				}
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "请先登录！");
			}
		} catch (Exception e) {
			logger.error("newMobileCaptcha方法异常!!!!!!!",e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "验证码发送失败，请刷新后再试！");
		}
		return map;
	}
	
	/**
	 * 方法描述：用户更换手机号，验证新手机号验证码
	 * @param session
	 * @param phone
	 * @param yzm
	 * @return Map<String,String>
	 */
	@RequestMapping(value="/newcaptchaVerification")
	@ResponseBody
	public Map<String,String> newcaptchaVerification(HttpSession session,String phone,String yzm){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			Users users = (Users) session.getAttribute(Const.SESSION_USER);
	    	
			if(null!=users){
				if(users.getuPhone().equals(phone)){
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "新手机号不能与原手机号相同！");
				}else{
					//验证手机验证码
					boolean bool = yzmServiceImpl.saveVerify(session, map, yzm, phone,"newPhoneInfo", true,false);
					if(bool){
						users.setuPhone(phone);
						try {
							usersService.updateUserPhone(users);
							String html = "<div class='myinfo-list'>"+
								"<div class='cel-success'>"+
								"	<i></i>恭喜！更换成功，您已经绑定新的手机号码"+phone.substring(0, 3)+"****"+phone.substring(6, 11)+
								"</div>"+
								"</div>";
								map.put("html", html);
							session.setAttribute(Const.SESSION_USER, users);
							YzmDTO yzmdto = (YzmDTO) session.getAttribute(Const.UPDATE_PHONE_OBJ);
							if(yzmdto!=null){
								yzmdto.setNewYzm("");
								session.setAttribute(Const.UPDATE_PHONE_OBJ, yzmdto);
							}
						} catch (Exception e) {
							map.put(Const.RESPONSE_STATE, "500");
							map.put(Const.ERROR_INFO, "绑定新手机号失败，请稍后再试");
						}
					}else{
						map.put(Const.RESPONSE_STATE, "500");
						map.put(Const.ERROR_INFO, "验证失败，请稍后再试！");
					}
				}
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "请先登录！");
			}
		} catch (Exception e) {
			logger.error("newcaptchaVerification方法异常！！！！",e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "验证失败，请稍后再试！");
		}
		return map;
	}
	
    /**
     * 方法描述：用户更换绑定邮箱
     * @return ModelAndView
     */
    @RequestMapping(value="/changeEmail")
    @ResponseBody
    public ModelAndView changeEmail(ModelAndView mv){
    	Users users = this.getSessionUser();
    	
    	String email = "";
    	if(!"".equals(users.getuEmail())&&null!=users.getuEmail()){
    		String[] url = users.getuEmail().split("@");
    		if(url.length>1){
    			email = users.getuEmail().substring(0, 2)+"****@"+url[1];
    		}else{
    			email = users.getuEmail().substring(0, 2)+"****@";
    		}
    	}
    	
    	mv.addObject("users", users);
    	mv.addObject("userName", users.getuName());
    	mv.addObject("email",email);
    	mv.addObject("left_menu_name","账号安全");
    	mv.setViewName("usercenter/user/usersecurity/changeEmail");
    	
    	setHeaderMap(mv);
    	
    	return mv;
    }
    
    /**
     * 方法描述：用户更改邮箱账号，给当前绑定邮箱账号发送验证码
     * @param session
     * @return Map<String,String>
     */
    @RequestMapping(value="/changeEmailCaptcha")
	@ResponseBody
	public Map<String,String> changeEmailCaptcha(HttpSession session,String type){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			Users users = (Users) session.getAttribute(Const.SESSION_USER);
			if(null!=users){
				if("1".equals(type)){
					//发送手机验证码
					yzmServiceImpl.validateYzmTime(session, map, users.getuPhone(), "updateEmail",true);
				}else if("2".equals(type)){
					String yzm = emailUtil.sendCheckEmail(users.getuEmail(),"ChangeEmail");

		    		
		    		//将邮箱验证码存入缓存
		    		String key = "changeEmail_"+users.getuEmail()+"_"+users.getuId();
		    		RedisUtil.set(key,yzm);
		    		RedisUtil.expire(key.getBytes(), 259200);//259200秒过期 即三天后自动删除缓存
		    		
		    		map.put(Const.RESPONSE_STATE, "200");
		    		map.put("url", emailUrl(users.getuEmail()));
		    		map.put(Const.SUCCESS_INFO, "验证码已经发送到你的邮箱，请注意查收");
				}else{
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "验证码发送失败，请刷新后再试！");
				}
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "请先登录！");
			}
		} catch (Exception e) {
			logger.error("changeEmailCaptcha方法异常！！！！！！！！",e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "验证码发送失败，请刷新后再试！");
		}
		return map;
	}
    
    /**
     * 方法描述：用户更换邮箱账号，验证验证码
     * @param session
     * @param yzm
     * @return Map<String,String>
     */
	@RequestMapping(value="/captchaVerificationEmail")
	@ResponseBody
	public Map<String,String> captchaVerificationEmail(HttpSession session,String yzm,String type){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			Users users = (Users) session.getAttribute(Const.SESSION_USER);
	    	
			if(null!=users){
				boolean bool = false;

				String key = "";
				if("1".equals(type)){
					//验证手机验证码
					bool = yzmServiceImpl.saveVerify(session, map, yzm, users.getuPhone(),"updateEmail", true,true);
				}else if("2".equals(type)){
					//验证邮箱验证码

		    		
		    		key = "changeEmail_"+users.getuEmail()+"_"+users.getuId();
		    		String jedisyzm = RedisUtil.get(key);
					
					if(jedisyzm!=null&&jedisyzm.equals(yzm)){
						bool = true;
						map.put(Const.RESPONSE_STATE, "200");
					}else{
						map.put(Const.RESPONSE_STATE, "500");
						map.put(Const.ERROR_INFO, "验证码错误！");
					}
				}else{
					bool = false;
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "数据异常，请刷新后再试！");
				}
				if(bool){
					String html = "<div class='payPassword-box'>"+
					"	<div class='edit-payPassword'>"+
					"		<dl class='clearfix'>"+
					"			<dt>新的邮箱账号：</dt>"+
					"			<dd>"+
					"				<input type='text' name='newemail' id='newemail'>"+
					"				<input type='hidden' name='oldemail' id='oldemail'>"+
					"				<a href='javascript:getCaptchaNew()' target='_blank' class='btn-code' id='getCaptchaNew'>获取验证码</a>"+
					"			</dd>"+
					"			<dd class='tips-error' id='emailerro'></dd>"+
					"		</dl>"+
					"		<dl class='clearfix'>"+
					"			<dt>验证码：</dt>"+
					"			<dd>"+
					"				<input type='text' name='yzm' id='yzm'>"+
					"			</dd>"+
					"			<dd class='tips-error' id='yzmerro'></dd>"+
					"		</dl>"+
					"		<div class='btns'>"+
					"			<a class='btn-over' id='submittwo' href='javascript:void(0)'>下一步</a>"+
					"		</div>"+
					"	</div>"+
					"</div>";
					map.put("html", html);
					if("1".equals(type)){
						YzmDTO yzmdto = (YzmDTO) session.getAttribute(Const.UPDATE_EMAIL_OBJ);
						if(yzmdto!=null){
							yzmdto.setOldYzm("");
							session.setAttribute(Const.UPDATE_EMAIL_OBJ, yzmdto);
						}
					}else if("2".equals(type)){
						RedisUtil.del(key);
					}
				}
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "请先登录！");
			}
		} catch (Exception e) {
			logger.error("captchaVerificationEmail方法异常！！！！！！",e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "数据异常，请刷新后再试！");
		}
		return map;
	}
	
	/**
	 * 方法描述：用户更换邮箱账号，给新邮箱账号发送验证码
	 * @param session
	 * @param phone
	 * @return Map<String,String>
	 * @throws Exception 
	 */
	@RequestMapping(value="/newEmailCaptcha")
	@ResponseBody
	public Map<String,String> newEmailCaptcha(HttpSession session,String email){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			Users users = (Users) session.getAttribute(Const.SESSION_USER);
	    	
			if(null!=users){
				if(users.getuEmail().equals(email)){
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "新邮箱账号不能与原邮箱账号相同！");
				}else if(!usersService.checkUserEmailUnique(email)){
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "该邮箱账号已绑定其他账号!");
				}else{
					String yzm = emailUtil.sendCheckEmail(email,"ChangeEmail");

		    		
		    		String key = "newEmail_"+email+"_"+users.getuId();
		    		RedisUtil.set(key,yzm);
		    		RedisUtil.expire(key.getBytes(), 259200);//259200秒过期 即三天后自动删除缓存
		    		
		    		map.put(Const.RESPONSE_STATE, "200");
		    		map.put("url", emailUrl(email));
		    		map.put(Const.SUCCESS_INFO, "验证码已经发送到你的邮箱，请注意查收");
				}
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "请先登录！");
			}
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "数据异常，请刷新后再试！");
			logger.error("newEmailCaptcha方法异常",e);
		}
		
		return map;
	}
	
	/**
	 * 方法描述：用户更换邮箱账号，验证新邮箱账号验证码
	 * @param session
	 * @param phone
	 * @param yzm
	 * @return Map<String,String>
	 */
	@RequestMapping(value="/newcaptchaVerificationEmail")
	@ResponseBody
	public Map<String,String> newcaptchaVerificationEmail(HttpSession session,String email,String yzm){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			Users users = (Users) session.getAttribute(Const.SESSION_USER);
	    	
			if(null!=users){
				if(users.getuEmail().equals(email)){
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "新邮箱账号不能与原邮箱账号相同！");
				}else{
					boolean bool = false;

		    		
		    		String key = "newEmail_"+email+"_"+users.getuId();
		    		String jedisyzm = RedisUtil.get(key);
					
					if(jedisyzm!=null&&jedisyzm.equals(yzm)){
						bool = true;
						map.put(Const.RESPONSE_STATE, "200");
					}else{
						map.put(Const.RESPONSE_STATE, "500");
						map.put(Const.ERROR_INFO, "验证码错误！");
					}
					if(bool){
						users.setuEmail(email);
						try {
							usersService.updateUserEmail(users);
							String html = "<div class='myinfo-list'>"+
								"<div class='cel-success'>"+
								"	<i></i>恭喜！更换成功，您已经绑定新的邮箱账号"+email+
								"</div>"+
								"</div>";
								map.put("html", html);
							session.setAttribute(Const.SESSION_USER, users);
							
				    		RedisUtil.del(key);
						} catch (Exception e) {
							map.put(Const.RESPONSE_STATE, "500");
							map.put(Const.ERROR_INFO, "绑定新邮箱账号失败，请稍后再试");
						}
					}else{
						map.put(Const.RESPONSE_STATE, "500");
						map.put(Const.ERROR_INFO, "验证失败，请稍后再试！");
					}
				}
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "请先登录！");
			}
		} catch (Exception e) {
			logger.error("newcaptchaVerificationEmail方法异常！！！！！！",e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "验证失败，请稍后再试！");
		}
		return map;
	}
	
	/**
     * 方法描述：修改登录密码
     * 返回类型：ModelAndView
     * @param mv
     * @return
     */
	@RequestMapping(value="/password")
	public ModelAndView goUpdatePassword(ModelAndView mv,String by){
		try {
			Users users = this.getSessionUser();
			mv.addObject("users", users);
			mv.addObject("userName", users.getuName());
	    	mv.addObject("left_menu_name","账号安全");
	    	if("mobile".equals(by)){
	    		mv.setViewName("usercenter/user/usersecurity/my-security-pass-cel");
	    	}else if("email".equals(by)){
	    		String email = "";
	        	if(!"".equals(users.getuEmail())&&null!=users.getuEmail()){
	        		String[] url = users.getuEmail().split("@");
	        		if(url.length>1){
	        			email = users.getuEmail().substring(0, 2)+"****@"+url[1];
	        		}else{
	        			email = users.getuEmail().substring(0, 2)+"****@";
	        		}
	        	}
	        	mv.addObject("email", email);
	    		mv.setViewName("usercenter/user/usersecurity/my-security-pass-email");
	    	}else{
	    		mv.setViewName("usercenter/user/usersecurity/my-security-pass-cel");
	    	}
		} catch (Exception e) {
			logger.error("修改登录密码方法异常！！！！！",e);
		}
    	
    	setHeaderMap(mv);
    	
    	return mv;
	}
	
	/**
     * 方法描述：用户修改密码，给手机发送验证码
     * @param session
     * @return Map<String,String>
     */
    @RequestMapping(value="/updatePassWordYzm")
	@ResponseBody
	public Map<String,String> updatePassWordYzm(HttpSession session,String type){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			Users users = (Users) session.getAttribute(Const.SESSION_USER);
	    	
			if(null!=users){
				if("1".equals(type)){
					yzmServiceImpl.validateYzmTime(session, map, users.getuPhone(), "updatePassword",true);
				}else if("2".equals(type)){
					try {
			    		String yzm = emailUtil.sendCheckEmail(users.getuEmail(),"UpdatePW");

			    		
			    		String key = "updatePW_"+users.getuEmail()+"_"+users.getuId();
			    		RedisUtil.set(key,yzm);
			    		RedisUtil.expire(key, 259200);//259200秒过期 即三天后自动删除缓存
			    		
			    		map.put(Const.RESPONSE_STATE, "200");
			    		map.put("url", emailUrl(users.getuEmail()));
			    		map.put(Const.SUCCESS_INFO, "验证码已经发送到你的邮箱，请注意查收");
					} catch (Exception e) {
						map.put(Const.RESPONSE_STATE, "500");
			    		map.put(Const.ERROR_INFO, "发送验证码失败，请稍后再试!");
					}
				}else{
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "数据异常，请刷新后再试！");
				}
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "请先登录！");
			}
		} catch (Exception e) {
			logger.error("updatePassWordYzm方法异常!!!!!!!",e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "数据异常，请刷新后再试！");
		}
		return map;
	}
	
    /**
     * 修改用户密码
     * @param session
     * @param captcha
     * @param newPassword
     * @return
     */
    @RequestMapping(value="/updatePassWord")
	@ResponseBody
    public Map<String,String> updatePassWord(HttpSession session,@RequestParam(value="captcha")String captcha,@RequestParam(value="newPassword")String newPassword,String type){
    	Map<String,String> map = new HashMap<String,String>();
		
		try {
			Users users = (Users) session.getAttribute(Const.SESSION_USER);
	    	
			if(null!=users){
				boolean bool = false;
				String key = "";
				if("1".equals(type)){
					//验证手机验证码
					bool = yzmServiceImpl.saveVerify(session, map, captcha, users.getuPhone(),"updatePassword", true,true);
				}else if("2".equals(type)){
					//验证邮箱验证码

		    		
		    		key = "updatePW_"+users.getuEmail()+"_"+users.getuId();
		    		String jedisyzm = RedisUtil.get(key);
					
					if(jedisyzm!=null&&jedisyzm.equals(captcha)){
						bool = true;
						map.put(Const.RESPONSE_STATE, "200");
					}else{
						map.put(Const.RESPONSE_STATE, "500");
						map.put(Const.ERROR_INFO, "验证码错误！");
					}
				}else{
					bool = false;
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "数据异常，请刷新后再试！");
				}
				
				if(bool){
					String md5 = MD5.md5(newPassword);
			    	if(!md5.equals(users.getuPassword())){
			    		users.setuPassword(md5);
				    	try {
							usersService.updateUserPassword(users);
							map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
							map.put(Const.SUCCESS_INFO, "密码修改成功！");
							session.setAttribute(Const.SESSION_USER, users);
							session.removeAttribute(Const.UPDATE_PASSWORD_OBJ);
							
							if("1".equals(type)){
								YzmDTO yzmdto = (YzmDTO) session.getAttribute(Const.UPDATE_PHONE_OBJ);
								if(yzmdto!=null){
									yzmdto.setOldYzm("");
									session.setAttribute(Const.UPDATE_PHONE_OBJ, yzmdto);
								}
							}else if("2".equals(type)){
								RedisUtil.del(key);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
				    		map.put(Const.ERROR_INFO, "修改异常，等稍后再试！");
						}
			    	}else{
			    		map.put(Const.RESPONSE_STATE, "500");
						map.put(Const.ERROR_INFO, "新密码与原密码不能相同！");
			    	}
				}
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "请先登录！");
			}
		} catch (Exception e) {
			logger.error("updatePassWord方法异常！！！！！！！！",e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "数据异常，请刷新后再试！");
		}
		return map;
    }
    
    /**
     * 绑定邮箱
     */
    @RequestMapping(value="/bindingEmail")
	@ResponseBody
	public ModelAndView bindingEmail(ModelAndView mv){
    	Users users = this.getSessionUser();
		mv.addObject("users", users);
		mv.addObject("userName", this.getSessionUser().getuName());
    	mv.addObject("left_menu_name","账号安全");
    	mv.setViewName("usercenter/user/usersecurity/bindingEmail");
    	
    	setHeaderMap(mv);
    	
    	return mv;
	}
    
	/**
     * 方法描述：用户绑定邮箱，给当前绑定手机号发送验证码
     * @param session
     * @return Map<String,String>
     */
    @RequestMapping(value="/bindingEmailCaptcha")
	@ResponseBody
	public Map<String,String> bindingEmailCaptcha(HttpSession session){
		Map<String,String> map = new HashMap<String,String>();
		
		Users users = (Users) session.getAttribute(Const.SESSION_USER);
    	
		if(null!=users){
			yzmServiceImpl.validateYzmTime(session, map, users.getuPhone(), "bindingEmail",true);
		}else{
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "请先登录！");
		}
		return map;
	}
	
    /**
     * 方法描述：用户绑定邮箱，验证验证码
     * @param session
     * @param yzm
     * @return Map<String,String>
     */
	@RequestMapping(value="/EmailCaptchaByphone")
	@ResponseBody
	public Map<String,String> EmailCaptchaByphone(HttpSession session,String yzm){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			Users users = (Users) session.getAttribute(Const.SESSION_USER);
	    	
			if(null!=users){
				//验证手机验证码
				boolean bool = yzmServiceImpl.saveVerify(session, map, yzm, users.getuPhone(),"bindingEmail", true,true);
				if(bool){
					String html = "<div class='payPassword-box'>"+
					"	<div class='edit-payPassword'>"+
					"		<dl class='clearfix'>"+
					"			<dt>邮箱：</dt>"+
					"			<dd>"+
					"				<input type='text' name='newEmail' id='newEmail'>"+
					"				<a href='javascript:getCaptchaEmail()' target='_blank' class='btn-code' id='getCaptchaNew'>获取验证码</a>"+
					"			</dd>"+
					"			<dd class='tips-error' id='emailErro'></dd>"+
					"		</dl>"+
					"		<dl class='clearfix'>"+
					"			<dt>验证码：</dt>"+
					"			<dd>"+
					"				<input type='text' name='yzm' id='yzm'>"+
					"			</dd>"+
					"			<dd class='tips-error' id='yzmerro'></dd>"+
					"		</dl>"+
					"		<div class='btns'>"+
					"			<a class='btn-over' id='submittwo' href='javascript:void(0)'>下一步</a>"+
					"		</div>"+
					"	</div>"+
					"</div>";
					map.put("html", html);
					YzmDTO yzmdto = (YzmDTO) session.getAttribute(Const.EMAIL_PHONE_OBJ);
					if(yzmdto!=null){
						yzmdto.setOldYzm("");
						session.setAttribute(Const.EMAIL_PHONE_OBJ, yzmdto);
					}
				}
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "请先登录！");
			}
		} catch (Exception e) {
			logger.error("EmailCaptchaByphone方法异常！！！！！",e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "数据异常，请刷新后再试！");
		}
		return map;
	}
    
    /**
     * 方法描述：给邮箱发送验证码
     * 返回类型：ModelAndView
     * @param mv
     * @return
     */
    @RequestMapping(value="/yzEmail")
    @ResponseBody
	public Map<String,String> yzEmail(String email){
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		Users users = this.getSessionUser();
    		String yzm = emailUtil.sendCheckEmail(email,"BindingEmail");

    		
    		String key = "bindingEmail_"+email+"_"+users.getuId();
    		RedisUtil.set(key,yzm);
    		RedisUtil.expire(key, 259200);//259200秒过期 即三天后自动删除缓存
    		
    		map.put(Const.RESPONSE_STATE, "200");
    		map.put("url", emailUrl(email));
    		map.put(Const.SUCCESS_INFO, "验证码已经发送到你的邮箱，请注意查收");
		} catch (Exception e) {
			logger.error("给邮箱发送验证码异常", e);
			map.put(Const.RESPONSE_STATE, "500");
    		map.put(Const.ERROR_INFO, "发送验证码失败，请稍后再试!");
		}
    	return map;
    }
    
    /**
	 * 方法描述：用户绑定邮箱，验证邮箱号码验证码
	 * @param session
	 * @param phone
	 * @param yzm
	 * @return Map<String,String>
	 */
	@RequestMapping(value="/EmailcaptchaVerification")
	@ResponseBody
	public Map<String,String> EmailcaptchaVerification(HttpSession session,String email,String yzm){
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			Users users = (Users) session.getAttribute(Const.SESSION_USER);
	    	
			if(null!=users){

	    		
	    		String key = "bindingEmail_"+email+"_"+users.getuId();
	    		String jedisyzm = RedisUtil.get(key);
				
				if(jedisyzm!=null&&jedisyzm.equals(yzm)){
					RedisUtil.del(key);
					users.setuEmail(email);
					try {
						String html = "<div class='myinfo-list'>"+
							"<div class='cel-success'>"+
							"	<i></i>恭喜！绑定成功，您已经绑定邮箱号码"+email+
							"</div>"+
							"</div>";
						map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
						map.put("html", html);
						
						usersService.updateUserEmail(users);
						session.setAttribute(Const.SESSION_USER, users);
					} catch (Exception e) {
						map.put(Const.RESPONSE_STATE, "500");
						map.put(Const.ERROR_INFO, "绑定邮箱失败，请稍后再试");
					}
				}else{
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "验证码错误！");
				}
			}else{
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "请先登录！");
			}
		} catch (Exception e) {
			logger.error("EmailcaptchaVerification方法异常！！！！！！",e);
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "数据异常，请刷新后再试！");
		}
		return map;
	}
    
	/**
	 * 获取邮箱后缀
	 * 判断邮箱url
	 * @param email
	 * @return
	 */
    public String emailUrl(String email){
    	String url = email.split("@")[1];
    	url = url.toLowerCase();
        if ("163.com".equals(url)) {
            return "http://mail.163.com";
        } else if ("vip.163.com".equals(url)) {
            return "http://vip.163.com";
        } else if ("126.com".equals(url)) {
            return "http://mail.126.com";
        } else if ("qq.com".equals(url) || "vip.qq.com".equals(url) || "foxmail.com".equals(url)) {
            return "http://mail.qq.com";
        } else if ("gmail.com".equals(url)) {
            return "http://mail.google.com";
        } else if ("sohu.com".equals(url)) {
            return "http://mail.sohu.com";
        } else if ("tom.com".equals(url)) {
            return "http://mail.tom.com";
        } else if ("vip.sina.com".equals(url)) {
            return "http://vip.sina.com";
        } else if ("sina.com.cn".equals(url) || "sina.com".equals(url)) {
            return "http://mail.sina.com.cn";
        } else if ("yahoo.com.cn".equals(url) || "yahoo.cn".equals(url)) {
            return "http://mail.cn.yahoo.com";
        } else if ("yeah.net".equals(url)) {
            return "http://www.yeah.net";
        } else if ("21cn.com".equals(url)) {
            return "http://mail.21cn.com";
        } else if ("hotmail.com".equals(url)) {
            return "http://www.hotmail.com";
        } else if ("sogou.com".equals(url)) {
            return "http://mail.sogou.com";
        } else if ("188.com".equals(url)) {
            return "http://www.188.com";
        } else if ("139.com".equals(url)) {
            return "http://mail.10086.cn";
        } else if ("189.cn".equals(url)) {
            return "http://webmail15.189.cn/webmail";
        } else if ("wo.com.cn".equals(url)) {
            return "http://mail.wo.com.cn/smsmail";
        } else {
            return "http://www."+url;
        }
    }
    
    
    /**
     * 用户个人中心
     */
    @RequestMapping("/center")
    public ModelAndView userCenter(String id,ModelAndView mv){
    	try {
    		Users users = userCenter(id, recentVisitServiceImpl,userFollowServiceImpl, usersService,mv);
    		
			if(users!=null){
				mv.addObject("users", users);
		    	
		    	Map<String,Object> map = new HashMap<String,Object>();
				// 当前导航标签
		    	map.put("center", "我的玩嘛");
		    	mv.addObject("map", map);
		    	
		    	//关注与最近访问分页查询
		    	Page<String> page = new Page<String>();
		    	page.setPageSize(8);
		    	
		    	//关注用户
		    	mv.addObject("follow", userFollowServiceImpl.findFollowForCenterPage(users.getuId(), page));
		    	
		    	//最近访问
		    	mv.addObject("recentVisit", recentVisitServiceImpl.findRecentVisitForCenterPage(users.getuId(), page));
		    	
		    	//用户游记
		    	List<MyTravelsResultsDTO> travelsList = travelsServiceImpl.findTravelsFroUsersCenter(users.getuId());
		    	mv.addObject("travelsList",travelsList);
		    	
		    	mv.addObject("page", page);
		    	
				mv.setViewName("usercenter/usercenter/my-home");
			}else{
				mv.setViewName("404");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv.setViewName("500");
		}
    	
    	return mv;
    }
    
    /**
     * 用户关注信息
     */
    @RequestMapping("/userFollow")
    @ResponseBody
    public Map<String,Object> userFollow(String userId,Page<String> page){
    	Map<String,Object> map = new HashMap<String,Object>();
    	
    	List<UserCenterInfo> list = null;
		try {
			page.setPageSize(8);
			list = userFollowServiceImpl.findFollowForCenterPage(userId, page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		map.put("list", list);
		map.put("page", page);
		
    	return map;
    }
    
    /**
     * 用户最近访问信息
     * 
     * @RequestMapping("/recentVisit")
     * @ResponseBody
     */
    public Map<String,Object> recentVisit(String userId,Page<String> page){
    	Map<String,Object> map = new HashMap<String,Object>();
    	
    	List<UserCenterInfo> list = null;
		try {
			page.setPageSize(8);
			list = recentVisitServiceImpl.findRecentVisitForCenterPage(userId, page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		map.put("list", list);
		map.put("page", page);
		
    	return map;
    }
    
    /**
     * 添加关注用户
     */
    @RequestMapping("/addFollow")
    @ResponseBody
    public Map<String,String> addFollow(String userId){
    	Map<String,String> map = new HashMap<String,String>();
    	try {
			userFollowServiceImpl.saveFollow(userId, this.getSessionUser().getuId());
			map.put(Const.RESPONSE_STATE,"200");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put(Const.RESPONSE_STATE,"500");
		}
    	
    	return map;
    }
    
    /**
     * 取消关注用户
     */
    @RequestMapping("/deleteFollow")
    @ResponseBody
    public Map<String,String> deleteFollow(String userId){
    	Map<String,String> map = new HashMap<String,String>();
    	try {
			userFollowServiceImpl.deleteFollow(userId, this.getSessionUser().getuId());
			map.put(Const.RESPONSE_STATE,"200");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put(Const.RESPONSE_STATE,"500");
		}
    	
    	return map;
    }
    
}
