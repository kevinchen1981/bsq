package com.hbcun.business.bs.util;

import com.hbcun.business.bs.constant.EplinkConfig;
import com.hbcun.business.product.enume.ProviderInTypeEnum;
import com.hbcun.common.sys.exception.InvalidRequestException;

public class CommonUtils {

	/**
	 * 获取支付公司编码
	 * @param payType
	 * @return
	 */
	public static String getPayCompanyCode(Short payType){
		
		if(payType == null) {
			throw new InvalidRequestException("支付类型不能为空");
		}
		String code = EplinkConfig.zfb_payCompanyCode;
		if(payType == 3) {
			code = EplinkConfig.wx_payCompanyCode;
		}
		return code;
	}

	/**
	 * 获取支付公司编码
	 * @param payType
	 * @return
	 */
	public static String getImportType(byte inType){

		if(inType == ProviderInTypeEnum.UNDER_BOND.value()) {
			//保税备货
			return "1";
		} else {
			//直邮
			return "0";
		}
	}
	
}
