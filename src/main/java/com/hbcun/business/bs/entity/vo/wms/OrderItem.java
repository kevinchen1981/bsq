package com.hbcun.business.bs.entity.vo.wms;

import java.io.Serializable;
/**
 * @author liuxy
 * @date 2015年9月6日下午2:48:35
 * @since 1.7
 */
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 卖家ID **/
	String sellerUserId;
	/** 商品ID **/
	String itemId;
	/** 商家对商品的编码 **/
	String productNo;
	/** 商品数量 **/
	Integer itemQty;

	public OrderItem() {
	}

	public OrderItem(String sellerUserId, String itemId, String productNo,
			int itemQty) {
		this.sellerUserId = sellerUserId;
		this.itemId = itemId;
		this.productNo = productNo;
		this.itemQty = itemQty;
	}

	public String getSellerUserId() {
		return sellerUserId;
	}

	public void setSellerUserId(String sellerUserId) {
		this.sellerUserId = sellerUserId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public Integer getItemQty() {
		return itemQty;
	}

	public void setItemQty(Integer itemQty) {
		this.itemQty = itemQty;
	}

}
