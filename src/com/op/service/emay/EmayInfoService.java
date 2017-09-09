package com.op.service.emay;

import java.util.List;

import org.springframework.stereotype.Service;

import com.op.entity.emay.EmayInfo;

@Service("emayInfoService")
public interface EmayInfoService {
	
	/**
	 * 方法描述：查找所有模板
	 * 返回类型：List<EmayInfo>
	 * @return
	 */
	public List<EmayInfo> emays() throws Exception;
	
	/**
	 * 方法描述：获取短信模板
	 * 返回类型：String
	 * @return
	 * @throws Exception
	 */
	public String getEmayTemplate(String type) throws Exception;


	/**
	 * 方法描述：初始化短信模板
	 * 返回类型：void
	 * @throws Exception
	 */
	String initEmailTemplate(String type) throws Exception;

}
