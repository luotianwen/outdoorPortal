package com.op.service.activity.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.dto.activity.consultation.ConsultationNumDTO;
import com.op.dto.activity.consultation.ReplyDTO;
import com.op.dto.activity.consultation.ResultConsultationDTO;
import com.op.dto.activity.reply.ReplyDataDTO;
import com.op.entity.activity.ActiveConsultation;
import com.op.entity.activity.ActiveConsultationPraises;
import com.op.entity.activity.ActiveConsultationReply;
import com.op.entity.users.Users;
import com.op.plugin.page.Page;
import com.op.service.activity.ActiveConsultationService;
import com.op.solr.SolrPage;
import com.op.util.AjaxMapUtil;


@Service("activeConsultationServiceImpl")
public class ActiveConsultationServiceImpl implements ActiveConsultationService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 方法描述：保存评论回复
	 * 实现接口：@see com.op.service.activity.ActiveConsultationService#saveConsultation(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * 返回类型：Map<String,Object>
	 * @param acr_ac_id 评论表ID
	 * @param acr_comment 回复内容
	 * @param acr_ac_user_id 被回复用户ID
	 * @param create_user_id 创建用户ID
	 * @throws Exception
	 */
	@Override
	public void saveConsultationReply(ActiveConsultationReply acr,Users user) throws Exception {
		
		// 回复时间
		acr.setAcr_create_time(new Date());
		
		// 回复用户ID
		acr.setAcr_create_user_id(user.getuId());
		
		// 回复用户名称
		acr.setAcr_create_user_name(user.getuName());
		
		//  头像
		acr.setAcr_create_user_header_img(user.getuHeadImg());
		
		dao.save("activeConsultationReplyMapper.inserintoReply", acr);
		
	}

	/**
	 * 方法描述：保存评论
	 * 实现接口：@see com.op.service.activity.ActiveConsultationService#saveConsultation(com.op.entity.activity.ActiveConsultation)
	 * @param ac
	 * @throws Exception
	 */
	@Override
	public void saveConsultation(ActiveConsultation ac) throws Exception {
		
		dao.save("activeConsultationMapper.inserintoConsultation", ac);
		
	}

	
	@Override
	public void test(){
		
		System.out.println("可以注入");
	}

	/**
	 * 方法描述：查询提问信息
	 * 实现接口：@see com.op.service.activity.ActiveConsultationService#findNewConsultation(com.op.plugin.Page)
	 * @param page
	 * @throws Exception
	 */
	@Override
	public void findConsultation(Page<Map<String,Object>> page) throws Exception {
		
		List<ActiveConsultation> list = (List<ActiveConsultation>) dao.findForList("activeConsultationMapper.findConsultationPage", page);
		
		page.setResultList(list);
		
	}

	/**
	 * 方法描述：增加和删除点赞
	 * 实现接口：@see com.op.service.activity.ActiveConsultationService#cdPraises(java.util.Map)
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void cdPraises(Map<String,Object> map) throws Exception {
		// 判断是否非用户恶意操作
		boolean isDelete = true;
		
		if(("add").equals(map.get("type")+"")){
			// 防止用户恶意修改导致重复添加，需要在查询一遍
			int i = (int) dao.findForObject("activeConsultationPraisesMapper.findByUserIdAndCoId", map);
			if(i==0){
				dao.save("activeConsultationPraisesMapper.insertData", map);
			}else{
				AjaxMapUtil.errorInfoMap(map, "您已经赞过该活动！");
				return;
			}
		}else{
			int i=(int) dao.delete("activeConsultationPraisesMapper.deleteData", map);
			if(i==0){
				isDelete = false;
			}
		}
		
		if(isDelete){
			dao.update("activeConsultationMapper.updatePraises", map);
		}
		
		AjaxMapUtil.successMap(map);
	}

	
	/**
	 * 方法描述：根据用户ID和咨询ID查询是否点赞
	 * 实现接口：@see com.op.service.activity.ActiveConsultationService#findPraisesByUserIdAndConsultationId()
	 * @throws Exception
	 */
	@Override
	public void findPraisesByUserIdAndConsultationId(Map<String,Object> map) throws Exception {
		// 根据用户ID和咨询ID查询关联点赞信息
		 List<ActiveConsultationPraises> list = (List<ActiveConsultationPraises>) dao.findForList("activeConsultationPraisesMapper.findPraisesByUserIdAndConsultationId", map);
		 
		 SolrPage page = (SolrPage) map.get("page");
		 List<ResultConsultationDTO> dtoList = (List<ResultConsultationDTO>) page.getResult();
		 
		 // 匹配相同的数据，向dto的点赞ID赋值，页面判断如果不为空证明当前用户已经点过赞
		 for(ResultConsultationDTO dto:dtoList){
			 for(ActiveConsultationPraises praises : list){
				if(praises.getAcp_ac_id().equals(dto.getId())){
					dto.setAcp_id(praises.getAcp_id());
				}
			 }
			 
		 }
		 
		 map.clear();
		 
	}

	
	/**
	 * 方法描述：个人中心	→	我的问答	→	回复
	 * 实现接口：@see com.op.service.activity.ActiveConsultationService#reply(com.op.dto.activity.consultation.ReplyDTO)
	 * @param dto
	 * @throws Exception
	 */
	@Override
	public void saveReply(ReplyDTO dto) throws Exception {
		dto.setTime(new Date());
		// 问题状态（0:未回复；1：已回复；2：追问）
		// 发布者解答未回复的数据
		if(dto.getReplyType() == 0){
			// 保存在activeConsultation表
			dao.update("activeConsultationMapper.updateReplyInfo", dto);
		}
		// 用户对已回复的数据进行追问
		else if(dto.getReplyType() == 1){
			// 保存在activeConsultationReply表
			dao.save("activeConsultationReplyMapper.saveReplyInfo", dto);
			// 修改状态为2：追问
			dto.setReplyType(2);
			dto.setUserId(dto.getReplyUserId());
			dao.update("activeConsultationMapper.updateReply", dto);
		}
		// 发布者回复用户的追问
		else if(dto.getReplyType() == 2){
			// 保存在activeConsultationReply表
			dao.save("activeConsultationReplyMapper.saveReplyInfo", dto);
			// 修改状态为1：已回复 
			dto.setReplyType(1);
			dao.update("activeConsultationMapper.updateReply", dto);
		}
		
	}

	/**
	 * 方法描述：个人中心	→	我的问答	→	异步加载数据
	 * 实现接口：@see com.op.service.activity.ActiveConsultationService#loadConsultation(java.util.Map)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ReplyDataDTO> loadConsultation(int userType,Page<Map<String,Object>> page ,String loadType) throws Exception {
		return (List<ReplyDataDTO>) dao.findForList("activeConsultationMapper.selectNoReplyByActiveIdForLeaderPage", page);
	}

	/**
	 * 方法描述：查询用户咨询回答问题数量
	 * 实现接口：@see com.op.service.activity.ActiveConsultationService#UserConsultationNum(java.lang.String)
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public ConsultationNumDTO userConsultationNum(String userId) throws Exception {
		return (ConsultationNumDTO) dao.findForObject("activeConsultationMapper.userConsultationNum", userId);
	}

	
}
