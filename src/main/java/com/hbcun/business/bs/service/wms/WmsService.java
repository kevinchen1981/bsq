package com.hbcun.business.bs.service.wms;

import com.hbcun.business.bs.entity.vo.param.BsOrderParam;
import com.hbcun.business.bs.entity.vo.wms.ResponseData;
import com.hbcun.business.bs.entity.vo.wms.ResponseData314;
import com.hbcun.business.bs.entity.vo.wms.WmsOrderNotify;
import com.hbcun.business.bs.entity.vo.wms.WmsOrderShipNotice;
import com.hbcun.business.bs.entity.vo.wms.WmsOrderStatusUpload;
import com.hbcun.common.entity.vo.ResponseEntity;
import com.hbcun.common.sys.exception.InvalidRequestException;

public interface WmsService {

	String WMS_OPERATOR = "wms";
	
	/**
	 * 获取创建网仓订单信息
	 * @param param
	 * @return
	 */
	WmsOrderNotify genWmsOrderNotify(BsOrderParam param) throws InvalidRequestException ;

	/**
	 * 发送创建订单命令
	 * @param entity
	 * @return
	 */
	ResponseEntity update_SendWmsOrderNotify(WmsOrderNotify entity);

	/**
	 * 发送创建订单命令
	 * @param entity
	 * @return
	 */
	ResponseEntity update_SendWmsOrderNotify(BsOrderParam param) throws InvalidRequestException ;

	/**
	 * 取消订单
	 * @param param
	 * @param op
	 */
	void update_CancelOrder(BsOrderParam param, String op);

	/**
	 * 缺货等待
	 * @param orderCode
	 * @param op
	 * @return
	 */
	int update_deliverGoodsArriving(String orderCode, String op);
	
	/**
	 * 打包指令
	 * @param param
	 * @return
	 */
	ResponseEntity update_toPackage(BsOrderParam param);

	/**
	 * 查询商品库存，如果不一致，更新商品库存
	 * @param productIds
	 * @return
	 */
	ResponseData314 updateAndQueryProductStock(String ... productIds);

	/**
	 * 库存盘点。网仓主动调，如果有库存不一致，更新库存
	 * @param requestData
	 */
	void updateInventoryCheckUpload(String requestData);

	/**
	 * 网仓
	 * @param wmsOrderStatusUpload
	 * @return
	 */
	ResponseData updateWmsOrderStatusUpload(WmsOrderStatusUpload wmsOrderStatusUpload);

	/**
	 *  发货订单出库时，验证子订单中的商品和数量
	 * @param notice
	 * @return
	 */
	ResponseData updateVerifyOrderShipItem(WmsOrderShipNotice notice);

	/**
	 * 入库订单入库确认接口（网仓调用平台接口）
	 * 会自动加库存，不要再商品初始化时加库存
	 * @param requestData
	 * @return
	 */
	void updatePurchaseOrderInStock(String requestData);
}
