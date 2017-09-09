package com.op.dto.activity.insertActivity.line;

/**
 * 项目名：outdoorPortal
 * 类描述：线路【活动】图片
 * 创建人：Yan
 * 创建时间： 2016-1-27 下午1:57:09
 * 最后修改时间：2016-1-27下午1:57:09
 */
public class InsertLineActiveImagesDTO {

	// 图片路径
	String activeLineImage;
	// 图片字幕
	String activeLineCaption;
	
	/*-------------------------------------------	get	&&	set --------------------------*/

	public String getActiveLineImage() {
		return activeLineImage;
	}
	public void setActiveLineImage(String activeLineImage) {
		this.activeLineImage = activeLineImage;
	}
	public String getActiveLineCaption() {
		return activeLineCaption;
	}
	public void setActiveLineCaption(String activeLineCaption) {
		this.activeLineCaption = activeLineCaption;
	}
}
