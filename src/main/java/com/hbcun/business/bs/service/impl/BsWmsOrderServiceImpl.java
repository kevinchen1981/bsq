package com.hbcun.business.bs.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbcun.business.bs.constant.WmsConfig;
import com.hbcun.business.bs.dao.mapper.BusinessBsWmsOrderMapper;
import com.hbcun.business.bs.entity.vo.ExportBsOrderVo;
import com.hbcun.business.bs.enume.BsStatusEnum;
import com.hbcun.business.bs.enume.BsTrackLogEnum;
import com.hbcun.business.bs.service.BsWmsOrderService;
import com.hbcun.business.bs.service.BsWmsOrderTrackLogService;
import com.hbcun.common.dao.mapper.BsWmsOrderMapper;
import com.hbcun.common.entity.po.BsWmsOrder;
import com.hbcun.common.entity.po.BsWmsOrderCondition;
import com.hbcun.common.sys.util.OrderUtils;

public class BsWmsOrderServiceImpl extends BaseService implements BsWmsOrderService {

	@Autowired
	private BsWmsOrderMapper bsWmsOrderMapper;

	@Autowired
	private BusinessBsWmsOrderMapper businessBsWmsOrderMapper;

	@Autowired
	private BsWmsOrderTrackLogService bsWmsOrderTrackLogService;
	
	@Override
	public int add(String orderId, String packageId, byte inType, String op) {
		
		Date date = new Date();
		String orderCode = OrderUtils.getOrderCode(orderId, packageId);
		BsWmsOrder entity = new BsWmsOrder();
		entity.setOrderCode(orderCode);
		entity.setStoreCode(WmsConfig.store_code);
		entity.setSellerUserId(WmsConfig.seller_user_id);
		entity.setUserOrderId(orderId);
		entity.setUserPackageId(packageId);
		entity.setInType(inType);
		entity.setCreateTime(date);
		entity.setUpdateTime(date);
		entity.setStatus(BsStatusEnum.STATUS_WMS_CREATE.getStatus());
		int result = bsWmsOrderMapper.insertSelective(entity);
		if(result > 0) {
			if(inType == (byte) 1) {
				//保税备货
				bsWmsOrderTrackLogService.add(orderCode, BsTrackLogEnum.content1_1.content(), op);
			} else {
				//保税直邮
				bsWmsOrderTrackLogService.add(orderCode, BsTrackLogEnum.content1_2.content(), op);
			}
		}
		return result;
	}
	
	@Override
	public int updateByPrimaryKeySelective(BsWmsOrder record) {
		
		return bsWmsOrderMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int update_deliverGoodsArriving(BsWmsOrder record, String op) {
		
		int result = updateByPrimaryKeySelective(record);
		if(result == 0) {
			return result;
		}
		bsWmsOrderTrackLogService.add(record.getOrderCode(), BsTrackLogEnum.content5.content(), op);
		return result;
	}

	@Override
	public BsWmsOrder selectByPrimaryKey(String orderCode) {
		
		return bsWmsOrderMapper.selectByPrimaryKey(orderCode);
	}

	@Override
	public BsWmsOrder selectByOrder(String orderId, String packageId) {
		
		BsWmsOrderCondition condition = new BsWmsOrderCondition();
		condition.createCriteria().andUserOrderIdEqualTo(orderId).andUserPackageIdEqualTo(packageId);
		List<BsWmsOrder> list = bsWmsOrderMapper.selectByExample(condition);
		if(!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public BsWmsOrder selectByPreEntryNumber(String preEntryNumber) {
		
		BsWmsOrderCondition condition = new BsWmsOrderCondition();
		condition.createCriteria().andPreEntryNumberEqualTo(preEntryNumber);
		List<BsWmsOrder> list = bsWmsOrderMapper.selectByExample(condition);
		if(!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<ExportBsOrderVo> getExportZyOrderList() {
		
		return businessBsWmsOrderMapper.getExportZyOrderList();
	}
	
	
}
