package com.hbcun.business.bs.entity.vo;

import java.io.Serializable;

public class BsProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	private String productId;

	private String barCode;

	private String title;

	private int itemQty;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getItemQty() {
		return itemQty;
	}

	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}

}