package com.op.dto.insurance;

import java.io.Serializable;
import java.text.DecimalFormat;


/**
 * 
*    
* 项目名称：outdoorPortal   
* 类名称：InsurancePriceDTO   
* 类描述：   保险保障期限区间价格
* 创建人：Win Zhong   
* 创建时间：2016年3月16日 下午2:08:24   
* 修改人：Win Zhong   
* 修改时间：2016年3月16日 下午2:08:24   
* 修改备注：   
* @version    
*
 */
public class InsurancePriceDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//保险保障期限价格id
	private String id;
	private int minDeadline;
	private int maxDeadline;
	private String term;
	private int unit;
	private String planname;
	private double price;
	private int planId;
	private int productId;
	private String caseCode;
	private String startDate;
	private String endDate;
	
	
	private DecimalFormat df = new DecimalFormat("#.00");
 
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public void setMaxDeadline(int maxDeadline) {
		this.maxDeadline = maxDeadline;
	}

	public String getTerm() {
		if(maxDeadline == 0){
			term = ""+minDeadline+ (unit==1?"天":"年");
		}else{
			term = minDeadline+"-"+maxDeadline+ (unit==1?"天":"年");
		}
		return term;
	}


	public void setUnit(int unit) {
		this.unit = unit;
	}

	public String getPlanname() {
		return planname == null ?"":planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}


	public String getPrice() {
		return df.format(price);
	}

	public void setPrice(double price) {
		this.price = price;
	}
	public int getMinDeadline() {
		return minDeadline;
	}
	public void setMinDeadline(int minDeadline) {
		this.minDeadline = minDeadline;
	}
	public int getMaxDeadline() {
		return maxDeadline;
	}
	public int getUnit() {
		return unit;
	}
	public String getCaseCode() {
		return caseCode;
	}
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
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
	
}
