package com.hbcun.business.bs.entity.vo;

import java.io.Serializable;
/**
 * 定时任务保税订单
 * @author liuxy
 * @date 2015年10月8日上午10:47:18
 * @since 1.7
 */
public class TaskBsOrderListVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderCode;

	private String status;

	private String userOrderId;

	private String userPackageId;

	private Byte inType;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserOrderId() {
		return userOrderId;
	}

	public void setUserOrderId(String userOrderId) {
		this.userOrderId = userOrderId;
	}

	public String getUserPackageId() {
		return userPackageId;
	}

	public void setUserPackageId(String userPackageId) {
		this.userPackageId = userPackageId;
	}

	public Byte getInType() {
		return inType;
	}

	public void setInType(Byte inType) {
		this.inType = inType;
	}

}
