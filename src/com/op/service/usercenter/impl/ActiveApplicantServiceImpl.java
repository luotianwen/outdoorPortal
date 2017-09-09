package com.op.service.usercenter.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.op.dao.BaseDao;
import com.op.entity.usercenter.ActiveApplicant;
import com.op.entity.usercenter.ActiveSignup;
import com.op.entity.usercenter.LinkSignUpUser;
import com.op.entity.users.Users;
import com.op.service.usercenter.ActiveApplicantService;


@Service("activeApplicantServiceImpl")
public class ActiveApplicantServiceImpl implements ActiveApplicantService{
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 新增订单联系人
	 * @throws Exception
	 */
	@Override
	public String[] saveActiveApplicant(List<LinkSignUpUser> linkSignUpUser,Users users,ActiveSignup activeSignup) throws Exception{
		List<ActiveApplicant> list = new ArrayList<ActiveApplicant>();

		String aa_id = "";
		for(int i=0;i<linkSignUpUser.size();i++){
			LinkSignUpUser linkman = new LinkSignUpUser();
			linkman = linkSignUpUser.get(i);
			/**
			 * 已有联系人，则修改属性
			 * 数据库中没有的联系人，则新增
			 */
			if(null!=linkman.getLsuu_id()&&!"".equals(linkman.getLsuu_id())){
				linkman.setLsuu_update_user(users.getuId());
				linkman.setLsuu_create_user(users.getuId());
				dao.update("linkSignUpUserMapper.updateUser",linkman);
			}else{
				linkman.setLsuu_create_user(users.getuId());
				linkman.setLsuu_create_time(new Date());
				
				if(linkman.getIssave()==1){
					dao.save("linkSignUpUserMapper.insertUser",linkman);
				}
			}
			
			ActiveApplicant activeApplicant = new ActiveApplicant();
		 	//订单ID
		  	activeApplicant.setAsu_id(activeSignup.getAsu_id());
		 	//参加人员姓名
		  	activeApplicant.setAa_user_name(linkman.getLsuu_user_name());
		 	//参加人员性别（1：男；2：女）
		  	activeApplicant.setAa_user_sex(linkman.getLsuu_user_sex());
		 	//参加人员手机号码
		  	activeApplicant.setAa_user_phone(linkman.getLsuu_phone());
		 	//参加人员证件类型
		  	activeApplicant.setAa_user_certificates_type(linkman.getLsuu_cart_type());
		 	//参加人员证件号码
		  	activeApplicant.setAa_user_certificates_num(linkman.getLsuu_cart_num());
		 	//活动ID
		  	activeApplicant.setAa_active_id(activeSignup.getAsu_active_id());
		 	//报名状态（10：等待领队确认；20：领队已确认；30：交易完成；40：报名已取消；50：等待领队确认取消；）
		  	activeApplicant.setAa_state(10);
		 	//是否申请退款（0：默认；1：是；）
		  	activeApplicant.setAa_refund_flag(0);
		  	//报名联系人
		  	activeApplicant.setAa_signup_linkman(linkman.getAa_signup_linkman());
		  	//创建时间
		  	activeApplicant.setAa_create_time(new Date());
		  	//创建人
		  	activeApplicant.setAa_user_id(users.getuId());
		  	
		  	list.add(activeApplicant);
		  	if(i!=0){
		  		aa_id+=",";
		  	}
		  	aa_id += activeApplicant.getAa_id();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		
		dao.save("activeApplicantMapper.saveActiveApplicant", map);
		
		return aa_id.split(",");
	}
	
	
	/**
	 * 到时间未付款取消订单
	 */
	@Override
	public void payCloseForAsu_id(String id) throws Exception{
		dao.update("activeApplicantMapper.payCloseForAsu_id", id);
	}
	
	/**
	 * 订单已退款金额
	 */
	public double getRefundPrice(String asu_id) throws Exception{
		String price = (String) dao.findForObject("activeApplicantMapper.getRefundPrice", asu_id);
		if(StringUtils.isEmpty(price)){
			return 0;
		}else{
			return Double.valueOf(price);
		}
	}
	
	
}
