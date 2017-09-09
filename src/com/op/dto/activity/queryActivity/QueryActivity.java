package com.op.dto.activity.queryActivity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.op.entity.activity.ActiveScenic;
import com.op.entity.activity.ActiveTraffic;
import com.op.entity.activity.ActiveTypes;
import com.op.entity.activity.cost.ActiveCorrelationCost;

public class QueryActivity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// 活动标题
	private String title;
	// 活动ID
	private int id;
	// 活动开始时间
	private Date activityTime;
	// 活动开始时间("yyyy-mm-dd")
	private String activityTimeStr;
	// 活动结束时间
	private Date endTime;
	// 活动结束时间
	private String endTimeStr;
	//活动出发地
	private String a_start_location;
	//活动目的地
	private String a_end_location;
	//联系电话
	private String a_phone;	
	//活动费用
	private double price;
	//预约费
	private double a_reserve_price;
	//需要人数
	private int needUserNum;
	//已报名人数
	private int alreadyInNum;
	//已确定人数
	private int confirmUserNum;
	//报名截止时间
	private Date a_enroll_end_time;
	//集合时间
	private Date a_gather_time;
	//集合时间
	private String gatherTimeStr;
	//集合地点
	private String a_gather_location;
	//保险ID
	private String in_id;
	//是否强制保险（1：是；2：否）
	private String isinsurance;
	//支付方式（0：免费；1：平台交易；2：当面交易）
	private int paytype;
	// 活动状态（1：草稿；2：发布待审核；3：审核中；4：审核不通过；5：审核成功（发布）；15：活动结束）
	private	int state;
	//创建用户
	private String createUser;
	
	//活动适合人群
	private String sc_name;
	//活动难度等级
	private String adt_description;
	
	/*				外键表数据				 */
	
	//活动类型
	private List<ActiveTypes> activeTypes;
	//活动景点
	private List<ActiveScenic> activeScenic;
	//交通方式
	private List<ActiveTraffic> activeTraffic;
	//包含费用
	private List<ActiveCorrelationCost> contain;
	//可选费用
	private List<ActiveCorrelationCost> optional;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

	public String getActivityTimeStr() {
		return activityTimeStr;
	}

	public void setActivityTimeStr(String activityTimeStr) {
		this.activityTimeStr = activityTimeStr;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getA_start_location() {
		return a_start_location;
	}

	public void setA_start_location(String a_start_location) {
		this.a_start_location = a_start_location;
	}

	public String getA_end_location() {
		return a_end_location;
	}

	public void setA_end_location(String a_end_location) {
		this.a_end_location = a_end_location;
	}

	public String getA_phone() {
		return a_phone;
	}

	public void setA_phone(String a_phone) {
		this.a_phone = a_phone;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getA_reserve_price() {
		return a_reserve_price;
	}
	
	public void setA_reserve_price(double a_reserve_price) {
		this.a_reserve_price = a_reserve_price;
	}

	public int getNeedUserNum() {
		return needUserNum;
	}
	
	public void setNeedUserNum(int needUserNum) {
		this.needUserNum = needUserNum;
	}

	public int getAlreadyInNum() {
		return alreadyInNum;
	}

	public void setAlreadyInNum(int alreadyInNum) {
		this.alreadyInNum = alreadyInNum;
	}

	public int getConfirmUserNum() {
		return confirmUserNum;
	}

	public void setConfirmUserNum(int confirmUserNum) {
		this.confirmUserNum = confirmUserNum;
	}

	public Date getA_enroll_end_time() {
		return a_enroll_end_time;
	}

	public void setA_enroll_end_time(Date a_enroll_end_time) {
		this.a_enroll_end_time = a_enroll_end_time;
	}

	public String getSc_name() {
		return sc_name;
	}

	public void setSc_name(String sc_name) {
		this.sc_name = sc_name;
	}

	public String getAdt_description() {
		return adt_description;
	}

	public void setAdt_description(String adt_description) {
		this.adt_description = adt_description;
	}

	public List<ActiveTypes> getActiveTypes() {
		return activeTypes;
	}

	public void setActiveTypes(List<ActiveTypes> activeTypes) {
		this.activeTypes = activeTypes;
	}

	public List<ActiveScenic> getActiveScenic() {
		return activeScenic;
	}

	public void setActiveScenic(List<ActiveScenic> activeScenic) {
		this.activeScenic = activeScenic;
	}

	public Date getA_gather_time() {
		return a_gather_time;
	}

	public void setA_gather_time(Date a_gather_time) {
		this.a_gather_time = a_gather_time;
	}

	public String getGatherTimeStr() {
		return gatherTimeStr;
	}

	public void setGatherTimeStr(String gatherTimeStr) {
		this.gatherTimeStr = gatherTimeStr;
	}

	public String getA_gather_location() {
		return a_gather_location;
	}

	public void setA_gather_location(String a_gather_location) {
		this.a_gather_location = a_gather_location;
	}

	public List<ActiveTraffic> getActiveTraffic() {
		return activeTraffic;
	}

	public void setActiveTraffic(List<ActiveTraffic> activeTraffic) {
		this.activeTraffic = activeTraffic;
	}

	public List<ActiveCorrelationCost> getContain() {
		return contain;
	}

	public void setContain(List<ActiveCorrelationCost> contain) {
		this.contain = contain;
	}

	public List<ActiveCorrelationCost> getOptional() {
		return optional;
	}

	public void setOptional(List<ActiveCorrelationCost> optional) {
		this.optional = optional;
	}

	public String getIsinsurance() {
		return isinsurance;
	}

	public void setIsinsurance(String isinsurance) {
		this.isinsurance = isinsurance;
	}

	public int getPaytype() {
		return paytype;
	}

	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getIn_id() {
		return in_id;
	}

	public void setIn_id(String in_id) {
		this.in_id = in_id;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
}
