package com.op.entity.keyword;

import java.io.Serializable;
import java.util.Date;

public class Keyword implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8253435552145098646L;

	private String id;
	private String word;
	private int searchCount;
	private Date createTime;
}
