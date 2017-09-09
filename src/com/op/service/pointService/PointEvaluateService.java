package com.op.service.pointService;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.op.dto.pointService.show.PointEvaluateDTO;
import com.op.dto.pointService.show.PointEvaluateReplyDTO;
import com.op.entity.pointService.PointEvaluate;
import com.op.plugin.page.Page;
/** 
 * 地点服务评价(pointEvaluate)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-06-30 10:56:15 
 */  
@Service("pointEvaluateService")
public interface PointEvaluateService {

	/**
	 * 场馆点评
	 * @param id 场馆ID
	 * @return
	 * @throws Exception
	 */
    List<PointEvaluateDTO> findPointCommentById(Page<Map<String,String>> page) throws Exception;
    
    /**
     * 商家后台查看所有评论
     * @param id 用户ID
	 * @return
	 * @throws Exception
     */
    List<PointEvaluateDTO> allEvaluate(Page<Map<String,String>> page) throws Exception;
    
    /**
	 * 商家个人中心页场馆点评
	 * @param id
	 * @return
	 * @throws Exception
	 */
    List<PointEvaluateDTO> findPointComment(Page<Map<String,String>> page) throws Exception;
    
    /**
	 * 项目评价
	 * @param id
	 * @return
	 * @throws Exception
	 */
    List<PointEvaluateDTO> findProjectCommentById(Page<Map<String,String>> page) throws Exception;
    
    /**
     * 评论回复数据
     * @param id
     * @param page
     * @return
     * @throws Exception
     */
    List<PointEvaluateReplyDTO> findPointEvaluateReplyById(Page<String> page) throws Exception;
    
    /**
     * 回复评论
     * @param pseId 评论ID
     * @param comment 回复内容
     * @param userId 回复人ID
     * @param buId 被回复人ID
     * @throws Exception
     */
    void replyEvaluate(String pseId,String comment,String userId,String buId) throws Exception;
    
    /**
     * 场馆打分
     * @param id 场馆ID
     */
    String avgPoint(String id) throws Exception;
    
    /**
     * 评论打分
     * @param id 评论ID
     */
    String avgProject(String id) throws Exception;

    /**
     * 删除项目
     * @param map
     * @throws Exception
     */
    void deleteEvaluate(Map<String,String> map) throws Exception;

    /**
     * 新增订单评价
     * @throws Exception
     */
    void saveEvaluate(PointEvaluate pointEvaluate,String images) throws Exception;
    
    
}
