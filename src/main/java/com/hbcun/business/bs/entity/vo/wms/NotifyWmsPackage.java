package com.hbcun.business.bs.entity.vo.wms;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.hbcun.business.bs.entity.vo.BaseVo;

/**
 * 3.3 下发发货订单打包指令接口
 * 
 * @author liuxy
 */
public class NotifyWmsPackage extends BaseVo implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotBlank(message = "仓库订单编码不能为空")
	private String orderCode;
	private String tmsServiceCode;
	private String tmsOrderCode;

	public NotifyWmsPackage() {
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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

}
