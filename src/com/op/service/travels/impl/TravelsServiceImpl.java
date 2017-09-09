package com.op.service.travels.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;




import com.op.dao.BaseDao;
import com.op.dto.travels.MyAllTravelsDTO;
import com.op.dto.travels.MyTravelsResultsDTO;
import com.op.dto.travels.TravelsDraftDTO;
import com.op.dto.travels.TravelsHotDataDTO;
import com.op.dto.travels.TravelsModifyDTO;
import com.op.dto.travels.show.TravelsPerfectInfoDTO;
import com.op.entity.travels.Travels;
import com.op.plugin.page.Page;
import com.op.service.travels.TravelsService;
import com.op.util.Const;
import com.op.util.DateUtil;
import com.op.util.FreeMarkerUtil;
import com.op.util.Tools;
import  com.op.util.jedis.RedisUtil;

/** 
 * 游记(travels)接口实现类
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-18 14:30:31 
 */  
@Service("travelsServiceImpl")
public class TravelsServiceImpl implements TravelsService{

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;
	
	@Value("#{properties['TravelDetailsPictureStyle']}")
	private String TravelDetailsPictureStyle;
	
	@Resource(name="freeMarkerUtil")
	FreeMarkerUtil freeMarkerUtil;
	
	/**
	 * 保存草稿(自动/手动保存)
	 */
	@Override
	public void saveTravels(Travels travels) throws Exception{
		
		dao.save("travelsMapper.saveTravels", travels);
	}
	
	/**
	 * 根据ID查询游记
	 */
	@Override
	public Travels findTravelsById(Map<String,String> map) throws Exception{
		return (Travels) dao.findForObject("travelsMapper.findTravelsById", map);
	}
	
	/**
	 * 修改游记信息
	 */
	@Override
	public void updateTravels(TravelsModifyDTO travelsModifyDTO,Map<String,Object> map) throws Exception{
		
	}
	
	/**
	 * 删除游记
	 */
	@Override
	public void updateDelTravels(Map<String,Object> map) throws Exception{
//		Map<String,String> travelsMap = new HashMap<String,String>();
//		travelsMap.put("id", travels.getId());
//		travelsMap.put("user_id", travels.getUser_id());
//		
//		travels = (Travels) dao.findForObject("travelsMapper.findTravelsByIdforDel", travelsMap);
//		if(travels!=null){
//			if("1".equals(travels.getIssued_state())||"4".equals(travels.getIssued_state())){
//				dao.delete("travelsMapper.deleteTravels", travels);
//				dao.delete("travelsContentMapper.deleteTravels", travels);
//				dao.delete("travelsTitleMapper.deleteTravels", travels);
//				dao.delete("travelsPhotosMapper.deleteTravels", travels);
//			}else{
//				dao.update("travelsMapper.deleteTravelsByRelease", travels);
//			}
//			
//			
//		}
		dao.update("travelsMapper.deleteTravelsByRelease", map);
		
	}

	/**
	 * 方法描述：根据游记ID查询详情
	 * 实现接口：@see com.op.service.travels.TravelsService#selectInfoById(java.lang.String)
	 * 返回类型：void
	 * @param id 游记id
	 * @param ftl 模板文件名
	 * @param targetFile 目标目录及文件
	 * @param contextPath servlet上下文
	 * @throws Exception
	 */
	@Override
	public void selectInfoById(String id,String ftl, String targetFile ,String contextPath) throws Exception {
		// 填充数据 
		Map<String,Object> data=new HashMap<String,Object>();
		
		Travels travels = (Travels) dao.findForObject("travelsMapper.findTravelsByIdForStatic", id);
		
		if(travels!=null){
			data.put("TravelDetailsPictureStyle", TravelDetailsPictureStyle);
			data.put("travels", travels);
			
			//TravelsDetailDTO dto = (TravelsDetailDTO) dao.findForObject("travelsMapper.", id);
			
			freeMarkerUtil.createFile(ftl, data, targetFile,contextPath);
		}
	}
	
	/**
	 * 方法描述：游记草稿箱
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TravelsDraftDTO> findTravelsDraft(Map<String,String> map) throws Exception{
		return (List<TravelsDraftDTO>) dao.findForList("travelsMapper.findTravelsDraft", map);
	}
	
	/**
	 * 方法描述：游记草稿数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public int travelsDraftCount(Map<String,String> map) throws Exception{
		return(int) dao.findForObject("travelsMapper.travelsDraftCount", map);
	}
	
	/**
	 * 查询游记点赞、收藏 、浏览等热数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public TravelsHotDataDTO findHotData(String id)throws Exception{
		 
		return (TravelsHotDataDTO)dao.findForObject("travelsMapper.findHotData", id);
	}
	
	/**
	 * 根据user id 分页查询用户游记
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<MyTravelsResultsDTO> findTravelsByUserId(Page<?> page)throws Exception{

		return (List<MyTravelsResultsDTO>) dao.findForList("travelsMapper.findTravelsByUserIdPageList", page);
	}
	
	/**
	 * 根据user id 查询用户游记 用户个人中心显示前5条
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<MyTravelsResultsDTO> findTravelsFroUsersCenter(String usersId)throws Exception{
		return (List<MyTravelsResultsDTO>) dao.findForList("travelsMapper.findTravelsFroUsersCenter", usersId);
	}
	
	/**
	 * 修改游记精简内容
	 */
	@Override
	public void updateTravelsDigest(TravelsModifyDTO travelsModifyDTO) throws Exception{
		dao.update("travelsMapper.updateTravels", travelsModifyDTO);
	}
	
	/**
	 * 我的游记统计
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	@Override
	public MyAllTravelsDTO myTravelsCount(String user_id)throws Exception{
		return (MyAllTravelsDTO)dao.findForObject("travelsMapper.myTravelsCount", user_id);
	}

	/**
	 * 完善游记信息
	 * @param travelsPerfectInfoDTO
	 */
	@Override
	public void updatePerfectinfo(TravelsPerfectInfoDTO travelsPerfectInfoDTO) throws Exception{

		
		String key = travelsPerfectInfoDTO.getTravelsId()+"_"+travelsPerfectInfoDTO.getUser_id()+"_departure_time";
		
		dao.update("travelsMapper.updatePerfectinfo", travelsPerfectInfoDTO);
		
		// 缓存不存在数据
		if(RedisUtil.hkeys(Const.TRAVELSHOTDATAKEY+travelsPerfectInfoDTO.getTravelsId()).isEmpty()){
			// 获取该游记数据
			TravelsHotDataDTO th = (TravelsHotDataDTO)dao.findForObject("travelsMapper.findHotData", travelsPerfectInfoDTO.getTravelsId());
//			th.setDeparture_time(travelsPerfectInfoDTO.getDeparture_time());
//			th.setDeparture_time_str(DateUtil.YYYY_MM_DDgetDay(travelsPerfectInfoDTO.getDeparture_time()));
//			th.setTravel_days(travelsPerfectInfoDTO.getTravel_days());
//			th.setTravel_person(travelsPerfectInfoDTO.getTravel_person());
//			th.setPer_capita_cost(travelsPerfectInfoDTO.getPer_capita_cost());
			
			//存入热数据到缓存
			RedisUtil.hmset(Const.TRAVELSHOTDATAKEY+travelsPerfectInfoDTO.getTravelsId(),Tools.transBean2MapString(th));
			//108000秒过期 即一天半后自动删除缓存
			RedisUtil.expire(Const.TRAVELSHOTDATAKEY+travelsPerfectInfoDTO.getTravelsId(), (int) (1.5*24*60*60));
			
		}else{
			List<String> hotData = RedisUtil.hmget(Const.TRAVELSHOTDATAKEY+travelsPerfectInfoDTO.getTravelsId(),"departure_time_str");
			RedisUtil.hset(Const.TRAVELSHOTDATAKEY+travelsPerfectInfoDTO.getTravelsId(), "departure_time_str",DateUtil.YYYY_MM_DDgetDay(travelsPerfectInfoDTO.getDeparture_time()));
			hotData = RedisUtil.hmget(Const.TRAVELSHOTDATAKEY+travelsPerfectInfoDTO.getTravelsId(),"travel_days");
			RedisUtil.hset(Const.TRAVELSHOTDATAKEY+travelsPerfectInfoDTO.getTravelsId(), "travel_days",travelsPerfectInfoDTO.getTravel_days()+"");
			hotData = RedisUtil.hmget(Const.TRAVELSHOTDATAKEY+travelsPerfectInfoDTO.getTravelsId(),"travel_person");
			RedisUtil.hset(Const.TRAVELSHOTDATAKEY+travelsPerfectInfoDTO.getTravelsId(), "travel_person",travelsPerfectInfoDTO.getTravel_person());
			hotData = RedisUtil.hmget(Const.TRAVELSHOTDATAKEY+travelsPerfectInfoDTO.getTravelsId(),"per_capita_cost");
			RedisUtil.hset(Const.TRAVELSHOTDATAKEY+travelsPerfectInfoDTO.getTravelsId(), "per_capita_cost",travelsPerfectInfoDTO.getPer_capita_cost()+"");
		}
		
	}
	
	/**
	 * 查询游记创建人与游记状态
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String,Object> findTravelsState(String id) throws Exception{
		return (Map<String, Object>) dao.findForObject("travelsMapper.findTravelsState", id);
	}
	
	/**
	 * 查询收藏游记
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<MyTravelsResultsDTO> findCollectionTravelsByUserId(Page<?> page)throws Exception{
		
		return (List<MyTravelsResultsDTO>) dao.findForList("travelsMapper.findCollectionTravelsByUserIdPage", page);
	}
	
}
