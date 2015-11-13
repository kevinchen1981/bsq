package com.hbcun.business.bs.omstrans.entity;

import java.io.Serializable;

import com.hbcun.business.bs.entity.vo.BaseVo;

public class ServiceResult extends BaseVo {
	public ServiceResult(){
		
	}
	private String business_no;
	private String business_type;
	private String chk_mark;
	private String notice_time;
	private String notice_content;
	private String way_bills;
	public String getBusiness_no() {
		return business_no;
	}
	public void setBusiness_no(String business_no) {
		this.business_no = business_no;
	}
	public String getBusiness_type() {
		return business_type;
	}
	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}
	public String getChk_mark() {
		return chk_mark;
	}
	public void setChk_mark(String chk_mark) {
		this.chk_mark = chk_mark;
	}
	public String getNotice_time() {
		return notice_time;
	}
	public void setNotice_time(String notice_time) {
		this.notice_time = notice_time;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public String getWay_bills() {
		return way_bills;
	}
	public void setWay_bills(String way_bills) {
		this.way_bills = way_bills;
	}
}
