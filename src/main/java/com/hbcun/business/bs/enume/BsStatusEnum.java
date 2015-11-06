package com.hbcun.business.bs.enume;

/**
 * 保税状态
 * @author liuxy
 * @date 2015年9月6日下午4:43:08
 * @since 1.7
 */
public enum BsStatusEnum {
	/**创建成功**/
	STATUS_WMS_CREATE ("WMS_CREATE"),
	/**取消订单**/
	STATUS_WMS_CANCEL ("WMS_CANCEL"),
	/**到货补发**/
	STATUS_WMS_PENDDING ("WMS_PENDDING"),
	/**发送订单成功**/
	STATUS_WMS_ORDER_NOTIFY_SUCCESS ("WMS_ORDER_NOTIFY_SUCCESS"),
	/**三单通过**/
	STATUS_WMS_CLEARED ("WMS_CLEARED"),
	/**三单通过**/
	STATUS_WMS_CUSTOMS_ACCEPT ("WMS_CUSTOMS_ACCEPT"),
	/**三单拒绝**/
	STATUS_WMS_CUSTOMS_REJECT ("WMS_CUSTOMS_REJECT"),
	/**库存不足**/
	STATUS_WMS_INVENTORY_LACK ("WMS_INVENTORY_LACK"),
	/**接单**/
	STATUS_WMS_ACCEPT ("WMS_ACCEPT"),
	/**打印**/
	STATUS_WMS_PRINT ("WMS_PRINT"),
	/**拣货**/
	STATUS_WMS_PICK ("WMS_PICK"),
	/**复核**/
	STATUS_WMS_CHECK ("WMS_CHECK"),
	/**打包**/
	STATUS_WMS_PACKAGE ("WMS_PACKAGE"),
	/**已发货**/
	STATUS_WMS_SHIPED ("WMS_SHIPED"),
	/**缺损**/
	STATUS_WMS_PICK_LACK ("WMS_PICK_LACK"),
	/**接单失败**/
	STATUS_WMS_REJECT ("WMS_REJECT"),
	/**订单发货失败**/
	STATUS_WMS_FAILED ("WMS_FAILED");
	
	private String status;
	
	private BsStatusEnum(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}

