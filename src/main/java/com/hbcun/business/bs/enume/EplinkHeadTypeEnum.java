package com.hbcun.business.bs.enume;

public enum EplinkHeadTypeEnum {

	/**商品订单**/
	BUSINESS_TYPE_IMPORTORDER("IMPORTORDER"),
	/**个人物品申报单**/
	BUSINESS_TYPE_PERSONAL_GOODS_DECLAR("PERSONAL_GOODS_DECLAR");

	private String type;
	
	private EplinkHeadTypeEnum(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
