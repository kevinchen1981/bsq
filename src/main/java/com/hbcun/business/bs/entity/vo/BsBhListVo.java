package com.hbcun.business.bs.entity.vo;
/**
 * 保税备货
 * @author liuxy
 * @date 2015年9月30日上午11:33:36
 * @since 1.7
 */
public class BsBhListVo extends BsBaseListVo {

	private static final long serialVersionUID = 1L;
	private boolean isOrderSendToWms;

	public boolean isOrderSendToWms() {
		return isOrderSendToWms;
	}

	public void setOrderSendToWms(boolean isOrderSendToWms) {
		this.isOrderSendToWms = isOrderSendToWms;
	}
	
	
}
