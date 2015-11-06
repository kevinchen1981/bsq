package com.hbcun.business.bs.entity.vo.eplink.callback26;

import java.io.Serializable;

import com.hbcun.business.bs.entity.vo.BaseVo;
import com.hbcun.business.bs.entity.vo.eplink.Head;
import com.hbcun.business.bs.entity.vo.eplink.JkfSign;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;

@XStreamAlias("mo")
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
			xstream.alias("jkfSign", JkfSign.class);
			xstream.alias("jkfGoodsDeclar", JkfGoodsDeclar.class);
			xstream.ignoreUnknownElements();	//忽略未知元素
		}
		return xstream;
	}
	
	public static CallBack fromXML(String xml){
		return (CallBack) getXStream1().fromXML(xml);
	}

	@XStreamAlias("body")
	public static class Body implements Serializable {

		private static final long serialVersionUID = 1L;
		private JkfSign jkfSign;
		private JkfGoodsDeclar jkfGoodsDeclar;

		public Body() {
		}

		public JkfSign getJkfSign() {
			return jkfSign;
		}

		public void setJkfSign(JkfSign jkfSign) {
			this.jkfSign = jkfSign;
		}

		public JkfGoodsDeclar getJkfGoodsDeclar() {
			return jkfGoodsDeclar;
		}

		public void setJkfGoodsDeclar(JkfGoodsDeclar jkfGoodsDeclar) {
			this.jkfGoodsDeclar = jkfGoodsDeclar;
		}

	}

	@XStreamAlias("jkfGoodsDeclar")
	public static class JkfGoodsDeclar implements Serializable {

		private static final long serialVersionUID = 1L;
		/**个人物品申报单编号**/
		private String personalGoodsFormNo;
		/**个人物品申报单状态**/
		private String approveResult;
		/**审批意见**/
		private String approveComment;
		/**处理时间.格式要求：20140623101024**/
		private String processTime;
		
		public JkfGoodsDeclar() {
		}

		public String getPersonalGoodsFormNo() {
			return personalGoodsFormNo;
		}

		public void setPersonalGoodsFormNo(String personalGoodsFormNo) {
			this.personalGoodsFormNo = personalGoodsFormNo;
		}

		public String getApproveResult() {
			return approveResult;
		}

		public void setApproveResult(String approveResult) {
			this.approveResult = approveResult;
		}

		public String getApproveComment() {
			return approveComment;
		}

		public void setApproveComment(String approveComment) {
			this.approveComment = approveComment;
		}

		public String getProcessTime() {
			return processTime;
		}

		public void setProcessTime(String processTime) {
			this.processTime = processTime;
		}
		
	}

	
	public static void main(String[] args) {
		
		String xml = "<?xml version=\"1.0\"?><mo version=\"1.0.0\"><head><businessType>PERSONAL_GOODS_DECLAR</businessType></head><body><jkfSign><companyCode>接收方海关十位数编码</companyCode><businessNo>业务编号</businessNo></jkfSign><jkfGoodsDeclar><personalGoodsFormNo>个人物品申报单编号</personalGoodsFormNo><approveResult>个人物品申报单状态（见参数表）</approveResult><approveComment>审批意见</approveComment><processTime>处理时间</processTime></jkfGoodsDeclar></body></mo>";
		System.out.println(fromXML(xml).toString());
	}
}
