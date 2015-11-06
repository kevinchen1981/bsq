package com.hbcun.business.bs.entity.vo.wms;

import java.util.List;

public class ResponseData314 extends ResponseData {

	private static final long serialVersionUID = 1L;

	private List<OrderItem314> inventoryList;

	public ResponseData314() {
	}

	public List<OrderItem314> getInventoryList() {
		return inventoryList;
	}

	public void setInventoryList(List<OrderItem314> inventoryList) {
		this.inventoryList = inventoryList;
	}

}
