package com.hbcun.business.bs.entity.vo.eplink;

import com.hbcun.business.bs.entity.vo.BaseVo;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("head")
public class Head extends BaseVo {

	private static final long serialVersionUID = 1L;

	private String businessType;
	
	public Head() {
	}

	public Head(String businessType) {
		this.businessType = businessType;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
}
