package com.hbcun.business.bs.service;

import java.util.List;

import com.hbcun.business.bs.entity.vo.ExportBsOrderVo;
import com.hbcun.common.entity.po.BsWmsOrder;

public interface BsWmsOrderService {

	int add(String orderId, String packageId, byte inType, String op);
	
	int updateByPrimaryKeySelective(BsWmsOrder record);

	BsWmsOrder selectByPrimaryKey(String orderCode);

	/**
	 * 缺货时，客户愿意等。更新状态，添加记录
	 * @param record
	 * @param op
	 * @return
	 */
	int update_deliverGoodsArriving(BsWmsOrder record, String op);

	BsWmsOrder selectByOrder(String orderId, String packageId);

	BsWmsOrder selectByPreEntryNumber(String preEntryNumber);

	/**
	 * 取出保税直邮，订单创建且取过单号的所有保税订单
	 * @return
	 */
	List<ExportBsOrderVo> getExportZyOrderList();

}
