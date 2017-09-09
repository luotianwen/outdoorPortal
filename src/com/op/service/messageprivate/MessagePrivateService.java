package com.op.service.messageprivate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.op.entity.messageprivate.MessagePrivate;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;

@Service("messagePrivateService")
public interface MessagePrivateService {
	
	/**
	 * 查看信息详情
	 * @param id 站内信ID
	 * @throws Exception
	 */
	MessagePrivate findForMessagePrivateById(String id) throws Exception;
	
	/**
	 * 删除信息(修改信息状态)
	 * @param id 站内信ID
	 * @throws Exception
	 */
	void messageDelete(String id) throws Exception;
	
	/**
	 * 彻底删除信息(删除数据库)
	 * @param id 站内信ID(多选用,隔开)
	 * @throws Exception
	 */
	void messageShiftDelete(String id) throws Exception;
	
	/**
	 * 发信箱
	 * @param userId 登录者ID
	 * @param type 信息类型(0:所有；其他待定)
	 * @param identity 用户身份(0:所有；其他待定)
	 * @param page 分页
	 * @return
	 * @throws Exception
	 */
	List<MessagePrivate> getMessagePageListSend(Integer type,Integer identity,Page<Map<String,Object>> page) throws Exception;
	
	/**
	 * 垃圾箱
	 * @param userId 登录者ID
	 * @param type 信息类型(0:所有；其他待定)
	 * @param identity 用户身份(0:所有；其他待定)
	 * @param page 分页
	 * @return
	 * @throws Exception
	 */
	List<MessagePrivate> getMessagePageListDelete(Integer type,Integer identity,Page<Map<String,Object>> page) throws Exception;
	
	
	/* ---------------------------------------------------------站内信最新内容----------------------------------------------------- */
	
	/**
	 * 一对一发送站内信
	 * @param title 标题
	 * @param content 内容
	 * @param login 当前登录用户
	 * @param users 收信人
	 * @param key 消息模板
	 * @throws Exception
	 */
	void saveMessagePrivate(Map<String,Object> countMap,Users users,String key) throws Exception;
   
	/**
	 * 群发站内信(选择不同身份的用户群发)
	 * 选择多个不同用户群发功能暂时保留
	 * @param title	标题
	 * @param content 内容
	 * @param identity 用户身份(0:所有；其他待定)
	 * @throws Exception
	 */
	void saveMassMessage(String title,String content,Integer identity,String key) throws Exception;
} 
