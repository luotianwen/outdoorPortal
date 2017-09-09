package com.op.service.messageprivate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.op.entity.messageprivate.MessageAddressee;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;


@Service("messageAddresseeService")
public interface MessageAddresseeService {

	/**
	 * 查看信息详情(收信人查看时修改阅读状态)
	 * @param id 站内信ID
	 * @throws Exception
	 */
	MessageAddressee findForMessageAddresseeById(String id) throws Exception;
	
	/**
	 * 匹配系统群发站内信
	 * @param Users 用户对象
	 * @throws Exception
	 */
	void saveMessageAddressee(Users user) throws Exception;
	
	/**
	 * 删除信息(系统定期清理删除数据)
	 * @param userId 用户ID
	 * @param type 删除类型(0:全部；其他待定)
	 * @param isaddresseedelete 是否删除（0：用户未删除数据，1：用户已删除数据）
	 * @throws Exception
	 */
	void messageShiftDelete(String userId,Integer type,Integer isaddresseedelete) throws Exception;
	
	
	/* ---------------------------------------------------------站内信最新内容----------------------------------------------------- */
	
	/**
	 * 收信箱
	 * @param userId 登录者ID
	 * @param type 信息类型(0:所有；其他待定)
	 * @param readStatus 阅读状态(0：未读；1：已读；2：全部)
	 * @param page 分页
	 * @return
	 * @throws Exception
	 */
	List<MessageAddressee> getMessagePageListReceive(String userId,Integer type,Page<Map<String,Object>> page) throws Exception;
	
	/**
	 * 未读信息总数
	 * @param Users 用户对象
	 */
	Map<String,String> messageCount(Users user) throws Exception;
	
	/**
	 * 删除信息(收信人隐藏信息，系统会定期清理删除数据)
	 * @param id 站内信ID
	 * @throws Exception
	 */
	void messageDelete(Users users,String id) throws Exception;
	
	/**
	 * 修改站内信阅读状态
	 * @param type 消息类型
	 * @throws Exception
	 */
	void updateReadState(Users users,String type) throws Exception;
	
}
