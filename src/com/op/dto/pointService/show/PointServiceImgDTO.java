package com.op.dto.pointService.show;

public class PointServiceImgDTO {
	// id
	private int pid;
	// 图片地址
	private String src;
	// 地点服务id
	private String psi_point_service_id;
	//图片名
	private String alt;
	//缩略图
	private String thumb;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getPsi_point_service_id() {
		return psi_point_service_id;
	}
	public void setPsi_point_service_id(String psi_point_service_id) {
		this.psi_point_service_id = psi_point_service_id;
	}
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	
}
