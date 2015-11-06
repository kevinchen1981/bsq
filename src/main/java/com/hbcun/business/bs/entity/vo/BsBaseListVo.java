package com.hbcun.business.bs.entity.vo;

import java.util.Date;

public abstract class BsBaseListVo extends BaseVo {

	private static final long serialVersionUID = 1L;

	private String orderCode;

	private String orderId;

	private String packageId;

	private String status;

	private String tmsOrderCode;

	private boolean isEmsNoGot;

	private boolean isGoodsDeclareToEp;

	private boolean isLogisticsSentToEms;

	private boolean isOrderSentToEp;

	private boolean isPayinfoSent;

	private Date createTime;

	private Date updateTime;

	private String operator;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isGoodsDeclareToEp() {
		return isGoodsDeclareToEp;
	}

	public void setGoodsDeclareToEp(boolean isGoodsDeclareToEp) {
		this.isGoodsDeclareToEp = isGoodsDeclareToEp;
	}

	public boolean isLogisticsSentToEms() {
		return isLogisticsSentToEms;
	}

	public void setLogisticsSentToEms(boolean isLogisticsSentToEms) {
		this.isLogisticsSentToEms = isLogisticsSentToEms;
	}

	public boolean isOrderSentToEp() {
		return isOrderSentToEp;
	}

	public void setOrderSentToEp(boolean isOrderSentToEp) {
		this.isOrderSentToEp = isOrderSentToEp;
	}

	public boolean isPayinfoSent() {
		return isPayinfoSent;
	}

	public void setPayinfoSent(boolean isPayinfoSent) {
		this.isPayinfoSent = isPayinfoSent;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTmsOrderCode() {
		return tmsOrderCode;
	}

	public void setTmsOrderCode(String tmsOrderCode) {
		this.tmsOrderCode = tmsOrderCode;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isEmsNoGot() {
		return isEmsNoGot;
	}

	public void setEmsNoGot(boolean isEmsNoGot) {
		this.isEmsNoGot = isEmsNoGot;
	}

}
