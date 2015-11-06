package com.hbcun.business.bs.entity.vo.ems.bigaccountdata;

import java.io.Serializable;
import java.util.List;

import com.hbcun.business.bs.entity.vo.BaseVo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 * 接收 ems 返回数据
 * @author liuxy
 */
public class ResponseData extends BaseVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private static XStream xstream;
	
	private Integer result;
	private String errorDesc;
	private String errorCode;
	private List<AssignId> assignIds;

	public ResponseData() {
	}

	
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}



	/**
	 * @return the result
	 */
	public Integer getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(Integer result) {
		this.result = result;
	}

	/**
	 * @return the errorDesc
	 */
	public String getErrorDesc() {
		return errorDesc;
	}

	/**
	 * @param errorDesc
	 *            the errorDesc to set
	 */
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	/**
	 * @return the assignIds
	 */
	public List<AssignId> getAssignIds() {
		return assignIds;
	}

	/**
	 * @param assignIds
	 *            the assignIds to set
	 */
	public void setAssignIds(List<AssignId> assignIds) {
		this.assignIds = assignIds;
	}
	
	private static XStream getXStream1(){
		
		if(xstream == null){
			xstream = new XStream(new DomDriver());
			xstream.autodetectAnnotations(true);
			xstream.alias("response", ResponseData.class);
			xstream.alias("assignId", AssignId.class);
		}
		return xstream;
	}
	
	public static ResponseData fromXML(String data){
		
		return (ResponseData) getXStream1().fromXML(data);
	}
	
	public static class AssignId implements Serializable {

		private static final long serialVersionUID = 1L;
		private String billno;

		public AssignId() {
		}

		public String getBillno() {
			return billno;
		}

		public void setBillno(String billno) {
			this.billno = billno;
		}
	}
}
