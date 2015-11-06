package com.hbcun.business.bs.service.wms.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbcun.business.bs.dao.mapper.BusinessBsWmsPurchaseOrderMapper;
import com.hbcun.business.bs.entity.vo.WmsPurchaseOrderVo;
import com.hbcun.business.bs.entity.vo.wms.PurchaseOrderNotify;
import com.hbcun.business.bs.entity.vo.wms.ResponseData;
import com.hbcun.business.bs.enume.WmsNotifyTypeEnum;
import com.hbcun.business.bs.service.wms.WmsPurchaseService;
import com.hbcun.common.dao.mapper.BsWmsPurchaseOrderMapper;
import com.hbcun.common.entity.po.BsWmsPurchaseOrder;
import com.hbcun.common.entity.vo.QueryResult;
import com.hbcun.common.entity.vo.ResultVo;
import com.hbcun.common.sys.log.SystemServiceLog;
import com.hbcun.common.sys.util.CommonUtil;
import com.hbcun.common.sys.util.LoginSessionUtils;

public class WmsPurchaseServiceImpl extends BaseWmsBaseService implements WmsPurchaseService {

	@Autowired
	private BsWmsPurchaseOrderMapper bsWmsPurchaseOrderMapper;

	@Autowired
	private BusinessBsWmsPurchaseOrderMapper businessBsWmsPurchaseOrderMapper;
	
	@Override
	@SystemServiceLog(description="进货单列表")
	public QueryResult<WmsPurchaseOrderVo> getScrollData(int pageIndex, int pageSize) {
		
		int start = CommonUtil.page2start(CommonUtil.page(pageIndex), pageSize);
		List<WmsPurchaseOrderVo> list = businessBsWmsPurchaseOrderMapper.getScrollData(start, pageSize);
		int total = businessBsWmsPurchaseOrderMapper.getScrollDataCounts();
		return new QueryResult<WmsPurchaseOrderVo>(list, total);
	}
	
	@Override
	@SystemServiceLog(description="添加进货单")
	public ResultVo addPurcharseOrder(PurchaseOrderNotify notify) {
		
		ResultVo vo = new ResultVo();
		ResponseData responseData = sendData2Wms(notify, WmsNotifyTypeEnum.PURCHASE_ORDER_NOTIFY, ResponseData.class);
		if(responseData.isSuccess()){
			
			vo.setResult(true);
			BsWmsPurchaseOrder record = new BsWmsPurchaseOrder();
			record.setOrderCode(notify.getOrderCode());
			record.setRequestData(notify.toString());
			record.setCreateTime(new Date());
			record.setUpdateTime(new Date());
			record.setOperator(LoginSessionUtils.getLoginUserAccount());
			bsWmsPurchaseOrderMapper.insertSelective(record);
			return vo;
		}
		vo.setMessage(responseData.getErrorMsg());
		return vo;
	}
	
	@Override
	@SystemServiceLog(description="查看详情")
	public BsWmsPurchaseOrder selectByPrimaryKey(String orderCode) {
		
		return bsWmsPurchaseOrderMapper.selectByPrimaryKey(orderCode);
	}
	
	@Override
	public int updatePrimaryKeySelective(BsWmsPurchaseOrder record) {
		
		return bsWmsPurchaseOrderMapper.updateByPrimaryKeySelective(record);
	}
	
}
