package com.op.dto.travels;

public class TravelsModifyData {
	
	//operation  操作类型  1(text)  2(img)  3(video) 4(title) 5(day)
	private String o;
	//content 内容
	private String c;
	//style 段落样式	/	图片宽度	/state	游记状态
	private String s;
	//word_count 文字总数
	private String w;
	//photo_count 图片总数
	private String p;
	
	public String getO() {
		return o;
	}
	public void setO(String o) {
		this.o = o;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public String getW() {
		return w;
	}
	public void setW(String w) {
		this.w = w;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	
	
}
