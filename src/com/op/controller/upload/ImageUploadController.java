package com.op.controller.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.dto.travels.TravelsModifyDTO;
import com.op.dto.travels.TravelsModifyData;
import com.op.entity.users.Users;
import com.op.service.travels.TravelsContentService;
import com.op.task.AvatarUploadOss;
import com.op.util.Const;

@RequestMapping(value="imageUpload")
@Controller
public class ImageUploadController {

	StringBuffer realPath = null;
	
	@Resource(name="avatarUploadOssImpl")
    AvatarUploadOss avatarUploadOssImpl;
	@Resource(name="travelsContentServiceImpl")
	TravelsContentService travelsContentServiceImpl;
	

	/**
	 * 方法描述：上传文件
	 * 返回类型：Map<String,Object>
	 * @param request
	 * @param dataURL 获取图片
	 * @param sort 排序
	 * @param travelsId 游记id
	 * @param content_id 内容ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/base64Img")
	@ResponseBody
	public Map<String,Object> uploadImg(HttpServletRequest request,
			@RequestParam(value="dataUrl") String dataURL,
			@RequestParam(value="sort") String sort,
			@RequestParam(value="id") String travelsId,
			@RequestParam(value="content_id") String content_id,
			@RequestParam(value="width",required=false) String width) throws Exception{
		
		Map<String,Object> res = new HashMap<String,Object>();
		
        
		// base64字符串转化成图片  
		res.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		res.put("savePath", dataURL);
		String url = dataURL;//avatarUploadOssImpl.uploadPhotos(saveUrl+fileName);
		
		TravelsModifyDTO travelsModifyDTO = new TravelsModifyDTO();
		//游记id
		travelsModifyDTO.setId(travelsId);
		//操作行为  insert update delete
		travelsModifyDTO.setAct("insert");
		//序列
		travelsModifyDTO.setContent_id(content_id);
		Users users = (Users) request.getSession().getAttribute(Const.SESSION_USER);
		//用户ID
		travelsModifyDTO.setUser_id(users.getuId());
		//数据类型与内容
		TravelsModifyData data = new TravelsModifyData();
		//最大排序数
		travelsModifyDTO.setSort(Integer.valueOf(sort));
		//operation  操作类型  1(text)  2(img)  3(video) 4(title) 5(day)
		if(null!=content_id&&!"".equals(content_id)){
			data.setO("52");
		}else{
			data.setO("2");
		}
		//图片宽度
		data.setS(width);
		//content 内容
		data.setC(url);
		travelsModifyDTO.setData(data);
		
		travelsContentServiceImpl.addTravelsContent(travelsModifyDTO, res);
		return res;
	}
	

	
	//base64字符串转化成图片  
    public boolean Base64TurnPicture(String content,String savePath,String fileName){   
    	if (content == null) // 图像数据为空
    		return false;
    	//System.out.println("========"+content);
		try {
			// 检查目录
	        File uploadDir = new File(savePath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			byte[] bs = Base64.decodeBase64(content.split(",")[1]);
			FileOutputStream os = new FileOutputStream(savePath+"/"+fileName);
			os.write(bs);
			os.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
	
}
