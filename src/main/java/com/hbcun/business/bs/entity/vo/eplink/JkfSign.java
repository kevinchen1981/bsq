package com.hbcun.business.bs.entity.vo.eplink;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 签名信息
 * @author liuxy
 */
@XStreamAlias("jkfSign")
public class JkfSign implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**发送方海关十位数编码**/
	private String companyCode;
	/**个人物品申报单预录入号**/
	private String businessNo;
	/**业务类型**/
	private String businessType;
	/**申报类型,固定填写1**/
	private String declareType = "1";
	/**备注**/
	private String note = "";

	public JkfSign() {
	}

	public JkfSign(String companyCode, String businessNo, String businessType) {
		this.companyCode = companyCode;
		this.businessNo = businessNo;
		this.businessType = businessType;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getDeclareType() {
		return declareType;
	}

	public void setDeclareType(String declareType) {
		this.declareType = declareType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}