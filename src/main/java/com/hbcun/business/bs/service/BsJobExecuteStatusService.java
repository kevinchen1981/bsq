package com.hbcun.business.bs.service;

import com.hbcun.common.entity.po.BsJobExecuteStatus;

public interface BsJobExecuteStatusService {

	int updateByPrimaryKeySelective(BsJobExecuteStatus record);

	int add(String orderId, String packageId);

}
