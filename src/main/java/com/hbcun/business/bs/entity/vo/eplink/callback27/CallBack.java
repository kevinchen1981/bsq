package com.hbcun.business.bs.entity.vo.eplink.callback27;

import java.io.Serializable;
import java.util.List;

import com.hbcun.business.bs.entity.vo.BaseVo;
import com.hbcun.business.bs.entity.vo.eplink.Head;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class CallBack extends BaseVo {

	private static final long serialVersionUID = 1L;
	private static XStream xstream;
	private Head head;
	private Body body;
	
	public CallBack() {
	}

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
	
	private static XStream getXStream1() {
		if (xstream == null) {
			xstream = new XStream(new DomDriver());
			xstream.autodetectAnnotations(true);
			xstream.alias("mo", CallBack.class);
			xstream.alias("head", Head.class);
			xstream.alias("body", Body.class);
			xstream.alias("jkfResult", JkfResult.class);
			xstream.alias("jkfResultDetail", JkfResultDetail.class);
			xstream.ignoreUnknownElements();	//忽略未知元素
		}
		return xstream;
	}
	
	public static CallBack fromXML(String xml){
		return (CallBack) getXStream1().fromXML(xml);
	}
	
	public static class Body implements Serializable {

		private static final long serialVersionUID = 1L;
		private List<JkfResult> list;
		
		public Body() {
		}

		public List<JkfResult> getList() {
			return list;
		}

		public void setList(List<JkfResult> list) {
			this.list = list;
		}
		
	}
	
	public static class JkfResult implements Serializable{

		private static final long serialVersionUID = 1L;
		private String companyCode;
		private String businessNo;
		private String businessType;
		private String declareType;
		private String chkMark;
		private String noticeDate;
		private String noticeTime;
		private String note;
		private List<JkfResultDetail> resultList;
		
		public JkfResult() {
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

		public String getChkMark() {
			return chkMark;
		}

		public void setChkMark(String chkMark) {
			this.chkMark = chkMark;
		}

		public String getNoticeDate() {
			return noticeDate;
		}

		public void setNoticeDate(String noticeDate) {
			this.noticeDate = noticeDate;
		}

		public String getNoticeTime() {
			return noticeTime;
		}

		public void setNoticeTime(String noticeTime) {
			this.noticeTime = noticeTime;
		}

		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
		}

		public List<JkfResultDetail> getResultList() {
			return resultList;
		}

		public void setResultList(List<JkfResultDetail> resultList) {
			this.resultList = resultList;
		}
	}
	
	public static class JkfResultDetail implements Serializable {

		private static final long serialVersionUID = 1L;
		private String resultInfo;

		public JkfResultDetail() {
		}

		public String getResultInfo() {
			return resultInfo;
		}

		public void setResultInfo(String resultInfo) {
			this.resultInfo = resultInfo;
		}

	}

	public static void main(String[] args) {
		
		String xml = "<?xml version=\"1.0\"?><mo version=\"1.0.0\"><head><businessType>RESULT</businessType></head><body><list><jkfResult><companyCode>企业备案编码</companyCode><businessNo>业务编号</businessNo><businessType>业务类型</businessType><declareType>申报类型</declareType><chkMark>处理结果</chkMark><noticeDate>通知日期</noticeDate><noticeTime>通知时间</noticeTime><note>备注</note><resultList><jkfResultDetail><resultInfo>处理结果文字信息</resultInfo></jkfResultDetail><jkfResultDetail><resultInfo>处理结果文字信息</resultInfo></jkfResultDetail></resultList></jkfResult></list></body></mo>";
		System.out.println(fromXML(xml));
	}
}
