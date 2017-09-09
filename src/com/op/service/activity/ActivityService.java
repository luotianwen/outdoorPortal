package com.op.service.activity;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.op.dto.activity.draft.DraftResultsDTO;
import com.op.dto.activity.edit.ActivityDraftDTO;
import com.op.dto.activity.insertActivity.ActivityBaseInfoDTO;
import com.op.dto.activity.insertActivity.ActivityRestsInfoDTO;
import com.op.dto.activity.insertActivity.cost.ActivityCostDTO;
import com.op.dto.activity.insertActivity.line.InsertLineDTO;
import com.op.dto.activity.queryActivity.QueryActivity;
import com.op.dto.activity.reply.ReplyActiveDTO;
import com.op.dto.activity.update.UpdateActiveDTO;
import com.op.dto.usercenter.keeper.MyActiveInfoDTO;
import com.op.dto.usercenter.keeper.MyActiveResultDTO;
import com.op.dto.usercenter.leader.activeManager.LeaderActivesDTO;
import com.op.entity.activity.Activity;
import com.op.plugin.page.Page;

/**
 * 数据库中(活动信息)接口
 * @author PYW
 * Date: 2015年12月18日 09:29:12
 */
@Service("activityService")
public interface ActivityService {

    /*
	*列表(全部) 
    */			
	public List<Activity> getActivitylist() throws Exception;
	/*
	 *根据创建用户查询出自己发布的活动id 
	 */
	public List<Integer> selectId(String createuser) throws Exception;
	
	/**
	 * 修改活动人数
	 */
	void updateAlreadyNum(Map<String,Object> map) throws Exception;
	
	/**
	 * 方法描述：根据活动ID查询活动详情
	 * 返回类型：Activity
	 * @param id	活动id
	 * @param ftl	模板文件
	 * @param targetFile	生成的目标文件
	 * @param contextPath	servletContext上下文
	 * @param servletContext	servletContext获取根目录
	 * @return
	 * @throws Exception
	 */
	public boolean detailById(String id,String ftl, String targetFile ,String contextPath) throws Exception;
	
	/**
	 * 方法描述：根据活动ID查询活动详情(订单详情使用)
	 * 返回类型：Activity
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public QueryActivity orderById(String id) throws Exception;
	
	/**
	 * 方法描述： 查询领队所发布的活动列表
	 * 返回类型：List<Activity>
	 * @param sa 查询对象
	 * @return
	 * @throws Exception
	 */
	public List<LeaderActivesDTO> findByUserId(Page<LeaderActivesDTO> page) throws Exception;
	
	/**
	 * 查询是否已经报名过该活动
	 * @param activeId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int findCountByUserAndActiveId(String activeId,String userId) throws Exception;
	
	/**
	 * 方法描述：是否已经报名
	 * 返回类型：boolean
	 * @param activeId
	 * @param userId
	 * @return
	 */
	public boolean isAlreadySignUp(String activeId,String userId) throws Exception;
	
	/**
	 * 方法描述：添加活动基础信息
	 * 返回类型：Map<String,Object>
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	void insertBaseInfo(ActivityBaseInfoDTO dto,Map<String,Object> map ,HttpSession session)  throws Exception;
	
	/**
	 * 方法描述：活动其他信息保存（描述、介绍、特色、费用、退款说明、活动须知...）
	 * 返回类型：void
	 * @throws Exception
	 */
	void insertRestsInfo(ActivityRestsInfoDTO dto,Map<String,Object> map) throws Exception;
	
	/**
	 * 方法描述：添加活动线路
	 * 返回类型：void
	 * @param dto
	 * @param map
	 * @throws Exception
	 */
	void insertLine(InsertLineDTO dto,Map<String,Object> map,HttpSession session) throws Exception;
	
	/**
	 * 方法描述：添加活动额外费用
	 * 返回类型：void
	 * @param dto
	 * @param map
	 * @throws Exception
	 */
	void insertCost(ActivityCostDTO dto,Map<String,Object> map) throws Exception;
	
	/**
	 * 方法描述：根据用户ID查询活动草稿数量
	 * 返回类型：int
	 * @param uId
	 * @return
	 * @throws Exception
	 */
	int draft(String uId) throws Exception;
	
	/**
	 * 方法描述：提交审核
	 * 返回类型：void
	 * @param map
	 * @throws Exception
	 */
	void submitAudit(Map<String,String> map) throws Exception;
	
	
	/**
	 * 方法描述：个人中心→我的管家→我的活动
	 * 返回类型：List<MyActiveDTO>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	MyActiveResultDTO myActive(int uType , int type ,Page<String> page) throws Exception;
	
	/**
	 * 方法描述：个人中心	→	我的问答
	 * 返回类型：List<NoReplyActiveDTO>
	 * @param uId
	 * @return
	 * @throws Exception
	 */
	List<ReplyActiveDTO> myConsultation(String searchType,Page<Map<String,String>> page) throws Exception;
	
	/**
	 * 方法描述：活动草稿箱列表
	 * 返回类型：List<DraftResultsDTO>
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<DraftResultsDTO> showDraftActivityList(Page<String> page)  throws Exception;
	
	/**
	 * 方法描述：删除草稿箱
	 * 返回类型：void
	 * @param map
	 * @throws Exception
	 */
	void deleteDraft(Map<String,String> map)  throws Exception;
	
	/**
	 * 方法描述：编写活动草稿信息
	 * 返回类型：void
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	ActivityDraftDTO editDraft(Map<String,String> map)  throws Exception;
	
	/**
	 * 方法描述：再次发布活动
	 * 返回类型：void
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	ActivityDraftDTO againRelease(Map<String,String> map)  throws Exception;
	
	/**
	 * 方法描述：查询活动数据（更新活动的最新数据）
	 * 返回类型：void
	 * @param id
	 * @throws Exception
	 */
	UpdateActiveDTO updateActivityData(String id)throws Exception;
	
	/**
	 * 方法描述：查询我的草稿活动
	 * 返回类型：List<MyActiveInfoDTO>
	 * @param uId
	 * @return
	 * @throws Exception
	 */
	List<MyActiveInfoDTO> myDraftActivity(String uId)throws Exception;
	
	/**
	 * 方法描述：更改过期的活动状态
	 * 返回类型：void
	 * @throws Exception
	 */
	void updatePastActivity()throws Exception;

	/**
	 * 查询活动状态
	 */
	Map<String,String> findActiveState(String id) throws Exception;
	
	/**
	 * 查询活动模板
	 * @param uType 用户类型
	 * @param page 分页
	 * @return
	 * @throws Exception
	 */
	List<MyActiveInfoDTO> findActiveTemplate(Page<String> page) throws Exception;
	
	/**
	 * 添加活动模板
	 */
	void saveTemplate(Map<String,String> map) throws Exception;	
	
	/**
	 * 删除活动模板
	 */
	void deleteTemplate(Map<String,String> map) throws Exception;	
	
	/**
	 * 关闭活动
	 */
	void closeActive(Map<String,String> map) throws Exception;
	
}
