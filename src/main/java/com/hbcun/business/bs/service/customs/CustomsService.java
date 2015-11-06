package com.hbcun.business.bs.service.customs;

import com.hbcun.business.bs.entity.vo.param.BsOrderParam;
import com.hbcun.common.entity.vo.ResultVo;
import com.hbcun.common.sys.exception.InvalidRequestException;

public interface CustomsService {

	String ALIPAY_OPERATOR = "alipay";

	String TENPAY_OPERATOR = "tenpay";

	/**
	 * 支付信息报关
	 * @param param
	 * @return
	 */
	ResultVo update_sendOrderData(BsOrderParam param) throws InvalidRequestException;
}
