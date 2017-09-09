package com.op.spot.entity;
import java.io.Serializable;

/**
 * 门票信息
 */
public class Ticketinformation  implements Serializable {
	 
	
	//columns START
    /**
     * id       db_column: id 
     */ 	
	private Integer id;
    /**
     * 景点id       db_column: sid 
     */ 	
	private Integer sid;

    /**
     * 旺季价格       db_column: peakseasonfare 
     */ 	
	private Double peakseasonfare;
    /**
     * 淡季价格       db_column: offseasorates 
     */ 	
	private Double offseasorates;
    /**
     * 注意       db_column: becareful 
     */ 	
	private String becareful;
    /**
     * 优惠信息       db_column: Preferentialinformation 
     */ 	
	private String preferentialinformation;
	//columns END

	/**
	 * 景点名称
	 * @param value
	 */
	private String name;
	
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setSid(Integer value) {
		this.sid = value;
	}
	
	public Integer getSid() {
		return this.sid;
	}
	public void setPeakseasonfare(Double value) {
		this.peakseasonfare = value;
	}
	
	public Double getPeakseasonfare() {
		return this.peakseasonfare;
	}
	public void setOffseasorates(Double value) {
		this.offseasorates = value;
	}
	
	public Double getOffseasorates() {
		return this.offseasorates;
	}
	public void setBecareful(String value) {
		this.becareful = value;
	}
	
	public String getBecareful() {
		return this.becareful;
	}
	public void setPreferentialinformation(String value) {
		this.preferentialinformation = value;
	}
	
	public String getPreferentialinformation() {
		return this.preferentialinformation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
 
