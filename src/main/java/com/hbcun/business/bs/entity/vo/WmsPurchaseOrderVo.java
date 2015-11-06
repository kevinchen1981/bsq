package com.hbcun.business.bs.entity.vo;

import java.io.Serializable;
import java.util.Date;

public class WmsPurchaseOrderVo implements Serializable {

	private String orderCode;
	
	private Date createTime;

	public String getOrderCode() {
		return orderCode;
	}
	
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
