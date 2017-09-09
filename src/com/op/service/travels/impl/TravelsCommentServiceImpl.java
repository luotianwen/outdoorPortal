package com.op.service.travels.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



import com.op.dao.BaseDao;
import com.op.dto.travels.TravelsHotDataDTO;
import com.op.entity.travels.TravelsComment;
import com.op.plugin.page.Page;
import com.op.service.travels.TravelsCommentService;
import com.op.service.travels.TravelsService;
import com.op.util.Const;
import com.op.util.Tools;
import  com.op.util.jedis.RedisUtil;


@Service("travelsCommentServiceImpl")
public class TravelsCommentServiceImpl implements TravelsCommentService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
    
	/**
	 * 游记
	 */
	@Resource(name="travelsServiceImpl")
	TravelsService travelsServiceImpl;

	/**
	 * 方法描述：评论
	 * 实现接口：@see com.op.service.travels.TravelsCommentService#comment(com.op.entity.travels.TravelsComment)
	 * @param comment
	 * @throws Exception
	 */
	@Override
	public void saveComment(TravelsComment comment) throws Exception {
		dao.save("travelsCommentMapper.comment", comment);
		
		// 修改评论次数
		dao.update("travelsMapper.updateReplyCount", comment.getTc_travelsId());
		
		// 修改缓存

		String id = comment.getTc_travelsId();
		
		// 缓存不存在数据
		if(RedisUtil.hkeys(Const.TRAVELSHOTDATAKEY+id).isEmpty()){
			// 获取该游记数据
			TravelsHotDataDTO th = travelsServiceImpl.findHotData(id.toString());
			
			//存入热数据到缓存
			RedisUtil.hmset(Const.TRAVELSHOTDATAKEY+id,Tools.transBean2MapString(th));
			
			//108000秒过期 即一天半后自动删除缓存
			RedisUtil.expire(Const.TRAVELSHOTDATAKEY+id, (int) (1.5*24*60*60));
			
		}else{
			List<String> hotData = RedisUtil.hmget(Const.TRAVELSHOTDATAKEY+id,"reply_count");
			RedisUtil.hset(Const.TRAVELSHOTDATAKEY+id, "reply_count",Integer.valueOf(hotData.get(0))+1+"");
		}
		
	}

	/**
	 * 方法描述：根据游记id查询评论内容
	 * 实现接口：@see com.op.service.travels.TravelsCommentService#findCommentByTravelsId(com.op.plugin.page.Page)
	 * @param page
	 * @throws Exception
	 */
	@Override
	public void findCommentByTravelsId(Page<String> page) throws Exception {
		List<TravelsComment> list = (List<TravelsComment>) dao.findForList("travelsCommentMapper.findCommentByTravelsIdPage", page);
		page.setResultList(list);
	}
	
}
