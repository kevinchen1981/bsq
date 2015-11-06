package com.hbcun.business.bs.entity.vo.wms;

import java.util.List;

import com.hbcun.business.bs.entity.vo.BaseVo;

/**
 * 3.14 商品库存查询接口
 * 
 * @author liuxy
 */
public class InventoryQuery extends BaseVo {

	private static final long serialVersionUID = 1L;
	private String sellerUserId;

	private List<OrderItem> itemIds;

	public InventoryQuery() {
	}

	public String getSellerUserId() {
		return sellerUserId;
	}

	public void setSellerUserId(String sellerUserId) {
		this.sellerUserId = sellerUserId;
	}

	public List<OrderItem> getItemIds() {
		return itemIds;
	}

	public void setItemIds(List<OrderItem> itemIds) {
		this.itemIds = itemIds;
	}

}
