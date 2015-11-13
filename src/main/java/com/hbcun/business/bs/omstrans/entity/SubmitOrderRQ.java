package com.hbcun.business.bs.omstrans.entity;

import java.io.Serializable;

import com.hbcun.business.bs.entity.vo.BaseVo;
import com.thoughtworks.xstream.annotations.XStreamAlias;

//import com.hbcun.business.bs.entity.vo.ems.bigaccountdata.XStreamAlias;

public class SubmitOrderRQ extends BaseVo {
	public SubmitOrderRQ(){
		
	}
	private String app_id;
	private String auth_token;
	private String custom_code;
	private String function;
	private String business_no;
	private String type;
	@XStreamAlias("data")
	private SubmitOrderRQData data;
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getAuth_token() {
		return auth_token;
	}
	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}
	public String getCustom_code() {
		return custom_code;
	}
	public void setCustom_code(String custom_code) {
		this.custom_code = custom_code;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getBusiness_no() {
		return business_no;
	}
	public void setBusiness_no(String business_no) {
		this.business_no = business_no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public SubmitOrderRQData getData() {
		return data;
	}
	public void setData(SubmitOrderRQData data) {
		this.data = data;
	}
}
