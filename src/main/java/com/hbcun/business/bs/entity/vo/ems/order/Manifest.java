package com.hbcun.business.bs.entity.vo.ems.order;

import java.io.Serializable;
import java.util.List;

import com.hbcun.business.bs.entity.vo.BaseVo;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Manifest")
public class Manifest extends BaseVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@XStreamAlias("Head")
	private Head head;

	@XStreamAlias("Declaration")
	private Declaration declaration;

	public Manifest() {
	}

	/**
	 * @return the head
	 */
	public Head getHead() {
		return head;
	}

	/**
	 * @param head
	 *            the head to set
	 */
	public void setHead(Head head) {
		this.head = head;
	}

	/**
	 * @return the declaration
	 */
	public Declaration getDeclaration() {
		return declaration;
	}

	public void setDeclaration(Declaration declaration) {
		this.declaration = declaration;
	}

	public static class Head implements Serializable {

		private static final long serialVersionUID = 1L;
		@XStreamAlias("MessageID")
		private String messageID;

		@XStreamAlias("MessageType")
		private String messageType = "200200";

		@XStreamAlias("USERNAME")
		private String username = "admin";

		@XStreamAlias("Signature")
		private String signature;

		@XStreamAlias("SenderID")
		private String senderID;

		@XStreamAlias("SendTime")
		private String sendTime;

		@XStreamAlias("Version")
		private String version = "1.0";

		public Head() {
		}

		public Head(String messageID, String messageType, String username, String signature, String senderID,
				String sendTime) {
			this.messageID = messageID;
			this.messageType = messageType;
			this.username = username;
			this.signature = signature;
			this.senderID = senderID;
			this.sendTime = sendTime;
		}

		public String getMessageID() {
			return messageID;
		}

		public void setMessageID(String messageID) {
			this.messageID = messageID;
		}

		public String getMessageType() {
			return messageType;
		}

		public void setMessageType(String messageType) {
			this.messageType = messageType;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getSignature() {
			return signature;
		}

		public void setSignature(String signature) {
			this.signature = signature;
		}

		public String getSenderID() {
			return senderID;
		}

		public void setSenderID(String senderID) {
			this.senderID = senderID;
		}

		public String getSendTime() {
			return sendTime;
		}

		public void setSendTime(String sendTime) {
			this.sendTime = sendTime;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

	}

	public static class Declaration implements Serializable {

		private static final long serialVersionUID = 1L;
		@XStreamAlias("EWayList")
		private List<EWay> ewList;

		public Declaration() {
		}

		public List<EWay> getEwList() {
			return ewList;
		}

		public void setEwList(List<EWay> ewList) {
			this.ewList = ewList;
		}

	}

	@XStreamAlias("EWay")
	public static class EWay implements Serializable{

		private static final long serialVersionUID = 1L;

		private String waybill;

		private int packNo;
		
		private double grossWeigt;
		
		private String goodsName;
		
		private String sendArea;
		
		private String consigneeArea;
		
		private String consignee;
		
		private String consigneeAddress;
		
		private String consigneeTel;
		
		private String zipCode;
		
		private String customsCode;
		
		private String worth;
		
		private String importDateStr;
		
		private String currCode;
		
		private String modifyMark;
		
		private String businessType;
		
		public EWay() {
		}

		public EWay(String waybill, int packNo, double grossWeigt,
				String goodsName, String sendArea, String consigneeArea,
				String consignee, String consigneeAddress, String consigneeTel,
				String zipCode, String customsCode, String worth,
				String importDateStr, String currCode, String modifyMark,
				String businessType) {
			this.waybill = waybill;
			this.packNo = packNo;
			this.grossWeigt = grossWeigt;
			this.goodsName = goodsName;
			this.sendArea = sendArea;
			this.consigneeArea = consigneeArea;
			this.consignee = consignee;
			this.consigneeAddress = consigneeAddress;
			this.consigneeTel = consigneeTel;
			this.zipCode = zipCode;
			this.customsCode = customsCode;
			this.worth = worth;
			this.importDateStr = importDateStr;
			this.currCode = currCode;
			this.modifyMark = modifyMark;
			this.businessType = businessType;
		}

		/**
		 * @return the waybill
		 */
		public String getWaybill() {
			return waybill;
		}

		/**
		 * @param waybill the waybill to set
		 */
		public void setWaybill(String waybill) {
			this.waybill = waybill;
		}

		/**
		 * @return the packNo
		 */
		public int getPackNo() {
			return packNo;
		}

		/**
		 * @param packNo the packNo to set
		 */
		public void setPackNo(int packNo) {
			this.packNo = packNo;
		}

		/**
		 * @return the grossWeigt
		 */
		public double getGrossWeigt() {
			return grossWeigt;
		}

		/**
		 * @param grossWeigt the grossWeigt to set
		 */
		public void setGrossWeigt(double grossWeigt) {
			this.grossWeigt = grossWeigt;
		}

		/**
		 * @return the goodsName
		 */
		public String getGoodsName() {
			return goodsName;
		}

		/**
		 * @param goodsName the goodsName to set
		 */
		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		/**
		 * @return the sendArea
		 */
		public String getSendArea() {
			return sendArea;
		}

		/**
		 * @param sendArea the sendArea to set
		 */
		public void setSendArea(String sendArea) {
			this.sendArea = sendArea;
		}

		/**
		 * @return the consigneeArea
		 */
		public String getConsigneeArea() {
			return consigneeArea;
		}

		/**
		 * @param consigneeArea the consigneeArea to set
		 */
		public void setConsigneeArea(String consigneeArea) {
			this.consigneeArea = consigneeArea;
		}

		/**
		 * @return the consignee
		 */
		public String getConsignee() {
			return consignee;
		}

		/**
		 * @param consignee the consignee to set
		 */
		public void setConsignee(String consignee) {
			this.consignee = consignee;
		}

		/**
		 * @return the consigneeAddress
		 */
		public String getConsigneeAddress() {
			return consigneeAddress;
		}

		/**
		 * @param consigneeAddress the consigneeAddress to set
		 */
		public void setConsigneeAddress(String consigneeAddress) {
			this.consigneeAddress = consigneeAddress;
		}

		/**
		 * @return the consigneeTel
		 */
		public String getConsigneeTel() {
			return consigneeTel;
		}

		/**
		 * @param consigneeTel the consigneeTel to set
		 */
		public void setConsigneeTel(String consigneeTel) {
			this.consigneeTel = consigneeTel;
		}

		/**
		 * @return the zipCode
		 */
		public String getZipCode() {
			return zipCode;
		}

		/**
		 * @param zipCode the zipCode to set
		 */
		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
		}

		/**
		 * @return the customsCode
		 */
		public String getCustomsCode() {
			return customsCode;
		}

		/**
		 * @param customsCode the customsCode to set
		 */
		public void setCustomsCode(String customsCode) {
			this.customsCode = customsCode;
		}

		/**
		 * @return the worth
		 */
		public String getWorth() {
			return worth;
		}

		/**
		 * @param worth the worth to set
		 */
		public void setWorth(String worth) {
			this.worth = worth;
		}

		/**
		 * @return the importDateStr
		 */
		public String getImportDateStr() {
			return importDateStr;
		}

		/**
		 * @param importDateStr the importDateStr to set
		 */
		public void setImportDateStr(String importDateStr) {
			this.importDateStr = importDateStr;
		}

		/**
		 * @return the currCode
		 */
		public String getCurrCode() {
			return currCode;
		}

		/**
		 * @param currCode the currCode to set
		 */
		public void setCurrCode(String currCode) {
			this.currCode = currCode;
		}

		/**
		 * @return the modifyMark
		 */
		public String getModifyMark() {
			return modifyMark;
		}

		/**
		 * @param modifyMark the modifyMark to set
		 */
		public void setModifyMark(String modifyMark) {
			this.modifyMark = modifyMark;
		}

		/**
		 * @return the businessType
		 */
		public String getBusinessType() {
			return businessType;
		}

		/**
		 * @param businessType the businessType to set
		 */
		public void setBusinessType(String businessType) {
			this.businessType = businessType;
		}
			
	}

}
