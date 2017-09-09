package com.op.service.travels;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.op.dto.travels.TravelsModifyDTO;
import com.op.entity.travels.TravelsContent;
/** 
 * 游记内容(travelsContent)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-18 14:30:32 
 */  
public interface TravelsContentService {

	/**
	 * 新增游记内容
	 * @param travelsModifyDTO
	 * @throws Exception
	 */
    void addTravelsContent(TravelsModifyDTO travelsModifyDTO,Map<String,Object> map) throws Exception;
    
    /**
	 * 修改游记内容
	 * @param travelsModifyDTO
	 * @throws Exception
	 */
    void updateTravelsContent(TravelsModifyDTO travelsModifyDTO,Map<String,Object> map) throws Exception;
    
    /**
	 * 删除游记内容
	 * @param travelsModifyDTO
	 * @throws Exception
	 */
    void delTravelsContent(TravelsModifyDTO travelsModifyDTO,Map<String,Object> map,HttpServletRequest request) throws Exception;

    /**
	 * 所有内容排序
	 * @param contentIds
	 * @throws Exception
	 */
	void updatesort(Map<String,String> map) throws Exception;

	/**
	 * 查询所有文字内容
	 */
	List<TravelsContent> findTravelsContentForWord(String id) throws Exception;
	
	/**
	 * 修改查看次数
	 */
	void updateTravelsRead(String id) throws Exception;
	
	/**
	 * 修改操作时间
	 */
	void updateTravelsOperationTime(String id) throws Exception;
	
}
