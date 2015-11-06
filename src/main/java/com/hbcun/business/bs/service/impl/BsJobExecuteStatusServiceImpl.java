package com.hbcun.business.bs.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbcun.business.bs.service.BsJobExecuteStatusService;
import com.hbcun.common.dao.mapper.BsJobExecuteStatusMapper;
import com.hbcun.common.entity.po.BsJobExecuteStatus;
import com.hbcun.common.sys.util.OrderUtils;

public class BsJobExecuteStatusServiceImpl implements BsJobExecuteStatusService {

	@Autowired
	private BsJobExecuteStatusMapper bsJobExecuteStatusMapper;

	@Override
	public int add(String orderId, String packageId) {
		
		Date date = new Date();
		String orderCode = OrderUtils.getOrderCode(orderId, packageId);
		BsJobExecuteStatus record = new BsJobExecuteStatus();
		record.setOrderCode(orderCode);
		record.setIsOrderSentToWms(false);
		record.setIsEmsNoGot(false);
		record.setIsOrderSentToEp(false);
		record.setIsLogisticsSentToEms(false);;
		record.setIsPayinfoSent(false);
		record.setIsGoodsDeclaredToEp(false);
		record.setIsPackageNotifyToWms(false);
		record.setCreateTime(date);
		record.setUpdateTime(date);
		return bsJobExecuteStatusMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(BsJobExecuteStatus record) {
		
		record.setUpdateTime(new Date());
		return bsJobExecuteStatusMapper.updateByPrimaryKeySelective(record);
	}
	
}
