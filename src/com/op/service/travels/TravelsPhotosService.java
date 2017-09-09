package com.op.service.travels;

import java.util.Map;

/** 
 * 游记图片(travelsPhotos)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2016-04-18 14:30:32 
 */  
public interface TravelsPhotosService {

	/**
	 * 添加游记图片
	 * @param map （包含：游记id 图片路径）
	 * @return 返回图片id
	 * @throws Exception
	 */
    String addPhoto(Map<String, Object> map)throws Exception;

	/**
	 * 修改游记图片
	 * @param map （包含：游记id 图片路径）
	 * @throws Exception
	 */
     void updatePhoto(Map<String, Object> map)throws Exception;

	/**
	 * 删除游记图片
	 * @param map （包含：游记id 图片路径）
	 * @throws Exception
	 */
     void delPhoto(Map<String, Object> map)throws Exception;
     
     /**
      * 查询关联地点的图片数量
      * @param travelsId 游记ID
      * @return
      * @throws Exception
      */
     int findTravelsPhotosCountByTagId(String travelsId)throws Exception;
     /**
      * 方法描述：批量修改图片标签
      * 返回类型：void
      * @param map
      * @throws Exception
      */
     void updateImgTag(Map<String, Object> map)throws Exception;
	
}
