package com.op.util;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.op.controller.socket.SystemWebSocketHandler;

public class WebSocketUtil extends SystemWebSocketHandler{
	/**
     * 给所有在线用户发送消息
     * @param message
     * @throws IOException 
     */
    public static void sendMessageTosessions(Map<?,?> jsonMap) throws IOException{
    	// 循环所有在线用户session
    	JSONObject jsonObject = JSONObject.fromObject(jsonMap);
    	
    	Map<String,WebSocketSession> map = getMap();
    	Set<Entry<String,WebSocketSession>> set = map.entrySet();
    	TextMessage me = new TextMessage(jsonObject.toString());
    	for(Entry<String,WebSocketSession> entry : set){
    		if(entry.getKey().indexOf("NO_LOGIN")!=-1){
    			WebSocketSession session = entry.getValue();
        		// 打开会话链接
        		if (session.isOpen()) {
        			session.sendMessage(me);
        		}
    		}
    	}
    }
    
    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     * @throws IOException 
     */
    public static void sendMessageToUser(String userId, Map<?,?> jsonMap) throws IOException{
    	// 循环所有在线用户session
    	JSONObject jsonObject = JSONObject.fromObject(jsonMap);
    	Map<String,WebSocketSession> map = getMap();
    	TextMessage me = new TextMessage(jsonObject.toString());
    	
		WebSocketSession session = map.get(userId);
		if(session != null && session.isOpen()){
			session.sendMessage(me);
		}
    }
    
    /**
     * 批量用户发送消息
     *
     * @param userName
     * @param message
     * @throws IOException 
     */
    public static void sendMessageToUsers(String[] userIds, Map<?,?> jsonMap) throws IOException{
    	// 循环所有在线用户session
    	JSONObject jsonObject = JSONObject.fromObject(jsonMap);
    	Map<String,WebSocketSession> map = getMap();
    	TextMessage me = new TextMessage(jsonObject.toString());
    	
    	for(int i=0;i<userIds.length;i++){
    		WebSocketSession session = map.get(userIds[i]);
    		if(session != null && session.isOpen()){
    			session.sendMessage(me);
    		}
    	}
    }
	
}
