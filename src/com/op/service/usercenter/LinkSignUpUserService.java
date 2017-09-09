package com.op.service.usercenter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.op.entity.usercenter.LinkSignUpUser;

/**
 * 常用报名人(接口)
 * @author PYW
 * Date: 2015年12月21日 15:06:12
 */
@Service("linkSignUpUserService")
public interface LinkSignUpUserService {
	List<LinkSignUpUser> selectUser(String lsuu_create_user) throws Exception;//返回 常用报名人信息
	void insertUser(LinkSignUpUser lsuu) throws Exception;      			  //添加常用报名人           
	void updateUser(LinkSignUpUser lsuu) throws Exception;					  //修改常用报名人
	void deleteUser(Map<String, Object> map) throws Exception;      			  //删除常用报名人
	LinkSignUpUser selectUserId(Map<String, Object> map) throws Exception;		  //根据常用人id和创建人查找出相应的列
}
