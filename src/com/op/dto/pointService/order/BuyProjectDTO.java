package com.op.dto.pointService.order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.op.dto.pointService.show.BusinessTimeDTO;
import com.op.entity.pointService.PointServiceContact;

public class BuyProjectDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 项目id
	private int psp_id;
	// 项目名称
	private String psp_item_name;
	// 项目原价
	private double psp_item_price;
	// 项目打折价
	private double psp_discount_price;
	// 有效期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date psp_validity_date_start;
	// 有效期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date psp_validity_date_end;
	// 不可用日期
	private String psp_not_time;
	
	// id
	private int ps_id;
	// 地点中文名
	private String ps_zh_name;
	// 地点英文名
	private String ps_en_name;
	// 省id
	private String ps_province_id;
	// 市id
	private String ps_city_id;
	// 区/县id
	private String ps_countys_id;
	// 位置
	private String ps_address;
	
	// 商家联系方式
	private List<PointServiceContact> pointServiceContactList;
	
	// 营业时间
	private List<BusinessTimeDTO> businessTimeDTOList;
	
	//购买数量
	private String buyNum;

	
	public int getPsp_id() {
		return psp_id;
	}

	public void setPsp_id(int psp_id) {
		this.psp_id = psp_id;
	}

	public String getPsp_item_name() {
		return psp_item_name;
	}

	public void setPsp_item_name(String psp_item_name) {
		this.psp_item_name = psp_item_name;
	}

	public double getPsp_item_price() {
		return psp_item_price;
	}

	public void setPsp_item_price(double psp_item_price) {
		this.psp_item_price = psp_item_price;
	}

	public double getPsp_discount_price() {
		return psp_discount_price;
	}

	public void setPsp_discount_price(double psp_discount_price) {
		this.psp_discount_price = psp_discount_price;
	}

	public Date getPsp_validity_date_start() {
		return psp_validity_date_start;
	}

	public void setPsp_validity_date_start(Date psp_validity_date_start) {
		this.psp_validity_date_start = psp_validity_date_start;
	}

	public Date getPsp_validity_date_end() {
		return psp_validity_date_end;
	}

	public void setPsp_validity_date_end(Date psp_validity_date_end) {
		this.psp_validity_date_end = psp_validity_date_end;
	}

	public String getPsp_not_time() {
		return psp_not_time;
	}

	public void setPsp_not_time(String psp_not_time) {
		this.psp_not_time = psp_not_time;
	}

	public int getPs_id() {
		return ps_id;
	}

	public void setPs_id(int ps_id) {
		this.ps_id = ps_id;
	}

	public String getPs_zh_name() {
		return ps_zh_name;
	}

	public void setPs_zh_name(String ps_zh_name) {
		this.ps_zh_name = ps_zh_name;
	}

	public String getPs_en_name() {
		return ps_en_name;
	}

	public void setPs_en_name(String ps_en_name) {
		this.ps_en_name = ps_en_name;
	}

	public String getPs_province_id() {
		return ps_province_id;
	}

	public void setPs_province_id(String ps_province_id) {
		this.ps_province_id = ps_province_id;
	}

	public String getPs_city_id() {
		return ps_city_id;
	}

	public void setPs_city_id(String ps_city_id) {
		this.ps_city_id = ps_city_id;
	}

	public String getPs_countys_id() {
		return ps_countys_id;
	}

	public void setPs_countys_id(String ps_countys_id) {
		this.ps_countys_id = ps_countys_id;
	}

	public String getPs_address() {
		return ps_address;
	}

	public void setPs_address(String ps_address) {
		this.ps_address = ps_address;
	}

	public List<PointServiceContact> getPointServiceContactList() {
		return pointServiceContactList;
	}

	public void setPointServiceContactList(
			List<PointServiceContact> pointServiceContactList) {
		this.pointServiceContactList = pointServiceContactList;
	}

	public List<BusinessTimeDTO> getBusinessTimeDTOList() {
		return businessTimeDTOList;
	}

	public void setBusinessTimeDTOList(List<BusinessTimeDTO> businessTimeDTOList) {
		this.businessTimeDTOList = businessTimeDTOList;
	}

	public String getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(String buyNum) {
		this.buyNum = buyNum;
	}
	
	
}
