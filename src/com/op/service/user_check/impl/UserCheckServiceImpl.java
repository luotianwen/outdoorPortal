package com.op.service.user_check.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.entity.user_check.UserCheck;
import com.op.service.user_check.UserCheckService;
import com.op.task.PhotosUploadOss;
import com.op.util.Const;
import com.op.util.DateUtil;

/**
 * 数据库中(领队信息)实现类
 * @author PYW
 * Date: 2015年12月18日 09:25:19
 */
@Service("userCheckServiceImpl")
public class UserCheckServiceImpl implements UserCheckService {
	
	@Resource(name="baseDaoImpl")
	private BaseDao dao;
	
	@Resource(name="photosUploadOssImpl")
	private PhotosUploadOss photosUploadOssImpl;
	
	/**
	 * 注册领队
	 */
	@Override
	public void insertUserCheck(UserCheck userCheck,HttpSession session,Map<String, Object> map) throws Exception {
		
		/*
		 // 复制证件照-正面/法人代表身份证照片
		userCheck.setIdcartFrontUrl(copyImg(userCheck.getIdcartFrontUrl(), session, map, userCheck.getUcType()==1?"leader":"group"));
		
		// 证件照-反面/法人代表身份证照片
		userCheck.setIdcartBackUrl(copyImg(userCheck.getIdcartBackUrl(), session, map, userCheck.getUcType()==1?"leader":"group"));
		
		// 手持证件照/法人手持证件照
		userCheck.setIdcartHandUrl(copyImg(userCheck.getIdcartHandUrl(), session, map, userCheck.getUcType()==1?"leader":"group"));
		
		// 企业资质
		if(userCheck.getUcType() == 2){
			// 营业执照
			userCheck.setLicensePictureUrl(copyImg(userCheck.getLicensePictureUrl(), session, map, "group"));
			
			// 俱乐部LOGO
			userCheck.setClubLogo(copyImg(userCheck.getClubLogo(), session, map, "group"));
		}
		 */
		
		dao.save("UserCheckMapper.insertUser_Check", userCheck);

		// 操作成功
		map.clear();
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
	}
	
	
	/**
	 * 方法描述：将临时目录的图片拷贝至服务器目录永久保存
	 * 返回类型：void
	 * @param temp 文件的临时目录
	 * @param session 当前session
	 * @param type 保存类型{active/line}
	 * @throws Exception
	 */
	String copyImg(String temp,HttpSession session, Map<String, Object> map,String type) throws Exception{
		// Servlet上下文环境
		ServletContext application = session.getServletContext();
		// 根目录
		String contextPath = application.getRealPath("/");
		// 当天目录文件名
		String day = DateUtil.getDays();
		// 新储存路径
		String newPath = contextPath+"upload/images/"+type+"/"+day;
		
		// 判断目录是否存在,不存在则创建
		File uploadFilePath = new File(newPath);
		if(!uploadFilePath.exists()){
			uploadFilePath.mkdirs();
		}
		
		// 复制文件
		return copyImg(contextPath, temp, newPath ,map);
		
	}

	
	/**
	 * 方法描述：复制临时图片保存到永久目录
	 * 返回类型：String
	 * @param contextPath	根目录
	 * @param temp	源文件存储路径
	 * @param newPath	新文件存储路径
	 * @return	复制文件的路径
	 */
	String copyImg(String contextPath,String temp,String newPath,Map<String,Object> map) throws Exception{
		// 解析图片名称
		int lastIndex = temp.lastIndexOf("/");
		String fileName = temp.substring(lastIndex);
		// 设置新文件的路径
		newPath += fileName;
		FileInputStream input=new FileInputStream(contextPath+temp);
		FileOutputStream output=new FileOutputStream(newPath);
		int in=input.read();// 读取
		while(in!=-1){
			output.write(in);// 写入
			in=input.read();
		}
		output.flush(); // 关闭流
		output.close(); 
		input.close(); 

		// 保存已经复制的新图片路径，用于添加失败删除垃圾数据
		List<String> list = map.get("newPathLocationUrl")==null?new ArrayList<String>():(List<String>)map.get("newPathLocationUrl");;
		if(list == null){
			map.put("list", list);
		}
		list.add(newPath);
		
		// 截取upload...之后的路径保存到数据库
		
		return newPath.substring(newPath.indexOf("upload/"));
	}
	/**
	 * 根据用户id查询是否已经申请过领队(通过)或正在审核中
	 */
	@Override
	public Integer selectUser_Check(String u_id) throws Exception {
		return (Integer) dao.findForObject("UserCheckMapper.selectUser_Check", u_id);
	}
	
	/**
	 * 修改注册领队信息
	 * @throws Exception 
	 */
	@Override
	public void updateUserCheck(UserCheck userCheck,HttpSession session,Map<String, Object> map) throws Exception{
		
		// 复制证件照-正面/法人代表身份证照片
		//userCheck.setIdcartFrontUrl(copyImg(userCheck.getIdcartFrontUrl(), session, map, userCheck.getUcType()==1?"leader":"group"));
		
		// 证件照-反面/法人代表身份证照片
		//userCheck.setIdcartBackUrl(copyImg(userCheck.getIdcartBackUrl(), session, map, userCheck.getUcType()==1?"leader":"group"));
		
		// 手持证件照/法人手持证件照
		//userCheck.setIdcartHandUrl(copyImg(userCheck.getIdcartHandUrl(), session, map, userCheck.getUcType()==1?"leader":"group"));
		
		dao.update("UserCheckMapper.updateUser_Check", userCheck);
	}
}
