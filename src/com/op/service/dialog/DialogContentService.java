package com.op.service.dialog;

import java.util.List;
import java.util.Map;

import com.op.entity.dialog.DialogContent;
import com.op.entity.users.Users;
/** 
 * 对话内容表(DialogContent)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-12 13:29:51 
 */  
public interface DialogContentService {
	/**
	 * 新增消息
	 * @param dialogId 对话ID(ID为0，则新增新对话)
	 * @param addresser 发送人
	 * @param addressee 接收人
	 * @param content 消息内容
	 */
	void saveDialogContent(String dialogId,Users user,String addressee,String content) throws Exception;
    
	/**
	 * 修改阅读状态
	 * @param dialogContentId 信息ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	void updateDialogContent(String dialogContentId,String userId) throws Exception;
	
	/**
	 * 删除对话(时间删除)
	 * @param dialogContentId 信息ID
	 * @throws Exception
	 */
	void deleteDialogContent(String dialogContentId) throws Exception;
	
	/**
	 * 用户未读数量
	 * @param userId 用户ID
	 * @return
	 * @throws Exception
	 */
	Map<String,String> dialogContentCount(Users users) throws Exception;
	
	/**
	 * 根据对话ID查询所有内容
	 * @param dialogId 对话表ID
	 * @return
	 * @throws Exception
	 */
	List<DialogContent> findDialogContentByDialogId(String dialogId,String userId) throws Exception;

	/**
	 * 切换聊天用户显示与该用户对话内容
	 * @param dialogId 对话框Id
	 * @param userId 好友Id
	 * @param login 登录用户Id
	 * @return
	 * @throws Exception
	 */
	List<DialogContent> findDialogContentByUserId(Map<String,String> map) throws Exception;
	
	/**
	 * 修改所有对话的阅读状态
	 * @param userId 用户ID
	 * @throws Exception
	 */
	void updateAllDialogContent(String userId) throws Exception;
}
