package com.op.service.pointService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.dto.pointService.order.BuyProjectDTO;
import com.op.dto.pointService.update.project.ProjectUpdateDTO;
import com.op.entity.pointService.project.PointServiceProjects;
import com.op.plugin.page.Page;

@Service("poinServiceProjectsService")
public interface PointServiceProjectsService {
	
	/**
	 * 项目详情
	 * @param id 项目id
	 * @return
	 * @throws Exception
	 */
	void pointServiceProjectShow(String id,String ftl,String targetFile ,String contextPath) throws Exception;

	/**
	 * 查询商家所有项目
	 * @param id 项目ID
	 * @param venueId 商家ID
	 * @throws Exception
	 */
	List<PointServiceProjects> findProjectByVenueId(String venueId) throws Exception;
	
	/**
	 * 商家查看所有项目
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<PointServiceProjects> findProjectByUserId(Page<String> page) throws Exception;
	
	/**
	 * 购买项目
	 * @param id 项目ID
	 * @throws Exception
	 */
	BuyProjectDTO buyProject(String id) throws Exception;
	
	/**
	 * 订单场馆项目信息
	 * @param id 项目ID
	 * @throws Exception
	 */
	BuyProjectDTO buyProjectDTOInfo(String id) throws Exception;
	
	/**
	 * 修改项目页面显示信息
	 * @param id 项目ID
	 * @return
	 * @throws Exception
	 */
	ProjectUpdateDTO findProjectUpdateDTOByPspId(String id,String userId) throws Exception;
	
	
}
