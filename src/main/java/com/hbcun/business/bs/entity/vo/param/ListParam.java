package com.hbcun.business.bs.entity.vo.param;

import java.io.Serializable;
/**
 * 保税列表入参
 * @author liuxy
 * @date 2015年9月30日上午11:22:37
 * @since 1.7
 */
public class ListParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderId;

	private String packageId;

	private String status;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
