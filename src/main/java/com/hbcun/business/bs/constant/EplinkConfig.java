package com.hbcun.business.bs.constant;

public interface EplinkConfig extends ApplicationConfig {

	String AES_KEY = "qZe60QZFxuirub2ey4+7+Q\\=\\=";

	String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCffOeIVYut9jW5w1L5uKX4aDvd837a8JhaWm5S8YqNQfgEmfD9T+rDknXLqMT+DXeQAqGo4hBmcbej1aoMzn6hIJHk3/TfTAToNN8fgwDotHewsTCBbVkQWtDTby3GouWToVsRi1i/A0Vfb0+xM8MnF46DdhhrnZrycERBSbyrcwIDAQAB";

	/** 发送方海关十位数编码,企业备案编码 **/
	String companyCode = "3301968FA2";

	/** 发送方海关十位数编码 **/
	String companyName = "杭州古罗巴鲁科技有限公司";

	/** 电商企业编码 **/
	String eCommerceCode = "2204144";

	/** 电商企业名称 **/
	String eCommerceName = "香港海豹村国际有限公司";

	/** 支付宝 **/
	String zfb_payCompanyCode = "ZF14021901";

	/** 微信 **/
	String wx_payCompanyCode = "ZF14120401";

	/** EMS编号 **/
	String logisCompanyCode = "3301980101";

	/** EMS编号 **/
	String logisCompanyName = "浙江省邮政速递物流有限公司";

	/** 物流 **/
	String HanDaCompanyCode = "3301560005";

	/** 物流名称 **/
	String HanDaCompanyName = "杭州汉达国际货运代理有限公司";

	/** 申请单编号 **/
	String applicationFormNo = "A33015600052014120000032";

	/** 账册编号 **/
	String accountBookNo = "H29914001191";

	
	/**
	 * 测试环境
	 */
	String WEB_SERVICE_URL = "http://122.224.230.4:18003/newyorkWS/ws/ReceivedDeclare?wsdl";

	/**
	 * 正式环境
	 */
//	String WEB_SERVICE_URL = "http://122.224.69.179:8080/newyorkWS/ws/ReceivedDeclare?wsdl";
	
}
