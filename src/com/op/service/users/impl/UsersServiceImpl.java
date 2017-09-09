package com.op.service.users.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.dto.usercenter.user.update.YzmDTO;
import com.op.dto.usercenter.userInfo.UsersInfo;
import com.op.dto.usercenter.usercenter.UserCenterInfo;
import com.op.entity.users.Users;
import com.op.service.users.UsersService;
import com.op.util.Const;
import com.op.util.MD5;


@Service("usersServiceImpl")
public class UsersServiceImpl implements UsersService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	
	
	
	/**
	 * 方法描述：利用手机号或者邮箱登录
	 * 实现接口：@see com.op.service.users.UsersService#loginUserByPhoneOrEmail(com.op.entity.users.Users)
	 * @param user
	 * @return
	 */
	@Override
	public Users loginUserByPhoneOrEmail(Users user){
		try {
			user = (Users) dao.findForObject("UsersMapper.loginUserByPhoneOrEmail", user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	
	/**
	 * 根据用户id查询用户部分信息
	 */
	@Override
	public Users selectId(String u_id) throws Exception {
		return (Users) dao.findForObject("UsersMapper.selectId", u_id);
	}
	
	 
	/**
	 * 根据用户ID获取用户详细信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Users findUserById(String userId) throws Exception{
		return (Users) dao.findForObject("UsersMapper.findUserById", userId);
	}
	
	/**
	 * 根据用户ID获取用户邮箱
	 */
	@Override
	public String findUserEmailById(String userId) throws Exception{
		return (String) dao.findForObject("UsersMapper.findUserEmailById", userId);
	}
	
	/**
	 * 查询用户头像
	 * @param u_Id
	 * @return
	 * @throws Exception
	 */
	@Override
	public String selectHead(String u_Id) throws Exception {
		return (String) dao.findForObject("UsersMapper.selectHead", u_Id);
	}
	
	/**
	 * 修改用户信息
	 * @param users
	 * @throws Exception
	 */
	@Override
	public void updateUsers(Users users) throws Exception {
		dao.update("UsersMapper.updateUsers", users);
	}
	
	/**
	 * 修改用户头像
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void updateHead(Map<String, Object> map) throws Exception {
		dao.update("UsersMapper.updateHead", map);
	}
	
	/**
	 * 修改用户紧急联系人
	 * @param Users
	 * @throws Exception
	 */
	@Override
	public void updateUsersEmergency(Users users) throws Exception{
		dao.update("UsersMapper.updateUsersEmergency", users);
	}
	
	/**
	 * 根据用户ID查询用户个人信息
	 * @param id 用户ID
	 */
	@Override
	public UsersInfo findUserInfoById(String id) throws Exception{
		return (UsersInfo) dao.findForObject("UsersMapper.findUserInfoById", id);
	}
	
	/**
	 * 方法描述：注册新用户
	 * 实现接口：@see com.op.service.users.UsersService#saveUser(com.op.entity.users.Users)
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public void saveUser(Users user,Map<String,String> map) throws Exception {
		// 判断手机号是否唯一
		int phone = (int) dao.findForObject("UsersMapper.checkUserPhoneUnique", user.getuPhone());
		if(phone > 0){
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.RESPONSE_STATE, "该手机号码已经存在");
			return;
		}
		user.setuCreateTime(new Date());// 用户创建时间
		user.setuPassword(MD5.md5(user.getuPassword()));// 系统MD5加密处理
		user.setuType(3);// 默认为普通用户
		dao.save("UsersMapper.saveUser", user);// save
		
		// 注册成功后给用户开通余额
		dao.save("balanceMapper.insertBalance", user.getuId());
		
		// 注册成功后设置用户个人资料
		dao.save("UsersMapper.saveUserInfo", user.getuId());
		
		map.clear();
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
	}
	
	 
	/**
	 * 方法描述：验证手机号唯一性
	 * 实现接口：@see com.op.service.users.UsersService#checkUserPhoneUnique(java.lang.String)
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean checkUserPhoneUnique(String phone)throws Exception{
		int num = (int) dao.findForObject("UsersMapper.checkUserPhoneUnique", phone);
		if(num > 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 方法描述：检索用户邮箱账号是否唯一
	 * 返回类型：boolean
	 * @param email
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean checkUserEmailUnique(String email)throws Exception{
		int num = (int) dao.findForObject("UsersMapper.checkUserEmailUnique", email);
		if(num > 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 验证用户名邮箱手机号是否有重复
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public int checkUserInfo(Map<String,Object> map) throws Exception{
		return (int) dao.findForObject("UsersMapper.checkUserInfo", map);
	}
	

	/**
	 * 方法描述： 改变用户登录信息（IP、最后登录时间、登录次数）
	 * 实现接口：@see com.op.service.users.UsersService#updateUsersLoginInfo(com.op.entity.users.Users)
	 * @param user
	 * @throws Exception
	 */
	@Override
	public void updateUserLoginInfo(Users user) throws Exception {
		user.setuLoginDate(new Date());// 最后登录时间
		user.setuLoginCount(user.getuLoginCount()+1);// 登录次数
		dao.update("UsersMapper.updateUserLoginInfo", user);
	}

	/**
	 * 方法描述：更换手机号码
	 * 实现接口：@see com.op.service.users.UsersService#updateUserPhone()
	 * @throws Exception
	 */
	@Override
	public void updateUserPhone(YzmDTO dto,Map<String,Object> resultMap) throws Exception {
		// TODO Auto-generated method stub

		resultMap.put(Const.ERROR_INFO, "更新手机号码异常!");
		dao.update("UsersMapper.updateUserPhoneById", dto);
		
		resultMap.clear();
		resultMap.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		
	}

	/**
	 * 方法描述：更换手机号码
	 * 实现接口：@see com.op.service.users.UsersService#updateUserPhone()
	 * @throws Exception
	 */
	@Override
	public void updateUserPhone(Users users) throws Exception {
		dao.update("UsersMapper.updateUserPhoneById", users);
	}
	
	/**
	 * 方法描述：更换邮箱号码
	 * 返回类型：void
	 * @throws Exception
	 */
	@Override
	public void updateUserEmail(Users users) throws Exception{
		dao.update("UsersMapper.updateUserEmailById", users);
	}

	/**
	 * 方法描述：更换登录密码
	 * 实现接口：@see com.op.service.users.UsersService#updateUserPassword(com.op.entity.users.Users)
	 * @param user
	 * @throws Exception
	 */
	@Override
	public void updateUserPassword(Users user) throws Exception {
		dao.update("UsersMapper.updateUserPasswordById", user);
	}



	/**
	 * 
	 * @Description: 更新用户头像地址为OSS地址
	 * @param user
	 * @throws Exception   
	 * @return void  
	 * @author WinZhong
	 * @date 2016年1月21日 下午4:00:10
	 */
	@Override
	public void updateUserAvatarOssUrl(Users user) throws Exception{
		dao.update("UsersMapper.updateUserAvatarOssUrl", user);
	}

	/**
	 * 方法描述：根据用户ID查询用户身份类型
	 * 实现接口：@see com.op.service.users.UsersService#selectUtypeById(java.lang.String)
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectUtypeById(String id) throws Exception {
		
		return (int) dao.findForObject("UsersMapper.selectUtypeById", id);
	}


	/**
	 * 方法描述：验证昵称唯一
	 * 实现接口：@see com.op.service.users.UsersService#validateName(java.lang.String)
	 * @param uName
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean validateName(String uName,String uId) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		map.put("uName", uName);
		map.put("uId", uId);
		int num = (int) dao.findForObject("UsersMapper.validateName", map);
		
		if(num == 0){
			return true;
		}
		return false;
	}


	/**
	 * 根据身份查询所有用户ID
	 * @param identity 用户身份
	 * @return
	 * @throws Exception
	 */
	@Override
	public String[] findUserIdByMessage(int identity) throws Exception{
		return (String[]) dao.findForObject("UsersMapper.findUserIdByMessage", identity);
	}

	/**
	 * 用户个人简单信息（头像，名称）
	 */
	@Override
	public UserCenterInfo findUserCenterInfo(String id) throws Exception{
		return (UserCenterInfo) dao.findForObject("UsersMapper.findUserCenterInfo", id);
	}

	
	
	
}
