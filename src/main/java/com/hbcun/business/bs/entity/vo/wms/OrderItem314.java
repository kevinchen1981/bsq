package com.hbcun.business.bs.entity.vo.wms;

public class OrderItem314 extends OrderItem {

	private static final long serialVersionUID = 1L;
	/**可选	在架数**/
	private Integer qtyOnLocation;
	/**可选	可售数**/
	private Integer qtyOfAvailable;
	/**必选	已拣选，未出库的库存数**/
	private Integer qtyOfShipping;
	/**必选	库存类型**/
	private int inventoryType;

	public OrderItem314() {
	}

	public Integer getQtyOnLocation() {
		return qtyOnLocation;
	}

	public void setQtyOnLocation(Integer qtyOnLocation) {
		this.qtyOnLocation = qtyOnLocation;
	}

	public Integer getQtyOfAvailable() {
		return qtyOfAvailable;
	}

	public void setQtyOfAvailable(Integer qtyOfAvailable) {
		this.qtyOfAvailable = qtyOfAvailable;
	}

	public Integer getQtyOfShipping() {
		return qtyOfShipping;
	}

	public void setQtyOfShipping(Integer qtyOfShipping) {
		this.qtyOfShipping = qtyOfShipping;
	}

	public int getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(int inventoryType) {
		this.inventoryType = inventoryType;
	}
	
}
