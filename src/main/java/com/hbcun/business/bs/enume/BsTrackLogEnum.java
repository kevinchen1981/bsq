package com.hbcun.business.bs.enume;

public enum BsTrackLogEnum {
	
	/**保税备货 已创建 订单已处理，等待海关清关**/
	content1_1("订单已处理，等待海关清关"),

	/**保税直邮 已创建 订单已处理，等待海外仓发货**/
	content1_2("订单已处理，等待海外仓发货"),
	
	
	/**缺货通知了用户说愿意等，点到货补发后 -> 已通知用户缺货，到货补发**/
	content5("已通知用户缺货，到货补发"),
	
	/**取单号成功 -> 获取EMS运单号（成功）**/
	GET_BILLNUM_SUCCESS("获取EMS运单号（成功）"),
	
	
	/**推送运单信息给EMS成功 -> "推送运动信息至EMS（成功）"**/
	ORDER_SEND_EMS_SUCCESS("推送运动信息至EMS（成功）"),
	
	/**推送运单信息给EMS后 -> "推送运动信息至EMS（失败）"**/
	ORDER_SEND_EMS_FAIL("推送运动信息至EMS（失败）"),
	
	/**电子口岸 异常 **/
	EPLINK_EXCEPTION("电子口岸异常:"),
	
	/**电子口岸2.2接口调用后 -> "已推送订单信息至电子口岸"**/
	ORDER_SEND_TO_EPLINK("已推送订单信息至电子口岸"),
	
	/**电子口岸2.3接口调用后 -> "已推送个人物品申报单至电子口岸"**/
	PACKAGE_SEND_TO_EPLINK("已推送个人物品申报单至电子口岸"),
	
	/**海关清关完成，等待仓库发货**/
	EPLINK_CLEARED_1("海关清关完成，等待仓库发货"),

	EPLINK_CLEARED_2("海关清关完成，进入国内派送(EMS单号:"),
	
	/**调用网仓3.2接口后成功 -> 推送发货单给网仓（成功）**/
	ORDER_SEND_TO_WMS_SUCCESS("推送发货单给网仓（成功）"),
	
	/**调用网仓3.2接口后缺货 -> "推送发货单给网仓（商品缺货）"**/
	ORDER_SEND_TO_WMS_LACK("推送发货单给网仓（商品缺货）"),
	
	/**网仓取消订单3.0接口调用后 -> "订单已取消"**/
	WMS_ORDER_CANCELED("订单已取消"),
	

	/**调用网仓3.3接口后成功 -> 推送发货指令给网仓（成功）**/
	DO_PACKAGE_SUCCESS("推送发货指令给网仓（成功）"),
	
	/**网仓3.6回调通知我们拣选缺货 -> 网仓拣选缺货**/
	DO_PACKAGE_LACK("网仓拣选缺货"),
	
	/**网仓3.6回调通知我们已接单 -> "网仓已接单"**/
	WMS_ACCEPT("网仓已接单"),
	
	/**网仓3.6回调通知我们已接单 -> "网仓打包"**/
	WMS_PACKAGED("网仓打包"),

	/**仓库已发货，请耐心等待(EMS单号:**/
	SHIPPED_1("仓库已发货，请耐心等待(EMS单号:"),

	/**海外仓已发货(航班号:**/
	SHIPPED_2("仓库已发货，请耐心等待(EMS单号:"),
	
	/**网仓接单失败**/
	WMS_REJECT("网仓接单失败"),
	
	PAYINFO_CUSTOM_SUCCESS("支付信息报关成功")
	
	
	;
	
	private String content;
	
	private BsTrackLogEnum (String content) {
		this.content = content;
	}
	
	public String content() {
		return content;
	}
}
