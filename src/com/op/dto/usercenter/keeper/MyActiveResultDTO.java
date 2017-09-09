package com.op.dto.usercenter.keeper;

import java.util.List;

/**
 * =============================================================
 * 项目名：outdoorPortal 类描述：个人中心→我的管家→我的活动 创建人：Yan 创建时间： 2016-3-31 modification
 * list： =============================================================
 */

public class MyActiveResultDTO {

	// 去过的活动/结束的活动 数量
	Integer stop;
	
	// 正在进行的活动数量
	Integer ing;
	
	// 待进行的活动数量
	Integer wait;
	
	// 活动信息
	List<MyActiveInfoDTO> infos;

	public Integer getStop() {
		return stop;
	}

	public void setStop(Integer stop) {
		this.stop = stop;
	}

	public Integer getIng() {
		return ing;
	}

	public void setIng(Integer ing) {
		this.ing = ing;
	}

	public Integer getWait() {
		return wait;
	}

	public void setWait(Integer wait) {
		this.wait = wait;
	}

	public List<MyActiveInfoDTO> getInfos() {
		return infos;
	}

	public void setInfos(List<MyActiveInfoDTO> infos) {
		this.infos = infos;
	}
	
	
	
}
