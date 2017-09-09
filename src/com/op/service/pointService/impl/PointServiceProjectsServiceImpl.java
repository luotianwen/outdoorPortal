package com.op.service.pointService.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.dto.pointService.order.BuyProjectDTO;
import com.op.dto.pointService.show.PointServiceProjectShowDTO;
import com.op.dto.pointService.update.project.ProjectUpdateDTO;
import com.op.entity.pointService.project.PointServiceProjects;
import com.op.plugin.page.Page;
import com.op.service.pointService.PointServiceProjectsService;
import com.op.util.FreeMarkerUtil;

@Service("pointServiceProjectsServiceImpl")
public class PointServiceProjectsServiceImpl implements PointServiceProjectsService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	@Resource(name="freeMarkerUtil")
	FreeMarkerUtil freeMarkerUtil;
	
	/**
	 * @param id 项目id
	 * @return
	 * @throws Exception
	 */
	@Override
	public void pointServiceProjectShow(String id,String ftl,String targetFile ,String contextPath) throws Exception {
		PointServiceProjectShowDTO pointServiceProjectShowDTO = (PointServiceProjectShowDTO) dao.findForObject("pointServiceProjectsMapper.pointServiceProjectShow", id);
		
		if(pointServiceProjectShowDTO!=null){
			Map<String,String> map = new HashMap<String,String>();
			// 当前导航标签
			map.put("ht", "场馆");
			
			// 填充数据 
			Map<String,Object> data=new HashMap<String,Object>();
			
			data.put("pointServiceProjectShowDTO", pointServiceProjectShowDTO);
			data.put("map", map);
			
			freeMarkerUtil.createFile(ftl, data, targetFile,contextPath);
		}
	}

	/**
	 * 查询商家其他项目
	 * @param id 项目ID
	 * @param venueId 商家ID
	 * @throws Exception
	 */
	@Override
	public List<PointServiceProjects> findProjectByVenueId(String venueId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("venueId", venueId);
		
		return (List<PointServiceProjects>) dao.findForList("pointServiceProjectsMapper.findProjectByVenueId", map);
	}
	
	/**
	 * 商家查看所有项目
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PointServiceProjects> findProjectByUserId(Page<String> page) throws Exception{
		
		return (List<PointServiceProjects>) dao.findForList("pointServiceProjectsMapper.findProjectByUserIdPage", page);
	}
	
	/**
	 * 购买项目
	 * @param id 项目ID
	 * @throws Exception
	 */
	@Override
	public BuyProjectDTO buyProject(String id) throws Exception{
		return (BuyProjectDTO) dao.findForObject("pointServiceProjectsMapper.buyProject", id);
	}
	
	/**
	 * 订单场馆项目信息
	 * @param id 项目ID
	 * @throws Exception
	 */
	@Override
	public BuyProjectDTO buyProjectDTOInfo(String id) throws Exception{
		return (BuyProjectDTO) dao.findForObject("pointServiceProjectsMapper.buyProjectDTOInfo", id);
	}
	
	/**
	 * 修改项目页面显示信息
	 * @param id 项目ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public ProjectUpdateDTO findProjectUpdateDTOByPspId(String id,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		
		return (ProjectUpdateDTO) dao.findForObject("pointServiceProjectsMapper.findProjectUpdateDTOByPspId", map);
	}
	
	
	
}
