package com.op.service.jointLogin.impl;

import java.util.Date;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao; 
import com.op.entity.jointLogin.JointLogin;
import com.op.entity.users.Users;
import com.op.service.jointLogin.JointLoginService;

/** 
 * 第三方平台账号联合登录(jointLogin)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-05-18 11:44:38 
 */  
@Service("jointLoginServiceImpl")
public class JointLoginServiceImpl implements JointLoginService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	


	/**
	 * 检测第三方登录账号是否已绑定网站账户
	 * @param jointLogin
	 * @return 绑定的用户信息
	 * @throws Exception
	 */
	@Override
	public Users findUserByOpenId(JointLogin jointLogin)throws Exception{

		return (Users)dao.findForObject("jointLoginMapper.findUserByOpenId", jointLogin);
	}

    
	/**
	 * 自动为第三方登录用户开通网站账户
	 * @param jointLogin
	 * @throws Exception
	 */
	@Override
	public Users insertJointLoginInfo(JointLogin jointLogin)throws Exception{
		
		//判断昵称是否重复
		int num = (int) dao.findForObject("UsersMapper.checkUserName", jointLogin.getNickname());
		//如果昵称重复，设置系统自动生成昵称
		if(num > 0){
			Object obj = dao.findForObject("jointLoginMapper.nicknameSeq");
			jointLogin.setNickname("玩友_"+obj);
		}
		
		Users user = new Users();
		user.setuName(jointLogin.getNickname());//用户昵称
		//设置用户性别
		if("男".equals(jointLogin.getGender())){
			user.setuSex(1);
		}else if("女".equals(jointLogin.getGender())){
			user.setuSex(2);
		}else{
			user.setuSex(3);
		}
		user.setuCreateTime(new Date());// 用户创建时间
		user.setuType(3);// 默认为普通用户
		dao.save("UsersMapper.saveUser", user);// save
		// 注册成功后给用户开通余额
		dao.save("balanceMapper.insertBalance", user.getuId());
		jointLogin.setUser_id(user.getuId());//设置绑定用户id
		// 绑定开通账户
		dao.save("jointLoginMapper.insertJointLoginInfo", jointLogin);
		
		return (Users)dao.findForObject("jointLoginMapper.findUserByOpenId", jointLogin);
	}

    
	/**
	 * 更新第三方登录用户信息
	 * @param jointLogin
	 * @throws Exception
	 */
	@Override
	public void updateUserJointLoginInfo(JointLogin jointLogin)throws Exception{
		dao.update("jointLoginMapper.updateUserJointLoginInfo", jointLogin);
	}
}
