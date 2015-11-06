package com.hbcun.business.bs.constant;
/**
 * 网仓信息
 * @author liuxy
 * @date 2015年9月6日下午3:22:08
 * @since 1.7
 */
public interface WmsConfig extends ApplicationConfig {

	/** 密钥 **/
//	String secret = "wsdfvgbnmklop0987uytrdesa32rtyu";
	String secret = "hbcun";

	/** 平台id，由网仓定义 **/
	String platform_id = "260";

	/** 平台定义的仓库编码 **/
	String store_code = "HZBS01";

	/** 平台定义的商家编码 **/
	String seller_user_id = "hbcun_hk";

	/** 仓库在平台的id，由平台定义 **/
	String wms_id = "1";

	String url = "http://testapi.iscs.com.cn/bsbusiness";	//测试环境
//	String url = "http://ecmapi.iscs.com.cn/bsbusiness";

}
