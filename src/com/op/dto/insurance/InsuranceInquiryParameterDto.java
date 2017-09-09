package com.op.dto.insurance;

import java.io.Serializable;

/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：InsuranceInquiryDto   
* 类描述：   查询保险详情参数DTO
* 创建人：Win Zhong   
* 创建时间：2016年3月23日 下午5:28:35   
* 修改人：Win Zhong   
* 修改时间：2016年3月23日 下午5:28:35   
* 修改备注：   
* @version    
*
 */
public class InsuranceInquiryParameterDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String productId;
	String planId;
	String deadlineId;
	String startDate;
	String endDate;
	String activityId;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getDeadlineId() {
		return deadlineId;
	}
	public void setDeadlineId(String deadlineId) {
		this.deadlineId = deadlineId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	

}
