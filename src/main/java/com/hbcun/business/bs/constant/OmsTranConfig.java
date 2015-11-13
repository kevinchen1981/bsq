package com.hbcun.business.bs.constant;

public interface OmsTranConfig extends ApplicationConfig {

	/**获取运单号**/
	String GET_BILL_NUM_URL = "http://os.ems.com.cn:8081/zkweb/bigaccount/getBigAccountDataAction.do?method=getBillNumBySys&xml=";

	String sysAccount = "33010270724000";
    
    String passWord = "e10adc3949ba59abbe56e057f20f883e";
    
    String appKey = "Sc38Ef743A5421A01";
    
    String messageType = "200200";
    
    String username = "admin";
    
    String ourcompanyName = "hbcun";
    
    String sendID = "33010270724000";
    
    /**
     * 授权码
     */
    String authorizationCode = "SQM123456789";
    String appID = "test";


    /*
     * 测试地址
     */
    String WEB_SERVICE_URL = "http://115.29.202.252:8091/omsTran/api/";
    String OmsTransServer = "OmsTransServer";
    String AuthorUrl = "auth.htm";
    String SubmitOrderURL = "post.htm";
    /*
     * 正式地址
     */
//    String WEB_SERVICE_URL = "http://211.156.193.152:8080/hzCWebservice/LoadDataPort?wsdl";
}
