package com.op.service.travels;

import java.util.List;
import java.util.Map;

import com.op.dto.travels.MyAllTravelsDTO;
import com.op.dto.travels.MyTravelsResultsDTO;
import com.op.dto.travels.TravelsDraftDTO;
import com.op.dto.travels.TravelsHotDataDTO;
import com.op.dto.travels.TravelsModifyDTO;
import com.op.dto.travels.show.TravelsPerfectInfoDTO;
import com.op.entity.travels.Travels;
import com.op.plugin.page.Page;
/** 
 * 游记(travels)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-18 14:30:31 
 */  
public interface TravelsService {

	/**
	 * 保存草稿(自动/手动保存)
	 */
	void saveTravels(Travels travels) throws Exception;
	
	/**
	 * 根据ID查询游记
	 */
	Travels findTravelsById(Map<String,String> map) throws Exception;
	
	/**
	 * 修改游记信息
	 */
	void updateTravels(TravelsModifyDTO travelsModifyDTO,Map<String,Object> map) throws Exception;
	
	/**
	 * 删除游记
	 */
	void updateDelTravels(Map<String,Object> map) throws Exception;
	
	/**
	 * 方法描述：根据游记ID查询详情
	 * 返回类型：void
	 * @param id 游记id
	 * @param ftl 模板文件名
	 * @param targetFile 目标目录及文件
	 * @param contextPath servlet上下文
	 * @throws Exception
	 */
	void selectInfoById(String id,String ftl, String targetFile ,String contextPath)  throws Exception;
	
	/**
	 * 方法描述：游记草稿箱
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<TravelsDraftDTO> findTravelsDraft(Map<String,String> map) throws Exception;

	/**
	 * 方法描述：游记草稿数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int travelsDraftCount(Map<String,String> map) throws Exception;
	
	/**
	 * 查询游记点赞、收藏 、浏览等热数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	TravelsHotDataDTO findHotData(String id)throws Exception;
	
	/**
	 * 根据user id 分页查询用户游记
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<MyTravelsResultsDTO> findTravelsByUserId(Page<?> page)throws Exception;
	
	/**
	 * 根据user id 查询用户游记 用户个人中心显示前5条
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<MyTravelsResultsDTO> findTravelsFroUsersCenter(String usersId)throws Exception;
	
	/**
	 * 我的游记统计
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	MyAllTravelsDTO myTravelsCount(String user_id)throws Exception;
	
	/**
	 * 修改游记精简内容
	 */
	void updateTravelsDigest(TravelsModifyDTO travelsModifyDTO) throws Exception;
	
	/**
	 * 完善游记信息
	 * @param travelsPerfectInfoDTO
	 */
	void updatePerfectinfo(TravelsPerfectInfoDTO travelsPerfectInfoDTO) throws Exception;
	
	/**
	 * 查询游记创建人与游记状态
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> findTravelsState(String id) throws Exception;
	
	/**
	 * 查询收藏游记
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<MyTravelsResultsDTO> findCollectionTravelsByUserId(Page<?> page)throws Exception;
	
}
