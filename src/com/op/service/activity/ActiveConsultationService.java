package com.op.service.activity;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.op.dto.activity.consultation.ConsultationNumDTO;
import com.op.dto.activity.consultation.ReplyDTO;
import com.op.dto.activity.reply.ReplyDataDTO;
import com.op.entity.activity.ActiveConsultation;
import com.op.entity.activity.ActiveConsultationReply;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;


@Service("activeConsultationService")
public interface ActiveConsultationService {
	
	/**
	 * 方法描述：保存评论回复
	 * 返回类型：void
	 * @param acr
	 * @throws Exception
	 */
	void saveConsultationReply(ActiveConsultationReply acr,Users user) throws Exception;
	
	/**
	 * 方法描述：保存评论
	 * 返回类型：void
	 * @param ac
	 * @throws Exception
	 */
	void saveConsultation(ActiveConsultation ac) throws Exception;
	
	/**
	 * 方法描述：查询提问信息
	 * 返回类型：void
	 * @param page
	 * @throws Exception
	 */
	void findConsultation(Page<Map<String,Object>> page) throws Exception; 
	
	/**
	 * 方法描述：增加和删除点赞
	 * 返回类型：void
	 * @param map
	 * @throws Exception
	 */
	void cdPraises(Map<String,Object> map) throws Exception;
	
	public void test();
	
	/**
	 * 方法描述：根据用户ID和咨询ID查询是否点赞
	 * 返回类型：void
	 * @throws Exception
	 */
	void findPraisesByUserIdAndConsultationId(Map<String,Object> map) throws Exception;
	
	/**
	 * 方法描述：个人中心	→	我的问答	→	回复
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	void saveReply(ReplyDTO dto) throws Exception;
	
	/**
	 * 方法描述：个人中心	→	我的问答	→	异步加载数据
	 * 返回类型：List<ReplyDataDTO>
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<ReplyDataDTO> loadConsultation(int userType,Page<Map<String,Object>> page ,String loadType) throws Exception;
	
	/**
	 * 方法描述：查询用户咨询回答问题数量
	 * 返回类型：ConsultationNumDTO
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	ConsultationNumDTO userConsultationNum(String userId) throws Exception;
} 
