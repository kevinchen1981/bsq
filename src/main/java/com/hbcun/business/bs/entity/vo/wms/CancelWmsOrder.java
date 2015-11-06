package com.hbcun.business.bs.entity.vo.wms;

import com.hbcun.business.bs.entity.vo.BaseVo;

/**
 * 3.10取消订单接口
 * @author liuxy
 */
public class CancelWmsOrder extends BaseVo {

	private static final long serialVersionUID = 1L;
	private String orderCode;

	public CancelWmsOrder() {
	}

	public CancelWmsOrder(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	

}
