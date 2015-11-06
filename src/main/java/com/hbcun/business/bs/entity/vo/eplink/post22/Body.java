package com.hbcun.business.bs.entity.vo.eplink.post22;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hbcun.business.bs.entity.vo.BaseVo;
import com.hbcun.business.bs.entity.vo.eplink.JkfSign;
import com.hbcun.common.sys.util.DateUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 电商平台发送商品订单数据到通关服务平台
 * 
 * @author liuxy
 */
@XStreamAlias("body")
public class Body extends BaseVo {

	private static final long serialVersionUID = 1L;
//	private static XStream xstream;

	private List<OrderInfo> orderInfoList;

	public List<OrderInfo> getOrderInfoList() {
		return orderInfoList;
	}

	public void setOrderInfoList(List<OrderInfo> orderInfoList) {
		this.orderInfoList = orderInfoList;
	}

	/*protected XStream getXStream() {
		if (xstream == null) {
			xstream = new XStream();
			xstream.registerConverter(new DateConverter("yyyy-MM-dd HH:mm:ss", null, TimeZone.getTimeZone("GMT+8")));
			xstream.autodetectAnnotations(true);
		}
		return xstream;
	}*/

	@XStreamAlias("orderInfo")
	public static class OrderInfo implements Serializable {

		/****/
		private static final long serialVersionUID = 1L;
		private JkfSign jkfSign;
		private JkfOrderImportHead jkfOrderImportHead;
		private List<JkfOrderDetail> jkfOrderDetailList;
		private JkfGoodsPurchaser jkfGoodsPurchaser;

		public OrderInfo() {
		}

		public JkfSign getJkfSign() {
			return jkfSign;
		}

		public void setJkfSign(JkfSign jkfSign) {
			this.jkfSign = jkfSign;
		}

		public JkfOrderImportHead getJkfOrderImportHead() {
			return jkfOrderImportHead;
		}

		public void setJkfOrderImportHead(JkfOrderImportHead jkfOrderImportHead) {
			this.jkfOrderImportHead = jkfOrderImportHead;
		}

		public List<JkfOrderDetail> getJkfOrderDetailList() {
			return jkfOrderDetailList;
		}

		public void setJkfOrderDetailList(List<JkfOrderDetail> jkfOrderDetailList) {
			this.jkfOrderDetailList = jkfOrderDetailList;
		}

		public JkfGoodsPurchaser getJkfGoodsPurchaser() {
			return jkfGoodsPurchaser;
		}

		public void setJkfGoodsPurchaser(JkfGoodsPurchaser jkfGoodsPurchaser) {
			this.jkfGoodsPurchaser = jkfGoodsPurchaser;
		}

	}

	/**
	 * 订单表头信息
	 * @author liuxy
	 */
	@XStreamAlias("jkfOrderImportHead")
	public static class JkfOrderImportHead implements Serializable {

		private static final long serialVersionUID = 1L;
		/** 企业备案名称 **/
		private String companyName;
		/** 企业备案编号 **/
		private String companyCode;
		/** 进出口标志 **/
		private String ieFlag = "I";
		/** 支付类型 **/
		private String payType;
		/** 支付公司编码 **/
		private String payCompanyCode;
		/** 支付单号 **/
		private String payNumber;
		/** 订单总金额 **/
		private double orderTotalAmount;
		/** 订单货款 **/
		private double orderGoodsAmount;
		/** 订单编号 **/
		private String orderNo;
		/** 订单税款 **/
		private double orderTaxAmount = 0;
		/** 运费 **/
		private double feeAmount;
		/** 电商企业编码 **/
		private String eCommerceCode;
		/** 电商企业名称 **/
		private String eCommerceName;
		/** 成交时间,2014-02-18 15:58:11 **/
		private String tradeTime;
		/** 币制 **/
		private String currCode;
		/** 成交总价 **/
		private Double totalAmount;
		/** 收件人联系方式 **/
		private String consigneeTel;
		/** 收件人姓名 **/
		private String consignee;
		/** 收件人地址 **/
		private String consigneeAddress;
		/** 总件数,包裹中独立包装的物品总数，不考虑物品计量单位 **/
		private int totalCount;
		/** 发件人国别 **/
		private String senderCountry;
		/** senderName **/
		private String senderName;
		/** 购买人ID,购买人在电商平台的注册ID **/
		private String purchaserId;
		/** 物流企业名称 **/
		private String logisCompanyName;
		/** 物流企业编号 **/
		private String logisCompanyCode;

		private String userProcotol;
		
		public JkfOrderImportHead() {
		}
		
		public JkfOrderImportHead(String companyName, String companyCode,
				String payType, String payCompanyCode, String payNumber,
				double orderTotalAmount, double orderGoodsAmount, String orderNo,
				String eCommerceCode, String eCommerceName, String tradeTime,
				String consigneeTel, String consignee, String consigneeAddress,
				int totalCount, String senderCountry, String senderName,
				String purchaserId, String logisCompanyName, String logisCompanyCode) {
			this.companyName = companyName;
			this.companyCode = companyCode;
			this.payType = payType;
			this.payCompanyCode = payCompanyCode;
			this.payNumber = payNumber;
			this.orderTotalAmount = orderTotalAmount;
			this.orderGoodsAmount = orderGoodsAmount;
			this.orderNo = orderNo;
			this.eCommerceCode = eCommerceCode;
			this.eCommerceName = eCommerceName;
			this.tradeTime = tradeTime;
			this.consigneeTel = consigneeTel;
			this.consignee = consignee;
			this.consigneeAddress = consigneeAddress;
			this.totalCount = totalCount;
			this.senderCountry = senderCountry;
			this.senderName = senderName;
			this.purchaserId = purchaserId;
			this.logisCompanyName = logisCompanyName;
			this.logisCompanyCode = logisCompanyCode;
		}

		public String getUserProcotol() {
			return userProcotol;
		}
		
		public void setUserProcotol(String userProcotol) {
			this.userProcotol = userProcotol;
		}
		
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

		public double getOrderGoodsAmount() {
			return orderGoodsAmount;
		}

		public void setOrderGoodsAmount(double orderGoodsAmount) {
			this.orderGoodsAmount = orderGoodsAmount;
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
			if(tradeTime.indexOf(".") != -1){
				
				this.tradeTime = tradeTime.substring(0, tradeTime.indexOf("."));
			}else{
				
				this.tradeTime = tradeTime;
			}
		}

		public void setTradeTime(Date tradeTime) {
			
			this.tradeTime = DateUtil.formatDateTime(tradeTime);
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

		public int getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
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

		public String getLogisCompanyCode() {
			return logisCompanyCode;
		}

		public void setLogisCompanyCode(String logisCompanyCode) {
			this.logisCompanyCode = logisCompanyCode;
		}

		public String getCurrCode() {
			return currCode;
		}

		public void setCurrCode(String currCode) {
			this.currCode = currCode;
		}

		public Double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(Double totalAmount) {
			this.totalAmount = totalAmount;
		}

	}
	/**
	 * 订单表体信息
	 * @author liuxy
	 */
	@XStreamAlias("jkfOrderDetail")
	public static class JkfOrderDetail implements Serializable {

		private static final long serialVersionUID = 1L;
		/** 商品序号 **/
		private int goodsOrder;
		/** 物品名称 **/
		private String goodsName;
		/** 行邮税号 **/
		private String codeTs;
		/** 产销国 **/
		private String originCountry;
		/** 申报单价 **/
		private double unitPrice;
		/** 申报数量 **/
		private int goodsCount;
		/** 申报计量单位 **/
		private String goodsUnit;

		public JkfOrderDetail() {
		}

		public JkfOrderDetail(int goodsOrder, String goodsName, String codeTs,
				String originCountry, double unitPrice, int goodsCount, String goodsUnit) {
			this.goodsOrder = goodsOrder;
			this.goodsName = goodsName;
			this.codeTs = codeTs;
			this.originCountry = originCountry;
			this.unitPrice = unitPrice;
			this.goodsCount = goodsCount;
			this.goodsUnit = goodsUnit;
		}

		public int getGoodsOrder() {
			return goodsOrder;
		}

		public void setGoodsOrder(int goodsOrder) {
			this.goodsOrder = goodsOrder;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public String getCodeTs() {
			return codeTs;
		}

		public void setCodeTs(String codeTs) {
			this.codeTs = codeTs;
		}

		public String getOriginCountry() {
			return originCountry;
		}

		public void setOriginCountry(String originCountry) {
			this.originCountry = originCountry;
		}

		public double getUnitPrice() {
			return unitPrice;
		}

		public void setUnitPrice(double unitPrice) {
			this.unitPrice = unitPrice;
		}

		public int getGoodsCount() {
			return goodsCount;
		}

		public void setGoodsCount(int goodsCount) {
			this.goodsCount = goodsCount;
		}

		public String getGoodsUnit() {
			return goodsUnit;
		}

		public void setGoodsUnit(String goodsUnit) {
			this.goodsUnit = goodsUnit;
		}

	}

	/**
	 * 购买人信息
	 * @author liuxy
	 */
	@XStreamAlias("jkfGoodsPurchaser")
	public static class JkfGoodsPurchaser implements Serializable {

		private static final long serialVersionUID = 1L;
		
		/** 购买人ID **/
		private String id;
		/** 姓名 **/
		private String name;
		/** 联系电话 **/
		private String telNumber;

		public JkfGoodsPurchaser() {
		}

		public JkfGoodsPurchaser(String id, String name, String telNumber) {
			this.id = id;
			this.name = name;
			this.telNumber = telNumber;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTelNumber() {
			return telNumber;
		}

		public void setTelNumber(String telNumber) {
			this.telNumber = telNumber;
		}

	}

}
