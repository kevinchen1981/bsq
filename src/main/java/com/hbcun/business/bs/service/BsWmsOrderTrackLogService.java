package com.hbcun.business.bs.service;

import java.util.List;

import com.hbcun.common.entity.po.BsWmsOrderTrackLog;

public interface BsWmsOrderTrackLogService {

	int add(String orderCode, String content, String wmsOperator);

	List<BsWmsOrderTrackLog> getLogList(String orderCode);

}
