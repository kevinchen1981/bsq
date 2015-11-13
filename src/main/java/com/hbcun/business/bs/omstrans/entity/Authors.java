package com.hbcun.business.bs.omstrans.entity;

import java.io.Serializable;

import com.hbcun.business.bs.entity.vo.BaseVo;

public class Authors extends BaseVo{
	public Authors(){
		
	}
	
	private String appID;
	private String authorCode;
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getAuthorCode() {
		return authorCode;
	}
	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}
}
