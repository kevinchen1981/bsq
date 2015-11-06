package com.hbcun.business.bs.entity.vo.wms;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.hbcun.business.bs.entity.vo.BaseVo;
import com.hbcun.common.sys.util.DateUtils;
/**
 * 3.2发货写单订单通知接口（平台调用网仓接口）
 * @author liuxy
 */
public class WmsOrderNotify extends BaseVo implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 仓储编码 **/
	@NotEmpty(message="仓储编码不能为空")
	private String storeCode;
	/** 仓储用户id **/
	@NotEmpty(message="仓储用户id不能为空")
	private String sellerUserId;
	/** 仓库订单编码 **/
	@NotEmpty(message="仓库订单编码不能为空")
	private String orderCode;
	/** 订单创建时间,yyyy-MM-dd hh:mm:ss **/
	@NotNull(message="订单创建时间不能为空")
	private Date orderCreateTime;
	/** 格式:yyyy-MM-dd hh:mm:ss **/
	@NotNull(message="订单支付时间不能为空")
	private Date orderPayTime;
	/** 订单总金额 **/
	@NotNull(message="订单总金额不能为空")
	private Double totalFee;
	/** 付款金额 **/
	@NotNull(message="付款金额 不能为空")
	private Double payment;
	/** 优惠金额 **/
	@NotNull(message="优惠金额 不能为空")
	private Double discountFee;
	/** 邮费 **/
	@NotNull(message="邮费 不能为空")
	private Double postFee;
	/** 配送商编码 **/
	private String tmsServiceCode;
	/** 运单号 **/
	private String tmsOrderCode;
	/** 收件方邮编 **/
	@NotNull(message="收件方邮编不能为空")
	private String receiverZipCode;
	/** 收件方省份 **/
	@NotNull(message="收件方省份不能为空")
	private String receiverProvince;
	/** 收件方城市 **/
	@NotNull(message="收件方城市不能为空")
	private String receiverCity;
	/** 收件方区县 **/
	private String receiverCounty;
	/** 收件方地址 **/
	private String receiverAddress;
	/** 收件人名称 **/
	@NotNull(message="收件人名称不能为空")
	private String receiverName;
	/** 收件人手机 **/
	@NotNull(message="收件人手机不能为空")
	private String receiverMobile;

	private List<OrderItem32> orderItemList;

	public WmsOrderNotify() {
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

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderCreateTime() {
		return DateUtils.formatToDateTimeString(orderCreateTime);
	}

	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getOrderPayTime() {
		return DateUtils.formatToDateTimeString(orderPayTime);
	}

	public void setOrderPayTime(Date orderPayTime) {
		this.orderPayTime = orderPayTime;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Double getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(Double discountFee) {
		this.discountFee = discountFee;
	}

	public Double getPostFee() {
		return postFee;
	}

	public void setPostFee(Double postFee) {
		this.postFee = postFee;
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

	public List<OrderItem32> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem32> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	public static class OrderItem32 extends OrderItem implements Serializable {

		private static final long serialVersionUID = 1L;
		/** 子订单ID **/
		private String orderItemId;
		/** 商品名称 **/
		private String itemName;
		/** 商品实际价格 **/
		private Double actualPrice;
		/** 销售价格 **/
		private Double itemPrice;
		/** 优惠金额 **/
		private Double discountFee;
		
		public OrderItem32() {
		}

		public OrderItem32(String orderItemId, String sellerUserId, String itemId,
				String itemName, String productNo, int itemQty) {
			super(sellerUserId, itemId, productNo, itemQty);
			this.orderItemId = orderItemId;
			this.itemName = itemName;
		}

		public String getOrderItemId() {
			return orderItemId;
		}

		public void setOrderItemId(String orderItemId) {
			this.orderItemId = orderItemId;
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

		public Double getDiscountFee() {
			return discountFee;
		}

		public void setDiscountFee(Double discountFee) {
			this.discountFee = discountFee;
		}
	}

}
