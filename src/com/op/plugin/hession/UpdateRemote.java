package com.op.plugin.hession;

import java.util.List;
import java.util.Map;

import com.op.dto.hession.ResponseDTO;
/**
 * 接收远程请求接口
 * @author Yan
 *
 */
public interface UpdateRemote {

	/**
	 * 更新本地shiro缓存
	 * @param map
	 * @return
	 */
	public ResponseDTO updateShiro(Map<String, String> map);

    /**
     * 给所有在线用户发送消息
     * @param message
     * @throws IOException 
     */
    public ResponseDTO sendMessageTosessions(String message);
    
    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     * @throws IOException 
     */
    public ResponseDTO sendMessageToUser(String userId, String message);


	/**
	 * 方法描述：获取所有在线人数ID
	 * 返回类型：Set<String>
	 * @return
	 */
	public List<String> getOnLineUserIds();
    
    /**
     * 批量用户发送消息
     *
     * @param userName
     * @param message
     * @throws IOException 
     */
    public ResponseDTO sendMessageToUsers(String[] userIds, String message);
	
}
