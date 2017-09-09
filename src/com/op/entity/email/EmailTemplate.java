package com.op.entity.email;

import java.io.Serializable;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：EmailTemplate   
* 类描述：   Email 发送模板
* 创建人：Win Zhong   
* 创建时间：2015年12月28日 上午10:22:06   
* 修改人：Win Zhong   
* 修改时间：2015年12月28日 上午10:22:06   
* 修改备注：   
* @version    
*
 */
public class EmailTemplate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5874466745450235152L;
	/*
	 * id （模板别名）
	 */
	private String et_id;
	/*
	 * 模板名称
	 */
	private String et_name;
	/*
	 *邮件标题 
	 */
	private String et_title;
	/*
	 * 模板内容
	 */
	private String et_template;
	public String getEt_id() {
		return et_id;
	}
	public void setEt_id(String et_id) {
		this.et_id = et_id;
	}
	public String getEt_name() {
		return et_name;
	}
	public void setEt_name(String et_name) {
		this.et_name = et_name;
	}
	public String getEt_template() {
		return et_template;
	}
	public void setEt_template(String et_template) {
		this.et_template = et_template;
	}
	public String getEt_title() {
		return et_title;
	}
	public void setEt_title(String et_title) {
		this.et_title = et_title;
	}
	 
	
}
