package com.op.dto.activity.consultation;

public class ConsultationNumDTO {
	// 所有问题数量
	int allNum;
	
	// 待回复问题数量
	int waitNum;
	
	// 已回复数量
	int replyNum;

	public int getAllNum() {
		return allNum;
	}

	public void setAllNum(int allNum) {
		this.allNum = allNum;
	}

	public int getWaitNum() {
		return waitNum;
	}

	public void setWaitNum(int waitNum) {
		this.waitNum = waitNum;
	}

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	
	
}
