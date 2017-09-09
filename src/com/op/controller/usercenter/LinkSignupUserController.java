package com.op.controller.usercenter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.op.controller.BaseController;
import com.op.entity.usercenter.LinkSignUpUser;
import com.op.service.usercenter.LinkSignUpUserService;
import com.op.util.Const;

/**
 * 常用报名人管理
 * @author PYW
 * Date: 2015年12月21日 16:55:41
 */
@RequestMapping("/SignupUser")
@Controller
public class LinkSignupUserController extends BaseController {
	@Resource(name="linkSignUpUserServiceImpl")
	private LinkSignUpUserService lsus;
	
	/**
	 * 查询常用报名人
	 * @throws Exception 
	 */
	@RequestMapping("/selectUser")
	public ModelAndView seletUser() throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("listUser",lsus.selectUser(this.getSessionUser().getuId()));
		mav.addObject("userName", this.getSessionUser().getuName());
		mav.addObject("left_menu_name","常用报名人");
		
    	mav.setViewName("usercenter/user/applicants/Applicants");
    	
    	Map<String,Object> map = new HashMap<String,Object>();
		// 当前导航标签
		map.put("center", "我的玩嘛");
		mav.addObject("map", map);
    	
		return mav;
	}
	/**
	 * 根据常用人lsuu_id和创建人查找出相应的列
	 * @param lsu
	 * @throws Exception
	 */
	@RequestMapping(value="/selectUserId",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectUserId(LinkSignUpUser Lsuu,@RequestParam("lsuu_id") String lsuu_id){
		Map<String, Object> map=new HashMap<String,Object>();//返回操作信息
		
		Map<String, Object> userMap=new HashMap<String,Object>();//常用联系人Map
		userMap.put("lsuu_create_user", this.getSessionUser().getuId());//用户id
		userMap.put("lsuu_id", lsuu_id);//常用联系人id
		
		try {
			map.put("lsuu",lsus.selectUserId(userMap));
			map.put(Const.RESPONSE_STATE, "200");
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "当前操作失败,请稍后再试!");
		}
		return map;
	}
	/**
	 * 新增常用报名人
	 * @param lsu
	 * @throws Exception
	 */
	@RequestMapping(value="/insertUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertUser(LinkSignUpUser lsuu){
		Map<String,Object> map=new HashMap<String,Object>();//返回操作信息
		map.put(Const.RESPONSE_STATE, "200");//默认执行此操作成功!
		map.put(Const.SUCCESS_INFO, "添加成功!");
		lsuu.setLsuu_create_user(this.getSessionUser().getuId());
		lsuu.setLsuu_update_user(this.getSessionUser().getuId());
		try {
			lsus.insertUser(lsuu);
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "操作失败!");
		}
		return map;
	}
	/**
	 * 修改常用报名人
	 * @param lsu
	 * @throws Exception
	 */
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateUser(LinkSignUpUser lsuu){
		Map<String,Object> map=new HashMap<String,Object>();//返回操作信息
		map.put(Const.RESPONSE_STATE, "200");//默认执行此操作成功!
		map.put(Const.SUCCESS_INFO, "修改成功!");
		lsuu.setLsuu_create_user(this.getSessionUser().getuId());
		lsuu.setLsuu_update_user(this.getSessionUser().getuId());
		try {
			lsus.updateUser(lsuu);
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "操作失败!");
		}
		return map;
	}
	/**
	 * 删除常用报名人
	 * @param lsu
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteUser(String lsuu_id){
		Map<String,Object> map=new HashMap<String,Object>();//返回操作信息
		map.put(Const.RESPONSE_STATE, "200");//默认执行此操作成功!
		map.put(Const.SUCCESS_INFO, "删除成功!");
		
		Map<String, Object> userMap=new HashMap<String,Object>();//常用联系人Map
		userMap.put("lsuu_create_user", this.getSessionUser().getuId());//用户id
		userMap.put("lsuu_id", lsuu_id);//常用联系人id
		try {
			lsus.deleteUser(userMap);
		} catch (Exception e) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "删除失败!");
		}
		return map;
	}
}
