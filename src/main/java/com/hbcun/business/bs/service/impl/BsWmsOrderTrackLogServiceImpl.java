package com.hbcun.business.bs.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hbcun.business.bs.service.BsWmsOrderTrackLogService;
import com.hbcun.common.dao.mapper.BsWmsOrderTrackLogMapper;
import com.hbcun.common.entity.po.BsWmsOrderTrackLog;
import com.hbcun.common.entity.po.BsWmsOrderTrackLogCondition;

public class BsWmsOrderTrackLogServiceImpl extends BaseService implements BsWmsOrderTrackLogService {

	@Autowired
	private BsWmsOrderTrackLogMapper bsWmsOrderTrackLogMapper;
	
	@Override
	public int add(String orderCode, String content, String wmsOperator) {
		
		if(StringUtils.isEmpty(content)) {
			return 0;
		}
		Date date = new Date();
		BsWmsOrderTrackLog entity = new BsWmsOrderTrackLog();
		entity.setOrderCode(orderCode);
		entity.setContent(content);
		entity.setCreateTime(date);
		entity.setUpdateTime(date);
		entity.setOperator(wmsOperator);
		return bsWmsOrderTrackLogMapper.insertSelective(entity);
	}
	
	@Override
	public List<BsWmsOrderTrackLog> getLogList(String orderCode) {
		
		BsWmsOrderTrackLogCondition condition = new BsWmsOrderTrackLogCondition();
		condition.createCriteria().andOrderCodeEqualTo(orderCode);
		condition.setOrderByClause("create_time desc");
		return bsWmsOrderTrackLogMapper.selectByExample(condition);
	}
}
