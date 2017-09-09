package com.op.service.pointService.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.op.dao.BaseDao;
import com.op.dto.pointService.auth.FindPointAuthDTO;
import com.op.dto.pointService.auth.InsertPointAuthDTO;
import com.op.dto.pointService.evaluate.PointEvaluateInfo;
import com.op.dto.pointService.insert.fabu.PointProjectsImgDTO;
import com.op.dto.pointService.insert.fabu.PointServiceInfoDTO;
import com.op.dto.pointService.insert.fabu.PointServiceProjectsDTO;
import com.op.dto.pointService.insert.fabu.ProjectCrowdTypeDTO;
import com.op.dto.pointService.insert.ruzhu.BusinessDays;
import com.op.dto.pointService.insert.ruzhu.InsertPointServiceDTO;
import com.op.dto.pointService.insert.ruzhu.InsertPointServiceDate;
import com.op.dto.pointService.insert.ruzhu.PointServiceImg;
import com.op.dto.pointService.show.PointServiceImgDTO;
import com.op.dto.pointService.show.PointServiceProjectDTO;
import com.op.dto.pointService.show.PointServiceShowDTO;
import com.op.dto.pointService.update.point.PointServiceUpdateDTO;
import com.op.entity.pointService.PointServiceFeature;
import com.op.plugin.page.Page;
import com.op.service.pointService.PointServiceService;
import com.op.util.Const;
import com.op.util.FreeMarkerUtil;
import com.op.util.listNull.YHDCollectionUtils;

/**
 * =============================================================
 * 项目名：outdoorPortal
 * 类描述：地点服务Service实现类
 * 创建人：Yan
 * 创建时间： 2016-6-20
 * modification list：
 * =============================================================
 */
@Service("pointServiceServiceImpl")
public class PointServiceServiceImpl implements PointServiceService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	@Resource(name="freeMarkerUtil")
	FreeMarkerUtil freeMarkerUtil;
	
	/**
	 * 方法描述：新增地点服务
	 * 实现接口：@see com.op.service.pointService.PointServiceService#insertReplyPointService(com.op.dto.pointService.insert.InsertPointServiceDTO)
	 * @param dto
	 * @throws Exception
	 */
	@Override
	public String insertReplyPointService(InsertPointServiceDTO dto) throws Exception {
		if(StringUtils.isEmpty(dto.getPs_point_service_id())){
			dto.setPs_is_add("0");
		}else{
			dto.setPs_is_add("1");
		}

		// 添加基本信息
		dao.save("pointServiceMapper.insertReplyPointService", dto);
		
		// 联系方式
		dao.save("pointServiceContactMapper.insertPointServiceContact", dto);
		
		/* --------------	营业日	------------------------	*/
		// 清空null数据
		List<InsertPointServiceDate> dates = dto.getDates();
		dates.removeAll(YHDCollectionUtils.nullCollection());
		
		for(int i=0,len = dates.size();i<len;i++){
			InsertPointServiceDate date = dates.get(i);
			// 封装地点服务ID
			date.setPsabd_point_service_id(dto.getPs_id());

			// 保存
			dao.save("pointServiceBusinessDayMapper.insert", date);
			
			String[] days = date.getDays().split(",");
			if(days.length>0){
				List<BusinessDays> list = new ArrayList<BusinessDays>();
				// 保存该营业日
				for(int j=0,jlen=days.length;j<jlen;j++){

					BusinessDays day = new BusinessDays();
					// 营业日
					day.setPsbd_day(days[j]);
					// 地点服务ID
					day.setPsbd_point_service_id(dto.getPs_id());
					// 地点服务营业日ID
					day.setPsabd_id(date.getPsabd_id());
					
					list.add(day);
				}
				
				dao.save("businessDaysMapper.insert", list);
			}
		}
		// 商户图片
		List<PointServiceImg> imgSrc = dto.getImgSrc();
		if(imgSrc!=null){
			for(int i=0,len = imgSrc.size();i<len;i++){
				PointServiceImg img = imgSrc.get(i);
				if(img!=null&&!StringUtils.isEmpty(img.getPsi_url())){
					// 地点服务ID
					img.setPsi_point_service_id(dto.getPs_id());
					
					// 作者ID
					img.setPsi_upload_user_id(dto.getPs_create_user_id());
				}else{
					imgSrc.remove(i);
					len--;
					i--;
				}
			}
			
			if(imgSrc.size()>0){
				dao.save("pointServiceImgMapper.insert", imgSrc);
			}
		}
		
		return dto.getPs_id();
	}

	
	/**
	 * 方法描述：发布项目/完善商户信息
	 * 实现接口：@see com.op.service.pointService.PointServiceService#fabu(com.op.dto.pointService.insert.fabu.PointServiceInfoDTO)
	 * @param dto
	 * @throws Exception
	 */
	@Override
	public void saveProjectAndPointServiceInfo(PointServiceInfoDTO dto,String psp_update_id) throws Exception {
		// 修改场馆信息(返回主键)
		int num = (int) dao.update("pointServiceInfoMapper.updatePointServiceInfo", dto);
		if(num==0){
			// 保存场馆信息(返回主键)
			dao.save("pointServiceInfoMapper.saveProjectAndPointServiceInfo", dto);
		}
		
		// 保存标签
		saveTabs(dto);
		
		// 保存项目
		saveProjects(dto,psp_update_id);
	}
	
	/**
	 * 方法描述：保存项目
	 * 返回类型：void
	 * @param dto
	 * @throws Exception 
	 */
	private void saveProjects(PointServiceInfoDTO dto,String psp_update_id) throws Exception {
		List<PointServiceProjectsDTO> projects = dto.getProjects();
		if(projects!=null){
			// 清除null标签
			projects.removeAll(YHDCollectionUtils.nullCollection());
			
			// 保存项目
			for(int i=0,len=projects.size();i<len;i++){
				PointServiceProjectsDTO pro = projects.get(i);
				// 地点服务ID
				pro.setPsp_venue_id(dto.getPs_id());
				// 温馨提示
				pro.setPsp_reminder(dto.getPsi_pay_info());
				//创建人
				pro.setPsp_create(dto.getCreate_user_id());
				
				if(!StringUtils.isEmpty(psp_update_id)){
					pro.setPsp_update_id(psp_update_id);
				}
				
				// 持久化项目(返回主键)
				dao.save("pointServiceProjectsMapper.saveProjects", pro);
				
				// 解析适合人群
				List<ProjectCrowdTypeDTO> crowdList=new ArrayList<ProjectCrowdTypeDTO>();
				String[] crowds = pro.getCrowds().split(",");
				for(int j=0,jlen = crowds.length;j<jlen;j++){
					crowdList.add(new ProjectCrowdTypeDTO(pro.getPsp_id(),crowds[j]));
				}
				// 持久化适合人群
				dao.save("projectCrowdTypeMapper.saveProjectCrowdType", crowdList);
				
				// 解析项目图片
				// 清除项目图片null对象
				List<PointProjectsImgDTO> imgs = pro.getImgs();
				if(imgs!=null){
					imgs.removeAll(YHDCollectionUtils.nullCollection());
					for(int imgIndex = 0,imgLen = imgs.size();i<imgLen;i++){
						PointProjectsImgDTO img = imgs.get(imgIndex);
						// 项目id
						img.setPpi_venue_project_id(pro.getPsp_id());
						
						// 上传时间
						img.setPi_upload_time(new Date());
						
						// 上传作者
						img.setPi_upload_user_id(dto.getCreate_user_id());
					}
					// 持久化项目图片
					dao.save("pointProjectsImgMapper.saveProjectImg", imgs);
				}
				
			}
		}
	}


	/**
	 * 方法描述：保存标签
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	void saveTabs(PointServiceInfoDTO dto) throws Exception {
		// 清除null标签
		List<PointServiceFeature> tabs = dto.getTabs();
		if(tabs!=null){
			tabs.removeAll(YHDCollectionUtils.nullCollection());
			for(int i=0,len = tabs.size();i<len;i++){
				PointServiceFeature tab = tabs.get(i);
				// 地点服务id
				tab.setPsf_venue_id(dto.getPs_id());
				
				// 创建时间
				tab.setPsf_create_time(new Date());
				
				// 创建用户
				tab.setPsf_create_user_id(dto.getCreate_user_id());
			}
			
			//删除之前标签
			dao.delete("pointServiceFeatureMapper.deleteTabs", dto.getPs_id());
			// 持久化标签(不用返回主键)
			dao.save("pointServiceFeatureMapper.saveTabs", tabs);
		}
		
	}


	/**
	 * 方法描述：发布项目认证校验
	 * 一：用户对某商户发布项目校验
	 * 		1：对该商户信息是否审核的一种判断
	 *		2：判断该商户是否已经认证
	 *			1）：已认证：如果是本人认证那么跳转发布页面；否则提示用户该商户已经认证，不可对其操作，如要认领请联系客服；
	 *			2）：未认证：是否存在认证信息
	 *				a)：已存在：判断是否本人，如果是本人提示待审核；非本人进行认证操作
	 *				b)：不存在：进行认证操作
	 * 实现接口：@see com.op.service.pointService.PointServiceService#checkAuth(java.util.Map)
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void checkAuth(Map<String, String> map) throws Exception {
		// 商户ID
		String pointServiceId = map.get("pointServiceId");
		String uId = map.get("uId");
		
		map.clear();
		
		// 商户信息
		FindPointAuthDTO dto = (FindPointAuthDTO) dao.findForObject("pointAuthMapper.findByPointServiceId", pointServiceId);
		
		if(dto != null){
			// 已认证
			if("3".equals(dto.getAuditor_state())){
				// 认证信息非本人提交，不可对其操作
				if(!uId.equals(dto.getUser_id())){
					map.put(Const.RESPONSE_STATE, "500");
					map.put("location", "该商户已经认证，不可对其操作，如有辩解请联系客服处理");
				}else{
					map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
					map.put("location", "pointService/reply.html?id="+pointServiceId);
				}
			}
			// 未认证,已存在:判断是否本人，如果是本人提示待审核；非本人进行认证操作
			else{
				// 认证信息非本人提交,进行认证操作
				if(!uId.equals(dto.getUser_id())){
					map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
					map.put("location", "pointService/auth.html?id="+pointServiceId);
				}
				// 是本人提示状态信息
				else{
					String stateVal = "";
					switch (dto.getAuditor_state()) {
					case "1":
						stateVal="待审核";
						break;
						
					case "2":
						stateVal="正在审核中";
						break;
						
					case "4":
						stateVal="审核失败,请前往<a href='javascript:void(0)'>个人中心</a>修改重新提交";
						break;
						
					default:
						break;
					}
					map.put(Const.RESPONSE_STATE, "500");
					map.put(Const.ERROR_INFO, "您所提交的资质信息"+stateVal);
				}
			}
		}
		// 不存在:未认证,进行认证操作
		else{
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
			map.put("location", "pointService/auth.html?id="+pointServiceId);
		}
		
	}


	/**
	 * 方法描述：添加商户认证数据
	 * 实现接口：@see com.op.service.pointService.PointServiceService#insertAuth(com.op.dto.pointService.auth.InsertPointAuthDTO)
	 * @param dto
	 * @throws Exception
	 */
	@Override
	public void insertAuth(InsertPointAuthDTO dto) throws Exception {
		dao.save("pointAuthMapper.insertAuth", dto);
	}
	
	/**
	 * 方法描述：修改商户认证数据
	 * 实现接口：@see com.op.service.pointService.PointServiceService#insertAuth(com.op.dto.pointService.auth.InsertPointAuthDTO)
	 * @param dto
	 * @throws Exception
	 */
	@Override
	public void updateAuth(InsertPointAuthDTO dto) throws Exception {
		dao.save("pointAuthMapper.updateAuth", dto);
	}

	/**
	 * 场馆展示
	 * @param id 场馆ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public void pointServiceShow(String id,String ftl,String targetFile ,String contextPath) throws Exception{
		PointServiceShowDTO pointServiceShowDTO =  (PointServiceShowDTO) dao.findForObject("pointServiceMapper.pointServiceShow", id);
		
		if(pointServiceShowDTO!=null){
			Map<String,String> map = new HashMap<String,String>();
			// 当前导航标签
			map.put("ht", "场馆");
			
			// 填充数据 
			Map<String,Object> data=new HashMap<String,Object>();
			
			data.put("pointServiceShowDTO", pointServiceShowDTO);
			data.put("map", map);
			
			freeMarkerUtil.createFile(ftl, data, targetFile,contextPath);
		}
		
	}
	
	
	/**
	 * 项目收藏数量
	 * @param id 项目ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public String pointCollection(String id) throws Exception{
		
		return (String) dao.findForObject("pointServiceMapper.pointCollection", id);
	}
	
	/**
	 * 收藏
	 * @param id 场馆ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	@Override
	public void addConllection(String id,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		
		dao.save("pointServiceMapper.addConllection", map);
		
//
//		if(StringUtils.isEmpty(RedisUtil.get("pointCollection_"+id))){
//			String count = pointCollection(id);
//			RedisUtil.set("pointCollection_"+id, count);
//			RedisUtil.expire(("pointCollection_"+id), (int) (1.5*24*60*60));
//		}else{
//			RedisUtil.set("pointCollection_"+id, Integer.valueOf(RedisUtil.get("pointCollection_"+id))+1+"");
//			RedisUtil.expire(("pointCollection_"+id), (int) (1.5*24*60*60));
//		}
	}
	
	/**
	 * 取消收藏
	 * @param id 场馆ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	@Override
	public void cancelCollection(String id,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		dao.delete("pointServiceMapper.cancelCollection", map);
		
//
//		if(StringUtils.isEmpty(RedisUtil.get("pointCollection_"+id))){
//			String count = pointCollection(id);
//			RedisUtil.set("pointCollection_"+id, count);
//			RedisUtil.expire(("pointCollection_"+id), (int) (1.5*24*60*60));
//		}else{
//			RedisUtil.set("pointCollection_"+id, Integer.valueOf(RedisUtil.get("pointCollection_"+id))-1+"");
//			RedisUtil.expire(("pointCollection_"+id), (int) (1.5*24*60*60));
//		}
	}
	
	/**
	 * 是否收藏
	 * @param id 场馆ID
	 * @param userId 用户ID
	 * @throws Exception
	 */
	@Override
	public boolean isCollection(String id,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		int count = (int)dao.findForObject("pointServiceMapper.isCollection", map);
		
		if(count>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 场馆评论页面显示信息
	 * @param id 场馆ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public PointEvaluateInfo pointEvaluate(String id) throws Exception{
		return (PointEvaluateInfo) dao.findForObject("pointServiceMapper.pointEvaluate", id);
	}
	
	/**
	 * 场馆图片
	 * @param id
	 * @throws Exception
	 */
	@Override
	public List<PointServiceImgDTO> findPointServiceImg(String id) throws Exception{
		return (List<PointServiceImgDTO>)dao.findForList("pointServiceImgMapper.findPointServiceImg", id);
	}
	
	/**
	 * 查询场馆详细信息
	 * @param id 场馆ID
	 * @param userId 用户ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public PointServiceUpdateDTO findInsertPointServiceDTO(String id,String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		
		return (PointServiceUpdateDTO) dao.findForObject("pointServiceMapper.findInsertPointServiceDTO", map);
	}
	
	
	/**
	 * 验证场馆创建人
	 * @param id 场馆ID
	 * @param userId 用户ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean checkPointCreateUser(String id,String userId) throws Exception{
		// 检验该地点服务id是否为当前用户所属
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("userId", userId);
		
		int pointNum = (int) dao.findForObject("pointServiceMapper.checkPointCreateUser", map);
		if(pointNum>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 查询场馆描述信息
	 * @param id 场馆ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public PointServiceInfoDTO findPointServiceInfoByPsId(String id) throws Exception{
		return (PointServiceInfoDTO) dao.findForObject("pointServiceInfoMapper.findPointServiceInfoByPsId", id);
	}
	
	/**
	 * 商家所有项目展示
	 * @param id 商家ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PointServiceProjectDTO> findPointServiceProjectDTOByPsId(Page<String> page) throws Exception{
		return (List<PointServiceProjectDTO>) dao.findForList("pointServiceProjectsMapper.findPointServiceProjectDTOByPsIdPage", page);
	}
	
	/**
	 * 商家所有项目个人中心展示
	 * @param id 用户ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PointServiceProjectDTO> findPointServiceProjectDTO(Page<Map<String,String>> page) throws Exception{
		return (List<PointServiceProjectDTO>) dao.findForList("pointServiceProjectsMapper.findPointServiceProjectDTOPage", page);
	}
	
	/**
	 * 购买人数
	 * @param id 场馆ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public String findPointPurchaseNumber(String id) throws Exception{
		return (String) dao.findForObject("pointServiceMapper.findPointPurchaseNumber", id);
	}
	
	/**
	 * 是否认证
	 * @param id 场馆ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean isAuth(String id) throws Exception{
		int num = (int) dao.findForObject("pointServiceMapper.isAuth", id);
		if(num>0){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * 场馆信息
	 * @param id 用户ID
	 * @return
	 * @throws Exception
	 */
    @Override
	public List<PointServiceShowDTO> findPointService(String userId) throws Exception{
		
		return (List<PointServiceShowDTO>) dao.findForList("pointServiceMapper.findPointService", userId);
	}
	
	/**
	 * 场馆认证信息
	 * @param id
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public InsertPointAuthDTO findPointServiceAuth(String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", userId);
		
		return (InsertPointAuthDTO) dao.findForObject("pointAuthMapper.findPointServiceAuth", map);
	}
	
	/**
	 * 查询该用户名下所有场馆
	 * @param id 用户ID
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PointServiceShowDTO> allPoint(String userId) throws Exception{
		return (List<PointServiceShowDTO>) dao.findForList("pointServiceMapper.allPoint", userId);
	}
}
