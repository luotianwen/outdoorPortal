package com.op.service.travels.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.op.dao.BaseDao;
import com.op.dto.travels.TravelsHotDataDTO;
import com.op.dto.travels.TravelsModifyDTO;
import com.op.entity.travels.TravelsContent;
import com.op.entity.travels.TravelsPhotos;
import com.op.entity.travels.TravelsTitle;
import com.op.service.travels.TravelsContentService;
import com.op.task.AvatarUploadOss;
import com.op.task.PhotosUploadOss;
import com.op.util.Const;
import com.op.util.HTMLEscape;
import com.op.util.Tools;
import com.op.util.jedis.RedisUtil;

/**
 * 游记内容(travelsContent)接口实现类
 * 
 * @author Win Zhong
 * @version Revision: 1.00 Date: 2016-04-18 14:30:32
 */
@Service("travelsContentServiceImpl")
public class TravelsContentServiceImpl implements TravelsContentService {

	@Resource(name = "baseDaoImpl")
	private BaseDao dao;

	@Resource(name="avatarUploadOssImpl")
    AvatarUploadOss avatarUploadOssImpl;
	
	@Resource(name="photosUploadOssImpl")
	PhotosUploadOss photosUploadOssImpl;
	
	/**
	 * 新增游记内容
	 * 
	 * @param travelsModifyDTO
	 * @throws Exception
	 */
	@Override
	public void addTravelsContent(TravelsModifyDTO travelsModifyDTO,Map<String,Object> map) throws Exception{
		TravelsContent travelsContent = new TravelsContent();
		if(!"daytitle".equals(travelsModifyDTO.getData().getO())){
		}
		
    	switch (travelsModifyDTO.getData().getO()) {
    		//文字
	    	case "1":
	    		addContent(travelsModifyDTO,travelsContent,map,"1");
	    		break;
	    	//图片
    		case "2":
    			addPhotos(travelsModifyDTO, travelsContent, map,"2");
    			break;
    		//视频(暂无)
    		case "3":
    			map.put(Const.RESPONSE_STATE, "500");
    			map.put(Const.ERROR_INFO, "服务异常，请稍后再试");
	    		break;
	    	//段落
    		case "4":
    			addTitle(travelsModifyDTO, travelsContent, map,"4");
    			break;
    	    //day模板
    		case "5":
    			addDay(travelsModifyDTO, travelsContent, map);
    			break;
    		//day文字
    		case "51":
    			addContent(travelsModifyDTO, travelsContent, map,"5");
    			break;
        	//day图片
    		case "52":
    			addPhotos(travelsModifyDTO, travelsContent, map,"5");
    			break;
            //day视频(暂无)
    		case "53":
    			map.put(Const.RESPONSE_STATE, "500");
    			map.put(Const.ERROR_INFO, "服务异常，请稍后再试");
    			break;
    		//day段落
    		case "54":
    			addTitle(travelsModifyDTO, travelsContent, map,"5");
    			break;
    		default:
    			map.put(Const.RESPONSE_STATE, "500");
    			map.put(Const.ERROR_INFO, "服务异常，请稍后再试");
				break;
    	
    	}
    	
    	dao.update("travelsMapper.updateTravelsForUpdateTime", travelsModifyDTO);
    }
	
	/**
	 * 新增文字内容
	 * @param travelsModifyDTO
	 * @return
	 * @throws Exception
	 */
	public void addContent(TravelsModifyDTO travelsModifyDTO,TravelsContent travelsContent,Map<String,Object> map,String type) throws Exception{
		// 游记ID
    	travelsContent.setTravels_id(travelsModifyDTO.getId());
    	// 内容类型（1：文字；2：图片；3：视频；4：段落标题；5：day模板）
    	travelsContent.setContent_type("1");
    	// 内容
    	if("1".equals(type)||"5".equals(type)){
    		String content = HTMLEscape.htmlEscape(travelsModifyDTO.getData().getC());
        	travelsContent.setContent(content);
        	map.put("content", content);
    	}else{
        	travelsContent.setContent(travelsModifyDTO.getData().getC());
    	}
    	// 排序
    	travelsContent.setSort(travelsModifyDTO.getSort()+1);
    	//创建人
    	travelsContent.setUser_id(travelsModifyDTO.getUser_id());
    	
    	if("5".equals(type)){
    		travelsContent.setParent_id(travelsModifyDTO.getContent_id());
    	}else{
    		travelsContent.setParent_id("0");
    	}
    	
    	
    	dao.save("travelsContentMapper.addTravelsContent", travelsContent);
    	
    	map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
    	map.put("sort", travelsContent.getSort());
    	map.put("contentId", travelsContent.getId());
	}
	
	/**
	 * 新增图片内容
	 * @param travelsModifyDTO
	 * @return
	 * @throws Exception
	 */
	public void addPhotos(TravelsModifyDTO travelsModifyDTO,TravelsContent travelsContent,Map<String,Object> map,String type) throws Exception{
		/**
		 * 生成新空的游记内容
		 */
		dao.save("travelsContentMapper.addTravelsContent", travelsContent);
		
		/**
		 * 新增图片
		 */
		TravelsPhotos travelsPhotos = new TravelsPhotos();
		//父标题ID（默认0为一级）
		if("5".equals(type)){
			travelsPhotos.setParent_id(travelsModifyDTO.getContent_id());
		}else{
			travelsPhotos.setParent_id("0");
		}
	 	//游记内容ID
		travelsPhotos.setContent_id(travelsContent.getId());
		//图片
		travelsPhotos.setSrc(travelsModifyDTO.getData().getC());
		//图片宽度
		travelsPhotos.setWidth(travelsModifyDTO.getData().getS());
		//游记ID
		travelsPhotos.setTravels_id(travelsModifyDTO.getId());
		//创建用户
		travelsPhotos.setUser_id(travelsModifyDTO.getUser_id());
		
		dao.save("travelsPhotosMapper.addPhotos", travelsPhotos);
		
		/**
		 * 新增游记内容
		 */
		// 游记ID
    	travelsContent.setTravels_id(travelsModifyDTO.getId());
    	// 内容类型（1：文字；2：图片；3：视频；4：段落标题；5：day模板）
    	travelsContent.setContent_type("2");
    	// 排序
    	travelsContent.setSort(travelsModifyDTO.getSort()+1);
    	//图片ID
    	travelsContent.setPhoto_id(travelsPhotos.getId());
    	//创建人
    	travelsContent.setUser_id(travelsModifyDTO.getUser_id());
    	if("5".equals(type)){
    		travelsContent.setParent_id(travelsModifyDTO.getContent_id());
    	}else{
    		travelsContent.setParent_id("0");
    	}
    	
    	dao.update("travelsContentMapper.updateNullTravelsContent", travelsContent);
    	
    	map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
    	map.put("sort", travelsContent.getSort());
    	
    	map.put("contentId", travelsContent.getId());
    	map.put("photosId", travelsPhotos.getId());
	}
	
	/**
	 * 新增段落内容
	 * @param travelsModifyDTO
	 * @return
	 * @throws Exception
	 */
	public void addTitle(TravelsModifyDTO travelsModifyDTO,TravelsContent travelsContent,Map<String,Object> map,String type) throws Exception{
		/**
		 * 生成新空的游记内容
		 */
		dao.save("travelsContentMapper.addTravelsContent", travelsContent);
		
		/**
		 * 新增段落
		 */
		TravelsTitle travelsTitle = new TravelsTitle();
		//父标题ID（默认0为一级）
		if("5".equals(type)){
			travelsTitle.setParent_id(travelsModifyDTO.getContent_id());
		}else{
			travelsTitle.setParent_id("0");
		}
	 	//游记内容ID
		travelsTitle.setContent_id(travelsContent.getId());
		//标题
		travelsTitle.setTitle(HTMLEscape.htmlEscape(travelsModifyDTO.getData().getC()));
	 	//样式ID
		travelsTitle.setStyle_id(travelsModifyDTO.getData().getS());
		//游记ID
		travelsTitle.setTravels_id(travelsModifyDTO.getId());
		//创建用户
		travelsTitle.setUser_id(travelsModifyDTO.getUser_id());
		
		dao.save("travelsTitleMapper.addTitle", travelsTitle);
		
		/**
		 * 新增游记内容
		 */
		// 游记ID
    	travelsContent.setTravels_id(travelsModifyDTO.getId());
    	// 内容类型（1：文字；2：图片；3：视频；4：段落标题；5：day模板）
    	travelsContent.setContent_type("4");
    	// 排序
    	travelsContent.setSort(travelsModifyDTO.getSort()+1);
    	//段落ID
    	travelsContent.setTitle_id(travelsTitle.getId());
    	//创建人
    	travelsContent.setUser_id(travelsModifyDTO.getUser_id());
    	if("5".equals(type)){
    		travelsContent.setParent_id(travelsModifyDTO.getContent_id());
    	}else{
    		travelsContent.setParent_id("0");
    	}
    	
    	dao.update("travelsContentMapper.updateNullTravelsContent", travelsContent);
    	
    	map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
    	map.put("sort", travelsContent.getSort());
    	map.put("contentId", travelsContent.getId());
    	map.put("titleId", travelsTitle.getId());
    	map.put("title", travelsTitle.getTitle());
    	
	}
	
	//新增day模板
	public void addDay(TravelsModifyDTO travelsModifyDTO, TravelsContent travelsContent, Map<String,Object> map) throws Exception{
		/**
		 * 新增游记内容
		 */
		// 游记ID
    	travelsContent.setTravels_id(travelsModifyDTO.getId());
    	// 内容类型（1：文字；2：图片；3：视频；4：段落标题；5：day模板）
    	travelsContent.setContent_type("5");
    	// 排序
    	travelsContent.setSort(travelsModifyDTO.getSort()+1);
    	//创建人
    	travelsContent.setUser_id(travelsModifyDTO.getUser_id());
    	travelsContent.setParent_id("0");
    	
    	dao.save("travelsContentMapper.addTravelsContent", travelsContent);
    	
    	map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
    	map.put("sort", travelsContent.getSort());
    	map.put("contentId", travelsContent.getId());
	}
	
	
	/**
	 * 修改游记内容
	 * @param travelsModifyDTO
	 * @throws Exception
	 */
    @Override
	public void updateTravelsContent(TravelsModifyDTO travelsModifyDTO,Map<String,Object> map) throws Exception{
		switch (travelsModifyDTO.getData().getO()) {
			//文字
    		case "1":
	    		updateContent(travelsModifyDTO,map);
	    		break;
	    	//图片(暂不做修改)
    		case "2":
    			break;
    		//视频(暂无)
    		case "3":
    			map.put(Const.RESPONSE_STATE, "500");
    			map.put(Const.ERROR_INFO, "服务异常，请稍后再试");
	    		break;
	    	//段落
    		case "4":
    			updateTitle(travelsModifyDTO,map);
    			break;
    		//游记标题
    		case "title":
    			updateTravels(travelsModifyDTO,map);
	    		break;
	    	//游记banner图
	    	case "image":
	    		updateTravels(travelsModifyDTO,map);
	    		break;
	    	//游记关联地点
	    	case "address":
	    		updateTravels(travelsModifyDTO,map);
	    		break;
	    	//day模板标题
	    	case "daytitle":
	    		updateTravels(travelsModifyDTO,map);
	    		break;
	    	//day模板banner图
	    	case "dayphotos":
	    		updateTravels(travelsModifyDTO,map);
	    		break;
	    	//游记封面
	    	case "cover":
	    		updateTravels(travelsModifyDTO,map);
	    		break;
	    	//游记音乐
	    	case "music":
	    		updateTravels(travelsModifyDTO,map);
	    		break;
	    	//游记音乐名
	    	case "musicname":
	    		updateTravels(travelsModifyDTO,map);
	    		break;
    		default:
    			map.put(Const.RESPONSE_STATE, "500");
    			map.put(Const.ERROR_INFO, "服务异常，请稍后再试");
				break;
    	}
		
		dao.update("travelsMapper.updateTravelsForUpdateTime", travelsModifyDTO);
	
    	
    }
    
    /**
     * 修改游记文字
     * @param travelsModifyDTO
     * @param travelsContent
     * @param map
     * @throws Exception 
     */
    public void updateContent(TravelsModifyDTO travelsModifyDTO,Map<String,Object> map) throws Exception{
    	String content = HTMLEscape.htmlEscape(travelsModifyDTO.getData().getC());
    	travelsModifyDTO.getData().setC(content);
    	
    	dao.update("travelsContentMapper.updateTravelsContent", travelsModifyDTO);
    	
    	map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
    	map.put("content", content);
    }
    
    /**
     * 修改游记标题
     * @param travelsModifyDTO
     * @param travelsContent
     * @param map
     * @throws Exception 
     */
    public void updateTitle(TravelsModifyDTO travelsModifyDTO,Map<String,Object> map) throws Exception{
    	travelsModifyDTO.getData().setC(HTMLEscape.htmlEscape(travelsModifyDTO.getData().getC()));
    	
    	dao.update("travelsTitleMapper.updateTravelsTitle", travelsModifyDTO);
    	map.put("title", travelsModifyDTO.getData().getC());
    	map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
    }
    
    /**
     * 修改游记信息
     * @param travelsModifyDTO
     * @param map
     * @throws Exception
     */
    public void updateTravels(TravelsModifyDTO travelsModifyDTO,Map<String,Object> map) throws Exception{
		if("image".equals(travelsModifyDTO.getData().getO())){
			travelsModifyDTO.getData().setS(photosUploadOssImpl.uploadPhotos(travelsModifyDTO.getData().getS()));
			dao.update("travelsMapper.updateTravels", travelsModifyDTO);
		}else if("title".equals(travelsModifyDTO.getData().getO())){
			dao.update("travelsMapper.updateTravels", travelsModifyDTO);
		}else if("cover".equals(travelsModifyDTO.getData().getO())){
			TravelsContent travelsContent = new TravelsContent();
			travelsContent = (TravelsContent) dao.findForObject("travelsContentMapper.findTravelsContentById", travelsModifyDTO);
			if(travelsContent!=null){
				if(null!=travelsContent.getPhoto_id()&&!"".equals(travelsContent.getPhoto_id())){
					travelsModifyDTO.getData().setC(travelsContent.getPhoto_id());
					dao.update("travelsMapper.updateTravels", travelsModifyDTO);
				}
			}
    	}else if("music".equals(travelsModifyDTO.getData().getO())){
    		dao.update("travelsMapper.updateTravels", travelsModifyDTO);
    	}else if("musicname".equals(travelsModifyDTO.getData().getO())){
    		dao.update("travelsMapper.updateTravels", travelsModifyDTO);
    	}else if("address".equals(travelsModifyDTO.getData().getO())){
    		//游记创建人与游记状态
    		Map<String,String> travelsMap = (Map<String, String>) dao.findForObject("travelsMapper.findTravelsState", travelsModifyDTO.getId());
    		
    		//游记状态
    		if(travelsMap.get("ISSUED_STATE").equals("5")||travelsMap.get("ISSUED_STATE").equals("3")){
    			travelsModifyDTO.getData().setS("5");
    			
    			String id = travelsModifyDTO.getId();
    			
    			TravelsHotDataDTO th = (TravelsHotDataDTO)dao.findForObject("travelsMapper.findHotData", id);
				//存入热数据到缓存
				RedisUtil.hmset(Const.TRAVELSHOTDATAKEY+id,Tools.transBean2MapString(th));
				//108000秒过期 即一天半后自动删除缓存
				RedisUtil.expire(Const.TRAVELSHOTDATAKEY+id, 108000);
    		}else{
    			travelsModifyDTO.getData().setS("2");
    		}
    		
    		dao.update("travelsMapper.updateTravels", travelsModifyDTO);
    	}else if("daytitle".equals(travelsModifyDTO.getData().getO())){
			TravelsContent travelsContent = new TravelsContent();
			travelsContent = (TravelsContent) dao.findForObject("travelsContentMapper.findTravelsContentById", travelsModifyDTO);
			if(null!=travelsContent.getTitle_id()&&!"".equals(travelsContent.getTitle_id())){
				updateTitle(travelsModifyDTO,map);
			}else{
				/**
				 * 新增段落
				 */
				TravelsTitle travelsTitle = new TravelsTitle();
				//父标题ID（默认0为一级）
				travelsTitle.setParent_id("0");
			 	//游记内容ID
				travelsTitle.setContent_id(travelsContent.getId());
				//标题
				travelsTitle.setTitle(HTMLEscape.htmlEscape(travelsModifyDTO.getData().getC()));
			 	//样式ID
				travelsTitle.setStyle_id(travelsModifyDTO.getData().getS());
				//游记ID
				travelsTitle.setTravels_id(travelsModifyDTO.getId());
				//创建用户
				travelsTitle.setUser_id(travelsModifyDTO.getUser_id());
				
				dao.save("travelsTitleMapper.addTitle", travelsTitle);
				
				/**
				 * 修改内容标题Id
				 */
				travelsModifyDTO.getData().setC(travelsTitle.getId());
				dao.update("travelsContentMapper.updateTravelsContent", travelsModifyDTO);
		    	
				map.put("title", travelsTitle.getTitle());
			}
			
    	}else if("dayphotos".equals(travelsModifyDTO.getData().getO())){
    		TravelsContent travelsContent = new TravelsContent();
			travelsContent = (TravelsContent) dao.findForObject("travelsContentMapper.findTravelsContentById", travelsModifyDTO);
			if(null!=travelsContent.getPhoto_id()&&!"".equals(travelsContent.getPhoto_id())){
				travelsModifyDTO.getData().setS(photosUploadOssImpl.uploadPhotos(travelsModifyDTO.getData().getS()));
				updatePhotos(travelsModifyDTO,map);
			}else{
				travelsModifyDTO.getData().setS(photosUploadOssImpl.uploadPhotos(travelsModifyDTO.getData().getS()));
				/**
				 * 新增banner图
				 */
				TravelsPhotos travelsPhots = new TravelsPhotos();
				//父标题ID（默认0为一级）
				travelsPhots.setParent_id("0");
			 	//游记内容ID
				travelsPhots.setContent_id(travelsContent.getId());
				//模板显示图片
				travelsPhots.setSrc(travelsModifyDTO.getData().getS());
			 	//模板原图
				travelsPhots.setTemplateimage(travelsModifyDTO.getData().getC());
				//游记ID
				travelsPhots.setTravels_id(travelsModifyDTO.getId());
				//创建人
				travelsPhots.setUser_id(travelsModifyDTO.getUser_id());
				
				dao.save("travelsPhotosMapper.addPhotos", travelsPhots);
				
				/**
				 * 修改内容标题Id
				 */
				travelsModifyDTO.getData().setC(travelsPhots.getId());
				dao.update("travelsContentMapper.updateTravelsContent", travelsModifyDTO);
		    	
			}
		}
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
    }
    
    //修改图片信息
    public void updatePhotos(TravelsModifyDTO travelsModifyDTO,Map<String,Object> map) throws Exception{
    	if("dayphotos".equals(travelsModifyDTO.getData().getO())){
    		dao.update("travelsPhotosMapper.updateTravelsPhotos", travelsModifyDTO);
    	}
    }
    
    
    /**
	 * 删除游记内容
	 * @param travelsModifyDTO
	 * @throws Exception
	 */
    @Override
	public void delTravelsContent(TravelsModifyDTO travelsModifyDTO,Map<String,Object> map,HttpServletRequest request) throws Exception{
    	switch (travelsModifyDTO.getData().getO()) {
    		//删除文字
			case "1":
	    		deleteContent(travelsModifyDTO,map,"1",request);
	    		break;
	    	//删除图片
			case "2":
				deleteContent(travelsModifyDTO, map,"2",request);
				break;
			//删除视频(暂无)
			case "3":
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "服务异常，请稍后再试");
	    		break;
	    	//删除段落
			case "4":
				deleteContent(travelsModifyDTO, map,"4",request);
				break;
			//删除day模板
			case "5":
				deleteContent(travelsModifyDTO, map,"5",request);
				break;
			//删除游记
			case "travels":
				deleteContent(travelsModifyDTO, map,"travels",request);
				break;
			default:
				map.put(Const.RESPONSE_STATE, "500");
				map.put(Const.ERROR_INFO, "服务异常，请稍后再试");
				break;
		}
    }

    /**
     * 删除游记内容
     * @param travelsModifyDTO
     * @param map
     * @param type
     * @throws Exception
     */
	public void deleteContent(TravelsModifyDTO travelsModifyDTO,Map<String,Object> map,String type,HttpServletRequest request) throws Exception{
		map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		
		if("1".equals(type)){
			//仅删除文字
			dao.delete("travelsContentMapper.deleteTravelsContent", travelsModifyDTO);
		}else if("2".equals(type)){
			//仅删除文字
			dao.delete("travelsPhotosMapper.deleteTravelsPhotos", travelsModifyDTO);
			dao.delete("travelsContentMapper.deleteTravelsContent", travelsModifyDTO);
		}else if("3".equals(type)){
			//仅删除视频
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "服务异常，请稍后再试");
		}else if("4".equals(type)){
			//仅删除段落
			dao.delete("travelsTitleMapper.deleteTravelsTitle", travelsModifyDTO);
			dao.delete("travelsContentMapper.deleteTravelsContent", travelsModifyDTO);
		}else if("5".equals(type)){
			//删除day模板所有内容
			dao.delete("travelsPhotosMapper.deleteTravelsPhotosByDay", travelsModifyDTO);
			dao.delete("travelsTitleMapper.deleteTravelsTitleByDay", travelsModifyDTO);
			dao.delete("travelsContentMapper.deleteTravelsContentByDay", travelsModifyDTO);
		}else if("travels".equals(type)){
			//删除整个游记
			dao.update("travelsMapper.deleteTravelsByRelease", travelsModifyDTO);
			
			ServletContext servletContext = request.getServletContext();
	    	
	    	String contextPath = servletContext.getRealPath("/")+"/view/travels/show/"+travelsModifyDTO.getId()+"_old.html";
	    	
		    // 创建该文件对象判断是否存在
		    File file = new File(contextPath);
		    
		    if(file.exists()){
		    	file.delete();
		    }
		}else{
			map.put(Const.RESPONSE_STATE, "500");
			map.put(Const.ERROR_INFO, "服务异常，请稍后再试");
		}
		map.put(Const.RESPONSE_STATE, "200");
	}
    
	/**
	 * 所有内容排序
	 * @param contentIds
	 * @throws Exception
	 */
	@Override
	public void updatesort(Map<String,String> map) throws Exception{
		String[] contentId = map.get("contentIds").split(",");
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		String id = map.get("id");
		for(int i=0;i<contentId.length;i++){
			if(null!=contentId[i]&&!"".equals(contentId[i])&&!"null".equals(contentId[i])){
				Map<String,String> contentMap = new HashMap<String,String>();
				contentMap.put("contentId", contentId[i]);
				contentMap.put("id", id);
				list.add(contentMap);
			}
		}
		dao.update("travelsContentMapper.updatesort", list);
		
	}
	
	/**
	 * 查询所有文字内容
	 */
	@Override
	public List<TravelsContent> findTravelsContentForWord(String id) throws Exception{
		return (List<TravelsContent>) dao.findForList("travelsContentMapper.findTravelsContentForWord", id);
	}
	
	/**
	 * 修改查看次数
	 * @throws Exception 
	 */
	@Override
	public void updateTravelsRead(String id) throws Exception {
		dao.update("travelsMapper.updateTravelsRead", id);
	}
	
	/**
	 * 修改操作时间
	 */
	@Override
	public void updateTravelsOperationTime(String id) throws Exception{
		dao.update("travelsMapper.updateTravelsOperationTime", id);
	}
	
}
