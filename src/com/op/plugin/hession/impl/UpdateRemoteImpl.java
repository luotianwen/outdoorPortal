package com.op.plugin.hession.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.op.controller.socket.SystemWebSocketHandler;
import com.op.dto.hession.ResponseDTO;
import com.op.plugin.hession.UpdateRemote;
import com.op.service.synQx.FilterChainDefinitionsService;
import com.op.util.Const;

/**
 * 接收远程请求实现类
 * @author Yan
 *
 */

public class UpdateRemoteImpl extends SystemWebSocketHandler  implements UpdateRemote {

	// shiro缓存
	private FilterChainDefinitionsService loading;
	
	// 初始化spring对象
	public UpdateRemoteImpl(){
		loading = ClassRepository.getLoadingDBChainDefinitions();
	}

	/**
	 * 方法描述：更新权限
	 * 实现接口：@see com.op.hession.service.UpdateShiro#updateShiro()
	 * @return
	 */
	@Override
	public ResponseDTO updateShiro(Map<String, String> map){
		ResponseDTO dto = new ResponseDTO();
		// 刷新缓存
		try {
			loading.updatePermission(map);
		} catch (Exception e) {
			dto.setState(500);
			dto.setError(e.toString());
		}
		return dto;
	}

	/**
	 * 方法描述：给所有在线用户发送消息
	 * 实现接口：@see com.op.hession.UpdateRemote#sendMessageTosessions(java.lang.String)
	 * @param message
	 * @return
	 */
	@Override
	public ResponseDTO sendMessageTosessions(String message) {
		ResponseDTO dto = new ResponseDTO();
		int success=0;
		int error = 0;
		// 循环所有在线用户session
		Map<String,WebSocketSession> map = getMap();
		Set<Entry<String,WebSocketSession>> set = map.entrySet();
		TextMessage me = new TextMessage(message);
		for(Entry<String,WebSocketSession> entry : set){
			WebSocketSession session = entry.getValue();
			// 打开会话链接
            if (session.isOpen()) {
                try {
                	// 发送消息
					session.sendMessage(me);
					success++;
				} catch (IOException e) {
					error++;
				}
                
            }
		}
        // 发送成功条数
    	dto.setPostSuccessNum(success);
    	// 发送失败条数
    	dto.setPostErrorNum(error);
		return dto;
	}

	/**
	 * 方法描述：给某个用户发送消息
	 * 实现接口：@see com.op.hession.UpdateRemote#sendMessageToUser(java.lang.String, java.lang.String)
	 * @param userId
	 * @param message
	 * @return
	 */
	@Override
	public ResponseDTO sendMessageToUser(String userId, String message) {
		ResponseDTO dto = new ResponseDTO();
		int success = 0;
		Map<String,WebSocketSession> map = getMap();
		WebSocketSession session = map.get(userId);
		if(session != null && session.isOpen()){
	    	try {
				session.sendMessage(new TextMessage(message));
				success++;
			} catch (IOException e) {
					
			}
		}
        // 发送成功条数
        dto.setPostSuccessNum(success);
		return dto;
	}

	/**
	 * 获取所有在线人数的ID集合
	 */
	@Override
	public List<String> getOnLineUserIds() {
		List<String> list = null;
		Map<String,WebSocketSession> map = getMap();
		if(map.keySet().size()>0){
			list = new ArrayList<String>();
			Set<Entry<String,WebSocketSession>> set = map.entrySet();
			for(Entry<String,WebSocketSession> entry : set){
				WebSocketSession session = entry.getValue();
				if(session.isOpen()){
					list.add(session.getAttributes().get(Const.SESSION_USER_ID)+"");	
				}
			}
		}
		
		// TODO Auto-generated method stub
		return list;
	}

	/**
	 * 批量用户发送消息
	 */
	@Override
	public ResponseDTO sendMessageToUsers(String[] userIds, String message) {
		ResponseDTO dto = new ResponseDTO();
		int success = 0;
		int error = 0;
		
		Map<String,WebSocketSession> map = getMap();
		TextMessage me = new TextMessage(message);
		for(int i=0,len=userIds.length;i<len;i++){
			WebSocketSession session = map.get(userIds[i]);
			if(session != null && session.isOpen()){
            	try {
					session.sendMessage(me);
					success++;
				} catch (IOException e) {
					error++;
				}
			}
		}
        // 发送成功条数
        dto.setPostSuccessNum(success);
        // 发送失败条数
        dto.setPostErrorNum(error);
        return dto;
	}




}
