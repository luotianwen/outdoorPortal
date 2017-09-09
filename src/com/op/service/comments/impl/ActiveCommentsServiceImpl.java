package com.op.service.comments.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.dto.activity.comment.CommentInfoDTO;
import com.op.dto.usercenter.leader.activeManager.activeSignUp.MySignUp;
import com.op.entity.activity.comment.CommentImages;
import com.op.entity.comments.ActiveComments;
import com.op.plugin.page.Page;
import com.op.service.comments.ActiveCommentsService;
import com.op.util.Const;
import com.op.util.RelativeDateFormat;


@Service("commentsServiceImpl")
public class ActiveCommentsServiceImpl implements ActiveCommentsService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	/**
	 * 方法描述：用户评论活动
	 * 实现接口：@see com.op.service.comments.CommentsService#insertComment(com.op.entity.comments.Comments, java.util.Map)
	 * @param co
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void insertComment(ActiveComments co, Map<String, Object> map,String images)
			throws Exception {
		
		Page<Map<String,Object>> page = new Page<Map<String,Object>>();
		Map<String,Object> ordermap = new HashMap<String,Object>();
		ordermap.put("userId", co.getUser_id());
		ordermap.put("order", 0);
		ordermap.put("asu_id", co.getSuId());
		
		page.setT(ordermap);
		
		List<MySignUp> mySignUps = new ArrayList<MySignUp>();
		mySignUps = (List<MySignUp>) dao.findForList("activeSignupMapper.findMySignUpByUserIdPage",page);
		
		if(mySignUps.size()>0){
			if(mySignUps.get(0).getAsu_state()!=30){
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO,"订单未<span style='color:red;'>交易完成</span><br>不可对其进行评价操作！");
			}
			
		}else{
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO,"评论失败，订单不存在或者您不是该订单的创建人!");
			return;
		}

		
		// 保存评论数据
		co.setCommentTime(new Date());
		dao.save("activeCommentsMapper.insertComments", co);
		
		if(null!=images&&!"".equals(images)){
			String[] image = images.split(",");
			List<CommentImages> list = new ArrayList<CommentImages>();
			for(int i=0;i<image.length;i++){
				CommentImages commentImages = new CommentImages();
				
				commentImages.setCo_id(co.getCo_id());
				commentImages.setCi_cly_url(image[i]);
				commentImages.setSort(i);
				
				list.add(commentImages);
			}
			dao.save("commentImagesMapper.saveCommentImages", list);
		}
		
		// 更改订单评论状态
		dao.update("activeSignupMapper.updateActiveSignUpCommentFlag", co.getSuId());
		
		// 成功
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		
	}

	/**
	 * 方法描述：计算该活动的满意度 和 参与评价的人数
	 * 实现接口：@see com.op.service.comments.ActiveCommentsService#evaluateNumAndSatisfaction(int)
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> evaluateNumAndSatisfaction(int id) throws Exception {
		// TODO Auto-generated method stub
		return (Map<String, Object>) dao.findForObject("activeCommentsMapper.evaluateNumAndSatisfaction", id);
	}

	/**
	 * 方法描述：最新评论数据
	 * 实现接口：@see com.op.service.comments.ActiveCommentsService#newDateDatas(com.op.plugin.Page)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public void newDatas(Page<String> page) throws Exception {
		List<ActiveComments> list = (List<ActiveComments>) dao.findForList("activeCommentsMapper.newDatasPage", page);
		// 计算间隔时间
		for(int i=0,len=list.size();i<len;i++){
			ActiveComments ac = list.get(i);
			ac.setInterval(RelativeDateFormat.format(ac.getCommentTime()));
		}
		
		page.setResultList(list);
	}

	/**
	 * 方法描述：最热评论数据
	 * 实现接口：@see com.op.service.comments.ActiveCommentsService#hotData(com.op.plugin.Page)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public void hotDatas(Page<String> page) throws Exception {
		List<ActiveComments> list = (List<ActiveComments>) dao.findForList("activeCommentsMapper.hotDatasPage", page);
		// 计算间隔时间
		for(int i=0,len=list.size();i<len;i++){
			ActiveComments ac = list.get(i);
			ac.setInterval(RelativeDateFormat.format(ac.getCommentTime()));
		}
		
		page.setResultList(list);
	}

	/**
	 * 方法描述：活动详情	→	评价详情
	 * 实现接口：@see com.op.service.comments.ActiveCommentsService#commentInfo(java.lang.String)
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public CommentInfoDTO commentInfo(String id) throws Exception {
		// TODO Auto-generated method stub
		return (CommentInfoDTO) dao.findForObject("activeCommentsMapper.commentInfoById", id);
	}
	
	
	
}
