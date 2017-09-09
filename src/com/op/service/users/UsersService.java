package com.op.service.users;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.op.dto.usercenter.user.update.YzmDTO;
import com.op.dto.usercenter.userInfo.UsersInfo;
import com.op.dto.usercenter.usercenter.UserCenterInfo;
import com.op.entity.users.Users;


@Service("usersService")
public interface UsersService {
	
	/**
	 * 检查用户名密码是否正确     用户登录
	 * @param user
	 * @return
	 */
	public Users loginUserByPhoneOrEmail(Users user);
	
	Users selectId(String u_id) throws Exception;
	
 
	/**
	 * 根据用户ID获取用户详细信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	Users findUserById(String userId) throws Exception;
	
	/**
	 * 根据用户ID获取用户邮箱
	 */
	String findUserEmailById(String userId) throws Exception;
	
	/**
	 * 查询用户头像
	 * @param u_Id
	 * @return
	 * @throws Exception
	 */
	String selectHead(String u_Id) throws Exception;
	
	/**
	 * 修改用户信息
	 * @param users
	 * @throws Exception
	 */
	void updateUsers(Users users) throws Exception;
	
	/**
	 * 修改用户头像
	 * @param map
	 * @throws Exception
	 */
	void updateHead(Map<String, Object> map) throws Exception;
	
	/**
	 * 修改用户紧急联系人
	 * @param Users
	 * @throws Exception
	 */
	void updateUsersEmergency(Users users) throws Exception;
	
	/**
	 * 根据用户ID查询用户个人信息
	 * @param id 用户ID
	 */
	UsersInfo findUserInfoById(String id) throws Exception;
	
/*******************************系统用户********************************************************/	
	
	 
	/**
	 * 方法描述：保存用户
	 * 返回类型：Map<String,Object>
	 * @param map
	 * @return
	 * @throws Exception
	 */
	void saveUser(Users user,Map<String,String> map) throws Exception;
	
	 
	
	/**
	 * 验证用户名邮箱手机号是否有重复
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int checkUserInfo(Map<String,Object> map) throws Exception;	
	
	/**
	 * 方法描述：检索用户手机号是否唯一
	 * 返回类型：boolean
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public boolean checkUserPhoneUnique(String phone)throws Exception;
	
	/**
	 * 方法描述：检索用户邮箱账号是否唯一
	 * 返回类型：boolean
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public boolean checkUserEmailUnique(String email)throws Exception;
	
	/**
	 * 方法描述：更新用户登录状态
	 * 返回类型：void
	 * @param user
	 * @throws Exception
	 */
	void updateUserLoginInfo(Users user) throws Exception;
	
	/**
	 * 方法描述：更换手机号码
	 * 返回类型：void
	 * @throws Exception
	 */
	void updateUserPhone(YzmDTO dto,Map<String,Object> resultMap) throws Exception;
	
	/**
	 * 方法描述：更换手机号码
	 * 返回类型：void
	 * @throws Exception
	 */
	void updateUserPhone(Users users) throws Exception;
	
	/**
	 * 方法描述：更换邮箱号码
	 * 返回类型：void
	 * @throws Exception
	 */
	void updateUserEmail(Users users) throws Exception;
	
	/**
	 * 方法描述：更换登录密码
	 * 返回类型：void
	 * @throws Exception
	 */
	void updateUserPassword(Users user) throws Exception;
	
	/**
	 * 
	 * @Description: 更新用户头像地址为OSS地址
	 * @param user
	 * @throws Exception   
	 * @return void  
	 * @author WinZhong
	 * @date 2016年1月21日 下午4:00:10
	 */
	void updateUserAvatarOssUrl(Users user) throws Exception;
	
	/**
	 * 方法描述：根据用户ID查询用户身份类型
	 * 返回类型：int
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int selectUtypeById(String id) throws Exception;
	
	/**
	 * 方法描述：验证昵称唯一
	 * 返回类型：boolean
	 * @param uName
	 * @return
	 * @throws Exception
	 */
	boolean validateName(String uName,String uId) throws Exception;
	
	/**
	 * 根据身份查询所有用户ID
	 * @param identity 用户身份
	 * @return
	 * @throws Exception
	 */
	String[] findUserIdByMessage(int identity) throws Exception;
	
	/**
	 * 用户个人简单信息（头像，名称）
	 */
	UserCenterInfo findUserCenterInfo(String id) throws Exception;
	
}
