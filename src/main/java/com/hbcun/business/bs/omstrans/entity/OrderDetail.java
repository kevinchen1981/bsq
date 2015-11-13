package com.hbcun.business.bs.omstrans.entity;

import java.io.Serializable;

import com.hbcun.business.bs.entity.vo.BaseVo;

public class OrderDetail extends BaseVo {
	public OrderDetail() {
	}	
	public Integer getGoodsOrder() {
		return goodsOrder;
	}
	public void setGoodsOrder(Integer goodsOrder) {
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
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getGoodCount() {
		return goodCount;
	}
	public void setGoodCount(Integer goodCount) {
		this.goodCount = goodCount;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public double getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(double netWeight) {
		this.netWeight = netWeight;
	}
	public String getGoodItemNo() {
		return goodItemNo;
	}
	public void setGoodItemNo(String goodItemNo) {
		this.goodItemNo = goodItemNo;
	}
	private Integer goodsOrder;
	private String goodsName;
	private String codeTs;
	private String goodsModel;
	private String originCountry;
	private double unitPrice;
	private Integer goodCount;
	private String goodsUnit;
	private double grossWeight;
	private double netWeight;
	private String goodItemNo;
}
