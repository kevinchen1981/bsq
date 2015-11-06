package com.hbcun.business.bs.entity.vo.eplink.post23;

import java.io.Serializable;
import java.util.List;

import com.hbcun.business.bs.entity.vo.BaseVo;
import com.hbcun.business.bs.entity.vo.eplink.JkfSign;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("body")
public class Body extends BaseVo {

	private static final long serialVersionUID = 1L;

	private List<GoodsDeclareModule> goodsDeclareModuleList;

	public Body() {
	}

	public Body(List<GoodsDeclareModule> goodsDeclareModuleList) {
		this.goodsDeclareModuleList = goodsDeclareModuleList;
	}

	public List<GoodsDeclareModule> getGoodsDeclareModuleList() {
		return goodsDeclareModuleList;
	}

	public void setGoodsDeclareModuleList(List<GoodsDeclareModule> goodsDeclareModuleList) {
		this.goodsDeclareModuleList = goodsDeclareModuleList;
	}

	@XStreamAlias("goodsDeclareModule")
	public static class GoodsDeclareModule implements Serializable {

		/****/
		private static final long serialVersionUID = 1L;
		private JkfSign jkfSign;
		private GoodsDeclare goodsDeclare;
		private List<GoodsDeclareDetail> goodsDeclareDetails;

		public GoodsDeclareModule() {
		}

		public JkfSign getJkfSign() {
			return jkfSign;
		}

		public void setJkfSign(JkfSign jkfSign) {
			this.jkfSign = jkfSign;
		}

		public GoodsDeclare getGoodsDeclare() {
			return goodsDeclare;
		}

		public void setGoodsDeclare(GoodsDeclare goodsDeclare) {
			this.goodsDeclare = goodsDeclare;
		}

		public List<GoodsDeclareDetail> getGoodsDeclareDetails() {
			return goodsDeclareDetails;
		}

		public void setGoodsDeclareDetails(List<GoodsDeclareDetail> goodsDeclareDetails) {
			this.goodsDeclareDetails = goodsDeclareDetails;
		}

	}

	/**
	 * 个人申报单商品表头MO对象
	 * 
	 * @author liuxy
	 */
	@XStreamAlias("goodsDeclare")
	public static class GoodsDeclare implements Serializable {

		/****/
		private static final long serialVersionUID = 1L;

		/** 账册编号 **/
		private String accountBookNo;
		private String ieFlag = "I";
		/** 预录入号码.4位电商编号+14位企业流水，电商平台/物流企业生成后发送服务平台，与运单号一一对应，同个运单重新申报时，保持不变 **/
		private String preEntryNumber;
		/** 进口类型,0：一般进口1：保税进口 **/
		private String importType = "1";
		/** 进出口岸代码 **/
		private String iePort;
		/** 指运港(抵运港) **/
		private String destinationPort;
		/** 运输方式代码 **/
		private String trafMode;
		/** 申报单位类别 **/
		private String declareCompanyType;
		/** 申报单位代码 **/
		private String declareCompanyCode;
		/** 申报单位名称 **/
		private String declareCompanyName;
		/** 电商企业代码 **/
		private String eCommerceCode;
		/** 电商企业名称 **/
		private String eCommerceName;
		/** 订单编号 **/
		private String orderNo;
		/** 分运单号 **/
		private String wayBill;
		/** 贸易国别（起运地） **/
		private String tradeCountry;
		/** 件数 **/
		private double packNo;
		/** 毛重（公斤） **/
		private double grossWeight;
		/** 净重（公斤） **/
		private double netWeight;
		/** 申报口岸代码 **/
		private String declPort;
		/** 录入人 **/
		private String enteringPerson = "9999";
		/** 录入单位名称 **/
		private String enteringCompanyName = "9999";
		/** 码头/货场代码 **/
		private String customsField;
		/** 发件人 **/
		private String senderName;
		/** 收件人 **/
		private String consignee;
		/** 发件人国别 **/
		private String senderCountry;
		/** 价值 **/
		private double worth;
		/** 币值 **/
		private String currCode;
		/** 主要货物名称 **/
		private String mainGName;
		/** 区内企业编码 **/
		private String internalAreaCompanyNo;
		/** 区内企业名称 **/
		private String internalAreaCompanyName;
		/** 申请单编号 **/
		private String applicationFormNo;
		/** 代表是否个人买家授权电商申报数据，填写0或1，0代表否，1代表是 **/
		private String isAuthorize = "1";

		public GoodsDeclare() {
		}

		public GoodsDeclare(String preEntryNumber, String iePort, String destinationPort, String trafMode,
				String declareCompanyType, String declareCompanyCode, String declareCompanyName, String eCommerceCode,
				String eCommerceName, String orderNo, String wayBill, String tradeCountry, double packNo,
				double grossWeight, String declPort, String enteringPerson, String enteringCompanyName,
				String customsField, String senderName, String consignee, String senderCountry, double worth,
				String currCode, String mainGName) {
			this.preEntryNumber = preEntryNumber;
			this.iePort = iePort;
			this.destinationPort = destinationPort;
			this.trafMode = trafMode;
			this.declareCompanyType = declareCompanyType;
			this.declareCompanyCode = declareCompanyCode;
			this.declareCompanyName = declareCompanyName;
			this.eCommerceCode = eCommerceCode;
			this.eCommerceName = eCommerceName;
			this.orderNo = orderNo;
			this.wayBill = wayBill;
			this.tradeCountry = tradeCountry;
			this.packNo = packNo;
			this.grossWeight = grossWeight;
			this.declPort = declPort;
			this.enteringPerson = enteringPerson;
			this.enteringCompanyName = enteringCompanyName;
			this.customsField = customsField;
			this.senderName = senderName;
			this.consignee = consignee;
			this.senderCountry = senderCountry;
			this.worth = worth;
			this.currCode = currCode;
			this.mainGName = mainGName;
		}

		public String getIeFlag() {
			return ieFlag;
		}

		public void setIeFlag(String ieFlag) {
			this.ieFlag = ieFlag;
		}

		public String getPreEntryNumber() {
			return preEntryNumber;
		}

		public void setPreEntryNumber(String preEntryNumber) {
			this.preEntryNumber = preEntryNumber;
		}

		public String getImportType() {
			return importType;
		}

		public void setImportType(String importType) {
			this.importType = importType;
		}

		public String getIePort() {
			return iePort;
		}

		public void setIePort(String iePort) {
			this.iePort = iePort;
		}

		public String getDestinationPort() {
			return destinationPort;
		}

		public void setDestinationPort(String destinationPort) {
			this.destinationPort = destinationPort;
		}

		public String getTrafMode() {
			return trafMode;
		}

		public void setTrafMode(String trafMode) {
			this.trafMode = trafMode;
		}

		public String getDeclareCompanyType() {
			return declareCompanyType;
		}

		public void setDeclareCompanyType(String declareCompanyType) {
			this.declareCompanyType = declareCompanyType;
		}

		public String getDeclareCompanyCode() {
			return declareCompanyCode;
		}

		public void setDeclareCompanyCode(String declareCompanyCode) {
			this.declareCompanyCode = declareCompanyCode;
		}

		public String getDeclareCompanyName() {
			return declareCompanyName;
		}

		public void setDeclareCompanyName(String declareCompanyName) {
			this.declareCompanyName = declareCompanyName;
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

		public String getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		public String getWayBill() {
			return wayBill;
		}

		public void setWayBill(String wayBill) {
			this.wayBill = wayBill;
		}

		public String getTradeCountry() {
			return tradeCountry;
		}

		public void setTradeCountry(String tradeCountry) {
			this.tradeCountry = tradeCountry;
		}

		public double getPackNo() {
			return packNo;
		}

		public void setPackNo(double packNo) {
			this.packNo = packNo;
		}

		public double getGrossWeight() {
			return grossWeight;
		}

		public void setGrossWeight(double grossWeight) {
			this.grossWeight = grossWeight;
		}

		public String getDeclPort() {
			return declPort;
		}

		public void setDeclPort(String declPort) {
			this.declPort = declPort;
		}

		public String getEnteringPerson() {
			return enteringPerson;
		}

		public void setEnteringPerson(String enteringPerson) {
			this.enteringPerson = enteringPerson;
		}

		public String getEnteringCompanyName() {
			return enteringCompanyName;
		}

		public void setEnteringCompanyName(String enteringCompanyName) {
			this.enteringCompanyName = enteringCompanyName;
		}

		public String getCustomsField() {
			return customsField;
		}

		public void setCustomsField(String customsField) {
			this.customsField = customsField;
		}

		public String getSenderName() {
			return senderName;
		}

		public void setSenderName(String senderName) {
			this.senderName = senderName;
		}

		public String getConsignee() {
			return consignee;
		}

		public void setConsignee(String consignee) {
			this.consignee = consignee;
		}

		public String getSenderCountry() {
			return senderCountry;
		}

		public void setSenderCountry(String senderCountry) {
			this.senderCountry = senderCountry;
		}

		public double getWorth() {
			return worth;
		}

		public void setWorth(double worth) {
			this.worth = worth;
		}

		public String getCurrCode() {
			return currCode;
		}

		public void setCurrCode(String currCode) {
			this.currCode = currCode;
		}

		public String getMainGName() {
			return mainGName;
		}

		public void setMainGName(String mainGName) {
			this.mainGName = mainGName;
		}

		public String getIsAuthorize() {
			return isAuthorize;
		}

		public void setIsAuthorize(String isAuthorize) {
			this.isAuthorize = isAuthorize;
		}

		public String getInternalAreaCompanyNo() {
			return internalAreaCompanyNo;
		}

		public void setInternalAreaCompanyNo(String internalAreaCompanyNo) {
			this.internalAreaCompanyNo = internalAreaCompanyNo;
		}

		public String getInternalAreaCompanyName() {
			return internalAreaCompanyName;
		}

		public void setInternalAreaCompanyName(String internalAreaCompanyName) {
			this.internalAreaCompanyName = internalAreaCompanyName;
		}

		public String getApplicationFormNo() {
			return applicationFormNo;
		}

		public void setApplicationFormNo(String applicationFormNo) {
			this.applicationFormNo = applicationFormNo;
		}

		public double getNetWeight() {
			return netWeight;
		}

		public void setNetWeight(double netWeight) {
			this.netWeight = netWeight;
		}

		public String getAccountBookNo() {
			return accountBookNo;
		}

		public void setAccountBookNo(String accountBookNo) {
			this.accountBookNo = accountBookNo;
		}

	}

	/**
	 * 个人物品申报单表体信息
	 * 
	 * @author liuxy
	 */
	@XStreamAlias("goodsDeclareDetail")
	public static class GoodsDeclareDetail implements Serializable {

		private static final long serialVersionUID = 1L;
		/** 商品序号 **/
		private int goodsOrder;
		/** 行邮税号 **/
		private String codeTs;
		/** 保税进口业务中，货号即指料号，与仓储企业备案的电子账册中料号数据一致 **/
		private String goodsItemNo;
		/** 物品名称 **/
		private String goodsName;
		/** 商品规格、型号 **/
		private String goodsModel;
		/** 产销国 **/
		private String originCountry;
		/** 成交币制 **/
		private String tradeCurr;
		/** 成交总价 **/
		private double tradeTotal;
		/** 申报单价 **/
		private double declPrice;
		/** 申报总价 **/
		private String declTotalPrice;
		/** 申报数量 **/
		private int declareCount;
		/** 申报计量单位 **/
		private String goodsUnit;
		/** 第一单位 **/
		private String firstUnit;
		/** 第一数量 **/
		private double firstCount;
		/** 产品国检备案编号 **/
		private String productRecordNo;

		public GoodsDeclareDetail() {
		}

		public GoodsDeclareDetail(int goodsOrder, String codeTs, String goodsItemNo, String goodsName,
				String goodsModel, String originCountry, String tradeCurr, double declPrice, String declTotalPrice,
				int declareCount, String goodsUnit, String firstUnit, double firstCount, String productRecordNo,
				double tradeTotal) {
			this.goodsOrder = goodsOrder;
			this.codeTs = codeTs;
			this.goodsItemNo = goodsItemNo;
			this.goodsName = goodsName;
			this.goodsModel = goodsModel;
			this.originCountry = originCountry;
			this.tradeCurr = tradeCurr;
			this.declPrice = declPrice;
			this.declTotalPrice = declTotalPrice;
			this.declareCount = declareCount;
			this.goodsUnit = goodsUnit;
			this.firstUnit = firstUnit;
			this.firstCount = firstCount;
			this.productRecordNo = productRecordNo;
			this.tradeTotal = tradeTotal;
		}

		public GoodsDeclareDetail(int goodsOrder, String codeTs, String goodsItemNo, String goodsName,
				String goodsModel, String originCountry, double declPrice, String declTotalPrice, int declareCount,
				String goodsUnit, String firstUnit, double firstCount, String productRecordNo, double tradeTotal) {

			this(goodsOrder, codeTs, goodsItemNo, goodsName, goodsModel, originCountry, "142", declPrice,
					declTotalPrice, declareCount, goodsUnit, firstUnit, firstCount, productRecordNo, tradeTotal);
		}

		public int getGoodsOrder() {
			return goodsOrder;
		}

		public void setGoodsOrder(int goodsOrder) {
			this.goodsOrder = goodsOrder;
		}

		public String getCodeTs() {
			return codeTs;
		}

		public void setCodeTs(String codeTs) {
			this.codeTs = codeTs;
		}

		public String getGoodsItemNo() {
			return goodsItemNo;
		}

		public void setGoodsItemNo(String goodsItemNo) {
			this.goodsItemNo = goodsItemNo;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public String getGoodsModel() {
			return goodsModel;
		}

		public void setGoodsModel(String goodsModel) {
			this.goodsModel = goodsModel;
		}

		public String getOriginCountry() {
			return originCountry;
		}

		public void setOriginCountry(String originCountry) {
			this.originCountry = originCountry;
		}

		public double getDeclPrice() {
			return declPrice;
		}

		public void setDeclPrice(double declPrice) {
			this.declPrice = declPrice;
		}

		public String getDeclTotalPrice() {
			return declTotalPrice;
		}

		public void setDeclTotalPrice(String declTotalPrice) {
			this.declTotalPrice = declTotalPrice;
		}

		public int getDeclareCount() {
			return declareCount;
		}

		public void setDeclareCount(int declareCount) {
			this.declareCount = declareCount;
		}

		public String getGoodsUnit() {
			return goodsUnit;
		}

		public void setGoodsUnit(String goodsUnit) {
			this.goodsUnit = goodsUnit;
		}

		public String getFirstUnit() {
			return firstUnit;
		}

		public void setFirstUnit(String firstUnit) {
			this.firstUnit = firstUnit;
		}

		public double getFirstCount() {
			return firstCount;
		}

		public void setFirstCount(double firstCount) {
			this.firstCount = firstCount;
		}

		public String getProductRecordNo() {
			return productRecordNo;
		}

		public void setProductRecordNo(String productRecordNo) {
			this.productRecordNo = productRecordNo;
		}

		public double getTradeTotal() {
			return tradeTotal;
		}

		public void setTradeTotal(double tradeTotal) {
			this.tradeTotal = tradeTotal;
		}

		public String getTradeCurr() {
			return tradeCurr;
		}

		public void setTradeCurr(String tradeCurr) {
			this.tradeCurr = tradeCurr;
		}

	}

}
