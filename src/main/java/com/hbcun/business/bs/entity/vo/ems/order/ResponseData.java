package com.hbcun.business.bs.entity.vo.ems.order;

import java.io.Serializable;
import java.util.List;

import com.hbcun.business.bs.entity.vo.BaseVo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ResponseData extends BaseVo {

	private static XStream xstream;

	@XStreamAlias("Head")
	private Head head;

	@XStreamAlias("Body")
	private Body body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	protected static XStream getXStream1() {

		if (xstream == null) {
			xstream = new XStream(new DomDriver());
			xstream.autodetectAnnotations(true);
			xstream.alias("Msg", ResponseData.class);
		}
		return xstream;
	}

	public static ResponseData fromXML(String xml) {

		return (ResponseData) getXStream1().fromXML(xml);
	}

	public static class Head implements Serializable {

		private static final long serialVersionUID = 1L;

		@XStreamAlias("Result")
		private String result;

		@XStreamAlias("MsgType")
		private String msgType;

		@XStreamAlias("Desc")
		private String desc;

		public Head() {
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		public String getMsgType() {
			return msgType;
		}

		public void setMsgType(String msgType) {
			this.msgType = msgType;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

	}

	public static class Body implements Serializable {

		private static final long serialVersionUID = 1L;

		@XStreamAlias("Responselist")
		private List<Response> responseList;

		public List<Response> getResponseList() {
			return responseList;
		}

		public void setResponseList(List<Response> responseList) {
			this.responseList = responseList;
		}
	}

	@XStreamAlias("Response")
	public static class Response implements Serializable {

		private static final long serialVersionUID = 1L;
		private String V_REMARK;
		private String V_STATUS;
		private String V_NO;

		public String getV_REMARK() {
			return V_REMARK;
		}

		public void setV_REMARK(String v_REMARK) {
			V_REMARK = v_REMARK;
		}

		public String getV_STATUS() {
			return V_STATUS;
		}

		public void setV_STATUS(String v_STATUS) {
			V_STATUS = v_STATUS;
		}

		public String getV_NO() {
			return V_NO;
		}

		public void setV_NO(String v_NO) {
			V_NO = v_NO;
		}
	}

}
