package com.hbcun.business.bs.service.wms;

import com.hbcun.business.bs.entity.vo.WmsPurchaseOrderVo;
import com.hbcun.business.bs.entity.vo.wms.PurchaseOrderNotify;
import com.hbcun.common.entity.po.BsWmsPurchaseOrder;
import com.hbcun.common.entity.vo.QueryResult;
import com.hbcun.common.entity.vo.ResultVo;

public interface WmsPurchaseService {

	/**
	 * 进货单列表
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	QueryResult<WmsPurchaseOrderVo> getScrollData(int pageIndex, int pageSize);

	/**
	 * 向网仓发送进货数据，如果返回成功，添加进货单数据,否则返回失败原因
	 * @param notify
	 * @return
	 */
	ResultVo addPurcharseOrder(PurchaseOrderNotify notify);

	BsWmsPurchaseOrder selectByPrimaryKey(String orderCode);

	int updatePrimaryKeySelective(BsWmsPurchaseOrder record);

}
