package com.op.service.pointService.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.op.dao.BaseDao;
import com.op.dto.pointService.show.PointEvaluateDTO;
import com.op.dto.pointService.show.PointEvaluateReplyDTO;
import com.op.entity.pointService.PointEvaluate;
import com.op.entity.pointService.PointEvaluateImg;
import com.op.entity.pointService.PointEvaluateReply;
import com.op.plugin.page.Page;
import com.op.service.pointService.PointEvaluateService;

@Service("pointEvaluateServiceImpl")
public class PointEvaluateServiceImpl implements PointEvaluateService{
	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	/**
	 * 场馆评论
	 */
	@Override
	public List<PointEvaluateDTO> findPointCommentById(Page<Map<String,String>> page) throws Exception {
		return (List<PointEvaluateDTO>)dao.findForList("pointEvaluateMapper.findPointCommentByIdPage", page);
	}
	
	/**
     * 商家后台查看所有评论
     * @param id 用户ID
	 * @return
	 * @throws Exception
     */
    @Override
	public List<PointEvaluateDTO> allEvaluate(Page<Map<String,String>> page) throws Exception{
    	return (List<PointEvaluateDTO>)dao.findForList("pointEvaluateMapper.allEvaluate", page);
	}
	
	/**
	 * 场馆个人中心页场馆评论
	 */
	@Override
	public List<PointEvaluateDTO> findPointComment(Page<Map<String,String>> page) throws Exception {
		return (List<PointEvaluateDTO>)dao.findForList("pointEvaluateMapper.findPointCommentPage", page);
	}

	/**
	 * 项目评价
	 * @param id
	 * @return
	 * @throws Exception
	 */
    @Override
	public List<PointEvaluateDTO> findProjectCommentById(Page<Map<String,String>> page) throws Exception{
    	return (List<PointEvaluateDTO>)dao.findForList("pointEvaluateMapper.findProjectCommentByIdPage", page);
	}
	
	/**
	 * 评论回复数据
	 */
	@Override
	public List<PointEvaluateReplyDTO> findPointEvaluateReplyById(Page<String> page) throws Exception {
		return (List<PointEvaluateReplyDTO>) dao.findForList("pointEvaluateMapper.findPointEvaluateReplyByIdPage", page);
	}

	/**
	 * 评论回复
	 */
	@Override
	public void replyEvaluate(String pseId, String comment, String userId,String buId) throws Exception {
		PointEvaluateReply pointEvaluateReply = new PointEvaluateReply();
		
		pointEvaluateReply.setPser_venue_evaluate_id(Integer.valueOf(pseId));
		pointEvaluateReply.setPser_reply_user_id(userId);
		pointEvaluateReply.setPser_comment(comment);
		pointEvaluateReply.setPser_by_reply_user_id(buId);
		
    	dao.save("pointEvaluateMapper.saveReplyEvaluate", pointEvaluateReply);
	}

	/**
	 * 场馆打分
	 */
	@Override
	public String avgPoint(String id) throws Exception {
		return (String) dao.findForObject("pointEvaluateMapper.avgPoint", id);
	}
	
	/**
	 * 评论打分
	 */
	@Override
	public String avgProject(String id) throws Exception {
		return (String) dao.findForObject("pointEvaluateMapper.avgProject", id);
	}
	
	/**
     * 删除项目
     * @param map
     * @throws Exception
     */
    @Override
	public void deleteEvaluate(Map<String,String> map) throws Exception{
    	dao.update("pointEvaluateMapper.deleteEvaluate", map);
    }
    
    /**
     * 新增订单评价
     * @throws Exception
     */
    @Override
	public void saveEvaluate(PointEvaluate pointEvaluate,String images) throws Exception{
    	dao.save("pointEvaluateMapper.saveEvaluate", pointEvaluate);
    	
    	if(!StringUtils.isEmpty(images)){
    		String[] image = images.split(",");
    		List<PointEvaluateImg> list = new ArrayList<PointEvaluateImg>();
    		for(int i=0,len=image.length;i<len;i++){
    			PointEvaluateImg pointEvaluateImg = new PointEvaluateImg();
    			
    		 	//评价ID
    			pointEvaluateImg.setPsei_venue_evaluate_id(Integer.valueOf(pointEvaluate.getPse_id()));
    		 	//图片地址
    			pointEvaluateImg.setPsei_img_url(image[i]);
    		 	//创建人
    			pointEvaluateImg.setPsei_create_user_id(pointEvaluate.getPse_create_user_id());
    			
    			list.add(pointEvaluateImg);
        	}
    		
    		dao.save("pointEvaluateImgMapper.savePointEvaluateImg", list);
    	}
    	
    	dao.update("pointServiceOrderMapper.updateOrderConment", pointEvaluate.getPse_venue_order_id());
    }
    
	
}
