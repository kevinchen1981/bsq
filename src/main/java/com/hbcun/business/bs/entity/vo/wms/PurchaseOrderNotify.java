package com.hbcun.business.bs.entity.vo.wms;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.hbcun.business.bs.entity.vo.BaseVo;
import com.hbcun.common.sys.util.DateUtils;

/**
 * 3.4采购订单信息
 * @author liuxy
 * @date 2015年9月6日下午2:52:28
 * @since 1.7
 */
public class PurchaseOrderNotify extends BaseVo {

	private static final long serialVersionUID = 1L;
	/** 仓储编码 **/
	@NotEmpty(message = "仓储编码不能为空")
	private String storeCode;
	/** 仓储用户id **/
	@NotEmpty(message = "仓储用户id不能为空")
	private String sellerUserId;
	/** 仓库订单编码 **/

	private String orderCode;
	/** 操作子类型 601 采购入库单 **/
	private int orderType = 601;
	/** 订单创建时间 **/
	private Date orderCreateTime;

	/** 配送编码 **/
	private String tmsServiceCode;

	/** 运单号 **/
	private String tmsOrderCode;

	/** 前物流订单号，如退货入库单可能会用到 **/
	private String prevOrderCode;

	/** 预期送达开始时间 **/
	private Date expectStartTime;

	/** 预期送达结束时间 **/
	private Date expectEndTime;

	private String receiverZipCode;

	private String receiverProvince;

	private String receiverCity;

	private String receiverCounty;

	private String receiverAddress;

	private String receiverName;

	private String receiverMobile;

	private String receiverPhone;

	private List<OrderItem34> orderItemList;

	private String remark;

	public PurchaseOrderNotify() {
	}

	public PurchaseOrderNotify(String storeCode, String sellerUserId, String orderCode, Date orderCreateTime) {
		this.storeCode = storeCode;
		this.sellerUserId = sellerUserId;
		this.orderCode = orderCode;
		this.orderCreateTime = orderCreateTime;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getSellerUserId() {
		return sellerUserId;
	}

	public void setSellerUserId(String sellerUserId) {
		this.sellerUserId = sellerUserId;
	}

	@NotEmpty(message = "仓库订单编码不能为空")
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	@NotNull(message = "订单创建时间不能为空")
	public String getOrderCreateTime() {
		return DateUtils.formatToDateTimeString(orderCreateTime);
	}

	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getReceiverZipCode() {
		return receiverZipCode;
	}

	public void setReceiverZipCode(String receiverZipCode) {
		this.receiverZipCode = receiverZipCode;
	}

	public String getReceiverProvince() {
		return receiverProvince;
	}

	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverCounty() {
		return receiverCounty;
	}

	public void setReceiverCounty(String receiverCounty) {
		this.receiverCounty = receiverCounty;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public List<OrderItem34> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem34> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTmsServiceCode() {
		return tmsServiceCode;
	}

	public void setTmsServiceCode(String tmsServiceCode) {
		this.tmsServiceCode = tmsServiceCode;
	}

	public String getTmsOrderCode() {
		return tmsOrderCode;
	}

	public void setTmsOrderCode(String tmsOrderCode) {
		this.tmsOrderCode = tmsOrderCode;
	}

	public String getPrevOrderCode() {
		return prevOrderCode;
	}

	public void setPrevOrderCode(String prevOrderCode) {
		this.prevOrderCode = prevOrderCode;
	}

	public Date getExpectStartTime() {
		return expectStartTime;
	}

	public void setExpectStartTime(Date expectStartTime) {
		this.expectStartTime = expectStartTime;
	}

	public Date getExpectEndTime() {
		return expectEndTime;
	}

	public void setExpectEndTime(Date expectEndTime) {
		this.expectEndTime = expectEndTime;
	}

	public static class OrderItem34 extends OrderItem implements Serializable {

		private static final long serialVersionUID = 1L;

		/** 子订单ID **/
		private String orderItemId;
		/** 1 可销售库存 101 残次 **/
		private Integer inventoryType;
		/** 商品名称 **/
		private String itemName;
		/** 商品实际价格 **/
		private Double actualPrice;
		/** 销售价格 **/
		private Double itemPrice;

		public OrderItem34() {
		}

		public OrderItem34(String orderItemId, String sellerUserId, String itemId, String itemName, String productNo,
				int itemQty) {
			super(sellerUserId, itemId, productNo, itemQty);
			this.orderItemId = orderItemId;
			this.itemName = itemName;
			this.inventoryType = 1;
		}

		public String getOrderItemId() {
			return orderItemId;
		}

		public void setOrderItemId(String orderItemId) {
			this.orderItemId = orderItemId;
		}

		public Integer getInventoryType() {
			return inventoryType;
		}

		public void setInventoryType(Integer inventoryType) {
			this.inventoryType = inventoryType;
		}

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public Double getActualPrice() {
			return actualPrice;
		}

		public void setActualPrice(Double actualPrice) {
			this.actualPrice = actualPrice;
		}

		public Double getItemPrice() {
			return itemPrice;
		}

		public void setItemPrice(Double itemPrice) {
			this.itemPrice = itemPrice;
		}

	}

}
