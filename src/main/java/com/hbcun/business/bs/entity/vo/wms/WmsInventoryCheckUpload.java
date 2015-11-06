package com.hbcun.business.bs.entity.vo.wms;

import java.io.Serializable;
import java.util.List;

import com.hbcun.business.bs.entity.vo.BaseVo;

/**
 * 3.15库存盘点接口（网仓调用平台接口）
 * 
 * @author liuxy
 */
public class WmsInventoryCheckUpload extends BaseVo {

	private static final long serialVersionUID = 1L;
	private String storeCode;
	/**
	 * 订单类型 701 盘点出库 702 盘点入库
	 **/
	private int orderType;

	private String sellerUserId;

	private String remark;

	private List<OrderItem315> itemList;

	public WmsInventoryCheckUpload() {
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getSellerUserId() {
		return sellerUserId;
	}

	public void setSellerUserId(String sellerUserId) {
		this.sellerUserId = sellerUserId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<OrderItem315> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderItem315> itemList) {
		this.itemList = itemList;
	}

	public static class OrderItem315 extends OrderItem implements Serializable {

		private static final long serialVersionUID = 1L;

		private int inventoryType;

		private int quantity;

		public OrderItem315() {
		}

		public int getInventoryType() {
			return inventoryType;
		}

		public void setInventoryType(int inventoryType) {
			this.inventoryType = inventoryType;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

	}

}
