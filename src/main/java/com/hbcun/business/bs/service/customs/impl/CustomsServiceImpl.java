package com.hbcun.business.bs.service.customs.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbcun.business.bs.entity.vo.param.BsOrderParam;
import com.hbcun.business.bs.enume.BsTrackLogEnum;
import com.hbcun.business.bs.service.BsJobExecuteStatusService;
import com.hbcun.business.bs.service.BsWmsOrderTrackLogService;
import com.hbcun.business.bs.service.customs.CustomsService;
import com.hbcun.business.order.service.OrderInfoService;
import com.hbcun.common.entity.po.BsJobExecuteStatus;
import com.hbcun.common.entity.po.OrderInfo;
import com.hbcun.common.entity.vo.ResultVo;
import com.hbcun.common.sys.exception.InvalidRequestException;

public class CustomsServiceImpl implements CustomsService {

	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private BsJobExecuteStatusService bsJobExecuteStatusService;
	
	@Autowired
	private BsWmsOrderTrackLogService bsWmsOrderTrackLogService;
	
	@Override
	public ResultVo update_sendOrderData(BsOrderParam param) throws InvalidRequestException {
		
		OrderInfo info = orderInfoService.selectByPrimaryKey(param.getOrderId());
		if(info.getPayNumber() == null){
			throw new InvalidRequestException("不存在支付信息");
		}
		//微信支付
		if(info.getPayType() == 2) {
			
			ResultVo vo = TenpayCustomsServiceImpl.update_sendOrderData(info);
			if(vo.getResult()) {
				whenSuccess(param.getOrderCode());
			}
			return vo;
		}
		throw new InvalidRequestException("暂不支持其他支付报关");
	}

	
	private void whenSuccess(String orderCode) {
		
		BsJobExecuteStatus record = new BsJobExecuteStatus();
		record.setOrderCode(orderCode);
		record.setIsPayinfoSent(true);
		record.setUpdateTime(new Date());
		bsJobExecuteStatusService.updateByPrimaryKeySelective(record);
		//
		bsWmsOrderTrackLogService.add(orderCode, BsTrackLogEnum.PAYINFO_CUSTOM_SUCCESS.content(), "WX");
		
	}
}
