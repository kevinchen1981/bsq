package com.hbcun.business.bs.entity.vo.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * 保税订单入参
 * 
 * @author liuxy
 * @date 2015年9月7日上午11:00:20
 * @since 1.7
 */
public class BsOrderParam implements Serializable {

	private static final long serialVersionUID = -8150592017417611847L;

	@NotNull(message = "仓库订单编码不能为空")
	private String orderCode;

	@NotNull(message = "订单号不能为空")
	private String orderId;

	@NotNull(message = "包裹不能为空")
	private String packageId;

	public BsOrderParam() {

	}

	public BsOrderParam(String orderCode, String orderId, String packageId) {
		super();
		this.orderCode = orderCode;
		this.orderId = orderId;
		this.packageId = packageId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

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

}
