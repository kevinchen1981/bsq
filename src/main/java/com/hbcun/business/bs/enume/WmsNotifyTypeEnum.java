package com.hbcun.business.bs.enume;
/**
 * 网仓定义的接口类型
 * @author liuxy
 * @date 2015年9月6日下午3:14:24
 * @since 1.7
 */
public enum WmsNotifyTypeEnum {
	
	/**3.1仓库订购信息通知**/
	WMS_SUBSCRIPTION_NOTIFY("WMS_SUBSCRIPTION_NOTIFY"),
	/**3.2发货订单通知**/
	WMS_ORDER_NOTIFY("WMS_ORDER_NOTIFY"),
	/**3.3仓库接收到订单后，需要平台通知后才能下发仓库中进行发货及打包操作**/
	NOTIFY_WMS_PACKAGE("NOTIFY_WMS_PACKAGE"),
	/**3.4采购订单通知**/
	PURCHASE_ORDER_NOTIFY("PURCHASE_ORDER_NOTIFY"),
	/**3.6回传订单在仓库中的状态**/
	WMS_ORDER_STATUS_UPLOAD("WMS_ORDER_STATUS_UPLOAD"),
	/**3.7发货订单确认**/
	WMS_ORDER_SHIP_NOTICE("WMS_ORDER_SHIP_NOTICE"),
	/**3.8入库订单入库通知**/
	PURCHASE_ORDER_IN_STOCK_CONFIRM("PURCHASE_ORDER_IN_STOCK_CONFIRM"),
	/**3.10取消订单**/
	CANCEL_WMS_ORDER("CANCEL_WMS_ORDER"),
	/**3.13商品查询**/
	ITEM_QUERY("ITEM_QUERY"),
	/**3.14商品库存查询接口**/
	INVENTORY_QUERY("INVENTORY_QUERY"),
	/**3.15库存盘点**/
	WMS_INVENTORY_CHECK_UPLOAD("WMS_INVENTORY_CHECK_UPLOAD"),
	/**3.18物流跟踪信息**/
	UNLOAD_LOGISTICS_INFO("UNLOAD_LOGISTICS_INFO");
	
	private String type;
	private WmsNotifyTypeEnum(String type){
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
