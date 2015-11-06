package com.hbcun.business.bs.service.ems.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbcun.business.bs.constant.EmsConfig;
import com.hbcun.business.bs.entity.vo.ems.bigaccountdata.GetBillNum;
import com.hbcun.business.bs.entity.vo.ems.bigaccountdata.ResponseData;
import com.hbcun.business.bs.entity.vo.ems.order.Manifest;
import com.hbcun.business.bs.entity.vo.ems.order.Manifest.Declaration;
import com.hbcun.business.bs.entity.vo.ems.order.Manifest.EWay;
import com.hbcun.business.bs.entity.vo.ems.order.Manifest.Head;
import com.hbcun.business.bs.entity.vo.ems.order.ResponseData.Response;
import com.hbcun.business.bs.entity.vo.ems.util.ILoadDataPortType_ILoadDataHttpPort_Client;
import com.hbcun.business.bs.entity.vo.param.BsOrderParam;
import com.hbcun.business.bs.enume.BsTrackLogEnum;
import com.hbcun.business.bs.service.BsJobExecuteStatusService;
import com.hbcun.business.bs.service.BsProductImportDateService;
import com.hbcun.business.bs.service.BsWmsOrderService;
import com.hbcun.business.bs.service.BsWmsOrderTrackLogService;
import com.hbcun.business.bs.service.ems.EmsService;
import com.hbcun.business.bs.service.impl.BaseService;
import com.hbcun.business.order.entity.vo.OrderProductSimpleVo;
import com.hbcun.common.constant.ResultCodeBase;
import com.hbcun.common.entity.po.BsJobExecuteStatus;
import com.hbcun.common.entity.po.BsWmsOrder;
import com.hbcun.common.entity.po.OrderInfo;
import com.hbcun.common.entity.po.OrderPackage;
import com.hbcun.common.entity.vo.ResponseEntity;
import com.hbcun.common.sys.exception.InvalidRequestException;
import com.hbcun.common.sys.http.HttpRequest;
import com.hbcun.common.sys.util.Base64;
import com.hbcun.common.sys.util.DateUtils;
import com.hbcun.common.sys.util.MD5;
import com.hbcun.common.sys.util.NumberFormatUtils;
import com.hbcun.common.sys.util.OrderUtils;

public class EmsServiceImpl extends BaseService implements EmsService {

	@Autowired
	private BsWmsOrderService bsWmsOrderService;
	
	@Autowired
	private BsProductImportDateService bsProductImportDateService;

	@Autowired
	private BsJobExecuteStatusService bsJobExecuteStatusService;
	
	@Autowired
	private BsWmsOrderTrackLogService bsWmsOrderTrackLogService;

	
	@Override
	public String update_getBillNumBySys(BsOrderParam param) throws InvalidRequestException {
		
		BsWmsOrder bsWmsOrder = bsWmsOrderService.selectByPrimaryKey(param.getOrderCode());
		if(bsWmsOrder == null) {
			throw new InvalidRequestException("不存在保税订单:"+param.getOrderCode());
		}
		OrderInfo order = getOrder(param.getOrderId()); 
		String province = order.getReceiverProvince();
		String businessType = getBusinessType(province);
		ResponseData data = getBillNumBySys(1, businessType);
		if(data == null || data.getResult() == 0){
			throw new InvalidRequestException("获取ems单号:"+data);
		}
		String emsNo = data.getAssignIds().get(0).getBillno();
		handlerGetBillNum(bsWmsOrder, emsNo);
		return emsNo;
	}
	
	/**
	 * 获取EMS单号
	 * @param billNoAmount
	 * @param businessType
	 * @return
	 */
	private ResponseData getBillNumBySys(int billNoAmount, String businessType){
		
		GetBillNum num = new GetBillNum(EmsConfig.sysAccount, EmsConfig.passWord, EmsConfig.appKey, businessType);
		num.setBillNoAmount(billNoAmount);
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n"+num.toXML();
		logger.info("ems getBillNumBySys\r\n"+xml);
		String result = null;
		try {
			xml = Base64.encode(xml.getBytes(EmsConfig.charset));	//加密
			xml = URLEncoder.encode(xml, EmsConfig.charset);		//需要url转码
			result = HttpRequest.getData(EmsConfig.GET_BILL_NUM_URL+xml);
			result = new String(Base64.decode(result), EmsConfig.charset);
			logger.info("ems response "+result);
			return ResponseData.fromXML(result);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 处理EMS单号
	 * @param order
	 * @param assignId
	 */
	private void handlerGetBillNum(BsWmsOrder order, String emsNo) {
		BsWmsOrder _tmpOrder;
		//更新ems单号到bswmsorder
		_tmpOrder = new BsWmsOrder();
		_tmpOrder.setOrderCode(order.getOrderCode());
		_tmpOrder.setTmsServiceCode("EMS");
		_tmpOrder.setTmsOrderCode(emsNo);
		bsWmsOrderService.updateByPrimaryKeySelective(_tmpOrder);
		
		//更新取单号成功
		BsJobExecuteStatus entity = new BsJobExecuteStatus();
		entity.setOrderCode(order.getOrderCode());
		entity.setIsEmsNoGot(true);
		entity.setOperator(EMS_OPERATOR);
		bsJobExecuteStatusService.updateByPrimaryKeySelective(entity);
		//更新订单日志
		bsWmsOrderTrackLogService.add(order.getOrderCode(), BsTrackLogEnum.GET_BILLNUM_SUCCESS.content(), EMS_OPERATOR);
	}

	/**
	 * 发货地区
	 */
	private static final String sendArea = "浙江杭州";
	@Override
	public Manifest genManiFest(BsOrderParam param, String modifyMark) {
		//取运单号
		BsWmsOrder wmsOrder = bsWmsOrderService.selectByOrder(param.getOrderId(), param.getPackageId());
		double weight = wmsOrder.getWeight() != null ? Double.valueOf(wmsOrder.getWeight())/1000 : 0;
		String no = NumberFormatUtils.genId(1, "0000");
		String sendTime = DateUtils.getCurrentDateTimeString();
		String signature = MD5.getMd5(EmsConfig.username + EmsConfig.authorizationCode +sendTime);
		Head head = new Head("200200"+DateUtils.getCurrentDateTimeString6()+no, 
				EmsConfig.messageType, EmsConfig.username, signature, EmsConfig.sysAccount, sendTime);
		List<OrderProductSimpleVo> ops = getOrderPackageProduct(param.getPackageId());
		OrderPackage orderPackage = getOrderPackage(param.getPackageId());
		OrderInfo orderInfo = getOrder(param.getOrderId());
		List<EWay> ways = new ArrayList<EWay>(1);
		String importDateStr = DateUtils.getCurrentDateString();		//进口日期
		EWay eWay = null;
		int index = 0;
		double worth = orderPackage.getTotalAmount().doubleValue();
		String title = null;
		int number = 0;
		PackageProductVo packageProductVo = getPackageProductVo(ops);
		for (OrderProductSimpleVo op : packageProductVo.getMap().values()) {
			if(index == 0){
				
				importDateStr = bsProductImportDateService.getImportDateStr(op.getProductId());
				title = op.getTitle();
			}
			if(op.getBuyCount() == 0) {
				continue;
			}
			number += op.getBuyCount();
			index ++;
		}
		/*for (OrderProductSimpleVo op : ops) {
			if(index == 0){
				
				importDateStr = bsProductImportDateService.getImportDateStr(op.getProductId());
				title = op.getTitle();
			}
			if(op.getBuyCount() == 0) {
				continue;
			}
			number += op.getBuyCount();
			index ++;
		}*/
		String consigneeAddress = OrderUtils.getAddress(orderInfo.getReceiverProvince(), orderInfo.getReceiverCity(), orderInfo.getReceiverDistrict(), orderInfo.getReceiverAddress());
		String consigneeArea = OrderUtils.getProvince(orderInfo.getReceiverProvince(), orderInfo.getReceiverCity()) + OrderUtils.getCity(orderInfo.getReceiverProvince(), orderInfo.getReceiverCity())
									+ OrderUtils.getDistric(orderInfo.getReceiverDistrict());
		eWay = new EWay(wmsOrder.getTmsOrderCode(), number, weight, title, sendArea, consigneeArea, orderInfo.getReceiverName(),
				consigneeAddress, orderInfo.getReceiverMobile(), orderInfo.getReceiverZipcode(), customsCode, String.valueOf(worth), importDateStr, currCode, modifyMark,
				getBusinessType(orderInfo.getReceiverProvince()));
		ways.add(eWay);
		Declaration declaration = new Declaration();
		declaration.setEwList(ways);
		Manifest manifest = new Manifest();
		manifest.setDeclaration(declaration);
		manifest.setHead(head);
		return manifest;
	}
	
	@Override
	public ResponseEntity update_sendLogistics(Manifest manifest, String orderCode){
		
		ResponseEntity response = new ResponseEntity();
		com.hbcun.business.bs.entity.vo.ems.order.ResponseData emsresponse = sendLogistics(manifest);
		//成功
		if(emsresponse.getHead().getResult().equals("0")) {
			//更新bs状态
			BsJobExecuteStatus record = new BsJobExecuteStatus();
			record.setOrderCode(orderCode);
			record.setIsLogisticsSentToEms(true);
			record.setOperator(EMS_OPERATOR);
			bsJobExecuteStatusService.updateByPrimaryKeySelective(record);
			bsWmsOrderTrackLogService.add(orderCode, BsTrackLogEnum.ORDER_SEND_EMS_SUCCESS.content(), EMS_OPERATOR);
			response.setCode(ResultCodeBase.CODE_SUCCESS);
		}else{
			List<Response> responses = emsresponse.getBody().getResponseList();
			StringBuffer sb = new StringBuffer(BsTrackLogEnum.ORDER_SEND_EMS_FAIL.content()).append(":");
			for (Response _response : responses) {
				sb.append(_response.getV_REMARK()).append(" ");
			}
			bsWmsOrderTrackLogService.add(orderCode, sb.toString(), EMS_OPERATOR);
			response.setCode(ResultCodeBase.CODE_BAD_REQUEST);
			response.setMsg(sb.toString());
		}
		return response;
	}
	
	/**
	 * 向ems推送订单信息
	 * @param manifest
	 * @return
	 */
	private com.hbcun.business.bs.entity.vo.ems.order.ResponseData sendLogistics(Manifest manifest) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n"+manifest.toXML();
		logger.info("callService\r\n"+xml);
		String result = ILoadDataPortType_ILoadDataHttpPort_Client.send22(xml);
		logger.info(result);
		com.hbcun.business.bs.entity.vo.ems.order.ResponseData responseData = null;
		try {
			responseData = com.hbcun.business.bs.entity.vo.ems.order.ResponseData.fromXML(result);
		} catch (Exception e) {
			throw new InvalidRequestException("推送EMS订单信息失败" + e.getMessage());
		}
		return responseData;
	}
	
	@Override
	public ResponseEntity update_sendLogistics(BsOrderParam param) {
		
		Manifest manifest = genManiFest(param, "A");
		return update_sendLogistics(manifest, param.getOrderCode());
	}
	
	private String getBusinessType (String province) {
		
		return String.valueOf(OrderUtils.getEMSBusinessType(province));
	}
}
