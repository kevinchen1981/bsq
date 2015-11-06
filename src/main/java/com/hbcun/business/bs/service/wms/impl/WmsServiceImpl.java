package com.hbcun.business.bs.service.wms.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbcun.business.bs.constant.WmsConfig;
import com.hbcun.business.bs.entity.vo.param.BsOrderParam;
import com.hbcun.business.bs.entity.vo.wms.CancelWmsOrder;
import com.hbcun.business.bs.entity.vo.wms.InventoryQuery;
import com.hbcun.business.bs.entity.vo.wms.NotifyWmsPackage;
import com.hbcun.business.bs.entity.vo.wms.OrderItem;
import com.hbcun.business.bs.entity.vo.wms.OrderItem314;
import com.hbcun.business.bs.entity.vo.wms.PurchaseOrderInStockConfirm;
import com.hbcun.business.bs.entity.vo.wms.PurchaseOrderInStockConfirm.OrderItem38;
import com.hbcun.business.bs.entity.vo.wms.ResponseData;
import com.hbcun.business.bs.entity.vo.wms.ResponseData314;
import com.hbcun.business.bs.entity.vo.wms.WmsInventoryCheckUpload;
import com.hbcun.business.bs.entity.vo.wms.WmsInventoryCheckUpload.OrderItem315;
import com.hbcun.business.bs.entity.vo.wms.WmsOrderNotify;
import com.hbcun.business.bs.entity.vo.wms.WmsOrderNotify.OrderItem32;
import com.hbcun.business.bs.entity.vo.wms.WmsOrderShipNotice;
import com.hbcun.business.bs.entity.vo.wms.WmsOrderShipNotice.OrderItem37;
import com.hbcun.business.bs.entity.vo.wms.WmsOrderStatusUpload;
import com.hbcun.business.bs.enume.BsStatusEnum;
import com.hbcun.business.bs.enume.BsTrackLogEnum;
import com.hbcun.business.bs.enume.WmsNotifyTypeEnum;
import com.hbcun.business.bs.service.BsJobExecuteStatusService;
import com.hbcun.business.bs.service.BsProductImportDateService;
import com.hbcun.business.bs.service.BsWmsOrderService;
import com.hbcun.business.bs.service.BsWmsOrderTrackLogService;
import com.hbcun.business.bs.service.wms.WmsPurchaseService;
import com.hbcun.business.bs.service.wms.WmsService;
import com.hbcun.business.order.entity.vo.OrderProductSimpleVo;
import com.hbcun.business.order.enume.OrderPackageStatusEnum;
import com.hbcun.business.order.enume.OrderTransitStatusEnum;
import com.hbcun.business.order.service.OrderTransitService;
import com.hbcun.business.product.entity.vo.ProductEffectiveAndFreezeAndHasSellStock;
import com.hbcun.business.product.enume.ProviderInTypeEnum;
import com.hbcun.business.product.service.BsGoodsDeclareInfoService;
import com.hbcun.business.product.service.ProductService;
import com.hbcun.business.product.service.ProductStockService;
import com.hbcun.common.constant.ResultCodeBase;
import com.hbcun.common.constant.TipConstBase;
import com.hbcun.common.entity.po.BsGoodsDeclareInfo;
import com.hbcun.common.entity.po.BsJobExecuteStatus;
import com.hbcun.common.entity.po.BsWmsOrder;
import com.hbcun.common.entity.po.BsWmsPurchaseOrder;
import com.hbcun.common.entity.po.OrderInfo;
import com.hbcun.common.entity.po.OrderPackage;
import com.hbcun.common.entity.vo.ResponseEntity;
import com.hbcun.common.sys.exception.InvalidRequestException;
import com.hbcun.common.sys.util.DateUtils;
import com.hbcun.common.sys.util.JacksonMapper;
import com.hbcun.common.sys.util.OrderUtils;

/**
 * 网仓操作
 * @author liuxy
 * @date 2015年9月6日下午3:15:38
 * @since 1.7
 */
public class WmsServiceImpl extends BaseWmsBaseService implements WmsService {

	@Autowired
	private BsWmsOrderTrackLogService bsWmsOrderTrackLogService;
	
	@Autowired
	private BsWmsOrderService bsWmsOrderService;
	
	@Autowired
	private BsJobExecuteStatusService bsJobExecuteStatusService;
	
	@Autowired
	private ProductStockService productStockService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BsGoodsDeclareInfoService bsGoodsDeclareInfoService;

	@Autowired
	private OrderTransitService orderTransitService;

	@Autowired
	private BsProductImportDateService bsProductImportDateService;

	@Autowired
	private WmsPurchaseService wmsPurchaseService;
	
	@Override
	public WmsOrderNotify genWmsOrderNotify(BsOrderParam param) throws InvalidRequestException {
		
		String orderCode = param.getOrderCode();
		OrderInfo order = getOrder(param.getOrderId());
		OrderPackage orderPackage = getOrderPackage(param.getPackageId());
//		String _orderCode = OrderUtils.getOrderCode(orderId, suborderno);
		WmsOrderNotify notify = new WmsOrderNotify();
		notify.setStoreCode(WmsConfig.store_code);
		notify.setSellerUserId(WmsConfig.seller_user_id);
		notify.setOrderCode(orderCode);
		notify.setOrderCreateTime(order.getCreateTime());
		notify.setOrderPayTime(order.getPayTime());
		notify.setTotalFee(orderPackage.getTotalAmount().doubleValue());	//订单总金额
		notify.setPayment(orderPackage.getTotalAmount().doubleValue());	//订单支付金额
		notify.setDiscountFee(0d);
		notify.setPostFee(orderPackage.getPostFee().doubleValue());	//邮费
		notify.setReceiverZipCode(order.getReceiverZipcode());
		notify.setReceiverProvince(OrderUtils.getProvince(order.getReceiverProvince(), order.getReceiverCity()));
		notify.setReceiverCity(OrderUtils.getCity(order.getReceiverProvince(), order.getReceiverCity()));
		notify.setReceiverCounty(OrderUtils.getDistric(order.getReceiverDistrict()));
		notify.setReceiverAddress(order.getReceiverAddress());
		notify.setReceiverName(order.getReceiverName());
		notify.setReceiverMobile(order.getReceiverMobile());
		List<OrderItem32> orderItemList = new ArrayList<OrderItem32>(3);
		//包裹商品信息
		List<OrderProductSimpleVo> ops = getOrderPackageProduct(param.getPackageId());
		//获取保税信息
		PackageProductVo packageProductVo = getPackageProductVo(ops);
//		Collection<OrderProductSimpleVo> newOs = packageProductVo.getMap().values();
		List<String> productIdArray = packageProductVo.getProductIdArray();
		Map<String, BsGoodsDeclareInfo> product2DeclareInfo = bsGoodsDeclareInfoService.getProduct2DeclareInfo(productIdArray);
		OrderItem32 item = null;
//		OrderProductSimpleVo op = null;
		BsGoodsDeclareInfo declareInfo = null;
		int i = 0;
		for (OrderProductSimpleVo op : packageProductVo.getMap().values()) {
			
			String productId = op.getProductId();
			if(op.getBuyCount() == 0) {
				continue;
			}
			declareInfo = product2DeclareInfo.get(productId);
			if(declareInfo == null) {
				throw new InvalidRequestException(productId + "没有填写保税信息");
			}
			item = new OrderItem32(param.getOrderId()+"-"+(i++), WmsConfig.seller_user_id, productId, op.getTitle(), declareInfo.getItemNumber(), op.getBuyCount());
			orderItemList.add(item);
		}
//		for (int i = 0; i < ops.size(); i++) {
//			op = ops.get(i);
//			String productId = op.getProductId();
//			if(op.getBuyCount() == 0) {
//				continue;
//			}
//			declareInfo = product2DeclareInfo.get(productId);
//			if(declareInfo == null) {
//				throw new InvalidRequestException(productId + "没有填写保税信息");
//			}
//			item = new OrderItem32(param.getOrderId()+"-"+i, WmsConfig.seller_user_id, productId, op.getTitle(), declareInfo.getItemNumber(), op.getBuyCount());
//			orderItemList.add(item);
//		}
		notify.setOrderItemList(orderItemList);
		return notify;
	}
	
	@Override
	public ResponseEntity update_SendWmsOrderNotify(WmsOrderNotify entity){
		
		//返回值
		ResponseData responseData = sendData2Wms(entity, WmsNotifyTypeEnum.WMS_ORDER_NOTIFY, ResponseData.class);
		BsWmsOrder record = new BsWmsOrder();
		record.setOrderCode(entity.getOrderCode());
		record.setStoreCode(entity.getStoreCode());
		record.setSellerUserId(entity.getSellerUserId());
		ResponseEntity responseEntity = new ResponseEntity();
		if(responseData.isSuccess()){
			responseEntity.setCode(ResultCodeBase.CODE_SUCCESS);
			if("".equals(responseData.getErrorMsg())){
				record.setStatus(BsStatusEnum.STATUS_WMS_ORDER_NOTIFY_SUCCESS.getStatus());
				bsWmsOrderTrackLogService.add(entity.getOrderCode(), BsTrackLogEnum.ORDER_SEND_TO_WMS_SUCCESS.content(), WMS_OPERATOR);
			}else if("库存不足".equals(responseData.getErrorMsg())){
				responseEntity.setData("库存不足");
				record.setStatus(BsStatusEnum.STATUS_WMS_INVENTORY_LACK.getStatus());
				bsWmsOrderTrackLogService.add(entity.getOrderCode(), BsTrackLogEnum.ORDER_SEND_TO_WMS_LACK.content(), WMS_OPERATOR);
			}
			bsWmsOrderService.updateByPrimaryKeySelective(record);
		}else{
			responseEntity.setCode(ResultCodeBase.CODE_BAD_REQUEST);
			responseEntity.setMsg(responseData.getErrorMsg());
			bsWmsOrderTrackLogService.add(entity.getOrderCode(), BsTrackLogEnum.ORDER_SEND_TO_WMS_LACK.content() + responseData.getErrorMsg(), WMS_OPERATOR);
			record.setStatus(BsStatusEnum.STATUS_WMS_FAILED.getStatus());
		}
		
		return responseEntity;
	}

	@Override
	public ResponseEntity update_SendWmsOrderNotify(BsOrderParam param) throws InvalidRequestException {
		
		WmsOrderNotify notify = genWmsOrderNotify(param);
		return update_SendWmsOrderNotify(notify);
	}
	
	@Override
	public void update_CancelOrder(BsOrderParam param, String op){
		
		String orderCode = param.getOrderCode();
		BsWmsOrder bsOrder = bsWmsOrderService.selectByPrimaryKey(orderCode);
		if(bsOrder == null){
			throw new InvalidRequestException("无效的仓库订单编码" + orderCode);
		}
		
		CancelWmsOrder cancelWmsOrder = new CancelWmsOrder(orderCode);
		ResponseData responseData = sendData2Wms(cancelWmsOrder, WmsNotifyTypeEnum.CANCEL_WMS_ORDER, ResponseData.class);
		if(responseData.isSuccess()){
			bsOrder = new BsWmsOrder();
			bsOrder.setOrderCode(orderCode);
			bsOrder.setStatus(BsStatusEnum.STATUS_WMS_CANCEL.getStatus());
			bsWmsOrderService.updateByPrimaryKeySelective(bsOrder);
			bsWmsOrderTrackLogService.add(orderCode, BsTrackLogEnum.WMS_ORDER_CANCELED.content(), op);
		}
		
	}
	
	@Override
	public int update_deliverGoodsArriving(String orderCode, String op) {
	
		BsWmsOrder bsOrder = bsWmsOrderService.selectByPrimaryKey(orderCode);
		if(bsOrder == null || !BsStatusEnum.STATUS_WMS_INVENTORY_LACK.getStatus().equals(bsOrder.getStatus())){
			throw new InvalidRequestException("不存在仓库订单编码或该状态不是缺货");
		}
		return bsWmsOrderService.update_deliverGoodsArriving(bsOrder, op);
	}
	
	@Override
	public ResponseEntity update_toPackage(BsOrderParam param){
		
		String orderCode = param.getOrderCode();
		BsWmsOrder entity = bsWmsOrderService.selectByPrimaryKey(orderCode);
		NotifyWmsPackage wmsPackage = new NotifyWmsPackage();
		wmsPackage.setOrderCode(entity.getOrderCode());
		wmsPackage.setTmsOrderCode(entity.getTmsOrderCode());
		wmsPackage.setTmsServiceCode(entity.getTmsServiceCode());
		ResponseData responseData = sendData2Wms(wmsPackage, WmsNotifyTypeEnum.NOTIFY_WMS_PACKAGE, ResponseData.class);
		BsWmsOrder record = new BsWmsOrder();
		ResponseEntity responseEntity = new ResponseEntity();
		if(responseData.isSuccess()){
			responseEntity.setCode(ResultCodeBase.CODE_SUCCESS);
			responseEntity.setMsg(TipConstBase.OPERATION_UPDATE_SUCCESS);
			record.setOrderCode(entity.getOrderCode());
			if("OK".equals(responseData.getErrorMsg())){
				record.setStatus(BsStatusEnum.STATUS_WMS_PICK.getStatus());
				bsWmsOrderTrackLogService.add(entity.getOrderCode(), BsTrackLogEnum.DO_PACKAGE_SUCCESS.content(), WMS_OPERATOR);
				// 更新EMS单号
				orderPackageService.updateWayBillNum(param.getOrderId(), param.getPackageId(), entity.getTmsOrderCode(), entity.getTmsOrderCode(), WMS_OPERATOR);
				//更新订单状态
				BsJobExecuteStatus bsJobExecuteStatus = new BsJobExecuteStatus();
				bsJobExecuteStatus.setOrderCode(entity.getOrderCode());
				bsJobExecuteStatus.setIsPackageNotifyToWms(true);
				bsJobExecuteStatus.setOperator(WMS_OPERATOR);
				bsJobExecuteStatusService.updateByPrimaryKeySelective(bsJobExecuteStatus);
			}else if("库存不足".equals(responseData.getErrorMsg())){
				responseEntity.setData(responseData.getErrorMsg());
				record.setStatus(BsStatusEnum.STATUS_WMS_PICK_LACK.getStatus());
				bsWmsOrderTrackLogService.add(entity.getOrderCode(), BsTrackLogEnum.DO_PACKAGE_LACK.content(), WMS_OPERATOR);
			}
			bsWmsOrderService.updateByPrimaryKeySelective(record);
		}else {
			record.setStatus(BsStatusEnum.STATUS_WMS_FAILED.getStatus());
			bsWmsOrderTrackLogService.add(entity.getOrderCode(), responseData.getErrorMsg(), WMS_OPERATOR);
			responseEntity.setCode(ResultCodeBase.CODE_BAD_REQUEST);
			responseEntity.setMsg(responseData.getErrorMsg());
		}
		return responseEntity;
	}
	
	@Override
	public ResponseData314 updateAndQueryProductStock(final String ...productIds){
		
		InventoryQuery entity = new InventoryQuery();
		entity.setSellerUserId(WmsConfig.seller_user_id);
		List<OrderItem> items = new ArrayList<OrderItem>();
		
		OrderItem item = null;
		for (String string : productIds) {
			
			item = new OrderItem();
			item.setItemId(string);
			items.add(item);
		}
		entity.setItemIds(items);
		ResponseData314 responseData = sendData2Wms(entity, WmsNotifyTypeEnum.INVENTORY_QUERY, ResponseData314.class);
		//对比库存，如果不同步，更新
		List<OrderItem314> orderItemList = responseData.getInventoryList();
		ProductEffectiveAndFreezeAndHasSellStock stock = null;
		if(orderItemList != null){
			for (OrderItem314 item1 : orderItemList) {
				
				if(item1.getInventoryType() == 1){
					stock = productStockService.selectBSEffectiveAndFreezeAndHasSellStock(item.getItemId());
					int effective = stock.getEffectiveNumber();
					int freeze = stock.getFreezeNumber();
					int hasSell = stock.getHasSellNumber();
					if(item1.getQtyOfAvailable() == (effective + freeze + hasSell)){
						continue;
					}
					int diff = item1.getQtyOfAvailable() - (effective + freeze + hasSell);
					productService.updateStock(item1.getItemId(), diff, WMS_OPERATOR);
				}
			}
		}
		return responseData;
	}

	@Override
	public void updateInventoryCheckUpload(String requestData){
		
		WmsInventoryCheckUpload upload = JacksonMapper.nonEmptyMapper().fromJson(requestData, WmsInventoryCheckUpload.class);
		List<OrderItem315> orderItemList = upload.getItemList();
		
		StringBuffer sb = new StringBuffer();
		for (OrderItem315 item : orderItemList) {
			
			sb.append("storeCode:"+upload.getStoreCode()+" sellerUserId:"+upload.getSellerUserId());
			sb.append(" productId:"+item.getItemId());
			if(item.getInventoryType() == 1){
				int count = item.getQuantity();
				if(upload.getOrderType() == 701){
					sb.append(" 盘点出库");
					count = 0 - count;
				}else{
					sb.append(" 盘点入库");
				}
				sb.append(" 可销售库存：").append(item.getQuantity());
				productService.updateStock(item.getItemId(), count, WMS_OPERATOR);
			}else{
				if(upload.getOrderType() == 701){
					sb.append(" 盘点出库");
				}else{
					sb.append(" 盘点入库");
				}
				sb.append(" 残次品：").append(item.getQuantity()).append("\r\n");
			}
		}
		logger.info("库存盘点通知:\r\n"+sb.toString());
	}
	

	
	
	private static final String FAILED = "订单发货失败";
	
	@Override
	public ResponseData updateWmsOrderStatusUpload(final WmsOrderStatusUpload wmsOrderStatusUpload){
		
		ResponseData responseData = new ResponseData();
		String status = wmsOrderStatusUpload.getStatus();
		String orderCode = wmsOrderStatusUpload.getOrderCode();
		BsWmsOrder record = bsWmsOrderService.selectByPrimaryKey(orderCode);
		if(record == null){
			
			responseData.setErrorMsg("无效的仓库订单编码");
			return responseData;
		}
		responseData.setSuccess(true);
		//判断状态
		if(BsStatusEnum.STATUS_WMS_CHECK.getStatus().equals(status) || BsStatusEnum.STATUS_WMS_PRINT.getStatus().equals(status)){
			return responseData;
		}
		String str = null;
		if(BsStatusEnum.STATUS_WMS_ACCEPT.getStatus().equals(status)){
			str = BsTrackLogEnum.WMS_ACCEPT.content();
		}else if(BsStatusEnum.STATUS_WMS_PACKAGE.getStatus().equals(status)){
			str = BsTrackLogEnum.DO_PACKAGE_SUCCESS.content();
			//订单打包
			if(record.getInType() == ProviderInTypeEnum.UNDER_BOND.value())
				orderTransitService.add(record.getUserOrderId(), record.getUserPackageId(), OrderTransitStatusEnum.WMS_PACKAGE, null);
		}else if(BsStatusEnum.STATUS_WMS_SHIPED.getStatus().equals(status)){
			if(record.getInType() == ProviderInTypeEnum.UNDER_BOND.value()) {
				str = BsTrackLogEnum.SHIPPED_1.content() + record.getTmsOrderCode() + ")";
			} else {
				str = BsTrackLogEnum.SHIPPED_2.content() + record.getFlightNumber() + ")";
			}
		}else if(BsStatusEnum.STATUS_WMS_PICK_LACK.getStatus().equals(status)){
			str = BsTrackLogEnum.DO_PACKAGE_LACK.content();
		}else if(BsStatusEnum.STATUS_WMS_REJECT.getStatus().equals(status)){
			str = BsTrackLogEnum.WMS_REJECT.content();
		}else if(BsStatusEnum.STATUS_WMS_FAILED.getStatus().equals(status)){
			str = FAILED;
		}else if(BsStatusEnum.STATUS_WMS_PRINT.getStatus().equals(status)) {
			//订单打印
			if(record.getInType() == ProviderInTypeEnum.UNDER_BOND.value())
				orderTransitService.add(record.getUserOrderId(), record.getUserPackageId(), OrderTransitStatusEnum.WMS_PRINT, null);
		}else if(BsStatusEnum.STATUS_WMS_PICK.getStatus().equals(status)) {
			//订单分拣
			if(record.getInType() == ProviderInTypeEnum.UNDER_BOND.value())
				orderTransitService.add(record.getUserOrderId(), record.getUserPackageId(), OrderTransitStatusEnum.WMS_PICK, null);
		}
		if(null != str)
			bsWmsOrderTrackLogService.add(orderCode, str, WMS_OPERATOR);
		BsWmsOrder updateRecord = new BsWmsOrder();
		updateRecord.setOrderCode(orderCode);
		updateRecord.setStatus(status);
		updateRecord.setWeight(wmsOrderStatusUpload.getWeight());
		if(BsStatusEnum.STATUS_WMS_ACCEPT.getStatus().equals(status)) {
			updateRecord.setTmsOrderCode(wmsOrderStatusUpload.getTmsOrderCode());
			updateRecord.setTmsServiceCode(wmsOrderStatusUpload.getTmsServiceCode());
			// 更新状态
			// EMS运单状态更新
			BsJobExecuteStatus bsJobExecuteStatus = new BsJobExecuteStatus();
			bsJobExecuteStatus.setOrderCode(orderCode);
			bsJobExecuteStatus.setIsEmsNoGot(true);
			bsJobExecuteStatus.setIsLogisticsSentToEms(true);
			bsJobExecuteStatus.setUpdateTime(new Date());
			bsJobExecuteStatus.setOperator(WMS_OPERATOR);
			bsJobExecuteStatusService.updateByPrimaryKeySelective(bsJobExecuteStatus);
		}
		bsWmsOrderService.updateByPrimaryKeySelective(updateRecord);
		return responseData;
	}
	
	@Override
	public ResponseData updateVerifyOrderShipItem(final WmsOrderShipNotice notice){
		
		ResponseData responseData = new ResponseData();
		BsWmsOrder record = bsWmsOrderService.selectByPrimaryKey(notice.getOrderCode());
		if(record == null){
			
			responseData.setErrorMsg("无效的仓库订单编码");
			return responseData;
		}
//		String orderId = record.getUserOrderId();
		String packageId = record.getUserPackageId();
		List<OrderProductSimpleVo> list = orderPackageService.getPackageRealBuyProductByPackageId(packageId);
		List<OrderItem37> orderItemList = notice.getOrderItems();
		if(orderItemList.size() == 0){
			
			responseData.setErrorMsg("无效的出库订单");
			return responseData;
		}
		boolean flag = false;
		for (OrderItem37 item : orderItemList) {
			
			for (OrderProductSimpleVo op : list) {
				
				if(item.getItemId().equals(op.getProductId()) && item.getItemQty().byteValue() == op.getBuyCount()){
					flag = true;
					break;
				}
			}
			//商品不在系统中或商量不一致
			if(!flag){
				responseData.setErrorMsg("商品："+item.getItemId()+"不在系统订单中或商品数量不一致");
				return responseData;
			}
		}
		
		//更新状态为已发货
		BsWmsOrder _record = new BsWmsOrder();
		_record.setOrderCode(record.getOrderCode());
		_record.setStatus(BsStatusEnum.STATUS_WMS_SHIPED.getStatus());
		bsWmsOrderService.updateByPrimaryKeySelective(_record);
		//更新包裹为已发货
		OrderPackage temp = new OrderPackage();
		temp.setPackageId(packageId);
		temp.setPackageStatus(OrderPackageStatusEnum.TRANSIT.getStatus());
		temp.setUpdateTime(DateUtils.getCurrentDateTime());
		temp.setOperator(WMS_OPERATOR);
		orderPackageService.updateByPrimaryKeySelective(temp);
		responseData.setSuccess(true);
		return responseData;
	}
	
	@Override
	public void updatePurchaseOrderInStock(String requestData) {
		
		PurchaseOrderInStockConfirm confirm = JacksonMapper.nonEmptyMapper().fromJson(requestData, PurchaseOrderInStockConfirm.class);
		List<OrderItem38> orderItemList = confirm.getOrderItems();
		StringBuffer sb = new StringBuffer();
		for (OrderItem38 item : orderItemList) {

			sb.append("orderCode:"+confirm.getOrderCode()+" productId:"+item.getItemId());
			if(item.getInventoryType() == 1){
				sb.append("可销售库存：").append(item.getItemQty());
				productService.updateStock(item.getItemId(), item.getItemQty(), WMS_OPERATOR);
				bsProductImportDateService.add(item.getItemId(), confirm.getArrivalBeginTime(), confirm.getArrivalEndTime());	//进口日期
			}else{
				sb.append("残次品：").append(item.getItemQty());
			}
			sb.append(" 长度(mm)：").append(item.getLength());
			sb.append(" 宽度(mm)：").append(item.getWeight());
			sb.append(" 高度(mm)：").append(item.getHeight());
			sb.append(" 重量(克)：").append(item.getWeight()).append("\r\n");
		}
		logger.info("采购商品入库通知:\r\n"+sb.toString());
		BsWmsPurchaseOrder record = new BsWmsPurchaseOrder();
		record.setOrderCode(confirm.getOrderCode());
		record.setResponseData(requestData);
		wmsPurchaseService.updatePrimaryKeySelective(record);
	}
	
}
