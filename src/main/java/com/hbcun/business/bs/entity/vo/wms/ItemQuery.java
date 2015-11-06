package com.hbcun.business.bs.entity.vo.wms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hbcun.business.bs.entity.vo.BaseVo;

/**
 * 3.13商品查询
 * 
 * @author liuxy
 */
public class ItemQuery extends BaseVo {

	private static final long serialVersionUID = 1L;
	private String sellerUserId;
	private List<OrderItem313> itemIds = new ArrayList<OrderItem313>();

	public ItemQuery() {
	}

	public String getSellerUserId() {
		return sellerUserId;
	}

	public void setSellerUserId(String sellerUserId) {
		this.sellerUserId = sellerUserId;
	}

	public List<OrderItem313> getItemIds() {
		return itemIds;
	}

	public void setItemIds(List<OrderItem313> itemIds) {
		this.itemIds.addAll(itemIds);
	}

	public void setItemIds(OrderItem313 item) {
		this.itemIds.add(item);
	}
	
	

	public static class OrderItem313 extends OrderItem implements Serializable {

		private static final long serialVersionUID = 1L;
		private String itemName;
		private String itemTitle;
		private Double salePrice;
		private String attributes;
		private String remarks;

		public OrderItem313() {
		}

		public OrderItem313(String itemId, String productNo, String sellerUserId, String itemName, String itemTitle,
				Double salePrice, String attributes, String remarks) {
			super(sellerUserId, itemId, productNo, 0);
			this.itemName = itemName;
			this.itemTitle = itemTitle;
			this.salePrice = salePrice;
			this.attributes = attributes;
			this.remarks = remarks;
		}

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public String getItemTitle() {
			return itemTitle;
		}

		public void setItemTitle(String itemTitle) {
			this.itemTitle = itemTitle;
		}

		public Double getSalePrice() {
			return salePrice;
		}

		public void setSalePrice(Double salePrice) {
			this.salePrice = salePrice;
		}

		public String getAttributes() {
			return attributes;
		}

		public void setAttributes(String attributes) {
			this.attributes = attributes;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

	}

}
