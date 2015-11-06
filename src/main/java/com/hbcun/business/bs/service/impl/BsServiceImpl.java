package com.hbcun.business.bs.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hbcun.business.bs.dao.mapper.BusinessBsMapper;
import com.hbcun.business.bs.entity.vo.BsBhListVo;
import com.hbcun.business.bs.entity.vo.BsZyListVo;
import com.hbcun.business.bs.entity.vo.TaskBsOrderListVo;
import com.hbcun.business.bs.entity.vo.param.ListParam;
import com.hbcun.business.bs.enume.BsStatusEnum;
import com.hbcun.business.bs.service.BsService;
import com.hbcun.business.bs.service.BsWmsOrderService;
import com.hbcun.common.entity.po.BsWmsOrder;
import com.hbcun.common.entity.vo.QueryResult;
import com.hbcun.common.entity.vo.ResultVo;
import com.hbcun.common.sys.excel.ResolveExcel;
import com.hbcun.common.sys.util.CommonUtil;
import com.hbcun.common.sys.util.DateUtil;
import com.hbcun.common.sys.util.OrderUtils;

public class BsServiceImpl implements BsService {
	
	@Autowired
	private BusinessBsMapper businessBsMapper;
	
	@Autowired
	private BsWmsOrderService bsWmsOrderService;
	
	@Override
	public QueryResult<BsBhListVo> getBhScrollData(ListParam param, int pageIndex, int pageSize) {
		
		int start = getPageStart(pageIndex, pageSize);
		List<BsBhListVo> list = businessBsMapper.getBhScrollData(param, start, pageSize);
		int totalRecord = businessBsMapper.getBhScrollDataCounts(param);
		return new QueryResult<BsBhListVo>(list, totalRecord);
	}
	
	@Override
	public QueryResult<BsZyListVo> getZyScrollData(ListParam param, int pageIndex, int pageSize) {
		
		int start = getPageStart(pageIndex, pageSize);
		List<BsZyListVo> list = businessBsMapper.getZyScrollData(param, start, pageSize);
		int totalRecord = businessBsMapper.getZyScrollDataCounts(param);
		return new QueryResult<BsZyListVo>(list, totalRecord);
	}
	
	@Override
	public ResultVo updateImportOrder(InputStream is) {
		
		ResultVo vo = new ResultVo();
		List<Map<Integer, String>> list;
		try {
			list = ResolveExcel.readExcel(is);
		} catch (IOException e1) {
			vo.setMessage(e1.getMessage());
			return vo;
		}
		BsWmsOrder order = null;
		StringBuilder sb = new StringBuilder();
		String userOrderId = null;
		String userPackageId = null;
		for (Map<Integer, String> map : list) {
			try {
				String handlerDate = map.get(0);
				userOrderId = map.get(1);
				userPackageId = map.get(2);
				String weight = map.get(12);
				String flightNumber = map.get(13);
				String orderCode = OrderUtils.getOrderCode(userOrderId, userPackageId);
				order = new BsWmsOrder();
				order.setHandlerDate(DateUtil.parseDate(handlerDate));
				order.setOrderCode(orderCode);
				order.setStatus(BsStatusEnum.STATUS_WMS_SHIPED.getStatus());
				order.setWeight(StringUtils.isNotEmpty(weight) ? Double.valueOf(weight).intValue() : 0);
				order.setFlightNumber(flightNumber);
				order.setUpdateTime(new Date());
				int result = bsWmsOrderService.updateByPrimaryKeySelective(order);
				if(result == 0) {
					sb.append("订单号：" + userOrderId + " 包裹号：" + userPackageId + "不存在").append("\r\n");
				}
			} catch (NumberFormatException e) {
				sb.append("订单号：" + userOrderId + " 包裹号：" + userPackageId + e.getMessage()).append("\r\n");
			}
		}
		vo.setResult(true);
		vo.setMessage(sb.toString());
		return vo;
	}
	
	@Override
	public List<TaskBsOrderListVo> getOrderNotSendToWms() {
		
		return businessBsMapper.getOrderNotSendToWms();
	}
	
	@Override
	public List<TaskBsOrderListVo> getEMSNumNotGetList() {
		
		return businessBsMapper.getEMSNumNotGetList();
	}
	
	@Override
	public List<TaskBsOrderListVo> getOrderNotSentToEpList() {
		
		return businessBsMapper.getOrderNotSentToEpList();
	}
	
	@Override
	public List<TaskBsOrderListVo> getOrderNotSendToPayList() {
		
		return businessBsMapper.getOrderNotSendToPayList();
	}

	@Override
	public List<TaskBsOrderListVo> getOrderNotSendToEmsList() {
		
		return businessBsMapper.getOrderNotSendToEmsList();
	}

	@Override
	public List<TaskBsOrderListVo> getOrderListByStatus(BsStatusEnum status) {
		
		if(status == null) {
			return new ArrayList<TaskBsOrderListVo>(0);
		}
		return businessBsMapper.getOrderListByStatus(status.getStatus());
	}

	
	
	private int getPageStart(int pageIndex, int pageSize) {
		return CommonUtil.page2start(CommonUtil.page(pageIndex), pageSize);
	}
}
