package com.op.service.dialog;

import java.util.List;

import com.op.entity.dialog.Dialog;
/** 
 * 对话表(Dialog)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-03-12 13:29:51 
 */  
public interface DialogService {
	
    /**
     * 删除对话(连同对话内容一起删除)
     * @param dialogId 对话表ID
     */
    void deleteDialog(String dialogId) throws Exception;
    
    /**
     * 查询用户所有对话
     * @param addresser 发信人ID
     */
    List<Dialog> findDialogByUserId (String addresser,String id,String count) throws Exception;
    
    /**
     * 判断用户之间是否有对话
     * @param addresser 发信人ID
     * @param addressee 接收人ID
     * @return
     * @throws Exception
     */
    List<Dialog> findDialog (String addresser,String addressee) throws Exception;
    
    /**
     * 关闭对话框
     */
    void deleteDialog(String id,String userId) throws Exception;
}
