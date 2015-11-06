package com.hbcun.business.bs.entity.vo.wms;

import java.io.Serializable;
import java.util.List;

import com.hbcun.business.bs.entity.vo.BaseVo;
/**
 * 3.7发货订单出库确认接口（网仓调用平台接口）
 * @author liuxy
 */
public class WmsOrderShipNotice extends BaseVo {
	
	private static final long serialVersionUID = 1L;
	private String orderCode;
	private double weight;
	private String tmsServiceCode;
	private String tmsOrderCode;
	private List<InvoinceConfirm> invoinceConfirms;
	private List<OrderItem37> orderItems;
	
	public WmsOrderShipNotice() {
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
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

	public List<InvoinceConfirm> getInvoinceConfirms() {
		return invoinceConfirms;
	}

	public void setInvoinceConfirms(List<InvoinceConfirm> invoinceConfirms) {
		this.invoinceConfirms = invoinceConfirms;
	}

	public List<OrderItem37> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem37> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	/**
	 * 发货订单出库确认接口（网仓调用平台接口）
	 * 运发票确认信息
	 * @author liuxy
	 */
	public static class InvoinceConfirm implements Serializable{

		private static final long serialVersionUID = 1L;
		private String orderCode;
		private String invoiceNumber;
		private String invoiceCode;
		
		public InvoinceConfirm() {
		}

		public String getOrderCode() {
			return orderCode;
		}

		public void setOrderCode(String orderCode) {
			this.orderCode = orderCode;
		}

		public String getInvoiceNumber() {
			return invoiceNumber;
		}

		public void setInvoiceNumber(String invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
		}

		public String getInvoiceCode() {
			return invoiceCode;
		}

		public void setInvoiceCode(String invoiceCode) {
			this.invoiceCode = invoiceCode;
		}
		
	}

	/**
	 * 发货订单出库确认接口（网仓调用平台接口）
	 * 订单商品信息
	 * @author liuxy
	 */
	public static class OrderItem37 extends OrderItem implements Serializable{

		private static final long serialVersionUID = 1L;
		private int weight;
		
		public OrderItem37() {
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}
		
	}
}
