package com.hbcun.business.bs.service.ems;

import com.hbcun.business.bs.entity.vo.ems.order.Manifest;
import com.hbcun.business.bs.entity.vo.param.BsOrderParam;
import com.hbcun.common.entity.vo.ResponseEntity;
import com.hbcun.common.sys.exception.InvalidRequestException;

public interface EmsService {

	String EMS_OPERATOR = "ems";

	/**
	 * 取单号。
	 * @param orderCode
	 * @return 返回ems单号
	 */
	String update_getBillNumBySys(BsOrderParam param) throws InvalidRequestException ;

	/**
	 * 获取ems订单信息
	 * @param orderId
	 * @param suborderno
	 * @param modifyMark
	 * @return
	 */
	Manifest genManiFest(BsOrderParam param, String modifyMark);

	/**
	 * 推送EMS订单信息
	 * @param manifest
	 * @param orderCode
	 * @return
	 */
	ResponseEntity update_sendLogistics(Manifest manifest, String orderCode);

	/**
	 * 推送EMS订单信息
	 * @param manifest
	 * @param orderCode
	 * @return
	 */
	ResponseEntity update_sendLogistics(BsOrderParam param);

	
	
}
