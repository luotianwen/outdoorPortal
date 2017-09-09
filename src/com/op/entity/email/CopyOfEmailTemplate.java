package com.op.entity.email;

import java.util.Map;

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
public class CopyOfEmailTemplate {
	
	/**
	 * 所有的Email发送模板
	 */
	 private Map<String, String> eTemplate;

	public Map<String, String> geteTemplate() {
		return eTemplate;
	}

	public void seteTemplate(Map<String, String> eTemplate) {
		this.eTemplate = eTemplate;
	}
	 
	 
	
}
