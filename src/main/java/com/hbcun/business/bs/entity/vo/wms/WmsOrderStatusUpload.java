package com.hbcun.business.bs.entity.vo.wms;

import com.hbcun.business.bs.entity.vo.BaseVo;

/**
 * 3.6	发货订单状态回传接口
 * @author liuxy
 */
public class WmsOrderStatusUpload extends BaseVo {

	private static final long serialVersionUID = 1L;
	private String orderCode;
	private String sellerUserId;
	private String tmsServiceCode;
	private String tmsOrderCode;
	private Integer weight;
	private String status;
	private String operator;
	private String operatorContact;
	private String operateDate;
	private String content;
	private String remark;
	private String features;
	
	public WmsOrderStatusUpload() {
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getSellerUserId() {
		return sellerUserId;
	}

	public void setSellerUserId(String sellerUserId) {
		this.sellerUserId = sellerUserId;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorContact() {
		return operatorContact;
	}

	public void setOperatorContact(String operatorContact) {
		this.operatorContact = operatorContact;
	}

	public String getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getTmsServiceCode() {
		return tmsServiceCode;
	}

	public void setTmsServiceCode(String tmsServiceCode) {
		this.tmsServiceCode = tmsServiceCode;
	}

	public String getTmsOrderCode() {
		return tmsOrderCode;
	}

	public void setTmsOrderCode(String tmsOrderCode) {
		this.tmsOrderCode = tmsOrderCode;
	}
	
}
