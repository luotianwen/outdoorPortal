package com.op.service.lines;

import java.util.List;
import org.springframework.stereotype.Service;
import com.op.entity.lines.LineImages;

@Service("lineImagesService")
public interface LineImagesService {
	
	/**
	 * 获取没有上传阿里云OSS云存储的图片
	 * @return
	 * @throws Exception
	 */
	List<LineImages> notUploadOSSImages() throws Exception;
	
	/**
	 * 更改图片上传阿里云OSS云存储的状态
	 * @return
	 * @throws Exception
	 */
	void updateUploadOSSState(List<LineImages> lineImageList) throws Exception;	
	
	
	
	
}
