package com.op.controller.usercheck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.op.entity.district.Citys;
import com.op.entity.user_check.UserCheck;
import com.op.entity.users.Users;
import com.op.service.district.CitysService;
import com.op.service.district.ProvincesService;
import com.op.service.user_check.UserCheckService;
import com.op.util.Const;

/**
 * 项目名称：outdoorPortal 类名称：User_CheckController 类描述： 注册 企业领队信息验证 创建人：潘永威
 * 创建时间：2015年12月11日 修改人：闫鑫 修改时间：2016年4月15日 修改备注： 优化显示跳转操作问题
 * 
 * @version
 */
@RequestMapping("/uCheck")
@Controller
public class UserCheckController {
	// 领队注册
	@Resource(name = "userCheckServiceImpl")
	private UserCheckService userCheckServiceImpl;
	// 省
	@Resource(name = "provincesServiceImpl")
	private ProvincesService provincesServiceImpl;
	// 市
	@Resource(name = "citysServiceImpl")
	private CitysService citysServiceImpl;
	
	Logger log = Logger.getLogger(this.getClass());

	/**
	 * 方法描述：跳转相应的个人/企业注册页面
	 * 返回类型：ModelAndView
	 * @param type
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/reply")
	// 领队注册
	public ModelAndView selectProvinces(@RequestParam("type") String type, 
			ModelAndView mv,
			HttpSession session) throws Exception {
		
		// 当前用户
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		
		// 获取所有的省
		mv.addObject("provinces", provincesServiceImpl.selectProvinces());
		
		// 根据申请不同类型跳转不同页面
		switch (type) {

		// 个人领队申请
		case "leader":
			mv.setViewName("usercheck/reply-leader");
			break;

		// 企业申请
		case "group":
			mv.setViewName("usercheck/reply-group");
			break;
		}
		
		return mv;
	}

	/**
	 * 方法描述：验证个人申请领队信息
	 * 返回类型：Map
	 * @param type
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/check")
	@ResponseBody
	// 领队注册
	public Map<String,String> check(HttpSession session) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		// 当前用户
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		
		// 历史申请状态
		Integer state = userCheckServiceImpl.selectUser_Check(user.getuId());
			
		// 没有申请过领队(即是新增增操作.)
		if (state == null ) {
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} 
		// 领队之前申请失败,重新申请领队(既是修改操作.)
		else if (state == 4) {
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			// 要把审核失败的数据带到申请leader/group页面，并显示审核失败原因(后期优化)
		} 
		// 已经是领队了,不可再次申请.
		else if (state == 3) {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "您已具备了领队/企业的资质!不可重复申请领队!");
		} 
		// 正在审核state==1 || state==2
		else {
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "您已经提交过领队申请,审核结果会以短信/邮箱通知您，请耐心等待审核结果!");
		}
		return map;
	}
	
	/**
	 * 方法描述：异步获取省对应的市
	 * 返回类型：List<Citys>
	 * @param parent_code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectCitys")
	@ResponseBody
	public List<Citys> selectCitys(@RequestParam("pcId") String provinceCode) throws Exception {
		List<Citys> listc = citysServiceImpl.selectCitys(provinceCode);
		return listc;
	}

	/**
	 * 方法描述：异步获取省对应的市
	 * 返回类型：List<Citys>
	 * @param parent_code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectCounty")
	@ResponseBody
	public List<com.op.entity.district.Countys> selectCounty(@RequestParam("ccId") String ccId) throws Exception {
		return citysServiceImpl.selectCounty(ccId);
	}

	/**
	 * 方法描述：申请领队/企业
	 * 返回类型：Map<String,Object>
	 * @param type
	 * @param user_Check
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertUser_Check(
			@RequestParam("type") String type, 
			UserCheck userCheck, 
			HttpSession session) {
		// 当前用户
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
		
		// ajax返回对象
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 历史申请状态
		Integer state;
		try {
			// 查询是否有历史申请信息
			state = userCheckServiceImpl.selectUser_Check(user.getuId());
			
			// 没有申请过领队(即是新增增操作.)
			if (state == null) {
				// 根据申请不同身份设置不同信息
				setInfo(type, userCheck ,user.getuId());
				
				// 保存申请信息
				userCheckServiceImpl.insertUserCheck(userCheck, session ,map);
			} 
			// 领队之前申请失败,重新申请领队(既是修改操作.)
			else if (state == 4) {
				// 根据申请不同身份设置不同信息
				setInfo(type, userCheck ,user.getuId());
				
				// 保存申请信息
				userCheckServiceImpl.insertUserCheck(userCheck, session ,map);
				
				// 修改申请信息
				//userCheckServiceImpl.updateUserCheck(userCheck, session ,map);
			} 
			// 已经是领队了,不可再次申请.
			else if (state == 3) {
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "您已具备了领队/企业的资质!不可重复申请领队!");
				return map;
			} 
			// 正在审核state==1 || state==2
			else {
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "您已经提交过领队申请,审核结果会以短信/邮箱通知您，请耐心等待审核结果!");
				return map;
			}
			
		} catch (Exception e) {
			// 删除垃圾图片数据
			if(map.get("newPathLocationUrl") != null){
				List<String> list = (List<String>) map.get("newPathLocationUrl");
				for (String str : list) {
					new java.io.File(str).delete();
				}
			}
			
			map.clear();
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, Const.ERROR_INFO_STR);
			log.error("注册领队/企业异常", e);
			
		}
		return map;
	}
	
	/**
	 * 方法描述：根据不同的申请类型赋不同的值
	 * 返回类型：void
	 * @param type
	 * @param userCheck
	 */
	void setInfo( String type,UserCheck userCheck,String uId){
		userCheck.setU_Id(uId);
		// 个人领队注册
		if ("leader".equals(type)) {
			// 身份类型（1：个人发布者；2：企业；）
			userCheck.setUcType(1);
			
			// 申请进度（1：提交申请，2：人工审核中，3：审核完成，4：审核失败）
			userCheck.setProposer(0);
		} 
		// 企业申请
		else if ("group".equals(type)) {
			// 身份类型（1：个人发布者；2：企业；）
			userCheck.setUcType(2);
			
			// 证件类型只能为身份证
			userCheck.setIdcardType(1);
		}
	}
}
