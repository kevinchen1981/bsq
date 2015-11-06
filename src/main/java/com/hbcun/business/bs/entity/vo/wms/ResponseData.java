package com.hbcun.business.bs.entity.vo.wms;

import com.hbcun.business.bs.entity.vo.BaseVo;

public class ResponseData extends BaseVo {

	private static final long serialVersionUID = 1L;
	boolean success;
	String errorCode = "";
	String errorMsg = "";
	
	public ResponseData() {
	}
	
	public ResponseData(boolean success) {
		
		this.success = success;
	}
	
	public ResponseData(boolean success, String errorCode, String errorMsg) {
		this.success = success;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
//	public void setSuccess(String success) {
//		this.success = Boolean.valueOf(success);
//	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}