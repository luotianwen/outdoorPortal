package com.op.task;


/**
 * 
 * ClassName: AvatarUploadOss 
 * @Description: 上传头像到阿里云OSS
 * @author WinZhong
 * @date 2016年1月21日 下午3:51:23
 */
public interface PhotosUploadOss {
	
	/**
	 * 上传图片到阿里云
	 * @param avatarLocalUrl 图片本地地址
	 * @return			
	 */
	String uploadPhotos(String avatarLocalUrl);
	
}
