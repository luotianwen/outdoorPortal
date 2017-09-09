package com.op.plugin.hession.impl;

import com.op.service.synQx.impl.LoadingDBChainDefinitions;

/**
 * 项目名：outdoorPortal
 * 类描述：缓存类对象
 * 创建人：Yan
 * 创建时间： 2016-1-27 下午1:57:09
 * 最后修改时间：2016-1-27下午1:57:09
 */
public class ClassRepository {

	// 系统加载缓存shiro对象
	private static LoadingDBChainDefinitions loadingDBChainDefinitions;

	public static LoadingDBChainDefinitions getLoadingDBChainDefinitions() {
		return loadingDBChainDefinitions;
	}

	public static void setLoadingDBChainDefinitions(
			LoadingDBChainDefinitions loadingDBChainDefinitions) {
		ClassRepository.loadingDBChainDefinitions = loadingDBChainDefinitions;
	}
	
	
}
