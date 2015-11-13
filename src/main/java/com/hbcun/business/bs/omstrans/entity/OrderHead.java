package com.hbcun.business.bs.omstrans.entity;

import java.io.Serializable;

import com.hbcun.business.bs.entity.vo.BaseVo;

public class OrderHead extends BaseVo {
	public OrderHead(){
		
	}
	/**备案名称**/
	private String companyName;
	/**备案编码**/
	private String companyCode;
	/**进出口标志 I进口E出口**/
	private String ieFlag;
	/**支付类型 01 银行卡支付 02余额支付 03 其他**/
	private String payType;
	/**支付企业备案编码**/
	private String payCompanyCode;
	private String payNumber;
	private double orderTotalAmount;
	private double orderGoodAmount;
	private String orderNo;
	private double orderTaxAmount;
	private double feeAmount;
	private String eCommerceCode;
	private String eCommerceName;
	private String tradeTime;
	private String currCode;
	private double totalAmount;
	private String consigneeEmail;
	private String consigneeTel;
	private String consignee;
	private String consigneeAddress;
	private String consigneeProvince;
	private String consigneeCity;
	private String consigneeCountry;
	private Integer totalCount;
	private String postMode;
	private String senderCountry;
	private String senderName;
	private String purchaserId;
	private String logisCompanyName;
	private String logisCompanCode;
	private String zipCode;
	private String note;
	private String wayBills;
	private String rate;
	private Integer bonded;
	private String billNoType;
	private String warehouseId;
	private String i18nOrderNo;
	private String i18nWaybillNo;
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getIeFlag() {
		return ieFlag;
	}
	public void setIeFlag(String ieFlag) {
		this.ieFlag = ieFlag;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayCompanyCode() {
		return payCompanyCode;
	}
	public void setPayCompanyCode(String payCompanyCode) {
		this.payCompanyCode = payCompanyCode;
	}
	public String getPayNumber() {
		return payNumber;
	}
	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}
	public double getOrderTotalAmount() {
		return orderTotalAmount;
	}
	public void setOrderTotalAmount(double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}
	public double getOrderGoodAmount() {
		return orderGoodAmount;
	}
	public void setOrderGoodAmount(double orderGoodAmount) {
		this.orderGoodAmount = orderGoodAmount;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public double getOrderTaxAmount() {
		return orderTaxAmount;
	}
	public void setOrderTaxAmount(double orderTaxAmount) {
		this.orderTaxAmount = orderTaxAmount;
	}
	public double getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}
	public String geteCommerceCode() {
		return eCommerceCode;
	}
	public void seteCommerceCode(String eCommerceCode) {
		this.eCommerceCode = eCommerceCode;
	}
	public String geteCommerceName() {
		return eCommerceName;
	}
	public void seteCommerceName(String eCommerceName) {
		this.eCommerceName = eCommerceName;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getCurrCode() {
		return currCode;
	}
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getConsigneeEmail() {
		return consigneeEmail;
	}
	public void setConsigneeEmail(String consigneeEmail) {
		this.consigneeEmail = consigneeEmail;
	}
	public String getConsigneeTel() {
		return consigneeTel;
	}
	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getConsigneeAddress() {
		return consigneeAddress;
	}
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}
	public String getConsigneeProvince() {
		return consigneeProvince;
	}
	public void setConsigneeProvince(String consigneeProvince) {
		this.consigneeProvince = consigneeProvince;
	}
	public String getConsigneeCity() {
		return consigneeCity;
	}
	public void setConsigneeCity(String consigneeCity) {
		this.consigneeCity = consigneeCity;
	}
	public String getConsigneeCountry() {
		return consigneeCountry;
	}
	public void setConsigneeCountry(String consigneeCountry) {
		this.consigneeCountry = consigneeCountry;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public String getPostMode() {
		return postMode;
	}
	public void setPostMode(String postMode) {
		this.postMode = postMode;
	}
	public String getSenderCountry() {
		return senderCountry;
	}
	public void setSenderCountry(String senderCountry) {
		this.senderCountry = senderCountry;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getPurchaserId() {
		return purchaserId;
	}
	public void setPurchaserId(String purchaserId) {
		this.purchaserId = purchaserId;
	}
	public String getLogisCompanyName() {
		return logisCompanyName;
	}
	public void setLogisCompanyName(String logisCompanyName) {
		this.logisCompanyName = logisCompanyName;
	}
	public String getLogisCompanCode() {
		return logisCompanCode;
	}
	public void setLogisCompanCode(String logisCompanCode) {
		this.logisCompanCode = logisCompanCode;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getWayBills() {
		return wayBills;
	}
	public void setWayBills(String wayBills) {
		this.wayBills = wayBills;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public Integer getBonded() {
		return bonded;
	}
	public void setBonded(Integer bonded) {
		this.bonded = bonded;
	}
	public String getBillNoType() {
		return billNoType;
	}
	public void setBillNoType(String billNoType) {
		this.billNoType = billNoType;
	}
	public String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getI18nOrderNo() {
		return i18nOrderNo;
	}
	public void setI18nOrderNo(String i18nOrderNo) {
		this.i18nOrderNo = i18nOrderNo;
	}
	public String getI18nWaybillNo() {
		return i18nWaybillNo;
	}
	public void setI18nWaybillNo(String i18nWaybillNo) {
		this.i18nWaybillNo = i18nWaybillNo;
	}
}
