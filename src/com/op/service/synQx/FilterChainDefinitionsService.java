package com.op.service.synQx;

import java.util.Map;

/**
 * 项目名：outdoorPortal
 * 类描述：动态更新资源配置顶层接口
 * 创建人：Yan
 * 创建时间： 2015-11-7 上午11:33:04
 * 最后修改时间：2015-11-7上午11:33:04
 */
public interface FilterChainDefinitionsService {
	public static final String PREMISSION_STRING = "perms[{0}]"; // 资源结构格式
	public static final String ROLE_STRING = "role[{0}]"; // 角色结构格式

	/** 初始化框架权限资源配置 */
	public  void intiPermission();

	/** 初始化第三方权限资源配置 */
	public  Map<String, String> initOtherPermission();  

	/** 重新加载框架权限资源配置 (强制线程同步) */
	public  void updatePermission(Map<String, String> map) throws Exception;
}
