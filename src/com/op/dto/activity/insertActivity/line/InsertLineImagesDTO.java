package com.op.dto.activity.insertActivity.line;

/**
 * 项目名：outdoorPortal
 * 类描述：线路图片
 * 创建人：Yan
 * 创建时间： 2016-1-27 下午1:57:09
 * 最后修改时间：2016-1-27下午1:57:09
 */
public class InsertLineImagesDTO {

	// 图片路径
	String lineImage;
	// 图片字幕
	String lineCaption;
	
	/*-------------------------------------------	get	&&	set --------------------------*/
	
	public String getLineImage() {
		return lineImage;
	}
	public void setLineImage(String lineImage) {
		this.lineImage = lineImage;
	}
	public String getLineCaption() {
		return lineCaption;
	}
	public void setLineCaption(String lineCaption) {
		this.lineCaption = lineCaption;
	}

	
	
}
