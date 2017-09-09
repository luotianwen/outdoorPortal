package com.op.task.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.op.service.users.UsersService;
import com.op.task.PhotosUploadOss;
import com.op.util.aliyun.AliyunOSSFactory;
import com.op.util.aliyun.AliyunOSSProperties;
/**
 * 
 * ClassName: AvatarUploadOss 
 * @Description: 上传头像到阿里云OSS
 * @author WinZhong
 * @date 2016年1月21日 下午3:51:23
 */
@Service("photosUploadOssImpl")
public class PhotosUploadOssImpl implements PhotosUploadOss {
    
    @Resource(name="usersServiceImpl")
    protected UsersService usersService;
    
    protected Logger logger = Logger.getLogger(this.getClass());
    
    /**
	 * 上传图片到阿里云
	 * @param avatarLocalUrl 图片本地地址
	 * @return
	 */
	@Override
	public String uploadPhotos(String avatarLocalUrl){

		//用户头像本地路径
		//String avatarLocalUrl = uHeadImg;
		try {
			String src = this.getClass().getResource("").getPath();
			src = src.substring(1, src.indexOf("WEB-INF"));
			// 初始化OSSClient
			OSSClient client = AliyunOSSFactory.getOSSClient();
			// 创建上传Object的Metadata
			ObjectMetadata meta = new ObjectMetadata();
			//判断服务器系统
			if(System.getProperties().getProperty("os.name").indexOf("Windows") != -1){
				//logger.info(System.getProperties().getProperty("os.name"));
				src = src + avatarLocalUrl;
			}else{
				src = "/"+src + avatarLocalUrl;
			}
			// 获取指定文件的输入流
			File file = new File(src);
			//判断文件是否存在 存在则上传
			if(file.exists()){
				InputStream content = new FileInputStream(file);
				// 必须设置ContentLength
				meta.setContentLength(file.length());
				// 上传Object.
				PutObjectResult result = client.putObject(AliyunOSSProperties.BUCKETNAME, "img/"+file.getName(), content,meta);
				// 打印ETag
				logger.info("游记图片上传阿里云OSS云存储成功,文件ETag：" + result.getETag());
				//更新用户的头像url为阿里云OSS地址
				return AliyunOSSProperties.IMGVISITURL + "img/"+file.getName();
			}else {
				logger.info("游记图片文件不存在  路径："+src);
				return avatarLocalUrl;
			}
		} catch (OSSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return avatarLocalUrl;
	}

}
