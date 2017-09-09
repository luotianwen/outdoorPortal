package com.op.controller.socket;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import com.op.util.Const;


public class SystemWebSocketHandler implements WebSocketHandler{
	Logger log=Logger.getLogger(SystemWebSocketHandler.class);
	private static final Map<String,WebSocketSession> map;
	 
    static {
        map = new HashMap<String,WebSocketSession>();
    }

    public static Map<String,WebSocketSession> getMap(){
    	return map;
    }
    
    /**
     * 方法描述：建立客户端与服务器端的连接
     * @param session
     * @throws Exception
     */
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
        String userId = (String) session.getAttributes().get(Const.SESSION_USER_ID);
        
		map.put(userId, session);
		
        //session.sendMessage(new TextMessage("链接成功！！您的用户ID为："+userId));
        //查询未读消息
		log.info("链接成功！！");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> wsm)
			throws Exception {
        TextMessage returnMessage = new TextMessage(wsm.getPayload()
				+ " received at server");
        session.sendMessage(returnMessage);
		
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception)
			throws Exception {
		if(session.isOpen()){
            session.close();
        }
		
		String userId = (String) session.getAttributes().get(Const.SESSION_USER_ID);
		
		map.remove(userId);

		log.info("链接关闭");
	}
    
    
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1)
			throws Exception {
		String userId = (String) session.getAttributes().get(Const.SESSION_USER_ID);
		
		map.remove(userId);
		
		log.info("链接关闭");
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
