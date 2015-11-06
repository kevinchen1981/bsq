package com.hbcun.business.bs.entity.vo.wms;

import java.io.Serializable;
import java.util.List;

import com.hbcun.business.bs.entity.vo.BaseVo;

/**
 * 3.8采购订单入库确认接口（网仓调用平台接口）
 * @author liuxy
 * @date 2015年9月6日下午2:59:23
 * @since 1.7
 */
public class PurchaseOrderInStockConfirm extends BaseVo {

	private static final long serialVersionUID = 1L;

	private String orderCode;

	private String arrivalBeginTime;

	private String arrivalEndTime;

	private List<OrderItem38> orderItems;

	public PurchaseOrderInStockConfirm() {
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getArrivalBeginTime() {
		return arrivalBeginTime;
	}

	public void setArrivalBeginTime(String arrivalBeginTime) {
		this.arrivalBeginTime = arrivalBeginTime;
	}

	public String getArrivalEndTime() {
		return arrivalEndTime;
	}

	public void setArrivalEndTime(String arrivalEndTime) {
		this.arrivalEndTime = arrivalEndTime;
	}

	public List<OrderItem38> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem38> orderItems) {
		this.orderItems = orderItems;
	}

	public static class OrderItem38 extends OrderItem implements Serializable {

		private static final long serialVersionUID = 1L;

		private int inventoryType;

		/** 重量单位克 **/
		private int weight;
		/** 长单位 mm **/
		private int length;
		/** 宽单位 mm **/
		private int width;
		/** 高单位 mm **/
		private int height;

		public OrderItem38() {
		}

		public int getInventoryType() {
			return inventoryType;
		}

		public void setInventoryType(int inventoryType) {
			this.inventoryType = inventoryType;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

	}

}
