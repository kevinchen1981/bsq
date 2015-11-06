package com.hbcun.business.bs.entity.vo.ems.bigaccountdata;

import com.hbcun.business.bs.entity.vo.BaseVo;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("XMLInfo")
public class GetBillNum extends BaseVo {

	private static final long serialVersionUID = 1L;
	private String sysAccount;	//大客户号
	private String passWord;	//大客户密码
	private String appKey;		//对接方平台id
	private String businessType;	//业务类型 1为标准快递，4为经济快递（传数字）
	private int billNoAmount = 1;	//需要详情单数量，1-100之间最多输入100
	
	public GetBillNum() {
	}
	
	public GetBillNum(String sysAccount, String passWord, String appKey, String businessType) {
		this.sysAccount = sysAccount;
		this.passWord = passWord;
		this.appKey = appKey;
		this.businessType = businessType;
	}

	/**
	 * @return the sysAccount
	 */
	public String getSysAccount() {
		return sysAccount;
	}

	/**
	 * @param sysAccount the sysAccount to set
	 */
	public void setSysAccount(String sysAccount) {
		this.sysAccount = sysAccount;
	}

	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * @return the appKey
	 */
	public String getAppKey() {
		return appKey;
	}

	/**
	 * @param appKey the appKey to set
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	/**
	 * @return the businessType
	 */
	public String getBusinessType() {
		return businessType;
	}

	/**
	 * @param businessType the businessType to set
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/**
	 * @return the billNoAmount
	 */
	public int getBillNoAmount() {
		return billNoAmount;
	}

	/**
	 * @param billNoAmount the billNoAmount to set
	 */
	public void setBillNoAmount(int billNoAmount) {
		this.billNoAmount = billNoAmount;
	}
	
	
}
