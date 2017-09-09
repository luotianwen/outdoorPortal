package com.op.task;

import com.op.entity.users.Users;


/**
 * 
 * ClassName: AvatarUploadOss 
 * @Description: 上传头像到阿里云OSS
 * @author WinZhong
 * @date 2016年1月21日 下午3:51:23
 */
public interface AvatarUploadOss {
	
	/**
	 * 
	 * @Description: 上传
	 * @param user 用户 Session User 
	 * @param avatarLocalUrl 头像本地地址
	 * @return void  
	 * @author WinZhong
	 * @date 2016年1月21日 下午3:55:25
	 */
	void upload(Users user,String avatarLocalUrl);
	
}
