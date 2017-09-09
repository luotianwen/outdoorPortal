package com.op.service.activity.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.op.dao.BaseDao;
import com.op.dto.activity.baoxian.BaoxianDTO;
import com.op.dto.activity.draft.DraftResultsDTO;
import com.op.dto.activity.edit.ActivityDraftDTO;
import com.op.dto.activity.insertActivity.ActivityBaseInfoDTO;
import com.op.dto.activity.insertActivity.ActivityRestsInfoDTO;
import com.op.dto.activity.insertActivity.cost.ActivityCostDTO;
import com.op.dto.activity.insertActivity.cost.CostDTO;
import com.op.dto.activity.insertActivity.line.InsertLineDTO;
import com.op.dto.activity.queryActivity.QueryActivity;
import com.op.dto.activity.reply.ReplyActiveDTO;
import com.op.dto.activity.update.UpdateActiveDTO;
import com.op.dto.usercenter.keeper.MyActiveInfoDTO;
import com.op.dto.usercenter.keeper.MyActiveResultDTO;
import com.op.dto.usercenter.leader.activeManager.LeaderActivesDTO;
import com.op.entity.activity.Activity;
import com.op.entity.lines.ActiveLines;
import com.op.entity.lines.LineImages;
import com.op.entity.lines.SubsectionLines;
import com.op.entity.lines.address.LineAddress;
import com.op.plugin.page.Page;
import com.op.service.activity.ActivityService;
import com.op.task.PhotosUploadOss;
import com.op.util.Const;
import com.op.util.DateUtil;
import com.op.util.FreeMarkerUtil;

/**
 * 项目名：outdoorPortal
 * 类描述：活动Service实现类
 * 创建人：Yan
 * 创建时间： 2015-12-14 上午11:55:36
 * 最后修改时间：2015-12-14上午11:55:36
 */
@Service("activityServiceImpl")
public class ActivityServiceImpl implements ActivityService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	@Resource(name="freeMarkerUtil")
	FreeMarkerUtil freeMarkerUtil;
	
	@Resource(name="photosUploadOssImpl")
	PhotosUploadOss photosUploadOssImpl;
	
    /*
	*列表(全部) 
    */			
	@Override
	public List<Activity> getActivitylist() throws Exception{
		return (List<Activity>)dao.findForList("activityMapper.getActivitylist",null);	
	}
	/*
	 *根据创建用户查询出自己发布的活动id 
	 */
	@Override
	public List<Integer> selectId(String createuser) throws Exception {
		return (List<Integer>) dao.findForList("activityMapper.selectId", createuser);
	}

	/**
	 * 方法描述：根据活动ID查询活动详情
	 * 实现接口：@see com.op.service.activity.ActivityService#detailById(java.lang.String, java.lang.String, java.lang.String, java.lang.String, javax.servlet.ServletContext)
	 * @param id	活动id
	 * @param ftl	模板文件
	 * @param targetFile	生成的目标文件
	 * @param contextPath	servletContext上下文
	 * @param servletContext	servletContext获取根目录
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean detailById(String id,String ftl, String targetFile ,String contextPath) throws Exception {
		
		Activity activity = (Activity)dao.findForObject("activityMapper.detailById", id);;
		
		if(activity == null){
			return false;
		}

		
		// 模板数据
		Map<String,Object> map = new HashMap<String,Object>();
		// 头部数据
		Map<String,Object> headerMap = new HashMap<String,Object>();
		// 当前导航标签
		headerMap.put("ht", "活动");
		
		// 活动
		map.put("activity", activity);
		// 头部数据
		map.put("map", headerMap);
		// 生成文件
		freeMarkerUtil.createFile(ftl, map, targetFile,contextPath );
		
		return true;
	}
	
	/**
	 * 方法描述：根据活动ID查询活动详情(订单使用)
	 * 返回类型：Activity
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public QueryActivity orderById(String id) throws Exception{
		QueryActivity queryActivity = new QueryActivity();
		queryActivity = (QueryActivity) dao.findForObject("activityMapper.findOrderActivity", id);
		
		return queryActivity;
	}
	
	/**
	 * 修改活动人数
	 */
	@Override
	public void updateAlreadyNum(Map<String,Object> map) throws Exception {
		dao.update("activityMapper.updateAlreadyNum", map);
	}
	
	/**
	 * 方法描述： 查询领队所发布的活动列表
	 * 实现接口：@see com.op.service.activity.ActivityService#findByUserId(com.op.dto.usercenter.leader.activeManager.LeaderSearchActives)
	 * @param sa 查询对象
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<LeaderActivesDTO> findByUserId(Page<LeaderActivesDTO> page) throws Exception {
		// TODO Auto-generated method stub
		return (List<LeaderActivesDTO>) dao.findForList("activityMapper.findByUserIdPage", page);
	}
	
	/**
	 * 查询是否已经报名过该活动
	 */
	@Override
	public int findCountByUserAndActiveId(String activeId, String userId)
			throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		map.put("activeId", activeId);
		map.put("userId", userId);
		return (int) dao.findForObject("activeSignupMapper.findCountByUserAndActiveId", map);
	}
	
	/*
	 * 方法描述：是否已经报名
	 * 实现接口：@see com.op.service.activity.ActivityService#isAlreadySignUp(java.lang.String, java.lang.String)
	 * @param activeId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean isAlreadySignUp(String activeId, String userId) throws Exception{
		Map<String,String> map = new HashMap<String,String>(); 
		map.put("activeId", activeId);
		map.put("userId", userId);
		return ((List<String>)dao.findForList("activeSignupMapper.isAlreadySignUp", map)).size()>0?true:false;
	}
	
	/**
	 * 方法描述：添加活动基础信息
	 * 步骤：
	 * 	1：数据验证
	 * 	2：保存临时目录的主图至永久目录
	 * 	3：保存活动信息
	 * 	4：保存多表活动类型信息
	 * 	5：保存多表活动标签
	 * 	6：保存多表活动景点
	 * 	7：保存多表活动交通方式
	 * 	8：返回活动ID，用于保存该活动其他数据
	 * 
	 * 涉及表：activity（活动表）、activeTypes（活动类型表信息）、activeTag（活动标签表）、activeScenic（活动景点表）、activeTraffic（活动对应的交通方式）
	 * 实现接口：@see com.op.service.activity.ActivityService#insertBaseInfo(com.op.dto.activity.insertActivity.InsertActivityDTO, java.util.Map)
	 * @param dto 活动基础信息
	 * @param map 返回数据对象
	 * @throws Exception
	 */
	@Override
	public void insertBaseInfo(ActivityBaseInfoDTO dto, Map<String, Object> map ,HttpSession session) throws Exception {
		
		// 用于判断或保存标签及景点
		List<String> list = new ArrayList<String>();
		
		// 数据验证
		if(!dataCheck(dto,map,list)){
			return;
		}
		
		// 验证价格是否小于【费用包括总价】
		if(!StringUtils.isEmpty(dto.getActivityId()) && !checkPrice(dto, map)){
			return;
		}
		
		// 保存存储的图片路径，如果新增失败后要删除垃圾数据
		map.put("newPathLocationUrl", new ArrayList<String>());
		
		// 将临时目录的活动主图永久保存
		dto.setA_active_img(photosUploadOssImpl.uploadPhotos(dto.getA_active_img()));
		//dto.setA_active_img(copyImg(dto.getA_active_img(),session,map,"active"));
		
		boolean isUpdate = StringUtils.isEmpty(dto.getActivityId())?false:true;
		
		//判断是否修改开始结束时间，更改保险信息
		String type = "";
		if(isUpdate){
			Map<String,Date> activityDate = (Map<String, Date>) dao.findForObject("activityMapper.findActivityTime", dto.getActivityId());
			String startDate = DateUtil.YYYY_MM_DDgetDay(activityDate.get("activityTime"));
			String endDate = DateUtil.YYYY_MM_DDgetDay(activityDate.get("endTime"));
			
			String activityTime = DateUtil.YYYY_MM_DDgetDay(dto.getActivityTime());
			String endTime = DateUtil.YYYY_MM_DDgetDay(dto.getEndTime());
			
			if(!startDate.equals(activityTime)||!endDate.equals(endTime)){
				BaoxianDTO bxdto = new BaoxianDTO();
				bxdto.setActivityId(dto.getActivityId());
				// 删除历史保险信息
				dao.delete("activeCorrelationCostMapper.deleteInsuranceBaoxian", bxdto);
				
				Map<String,String> delI = new HashMap<String,String>();
				delI.put("ai_activity_id", dto.getActivityId());
				
				dao.update("activityMapper.delInsureId", delI);
				
				type = "deletebx";
			}
		}
		
		// 保存活动信息
		saveActivity(dto,isUpdate);
		
		// 成功修改之后要更改其他数据
		if(dto.getDataNum() > 0){
			
			// 保存活动类型
			saveActiveType(dto,map,isUpdate);
			
			// 保存活动标签
			// 注意：不可改变以下代码的位置,该list存储了上一个方法封装进去的标签数据
			saveActiveTags(map,list,isUpdate);
			
			// 保存活动景点
			saveActiveScenic(dto,map,list,isUpdate);

			// 保存活动交通方式
			saveActiveTraffic(dto,map,list,isUpdate);
		}
		
		// 保存活动ID，用于保存该活动的其他信息
		map.clear();
		map.put(Const.DATA_NUM, dto.getDataNum());
		map.put(Const.ACTIVITY_ID, dto.getActivityId());
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		map.put("type", type);
	}
	
	/**
	 * 方法描述：验证价格是否小于【费用包括总价】
	 * 返回类型：boolean
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	boolean checkPrice(ActivityBaseInfoDTO dto, Map<String, Object> map) throws Exception{
		Double sum = (Double) dao.findForObject("activeCorrelationCostMapper.findActivitySumCost", dto.getActivityId());
		if(sum != null && sum > dto.getPrice()){
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "活动价格不能小于【费用包括总价+保险金额】");
			return false;
		}
		return true;
	}
	
	/**
	 * 方法描述：活动其他信息保存（描述、介绍、特色、费用、退款说明、活动须知...）
	 * 步骤：
	 * 	1：数据验证
	 * 	2：活动ID为null，保存活动数据
	 * 	3：活动ID存在，直接更改即可，因为刚方法修改的全是活动表的数据
	 * 	4：活动ID存在，判断当前修改的对象是否已经存在，用于是否更新[发布完成度]
	 * 
	 * 涉及的表：activity（活动表）
	 * 实现接口：@see com.op.service.activity.ActivityService#insertRestsInfo(com.op.dto.activity.insertActivity.ActivityRestsInfoDTO)
	 * @param dto
	 * @throws Exception
	 */
	@Override
	public void insertRestsInfo(ActivityRestsInfoDTO dto,Map<String,Object> map) throws Exception {
		
		// 修改操作
		if("d".equals(dto.getCrud())){
			updateRestsInfo(dto);
			dto.setDataNum(0);
		}
		// 新增或者修改操作
		else{
			if(!createRestsInfo(dto,map)){
				return;
			}
			dto.setDataNum(1);
		}

		// 保存活动ID，用于保存该活动的其他信息
		map.clear();
		map.put(Const.DATA_NUM, dto.getDataNum());
		map.put(Const.ACTIVITY_ID, dto.getActivityId());
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		
	}
	
	/**
	 * 修改活动附属信息
	 * @param dto
	 * @param map
	 * @return
	 * @throws Exception
	 */
	void updateRestsInfo(ActivityRestsInfoDTO dto) throws Exception {
		if(StringUtils.isEmpty(dto.getActivityId())){
			dto.setActivityId("");
			return;
		}
		
		dao.update("activityMapper.updateColumn", dto);
		
	}
	
		
	/**
	 * 创建活动附属信息
	 * @param dto
	 * @param map
	 * @return
	 * @throws Exception
	 */
	boolean createRestsInfo(ActivityRestsInfoDTO dto,Map<String,Object> map) throws Exception {
		// 数据验证
		if(dto.isNull()){
			map.put(Const.RESPONSE_STATE, 500);
			map.put(Const.ERROR_INFO, "数据不可为空!");
			return false;
		}
		
		// 检测活动价格
		if(!checkActivityPrice(dto, map)){
			return false;
		}
		
		// 如果活动ID为null要保存数据的同时生成活动ID
		if(StringUtils.isEmpty(dto.getActivityId())){
			dao.save("activityMapper.saveActiveRestsInfo", dto);
		}else{
			dao.update("activityMapper.updateActiveRestsInfo", dto);
		}
		return true;
	}
	
	/**
	 * 方法描述：检测活动价格
	 * 返回类型：boolean
	 * @param dto
	 * @param map
	 * @return
	 */
	boolean checkActivityPrice(ActivityRestsInfoDTO dto,Map<String,Object> map)throws Exception{
		if(dto.getA_reserve_price()!=null){
			// 涉及到了预订金额
			if(StringUtils.isEmpty(dto.getActivityId()) || StringUtils.isEmpty(dto.getActivityPrice())){
				map.put(Const.RESPONSE_STATE, 500);
				map.put(Const.ERROR_INFO, "请先填写并保存活动基本信息!");
				map.put(Const.FN, 1);
				return false;
			}
			
			dto.setActivityPrice((Double) dao.findForObject("activityMapper.selectPriceById",dto.getActivityId()));
			
			// 对于涉及到预定金额大小的判断
			// 俱乐部比例设置为50%
			if(dto.getuType() == 2 && (dto.getA_reserve_price() > (dto.getActivityPrice()*0.5))){
				map.put(Const.RESPONSE_STATE, 500);
				map.put(Const.ERROR_INFO, "预订金额不得大于"+(dto.getActivityPrice()*0.5)+"元(活动金额的50%)!");
				return false;
			}
			// 领队比例设置为30%
			else if(dto.getuType() == 1 && (dto.getA_reserve_price() > (dto.getActivityPrice()*0.3))){
				map.put(Const.RESPONSE_STATE, 500);
				map.put(Const.ERROR_INFO, "预订金额不得大于"+(dto.getActivityPrice()*0.3)+"元(活动金额的30%)!");
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 方法描述：添加活动线路
	 * 步骤：
	 * 	1：线路数据验证
	 *  2：活动不存在，生成活动主键并存储关联线路数据
	 *  3：活动存在，线路不存在，只需要存储关联线路数据
	 *  4：线路存在，修改线路信息
	 *  5：更改线路图片信息
	 *  6：返回线路及活动数据
	 * 涉及表：lines（线路表）、lineAddress（线路地点集合表）、lineImages（线路图片表）
	 * 实现接口：@see com.op.service.activity.ActivityService#tianjiaxianlu(com.op.dto.activity.insertActivity.line.InsertLineDTO, java.util.Map)
	 * @param dto
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void insertLine(InsertLineDTO dto, Map<String, Object> map,HttpSession session) throws Exception {
		// 线路数据验证
		if(!checkLine(dto,map)){
			return;
		}
		
		// 标识是否要查询历史图片数据
		boolean isfindHistoryImg = true
				// 判断是否可能存在历史线路信息，要重新进行排序赋值
				,isNewLine=false; 
		
		// 活动不存在
		if(StringUtils.isEmpty(dto.getActivityId())){
			// 保存线路数据活动不存在业务逻辑
			saveLineActiveNoAlready(dto);
			
			// 新增情况下不涉及修改历史图片操作
			isfindHistoryImg=false;
		}
		// 线路不存在
		if(StringUtils.isEmpty(dto.getL_id())){
			
			// 可能存在线路信息
			isNewLine = true;
			
			// 活动已存在，保存线路数据
			saveLineActiveIsAlready(dto);
			
			// 新增情况下不涉及修改历史图片操作
			isfindHistoryImg=false;
		}else{
			// 可能存在线路信息
			isNewLine = true;
			
			dao.update("linesMapper.updatelineInfo", dto);

			// 操作的数据量
			dto.setDataNum(1);
			
			// 更改该线路地点集合
			updateLineAddress(dto);
		}
		
		// 更改线路图片信息
		updateLineImage(dto,session,isfindHistoryImg,map);
		
		map.clear();
		map.put(Const.DATA_NUM, dto.getDataNum());
		map.put(Const.HANDLE_TYPE, dto.getHandletype());
		map.put(Const.ACTIVITY_ID, dto.getActivityId());
		// 要将该线路ID生成到页面，为了下一次判断该线路是否存在
		map.put(Const.LINE_ID, dto.getL_id());
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
	}
	
	/**
	 * 方法描述：活动线路信息按照日期进行从小到大排序
	 * 返回类型：void
	 * @param list
	 */
	void order(List<SubsectionLines> list){

		// 按照日期进行排序(从小到大排序)
		for (int i=0,len=list.size();i<len;i++) {
			for(int j=i+1;j<len;j++){
				SubsectionLines item1 = list.get(i);
				SubsectionLines item2 = list.get(j);
				// 比较两条线路的日期，从小到大排序
				if(item2.getL_time().getTime()<item1.getL_time().getTime()){
					SubsectionLines temp = item1;
					list.set(i, item2);
					list.set(j, temp);
				}
				
			}
		}
	}
	
	
	/**
	 * 方法描述：添加活动额外费用
	 * 实现接口：@see com.op.service.activity.ActivityService#insertCost(com.op.dto.activity.insertActivity.cost.ActivityCostDTO, java.util.Map)
	 * @param dto
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void insertCost(ActivityCostDTO dto, Map<String, Object> map) throws Exception {
		
		if("d".equals(dto.getCrud())){
			deleteCost(dto,map);
		}else{
			if(!createCost(dto,map)){
				return;
			}
		}
		
		map.clear();
		map.put(Const.DATA_NUM, dto.getDataNum());
		map.put(Const.ACTIVITY_ID, dto.getActivityId());
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
	}
	
	
	/**
	 * 方法描述：删除活动费用
	 * 返回类型：void
	 * @param dto
	 * @param map
	 * @throws Exception
	 */
	void deleteCost(ActivityCostDTO dto, Map<String, Object> map) throws Exception {
		if(StringUtils.isEmpty(dto.getActivityId())){
			dto.setDataNum(0);
			dto.setActivityId("");
			return;
		}else{
			dao.delete("activeCorrelationCostMapper.deleteByActiveIdAndUserId", dto);
			
			if(dto.getCosts() != null){

				List<CostDTO> list = new ArrayList<CostDTO>();
				
				for(CostDTO cost:dto.getCosts()){
					// checkbox判断
					if("true".equals(cost.getIsSave())){
						if(StringUtils.isEmpty(cost.getAcp_cost_name())){
							continue;
						}
						
						if(cost.getAcp_cost()==null || cost.getAcp_cost()<0){
							continue;
						}
						
						if(StringUtils.isEmpty(cost.getAcp_comment())){
							continue;
						}
						
						list.add(cost);
					}
				}
				
				if(list.size()>0){
					dto.setCosts(list);
					dao.save("activeCorrelationCostMapper.insertDataAlreadyActivityId", dto);
					dto.setDataNum(list.size());
				}else{
					dto.setDataNum(0);
				}
			}else{
				dto.setDataNum(0);
			}
			
		}
	}
	
	/**
	 * 方法描述：添加活动费用
	 * 返回类型：void
	 * @param dto
	 * @param map
	 * @throws Exception
	 */
	boolean createCost(ActivityCostDTO dto, Map<String, Object> map) throws Exception {
		// 数据验证
		if(!costCheck(dto,map)){
			return false;
		}
		
		// 活动不存在，保存费用同时根据活动序列生成主键
		if(StringUtils.isEmpty(dto.getActivityId())){
			Activity activity = new Activity();
			activity.setCreateUser(dto.getAcp_create_user());
			dao.save("activityMapper.saveNullActivity", activity);
			dto.setActivityId(activity.getId()+"");
			
			// 保存额外费用，生成活动主键
			dao.save("activeCorrelationCostMapper.insertDataNoActivityId", dto);
			
			// 保存草稿活动数据
			//dao.save("activityMapper.insertCaoGaoStateActiveByCost", dto);
		}else{
			// 查询活动价格（如果存在），费用包括总价不能大于活动价格
			Double price = (Double) dao.findForObject("activityMapper.findActivityPrice", dto.getActivityId());
			if(price != null){
				double sum = 0l;
				
				// 限制可选费用
				if(dto.getAcp_type() == 0){
					for(Iterator<CostDTO> iterator = dto.getCosts().iterator(); iterator.hasNext();){
						CostDTO cost = iterator.next();
						sum+=cost.getAcp_cost();
					}
				}
				if(sum>price){
					map.put(Const.RESPONSE_STATE, 500);
					map.put(Const.ERROR_INFO, "费用包括总价不能大于活动价格!");
					return false;
				}
			}
			
			dao.delete("activeCorrelationCostMapper.deleteByActiveIdAndUserId", dto);
			dao.save("activeCorrelationCostMapper.insertDataAlreadyActivityId", dto);
		}
		
		dto.setDataNum(dto.getCosts().size());
		return true;
	}
	
	/**
	 * 方法描述：费用数据验证
	 * 返回类型：boolean
	 * @param dto
	 * @param map
	 * @return
	 * @throws Exception
	 */
	boolean costCheck(ActivityCostDTO dto, Map<String, Object> map) throws Exception {
		map.put(Const.RESPONSE_STATE, 500);
		List<CostDTO> list = new ArrayList<CostDTO>();
		
		// 全部删除之后的保存情况
		if(dto.getCosts() == null){
			map.put(Const.ERROR_INFO, "数据不能为空!");
			return false;
		}
		
		for(CostDTO cost:dto.getCosts()){
			// checkbox判断
			if("true".equals(cost.getIsSave())){
				if(StringUtils.isEmpty(cost.getAcp_cost_name())){
					map.put(Const.ERROR_INFO, "费用名称不能为空!");
					return false;
				}
				
				if(cost.getAcp_cost()==null || cost.getAcp_cost()<0){
					map.put(Const.ERROR_INFO, "费用金额大于等于0!");
					return false;
				}
				
				if(StringUtils.isEmpty(cost.getAcp_comment())){
					map.put(Const.ERROR_INFO, "费用说明不能为空!");
					return false;
				}
				
				list.add(cost);
			}
		}
		
		// 重组费用数据
		 if(list.size()==0){
			map.put(Const.ERROR_INFO, "请选择一条费用进行保存，谢谢!");
			 return false;
		 }
		 
		 dto.setCosts(list);
		 
		return true;
	}
	
	/**
	 * 方法描述：更改线路图片信息
	 * 步骤：
	 * 	1：判断是否存在历史图片数据，查询数据库删除历史图片信息
	 *  2：将临时目录的图片保存至永久目录
	 *  3：存储数据库图片数据
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	void updateLineImage(InsertLineDTO dto,HttpSession session,boolean isfindHistoryImg, Map<String, Object> map) throws Exception{
		
		// 存在历史图片数据
		if(isfindHistoryImg){
			
			dao.delete("lineImagesMapper.deleteLineImages", dto.getL_id());
			// 查询数据库历史图片信息
			/*List<LineImages> list = (List<LineImages>) dao.findForList("lineImagesMapper.findImagesListByActiveLineId",dto.getL_id());
			if(list.size()>0){
				String context = session.getServletContext().getRealPath("/");
				
				// 根据路径删除本地历史图片信息
				for(LineImages img:list){
					new File(context+img.getLi_local_url()).delete();
				}
				
				// 删除数据库历史图片信息
				dao.delete("lineImagesMapper.deleteLineImages", dto.getL_id());
			}*/
		}
		
		// 创建保存新图片路径list，用于新增失败后删除
		List<String> list = new ArrayList<String>();
		map.put("newPathLocationUrl", list);
		
		// 将临时目录的图片保存至永久目录
		for(int i=0;i<dto.getImages().size();){
			LineImages img = dto.getImages().get(i);
			if(img.getLi_local_url() == null){
				dto.getImages().remove(i);
				continue;
			}
			
			img.setLi_local_url(photosUploadOssImpl.uploadPhotos(img.getLi_local_url()));
			//img.setLi_local_url(copyImg(img.getLi_local_url(),session,map,"line"));
			
			dto.getImages().set(i, img);
			i++;
		}
		
		// 存储数据库图片数据
		dao.save("lineImagesMapper.insertintoLineImage", dto);
	}
	
	/**
	 * 方法描述：保存线路数据活动不存在业务逻辑
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	void saveLineActiveNoAlready(InsertLineDTO dto) throws Exception{
		Activity activity = new Activity();
		activity.setCreateUser(dto.getL_create_user());
		dao.save("activityMapper.saveNullActivity", activity);
		dto.setActivityId(activity.getId()+"");
		
		// 保存线路信息
		dao.save("linesMapper.tianjiaxianluGenerateActiveId", dto);
		
		// 添加草稿状态的活动
		//dao.save("activityMapper.insertCaoGaoStateActiveByLine", dto);
		
		// 操作的数据量
		dto.setDataNum(1);
		
		// 保存该线路地点集合
		saveLineAddress(dto);
	}
	
	/**
	 * 方法描述：保存线路数据活动不存在业务逻辑
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	void saveLineActiveIsAlready(InsertLineDTO dto) throws Exception{
		// 保存线路信息
		dao.save("linesMapper.tianjiaxianlu", dto);
		
		// 操作的数据量
		dto.setDataNum(1);
		
		// 保存该线路地点集合
		saveLineAddress(dto);
	}
	
	/**
	 * 方法描述：线路数据验证
	 * 返回类型：boolean
	 * @param dto
	 * @param map
	 * @return
	 * @throws Exception
	 */
	boolean checkLine(InsertLineDTO dto, Map<String, Object> map) throws Exception {
		// 默认返回状态为500，只需要操作返回错误信息即可
		map.put(Const.RESPONSE_STATE, 500);
		
		
		for(LineAddress didian:dto.getDidian()){
			if(StringUtils.isEmpty(didian.getLa_address_name())){
				map.put(Const.ERROR_INFO, "地点不能为空！");
				return false;
			}
		}
		
		if(StringUtils.isEmpty(dto.getL_vehicle())){
			map.put(Const.ERROR_INFO, "出行方式不能为空！");
			return false;
		}
		
		if("day".equals(dto.getJourney_type())){
			if(dto.getL_time_day() == null){
				map.put(Const.ERROR_INFO, "日期不能为空！");
				return false;
			}
		}else if("hour".equals(dto.getJourney_type())){
			if(dto.getL_to_time() == null || dto.getL_time_hour() == null){
				map.put(Const.ERROR_INFO, "请选择日期区间!");
				return false;
			}
		}
		
		if(StringUtils.isEmpty(dto.getL_diet())){
			map.put(Const.ERROR_INFO, "餐饮不能为空！");
			return false;
		}
		if(StringUtils.isEmpty(dto.getL_accommodation())){
			map.put(Const.ERROR_INFO, "住宿不能为空！");
			return false;
		}
		if(StringUtils.isEmpty(dto.getL_active_description())){
			map.put(Const.ERROR_INFO, "活动介绍不能为空！");
			return false;
		}
		/*if(StringUtils.isEmpty(dto.getL_prompt())){
			map.put(Const.ERROR_INFO, "温馨提示不能为空！");
			return false;
		}*/
		
		// 地图标注验证 
		if(StringUtils.isEmpty(dto.getL_line_coordinate())){
			map.put(Const.ERROR_INFO, "请增加该行程的路线，更好的向用户展示线路信息");
			return false;
		}
		
		// 线路图片验证
		if(dto.getImages() == null 
				|| (dto.getImages().size() == 1 && StringUtils.isEmpty(dto.getImages().get(0).getLi_local_url()))){
			map.put(Const.ERROR_INFO, "最少上传一张线路的主图!");
			return false;
		}
		
		return true;
	}
	
	/**
	 * 方法描述：保存该线路地点集合
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	void saveLineAddress(InsertLineDTO dto) throws Exception{
		// 给地点的线路ID赋值
		for(LineAddress didian:dto.getDidian()){
			didian.setLa_line_id(dto.getL_id());
		}
		
		dao.save("lineAddressMapper.saveLineAddress", dto.getDidian());
	}

	/**
	 * 方法描述：更改该线路地点集合
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	void updateLineAddress(InsertLineDTO dto) throws Exception{
		dao.delete("lineAddressMapper.deleteInfoByLineID", dto.getL_id());
		saveLineAddress(dto);
	}
	/**
	 * 方法描述：保存活动信息
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	void saveActivity(ActivityBaseInfoDTO dto,boolean isUpdate)throws Exception{
		// 判断收费还是免费
		dto.setIsCharge(dto.getPrice()>0?1:2);
		
		int updateNum=0;
		// 判断是接续保存还是新增
		if(isUpdate){
			// 修改已经存在的活动信息
			updateNum = (int) dao.update("activityMapper.updateActiveBaseInfo", dto);
		}else{
			// 保存活动信息
			updateNum = (int) dao.save("activityMapper.saveActiveBaseInfo", dto);
		}
		dto.setDataNum(updateNum);
	}
	
	/**
	 * 方法描述：数据验证
	 * 返回类型：boolean
	 * @param dto
	 * @return
	 */
	boolean dataCheck(ActivityBaseInfoDTO dto, Map<String, Object> map,List<String> list) throws Exception{// 空串也可解析为长度为1的数组，要加判断

		// 默认返回状态为500，只需要操作返回错误信息即可
		map.put(Const.RESPONSE_STATE, 500);

		// 活动主图
		if(StringUtils.isEmpty(dto.getA_active_img())){
			map.put(Const.ERROR_INFO, "请上传活动主图!");
			return false;
		}
		// title
		if(StringUtils.isEmpty(dto.getTitle())){
			map.put(Const.ERROR_INFO, "请填写本次活动标题!");
			return false;
		}
		// 开始时间
		if(dto.getActivityTime() == null){
			map.put(Const.ERROR_INFO, "请选择开始时间!");
			return false;
		}
		// 结束时间
		if(dto.getEndTime() == null){
			map.put(Const.ERROR_INFO, "请选择结束时间!");
			return false;
		}
		// 报名截止时间
		if(dto.getA_enroll_end_time() == null){
			map.put(Const.ERROR_INFO, "请选择报名截止时间!");
			return false;
		}
		// 咨询电话
		if(dto.getA_phone() == null){
			map.put(Const.ERROR_INFO, "请填写联系电话!");
			return false;
		}
		// 出发地
		if(StringUtils.isEmpty(dto.getA_start_location())){
			map.put(Const.ERROR_INFO, "请填写出发地!");
			return false;
		}
		// 目的地
		if(StringUtils.isEmpty(dto.getA_end_location())){
			map.put(Const.ERROR_INFO, "请填写目的地!");
			return false;
		}

		// 活动需要人数
		if(dto.getNeedUserNum() == null || dto.getNeedUserNum() <= 0){
			map.put(Const.ERROR_INFO, "请填写活动人数!");
			return false;
		}
		// 集合时间
		if(dto.getA_gather_time() == null){
			map.put(Const.ERROR_INFO, "请填写集合时间!");
			return false;
		}
		// 活动费用
		if(dto.getPrice() == null){
			map.put(Const.ERROR_INFO, "请填写活动价格!");
			return false;
		}
		

		
		// 活动标签剔除空串
		for(String sc : dto.getActiveScenic()){
			if(!"".equals(sc)){
				list.add(sc);
			}
		}
		
		if(list.size() == 0){
			map.put(Const.ERROR_INFO, "最少输入一个活动景点!");
			return false;
		}

		list.clear();
		
		// 活动景点剔除空串
		// 注意：不可改变一下代码的位置，因为此次循环封装数据如果正常运行了下去，在下一个方法就直接使用该list的数据了
		for(String tag : dto.getActiveTag()){
			if(!"".equals(tag)){
				list.add(tag);
			}
		}
		
		if(list.size() == 0){
			map.put(Const.ERROR_INFO, "最少输入一个活动标签!");
			return false;
		}
		
		// 集合地点
		if(StringUtils.isEmpty(dto.getA_gather_location())){
			map.put(Const.ERROR_INFO, "请填写集合地点!");
			return false;
		}

		// 活动类型
		if(StringUtils.isEmpty(dto.getTypes())){
			map.put(Const.ERROR_INFO, "最少选择一种活动类型!");
			return false;
		}
		// 交通方式
		if(StringUtils.isEmpty(dto.getA_traffic_id())){
			map.put(Const.ERROR_INFO, "最少选择一种交通方式!");
			return false;
		}
		
		return true;
	}
	
	/**
	 * 方法描述：保存活动类型
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	
	void saveActiveType(ActivityBaseInfoDTO dto, Map<String, Object> map,boolean isUpdate) throws Exception{
		
		map.put("at_type_ids", dto.getTypes().split(","));
		map.put("at_active_id", dto.getActivityId());
		
		// 可能包含历史数据
		if(isUpdate){
			// 可能包含历史数据，先清除在新增
			dao.delete("activeTypesMapper.deleteByActivityId", dto.getActivityId());
		}
		
		dao.save("activeTypesMapper.insertActiveTypes", map);
	}

	
	/**
	 * 方法描述：保存活动标签
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	void saveActiveTags(Map<String, Object> map,List<String> list,boolean isUpdate) throws Exception{
		map.put("list", list);
		// 可能包含历史数据
		if(isUpdate){
			dao.delete("activeTagMapper.deleteByActivityId", map.get("at_active_id"));
		}
		dao.save("activeTagMapper.insertActiveTag", map);
	}

	
	/**
	 * 方法描述：保存活动景点
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	void saveActiveScenic(ActivityBaseInfoDTO dto, Map<String, Object> map,List<String> list,boolean isUpdate) throws Exception{
		list.clear();

		// 活动标签剔除空串
		for(String sc : dto.getActiveScenic()){
			if(!"".equals(sc)){
				list.add(sc);
			}
		}

		// 可能包含历史数据
		if(isUpdate){
			dao.delete("activeScenicMapper.deleteByActivityId", dto.getActivityId());
		}
		
		dao.save("activeScenicMapper.insertActiveScenic", map);
		
	}


	
	/**
	 * 方法描述：保存活动交通方式
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	void saveActiveTraffic(ActivityBaseInfoDTO dto, Map<String, Object> map,List<String> list,boolean isUpdate) throws Exception{
		
		map.put("at_traffic_id", dto.getA_traffic_id().split(","));

		// 可能包含历史数据
		if(isUpdate){
			dao.delete("activeTrafficMapper.deleteByActivityId", dto.getActivityId());
		}
		
		dao.save("activeTrafficMapper.insertActiveTraffic", map);
		
	}
	
	
	/**
	 * 方法描述：将临时目录的图片拷贝至服务器目录永久保存
	 * 返回类型：void
	 * @param temp 文件的临时目录
	 * @param session 当前session
	 * @param type 保存类型{active/line}
	 * @throws Exception
	 */
	String copyImg(String temp,HttpSession session, Map<String, Object> map,String type) throws Exception{
		// Servlet上下文环境
		ServletContext application = session.getServletContext();
		// 根目录
		String contextPath = application.getRealPath("/");
		// 当天目录文件名
		String day = DateUtil.getDays();
		// 新储存路径
		String newPath = contextPath+"upload/images/"+type+"/"+day;
		
		// 判断目录是否存在,不存在则创建
		File uploadFilePath = new File(newPath);
		if(!uploadFilePath.exists()){
			uploadFilePath.mkdirs();
		}
		
		// 复制文件
		return copyImg(contextPath, temp, newPath ,map);
		
	}

	
	/**
	 * 方法描述：复制临时图片保存到永久目录
	 * 返回类型：String
	 * @param contextPath	根目录
	 * @param temp	源文件存储路径
	 * @param newPath	新文件存储路径
	 * @return	复制文件的路径
	 */
	String copyImg(String contextPath,String temp,String newPath,Map<String,Object> map) throws Exception{
		// 解析图片名称
		int lastIndex = temp.lastIndexOf("/");
		String fileName = temp.substring(lastIndex);
		// 设置新文件的路径
		newPath += fileName;
		FileInputStream input=new FileInputStream(contextPath+temp);
		FileOutputStream output=new FileOutputStream(newPath);
		int in=input.read();// 读取
		while(in!=-1){
			output.write(in);// 写入
			in=input.read();
		}
		output.flush(); // 关闭流
		output.close(); 
		input.close(); 

		// 保存已经复制的新图片路径，用于添加失败删除垃圾数据
		List<String> list = (List<String>) map.get("newPathLocationUrl");
		list.add(newPath);
		
		// 截取upload...之后的路径保存到数据库
		return newPath.substring(newPath.indexOf("upload/"));
	}
	
	/**
	 * 方法描述：根据用户ID查询活动草稿数量
	 * 实现接口：@see com.op.service.activity.ActivityService#draft(java.lang.String)
	 * @param uId
	 * @return
	 * @throws Exception
	 */
	@Override
	public int draft(String uId) throws Exception {
		return (int) dao.findForObject("activityMapper.draft",uId);
	}
	
	/**
	 * 方法描述：提交审核
	 * 实现接口：@see com.op.service.activity.ActivityService#submitAudit(java.util.Map)
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void submitAudit(Map<String, String> map) throws Exception {
		// 封装总体线路
		ActiveLines al = activeLines(map);
		
		// 封装活动的出发地和目的地坐标
		map.put("coordinates", al.getL_start_location());
		
		dao.update("activityMapper.submitAudit", map);
		map.clear();
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
	}
	
	/**
	 * 方法描述：总体线路
	 * 返回类型：void
	 * @param dto
	 * @throws Exception
	 */
	ActiveLines activeLines(Map<String, String> map) throws Exception{
		// 查询历史信息；根据线路的【日期】进行排序重新生成线路信息
		List<SubsectionLines> list = (List<SubsectionLines>) dao.findForList("linesMapper.selectByActiveId",map);
		
		// 从小到大排序
		order(list);
		
		// 解析list线路封装总体线路对象
		ActiveLines al = setActiveLines(list,map);
		
		// 保存总体线路信息
		dao.save("activeLinesMapper.insertActiveLines", al);
		
		return al;
	}
	
	/**
	 * 方法描述：封装总体线路信息
	 * 返回类型：ActiveLines
	 * @param list
	 * @return
	 */
	ActiveLines setActiveLines(List<SubsectionLines> list,Map<String, String> map){
		ActiveLines al = new ActiveLines();
		// 创建用户
		al.setAl_create_user(map.get("userId"));
		// 活动ID
		al.setAl_active_id(map.get("id"));
		
		StringBuffer sb = new StringBuffer();
		for (SubsectionLines lines : list) {
			sb.append(lines.getL_line_coordinate());
		}
		// 总体线路坐标
		al.setAl_coordinates(sb.toString());
		
		return al;
	}
	
	/**
	 * 方法描述：个人中心→我的管家→我的活动
	 * 实现接口：@see com.op.service.activity.ActivityService#myActive(com.op.entity.users.Users)
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public MyActiveResultDTO myActive(int uType ,int type ,Page<String> page) throws Exception {
		
		MyActiveResultDTO dto = null;
		List<MyActiveInfoDTO> list = null;
		
		// 根据身份类型判断
		if(uType == 1 || uType == 2){// 领队，企业

			// 查询不同类型的活动数量
			dto = (MyActiveResultDTO) dao.findForObject("activityMapper.findMyActiveNumForLeader", page.getT());
			
			if(dto != null){
				switch (type) {
				case 1:// 用户参加过的活动
					
					list = (List<MyActiveInfoDTO>) dao.findForList("activityMapper.leaderDoneActivePage", page);
					break;
				case 2:// 进行中的活动
					
					list = (List<MyActiveInfoDTO>) dao.findForList("activityMapper.leaderIngActivePage", page);
					break;
				case 3:// 等待进行的活动
					
					list = (List<MyActiveInfoDTO>) dao.findForList("activityMapper.leaderWaitActivePage", page);
					break;
				}
			}
		}else{// 普通用户
			
			// 查询不同类型的活动数量
			dto = (MyActiveResultDTO) dao.findForObject("activityMapper.findMyActiveNumForUser", page.getT());
			
			if(dto != null){
				switch (type) {
				case 1:// 用户参加过的活动
					
					list = (List<MyActiveInfoDTO>) dao.findForList("activityMapper.userDoneActivePage", page);
					break;
				case 2:// 进行中的活动
					
					list = (List<MyActiveInfoDTO>) dao.findForList("activityMapper.userIngActivePage", page);
					break;
				case 3:// 等待进行的活动
					
					list = (List<MyActiveInfoDTO>) dao.findForList("activityMapper.userWaitActivePage", page);
					break;
				}
			}
		}
		if(dto != null){
			dto.setInfos(list);
		}
		return dto;
	}
	
	/**
	 * 方法描述：个人中心	→	我的问答
	 * 实现接口：@see com.op.service.activity.ActivityService#myConsultation(java.lang.String)
	 * @param uId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ReplyActiveDTO> myConsultation(String searchType,Page<Map<String,String>> page) throws Exception {
		List<ReplyActiveDTO> list = null;
		
		// 我来解答
		if("1".equals(searchType)){
			list = (List<ReplyActiveDTO>) dao.findForList("activityMapper.selectNoReplyDataPage", page);
		}
		// 我的回答
		else if("2".equals(searchType)){
			list = (List<ReplyActiveDTO>) dao.findForList("activityMapper.selectYesReplyDataPage", page);
		}
		// 用户
		else if("3".equals(searchType)){
			
			// 我的提问，用户所提问过的问题
			list = (List<ReplyActiveDTO>) dao.findForList("activityMapper.selectUserQAPage", page);
				
		}
		
		return list;
	}
	
	/**
	 * 方法描述：活动草稿箱列表
	 * 实现接口：@see com.op.service.activity.ActivityService#showDraftActivityList(com.op.plugin.Page)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<DraftResultsDTO> showDraftActivityList(Page<String> page) throws Exception {
		return (List<DraftResultsDTO>) dao.findForList("activityMapper.showDraftActivityListPage", page);
	}
	
	/**
	 * 方法描述：删除草稿箱
	 * 实现接口：@see com.op.service.activity.ActivityService#delete(java.util.Map)
	 * @param map
	 * @throws Exception
	 */
	@Override
	public void deleteDraft(Map<String, String> map) throws Exception {
		// 删除活动 
		int i = (int) dao.delete("activityMapper.deleteDraft", map);
		if(i>0){
			String id = map.get("id");
			// 删除线路
			dao.delete("linesMapper.deleteByActiveId", id);
			
			// 线路图片
			dao.delete("lineImagesMapper.deleteByActiveId", id);
			
			// 活动保险
			dao.delete("activityInsuranceMapper.deleteByActiveId", id);
			
			// 额外费用
			dao.delete("activeCorrelationCostMapper.deleteByActiveId", id);
		}
		
		map.clear();
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
	}
	
	/**
	 * 方法描述：编写活动草稿信息
	 * 实现接口：@see com.op.service.activity.ActivityService#editDraft(java.util.Map)
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	@Override
	public ActivityDraftDTO editDraft(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return (ActivityDraftDTO) dao.findForObject("activityMapper.editDraft", map);
	}
	
	/**
	 * 方法描述：再次发布项目
	 * 实现接口：@see com.op.service.activity.ActivityService#editDraft(java.util.Map)
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	@Override
	public ActivityDraftDTO againRelease(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return (ActivityDraftDTO) dao.findForObject("activityMapper.againRelease", map);
	}
	
	/**
	 * 方法描述：查询活动数据（更新活动的最新数据）
	 * 实现接口：@see com.op.service.activity.ActivityService#updateActivityData(java.lang.String)
	 * @param id
	 * @throws Exception
	 */
	@Override
	public UpdateActiveDTO updateActivityData(String id) throws Exception {
		// 更新活动对象
		return (UpdateActiveDTO) dao.findForObject("activityMapper.updateActivityData", id);
	}
	
	/**
	 * 方法描述：查询我的草稿活动
	 * 实现接口：@see com.op.service.activity.ActivityService#myDraftActivity(java.lang.String)
	 * @param uId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<MyActiveInfoDTO> myDraftActivity(String uId) throws Exception {
		
		return (List<MyActiveInfoDTO>) dao.findForList("activityMapper.uCenterDraftActivitys", uId);
	}
	
	/**
	 * 方法描述：更改过期的活动状态
	 * 实现接口：@see com.op.service.activity.ActivityService#updatePastActivity()
	 * @throws Exception
	 */
	@Override
	public void updatePastActivity() throws Exception {
		// TODO Auto-generated method stub
		dao.update("activityMapper.updatePastActivity",null);
	}
	
	/**
	 * 查询活动状态
	 */
	public Map<String,String> findActiveState(String id) throws Exception{
		return (Map<String,String>) dao.findForObject("activityMapper.findActiveState",id);
	}
	
	/**
	 * 查询活动模板
	 * @param uType 用户类型
	 * @param page 分页
	 * @return
	 * @throws Exception
	 */
	public List<MyActiveInfoDTO> findActiveTemplate(Page<String> page) throws Exception{
		return (List<MyActiveInfoDTO>) dao.findForList("activityMapper.findActiveTemplate", page);
	}
	
	/**
	 * 添加活动模板
	 */
	public void saveTemplate(Map<String,String> map) throws Exception{
		dao.update("activityMapper.saveTemplate", map);
		
		map.clear();
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
	}
	
	/**
	 * 删除活动模板
	 */
	public void deleteTemplate(Map<String,String> map) throws Exception{
		dao.update("activityMapper.deleteTemplate", map);
		
		map.clear();
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
	}
	
	/**
	 * 关闭活动
	 */
	public void closeActive(Map<String,String> map) throws Exception{
		dao.update("activityMapper.closeActive", map);
		
		map.clear();
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
	}
	
}
