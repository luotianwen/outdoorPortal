package com.op.entity.activity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.op.dto.activity.insurances.ActivityInsuranceResultDTO;
import com.op.entity.activity.cost.ActiveCorrelationCost;
import com.op.entity.activity.equipment.ActiveEquipment;
import com.op.entity.lines.ActiveLines;
import com.op.entity.users.Users;
import com.op.entity.zd.ActiveChildrenAge;
import com.op.util.ZdGetValue;

/**
 * =============================================================
 * 项目名：outdoorPortal 类描述：活动发布信息表(activity)实体类 创建人：Yan 创建时间： 2016-3-2
 * modification list：
 * =============================================================
 */
public class Activity implements Serializable {

	/**
	 * 
	 */
	static final long serialVersionUID = 917618839301224362L;
	// 频道ID
	int ch_id;
	// 活动类型ID(保留)
	int cl_id;
	// 活动标题
	String title;
	// 活动起点省
	String province;
	// 活动起点市
	String city;
	// 活动起点区
	String district;
	// 活动详细地址
	String address;
	// 活动开始时间
	Date activityTime;
	// 开始时间周几
	String activityWeek;
	// 活动ID
	int id;
	// 活动结束时间
	Date endTime;
	// 活动是否收费（1：是；2：否）
	int isCharge;
	// 活动收费价格(元)
	double price;
	// 保险ID
	String in_id;
	// 是否强制参加保险(1：是；2：否)
	int isInsurance;
	// 活动起点经纬度坐标（保存实例： (纬度,经度））
	String coordinates;
	// 活动地点经度（保留字段）
	String longitude;
	// 活动地点纬度（保留字段）
	String latitude;
	// 活动地点海拔（保留字段）
	String altitudes;
	// 活动终点经纬度坐标（保存实例： (纬度,经度））
	String l_coordinates;
	// 活动终点省
	String l_province;
	// 活动终点市
	String l_city;
	// 活动终点区
	String l_district;
	// 适合人群ID
	int sc_id;
	// 适合人群name
	String sc_name;
	// 活动时长 单位小时
	String duration;
	// 是否绘制线路（1：是；2：否）
	int isDrawingLine;
	// 线路ID
	String l_id;
	// 支付和退款说明
	String refundCondition;
	// 活动状态（1：草稿；2：发布待审核；3：审核中；4：审核不通过；5：审核成功（发布）；15：活动结束）
	int state;
	// 活动状态（1：草稿；2：发布待审核；3：审核中；4：审核不通过；5：审核成功（发布））
	private String stateVal;
	// 活动发布时间
	Date publishesTime;
	// 活动最后更新时间
	Date lastUpdateTime;
	// 活动举办次数
	int eventCount;
	// 活动亮点
	String highlights;
	// 创建用户
	String createUser;
	// 需要人数
	int needUserNum;
	// 已报名人数
	int alreadyInNum;
	// 确认人数
	int confirmUserNum;
	// 注意事项(报名须知)
	String a_careful;
	// 活动费用明细(包含)
	String a_price_deatil_on;
	// 活动费用明细(不包含)
	String a_price_deatil_off;
	// 报名截止时间
	Date a_enroll_end_time;
	// 行程简介
	String a_schedule;
	// 活动难度等级
	String a_difficulty_type;
	// 活动周期(天)
	int a_date_length;
	// 适合儿童年龄段（前提：适应人群为【儿童】或【儿童与家庭】）
	int a_children_age;
	// 审核人
	String auditor;
	// 审核时间
	Date auditTime;
	// 审核备注
	String auditNotes;
	// 活动发布者类型（1：个人领队；2：俱乐部）
	int a_active_add_user_type;
	// 活动预付款
	int a_reserve_price;
	// 咨询电话
	Long a_phone;
	// 活动主图
	String a_active_img;
	// 出发地
	String a_start_location;
	// 目的地
	String a_end_location;
	// 集合时间
	Date a_gather_time;
	// 集合地点
	String a_gather_location;
	// 活动描述
	String details;
	// 路线特色描述（原：活动特色）
	String characteristic;
	// 行程看点描述
	String tripWatchFocus;
	// 装备要求
	String equipment;
	//活动模板（0：否；1：是）
	String template;
	//关闭活动(0：正常；1：申请关闭；2：审核通过；3：审核失败)
	String a_close;
	
	
	/*---------------------		关联--------------------------*/

	// 活动类型
	List<com.op.entity.activity.ActiveTypes> activeTypes;
	// 活动线路
	List<com.op.entity.lines.SubsectionLines> lines;
	// 咨询问答集合
	List<ActiveConsultation> acs;
	// 领队身份信息(保留)
	// User_Check uc;
	
	// 费用包括
	List<ActiveCorrelationCost> costs;

	// 活动评价信息
	// List<ActiveComments> evaluate;

	// 评价数量
	int commentNums;

	// 咨询数量
	int consultationNum;

	Users user;
	
	// 活动总体线路
	ActiveLines al;
	
	// 儿童年龄信息
	ActiveChildrenAge aca;
	
	// 活动景点
	List<ActiveScenic> as;
	
	//活动对应的交通方式
	List<ActiveTraffic> at;
	
	// 装备需求
	List<ActiveEquipment> aes;

	
	// 保险信息
	ActivityInsuranceResultDTO insurance;
	/*-----------------------------------------------	get	&&	set	----------------------------------------------*/


	public int getCh_id() {
		return ch_id;
	}


	public ActivityInsuranceResultDTO getInsurance() {
		return insurance;
	}


	public void setInsurance(ActivityInsuranceResultDTO insurance) {
		this.insurance = insurance;
	}


	public List<ActiveEquipment> getAes() {
		return aes;
	}


	public void setAes(List<ActiveEquipment> aes) {
		this.aes = aes;
	}


	public List<ActiveScenic> getAs() {
		return as;
	}


	public void setAs(List<ActiveScenic> as) {
		this.as = as;
	}


	public List<ActiveTraffic> getAt() {
		return at;
	}


	public void setAt(List<ActiveTraffic> at) {
		this.at = at;
	}


	public ActiveChildrenAge getAca() {
		return aca;
	}

	public void setAca(ActiveChildrenAge aca) {
		this.aca = aca;
	}

	public String getSc_name() {
		return sc_name;
	}

	public void setSc_name(String sc_name) {
		this.sc_name = sc_name;
	}

	public ActiveLines getAl() {
		return al;
	}

	public void setAl(ActiveLines al) {
		this.al = al;
	}

	public List<ActiveCorrelationCost> getCosts() {
		return costs;
	}

	public void setCosts(List<ActiveCorrelationCost> costs) {
		this.costs = costs;
	}

	public void setCh_id(int ch_id) {
		this.ch_id = ch_id;
	}

	public int getCl_id() {
		return cl_id;
	}

	public void setCl_id(int cl_id) {
		this.cl_id = cl_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

	public String getActivityWeek() {
		return activityWeek;
	}

	public void setActivityWeek(String activityWeek) {
		this.activityWeek = activityWeek;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getIsCharge() {
		return isCharge;
	}

	public void setIsCharge(int isCharge) {
		this.isCharge = isCharge;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getIn_id() {
		return in_id;
	}

	public void setIn_id(String in_id) {
		this.in_id = in_id;
	}

	public int getIsInsurance() {
		return isInsurance;
	}

	public void setIsInsurance(int isInsurance) {
		this.isInsurance = isInsurance;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getAltitudes() {
		return altitudes;
	}

	public void setAltitudes(String altitudes) {
		this.altitudes = altitudes;
	}

	public String getL_coordinates() {
		return l_coordinates;
	}

	public void setL_coordinates(String l_coordinates) {
		this.l_coordinates = l_coordinates;
	}

	public String getL_province() {
		return l_province;
	}

	public void setL_province(String l_province) {
		this.l_province = l_province;
	}

	public String getL_city() {
		return l_city;
	}

	public void setL_city(String l_city) {
		this.l_city = l_city;
	}

	public String getL_district() {
		return l_district;
	}

	public void setL_district(String l_district) {
		this.l_district = l_district;
	}

	public int getSc_id() {
		return sc_id;
	}

	public void setSc_id(int sc_id) {
		this.sc_id = sc_id;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getIsDrawingLine() {
		return isDrawingLine;
	}

	public void setIsDrawingLine(int isDrawingLine) {
		this.isDrawingLine = isDrawingLine;
	}

	public String getL_id() {
		return l_id;
	}

	public void setL_id(String l_id) {
		this.l_id = l_id;
	}

	public String getRefundCondition() {
		return refundCondition;
	}

	public void setRefundCondition(String refundCondition) {
		this.refundCondition = refundCondition;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.setStateVal(ZdGetValue.getTableColumnKeyVal("ACTIVITY", "STATE", state+""));
		this.state = state;
	}

	public String getStateVal() {
		return stateVal;
	}

	public void setStateVal(String stateVal) {
		this.stateVal = stateVal;
	}

	public Date getPublishesTime() {
		return publishesTime;
	}

	public void setPublishesTime(Date publishesTime) {
		this.publishesTime = publishesTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getEventCount() {
		return eventCount;
	}

	public void setEventCount(int eventCount) {
		this.eventCount = eventCount;
	}

	public String getHighlights() {
		return highlights;
	}

	public void setHighlights(String highlights) {
		this.highlights = highlights;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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

	public String getA_careful() {
		return a_careful;
	}

	public void setA_careful(String a_careful) {
		this.a_careful = a_careful;
	}

	public String getA_price_deatil_on() {
		return a_price_deatil_on;
	}

	public void setA_price_deatil_on(String a_price_deatil_on) {
		this.a_price_deatil_on = a_price_deatil_on;
	}

	public String getA_price_deatil_off() {
		return a_price_deatil_off;
	}

	public void setA_price_deatil_off(String a_price_deatil_off) {
		this.a_price_deatil_off = a_price_deatil_off;
	}

	public Date getA_enroll_end_time() {
		return a_enroll_end_time;
	}

	public void setA_enroll_end_time(Date a_enroll_end_time) {
		this.a_enroll_end_time = a_enroll_end_time;
	}

	public String getA_schedule() {
		return a_schedule;
	}

	public void setA_schedule(String a_schedule) {
		this.a_schedule = a_schedule;
	}

	public String getA_difficulty_type() {
		return a_difficulty_type;
	}

	public void setA_difficulty_type(String a_difficulty_type) {
		this.a_difficulty_type = a_difficulty_type;
	}

	public int getA_date_length() {
		return a_date_length;
	}

	public void setA_date_length(int a_date_length) {
		this.a_date_length = a_date_length;
	}

	public int getA_children_age() {
		return a_children_age;
	}

	public void setA_children_age(int a_children_age) {
		this.a_children_age = a_children_age;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditNotes() {
		return auditNotes;
	}

	public void setAuditNotes(String auditNotes) {
		this.auditNotes = auditNotes;
	}

	public int getA_active_add_user_type() {
		return a_active_add_user_type;
	}

	public void setA_active_add_user_type(int a_active_add_user_type) {
		this.a_active_add_user_type = a_active_add_user_type;
	}

	public int getA_reserve_price() {
		return a_reserve_price;
	}

	public void setA_reserve_price(int a_reserve_price) {
		this.a_reserve_price = a_reserve_price;
	}

	public Long getA_phone() {
		return a_phone;
	}

	public void setA_phone(Long a_phone) {
		this.a_phone = a_phone;
	}

	public String getA_active_img() {
		return a_active_img;
	}

	public void setA_active_img(String a_active_img) {
		this.a_active_img = a_active_img;
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

	public Date getA_gather_time() {
		return a_gather_time;
	}

	public void setA_gather_time(Date a_gather_time) {
		this.a_gather_time = a_gather_time;
	}

	public String getA_gather_location() {
		return a_gather_location;
	}

	public void setA_gather_location(String a_gather_location) {
		this.a_gather_location = a_gather_location;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}

	public String getTripWatchFocus() {
		return tripWatchFocus;
	}

	public void setTripWatchFocus(String tripWatchFocus) {
		this.tripWatchFocus = tripWatchFocus;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public List<com.op.entity.activity.ActiveTypes> getActiveTypes() {
		return activeTypes;
	}

	public void setActiveTypes(List<com.op.entity.activity.ActiveTypes> activeTypes) {
		this.activeTypes = activeTypes;
	}

	public List<com.op.entity.lines.SubsectionLines> getLines() {
		return lines;
	}

	public void setLines(List<com.op.entity.lines.SubsectionLines> lines) {
		this.lines = lines;
	}

	public List<ActiveConsultation> getAcs() {
		return acs;
	}

	public void setAcs(List<ActiveConsultation> acs) {
		this.acs = acs;
	}

	public int getCommentNums() {
		return commentNums;
	}

	public void setCommentNums(int commentNums) {
		this.commentNums = commentNums;
	}

	public int getConsultationNum() {
		return consultationNum;
	}

	public void setConsultationNum(int consultationNum) {
		this.consultationNum = consultationNum;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}


	public String getTemplate() {
		return template;
	}


	public void setTemplate(String template) {
		this.template = template;
	}
	
}
