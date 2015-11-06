package com.hbcun.business.bs.service.eplink.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbcun.business.bs.constant.EplinkConfig;
import com.hbcun.business.bs.entity.vo.eplink.Head;
import com.hbcun.business.bs.entity.vo.eplink.JkfSign;
import com.hbcun.business.bs.entity.vo.eplink.ToXML;
import com.hbcun.business.bs.entity.vo.eplink.callback27.CallBack.JkfResult;
import com.hbcun.business.bs.entity.vo.eplink.callback27.CallBack.JkfResultDetail;
import com.hbcun.business.bs.entity.vo.eplink.post22.Body.JkfGoodsPurchaser;
import com.hbcun.business.bs.entity.vo.eplink.post22.Body.JkfOrderDetail;
import com.hbcun.business.bs.entity.vo.eplink.post22.Body.JkfOrderImportHead;
import com.hbcun.business.bs.entity.vo.eplink.post22.Body.OrderInfo;
import com.hbcun.business.bs.entity.vo.eplink.post23.Body.GoodsDeclare;
import com.hbcun.business.bs.entity.vo.eplink.post23.Body.GoodsDeclareDetail;
import com.hbcun.business.bs.entity.vo.eplink.post23.Body.GoodsDeclareModule;
import com.hbcun.business.bs.entity.vo.eplink.util.ReceivedDeclareService_ReceivedDeclareServiceImplPort_Client;
import com.hbcun.business.bs.entity.vo.param.BsOrderParam;
import com.hbcun.business.bs.enume.BsStatusEnum;
import com.hbcun.business.bs.enume.BsTrackLogEnum;
import com.hbcun.business.bs.enume.EplinkHeadTypeEnum;
import com.hbcun.business.bs.service.BsJobExecuteStatusService;
import com.hbcun.business.bs.service.BsWmsOrderService;
import com.hbcun.business.bs.service.BsWmsOrderTrackLogService;
import com.hbcun.business.bs.service.eplink.EplinkService;
import com.hbcun.business.bs.service.impl.BaseService;
import com.hbcun.business.bs.util.CommonUtils;
import com.hbcun.business.order.entity.vo.OrderProductSimpleVo;
import com.hbcun.business.order.enume.OrderTransitStatusEnum;
import com.hbcun.business.order.service.OrderTransitService;
import com.hbcun.business.product.enume.ProviderInTypeEnum;
import com.hbcun.business.product.service.BsGoodsDeclareInfoService;
import com.hbcun.business.product.service.ProductCumsomArgumentsService;
import com.hbcun.business.product.service.ProductTariffService;
import com.hbcun.common.constant.ResultCodeBase;
import com.hbcun.common.constant.TipConstBase;
import com.hbcun.common.entity.po.BsGoodsDeclareInfo;
import com.hbcun.common.entity.po.BsJobExecuteStatus;
import com.hbcun.common.entity.po.BsWmsOrder;
import com.hbcun.common.entity.po.Nation;
import com.hbcun.common.entity.po.OrderPackage;
import com.hbcun.common.entity.po.ProductTariff;
import com.hbcun.common.entity.vo.ResponseEntity;
import com.hbcun.common.sys.exception.InvalidRequestException;
import com.hbcun.common.sys.util.NumberFormatUtils;
import com.hbcun.common.sys.util.OrderUtils;

public class EplinkServiceImpl extends BaseService implements EplinkService {
	
	@Autowired
	private BsWmsOrderService bsWmsOrderService;
	
	@Autowired
	private BsWmsOrderTrackLogService bsWmsOrderTrackLogService;

	@Autowired
	private BsJobExecuteStatusService bsJobExecuteStatusService;

	@Autowired
	private ProductCumsomArgumentsService productCumsomArgumentsService;

	@Autowired
	private ProductTariffService productTariffService;

	@Autowired
	private BsGoodsDeclareInfoService bsGoodsDeclareInfoService;

	@Autowired
	private OrderTransitService orderTransitService;
	
	/**
	 * 其他支付类型
	 */
	private static final String payType = "03";

	/**
	 * 发件人国别
	 * 中国：142，香港：110，澳门：121
	 */
	private static final String senderCountry = "142";

	/**
	 * 申报协议
	 */
	private static final String userProcotol = "本人承诺所购买商品系个人合理自用，现委托致鲜生平台代理申报、代缴税款等通关事宜，本人保证遵守《海关法》和国家相关法律法规，保证所提供的身份信息和收货信息真实完整，无侵犯他人权益的行为，以上委托关系系如实填写，本人愿意接受海关、检验检疫机构及其他监管部门的监管，并承担相应法律责任.";
	
	@Override
	public com.hbcun.business.bs.entity.vo.eplink.post22.Body genPost22Body(BsOrderParam param) {
		
		
		com.hbcun.common.entity.po.OrderInfo order = getOrder(param.getOrderId());
		OrderPackage orderPackage = getOrderPackage(param.getPackageId());
		String payNumber = order.getPayNumber();
		JkfSign jkfSign = getJkfSign(EplinkHeadTypeEnum.BUSINESS_TYPE_IMPORTORDER, param.getOrderCode());
		com.hbcun.business.bs.entity.vo.eplink.post22.Body body = new com.hbcun.business.bs.entity.vo.eplink.post22.Body();
		//头信息
		JkfOrderImportHead orderImportHead = new JkfOrderImportHead();
		orderImportHead.setCompanyName(EplinkConfig.companyName);
		orderImportHead.setCompanyCode(EplinkConfig.companyCode);
		orderImportHead.setPayType(payType);
		orderImportHead.setPayCompanyCode(CommonUtils.getPayCompanyCode(order.getPayType()));
		orderImportHead.setPayNumber(payNumber);
		orderImportHead.setOrderNo(param.getOrderId());
		orderImportHead.seteCommerceCode(EplinkConfig.eCommerceCode);
		orderImportHead.seteCommerceName(EplinkConfig.eCommerceName);
		orderImportHead.setTradeTime(order.getCreateTime());
		orderImportHead.setCurrCode(currCode);
		orderImportHead.setConsigneeTel(order.getReceiverMobile());
		orderImportHead.setConsignee(order.getReceiverName());
		orderImportHead.setConsigneeAddress(OrderUtils.getAddress(order.getReceiverProvince(), order.getReceiverCity(), order.getReceiverDistrict(), order.getReceiverAddress()));
		orderImportHead.setSenderCountry(senderCountry);
		orderImportHead.setSenderName(EplinkConfig.eCommerceName);
		orderImportHead.setPurchaserId(String.valueOf(order.getMemberId()));
		orderImportHead.setLogisCompanyCode(EplinkConfig.logisCompanyCode);
		orderImportHead.setLogisCompanyName(EplinkConfig.logisCompanyName);
		orderImportHead.setUserProcotol(userProcotol);
		List<JkfOrderDetail> orderDetailList = new ArrayList<JkfOrderDetail>(3);
		List<OrderProductSimpleVo> ops = getOrderPackageProduct(param.getPackageId());
		JkfOrderDetail orderDetail = null;
		
		PackageProductVo packageProductVo = getPackageProductVo(ops);
		//商品数组
		List<String> productIdArray = packageProductVo.getProductIdArray();
		//商品对应的国家代码
 		Map<String, Nation> product2Nation = productCumsomArgumentsService.getProduct2Nation(productIdArray);
 		//商品对应的税号
 		Map<String, ProductTariff> product2Traiff = productTariffService.getProduct2Tariff(productIdArray);
 		//商品对应的保税信息
 		Map<String, BsGoodsDeclareInfo> product2Declare = bsGoodsDeclareInfoService.getProduct2DeclareInfo(productIdArray);
 		int totalCount = 0;
		int index = 1;
		Nation nation = null;
		ProductTariff tariff = null;
		BsGoodsDeclareInfo declareInfo = null;
		for (OrderProductSimpleVo op : packageProductVo.getMap().values()) {
			
			String productId = op.getProductId();
			int buyCount = op.getBuyCount();
			if(buyCount == 0) {
				continue;
			}
			nation = product2Nation.get(productId);
			if(nation == null){
				throw new InvalidRequestException(productId + "没有输入产地");
			}
			tariff = product2Traiff.get(productId);
			if(tariff == null) {
				throw new InvalidRequestException(productId + "没有选择税号");
			}
			declareInfo = product2Declare.get(productId);
			if(declareInfo == null) {
				throw new InvalidRequestException(productId + "没填写保税信息");
			}
			totalCount += buyCount;
			orderDetail = new JkfOrderDetail(index++, op.getTitle(), tariff.getShuihao(), nation.getCode(), op.getPrice(), buyCount, declareInfo.getDeclareUnitCode());
			orderDetailList.add(orderDetail);
		}
		
		orderImportHead.setOrderTaxAmount(orderPackage.getTaxAmount().doubleValue());		//税款
		orderImportHead.setFeeAmount(orderPackage.getPostFee().doubleValue());				//运费
		orderImportHead.setOrderGoodsAmount(orderPackage.getProductAmount().doubleValue());	//订单货款
		orderImportHead.setOrderTotalAmount(orderPackage.getTotalAmount().doubleValue());	//订单总金额
		orderImportHead.setTotalAmount(orderPackage.getProductAmount().doubleValue());		//成交总价 == 订单货款
		orderImportHead.setTotalCount(totalCount);	//独立包装数
		//购买人信息
		JkfGoodsPurchaser goodsPurchaser = new JkfGoodsPurchaser(String.valueOf(order.getMemberId()), order.getReceiverName(), order.getReceiverMobile());
		
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setJkfSign(jkfSign);
		orderInfo.setJkfOrderImportHead(orderImportHead);
		orderInfo.setJkfOrderDetailList(orderDetailList);
		orderInfo.setJkfGoodsPurchaser(goodsPurchaser);
		List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>(2);
		orderInfoList.add(orderInfo);
		body.setOrderInfoList(orderInfoList);
		return body;
	}
	
	@Override
	public ResponseEntity callPost22(com.hbcun.business.bs.entity.vo.eplink.post22.Body body){
		
		Head head = getHead(EplinkHeadTypeEnum.BUSINESS_TYPE_IMPORTORDER);
		String xmlData = ToXML.toXML(head, body);
		com.hbcun.business.bs.entity.vo.eplink.callback27.CallBack callBack = postData(xmlData, EplinkHeadTypeEnum.BUSINESS_TYPE_IMPORTORDER);
		ResponseEntity entity = update_handlerCallBack(callBack, true);
		return entity;
	}
	
	@Override
	public ResponseEntity callPost22(BsOrderParam param){
		
		com.hbcun.business.bs.entity.vo.eplink.post22.Body body = genPost22Body(param);
		return callPost22(body);
	}
	
	/**
	 * //杭州电商
	 */
	private static final String IePort = "2991";

	/**
	 * //运输方式 9:其他运输
	 */
	private static final String TrafMode = "9";

	/**
	 * 申报口岸代码 杭州经济技术开发区海关驻出口加工区办事处
	 */
	private static final String DeclPort = "2991";

	/**
	 * 299102：下沙园区
	 */
	private static final String CustomsField = "299102";

	/**
	 * 142 中国境内
	 */
	private static final String destinationPort = "142";
	
	
	
	@Override
	public com.hbcun.business.bs.entity.vo.eplink.post23.Body genPost23Body(BsOrderParam param) {
		BsWmsOrder bsWmsOrder = bsWmsOrderService.selectByOrder(param.getOrderId(), param.getPackageId());
		double weight = bsWmsOrder.getWeight() == null ? 0 : Double.valueOf(bsWmsOrder.getWeight()) / 1000;
		String tmsOrderCode = bsWmsOrder.getTmsOrderCode();	//EMS单号
		
		//订单信息
		com.hbcun.common.entity.po.OrderInfo order = getOrder(param.getOrderId());
		List<OrderProductSimpleVo> ops = getOrderPackageProduct(param.getPackageId());
		OrderPackage orderPackage = getOrderPackage(param.getPackageId());
		
		PackageProductVo packageProductVo = getPackageProductVo(ops);
		List<String> productIdArray = packageProductVo.getProductIdArray();
		//因为表头信息包含指运港等以产地为维度，所以此处以产地分组
		Map<String, Nation> product2Nation = productCumsomArgumentsService.getProduct2Nation(productIdArray);
		Map<String, String> product2Spec = productCumsomArgumentsService.getProduct2Spec(productIdArray);
//		Map<String, List<OrderProductSimpleVo>> map = groupByOrigin(product2Origin, ops);
		Map<String, BsGoodsDeclareInfo> product2Declare = bsGoodsDeclareInfoService.getProduct2DeclareInfo(productIdArray);
		Map<String, ProductTariff> product2Tariff = productTariffService.getProduct2Tariff(productIdArray);
//		List<OrderProductSimpleVo> _ops = null;
		Nation nation = null;
		
		List<GoodsDeclareModule> goodsDeclareModuleList = new ArrayList<GoodsDeclareModule>(5);
		GoodsDeclareModule goodsDeclareModule = null;
		GoodsDeclare goodsDeclare = null;
		ProductTariff tariff = null;
		GoodsDeclareDetail goodsDeclareDetail = null;
		List<GoodsDeclareDetail> goodsDeclareDetails = null;
//		for (String origin : map.keySet()) {
			
//			nation = getNationByOrigin(origin);
//			String destinationPort = CommonUtils.getDestinationPort(origin);
//			if(destinationPort == null || nation == null){
//				throw new InvalidRequestException("不支持该地区"+origin);
//			}
//			_ops = map.get(origin);
			goodsDeclareModule = new GoodsDeclareModule();
			goodsDeclare = new GoodsDeclare();
			
			goodsDeclare.setPreEntryNumber(genPreEntryNumber(param.getPackageId()));
			goodsDeclare.setImportType(CommonUtils.getImportType(bsWmsOrder.getInType()));	//进口类型，0：一般进口		1：保税进口
			goodsDeclare.setIePort(IePort);		//杭州电商
			goodsDeclare.setDestinationPort(destinationPort);	//抵运港 起运港，502:美国   116:日本   601:澳大利亚
			goodsDeclare.setTrafMode(TrafMode);		//运输方式 9:其他运输
			goodsDeclare.setDeclareCompanyType("个人委托第三方申报");	//申报单位类别
			goodsDeclare.setDeclareCompanyCode(EplinkConfig.HanDaCompanyCode);	//申报单位代码
			goodsDeclare.setDeclareCompanyName(EplinkConfig.HanDaCompanyName);	//申报单位名称
			goodsDeclare.seteCommerceCode(EplinkConfig.eCommerceCode);
			goodsDeclare.seteCommerceName(EplinkConfig.eCommerceName);
			goodsDeclare.setOrderNo(param.getOrderId());
			
			goodsDeclare.setDeclPort(DeclPort);	//申报口岸代码 杭州经济技术开发区海关驻出口加工区办事处
			goodsDeclare.setCustomsField(CustomsField);//299102：下沙园区
			goodsDeclare.setSenderName(EplinkConfig.eCommerceName);
			goodsDeclare.setConsignee(order.getReceiverName());
			goodsDeclare.setSenderCountry(senderCountry);	//国别 中国
			goodsDeclare.setCurrCode(currCode);	//币制 人民币
			goodsDeclare.setWayBill(tmsOrderCode);	//分运单号	ems运单
			goodsDeclare.setGrossWeight(weight);	//毛重
			goodsDeclare.setNetWeight(weight);		//净重
			goodsDeclare.setInternalAreaCompanyNo(EplinkConfig.HanDaCompanyCode);//区内企业编码
			goodsDeclare.setInternalAreaCompanyName(EplinkConfig.HanDaCompanyName);//区内企业名称
			//保税才要申请单编号
			if(bsWmsOrder.getInType() == ProviderInTypeEnum.UNDER_BOND.value()) {
				goodsDeclare.setApplicationFormNo(EplinkConfig.applicationFormNo);//申请单编号
			}
			goodsDeclare.setAccountBookNo(EplinkConfig.accountBookNo);//账册编号
			int index = 1;
			int packNo = 0;
			goodsDeclareDetails = new ArrayList<GoodsDeclareDetail>(3);
			BsGoodsDeclareInfo goodsDeclareInfo = null;
			String title = "";
			for (OrderProductSimpleVo op : packageProductVo.getMap().values()) {
				
				if(op.getBuyCount() == 0) {
					continue;
				}
				nation = product2Nation.get(op.getProductId());
				goodsDeclareInfo = product2Declare.get(op.getProductId());
				if(goodsDeclareInfo == null ||
						goodsDeclareInfo.getUnitRate() == null || goodsDeclareInfo.getUnitRate().intValue() == 0){
					throw new InvalidRequestException("商品保税信息必填");
				}
				title = op.getTitle();
				double firstCount = NumberFormatUtils.formatNumberDigit(op.getBuyCount(), goodsDeclareInfo.getUnitRate().doubleValue());
				tariff = product2Tariff.get(op.getProductId());
				packNo += op.getBuyCount();
				goodsDeclareDetail = new GoodsDeclareDetail(index++, tariff.getShuihao(), goodsDeclareInfo.getItemNumber(), op.getTitle(), product2Spec.get(op.getProductId()), nation.getCode(), currCode,
						op.getPrice(), String.valueOf(NumberFormatUtils.formatNumberDigit(op.getPrice(), op.getBuyCount())),
						op.getBuyCount(), goodsDeclareInfo.getDeclareUnitCode(), goodsDeclareInfo.getFirstUnitCode(), firstCount, null, NumberFormatUtils.formatNumberDigit(op.getPrice(), op.getBuyCount()));
				goodsDeclareDetails.add(goodsDeclareDetail);
			}
			goodsDeclare.setTradeCountry(nation.getCode());//贸易国别
			goodsDeclare.setWorth(orderPackage.getProductAmount().doubleValue());	//价值
			String mainGName = packageProductVo.getMap().values().size() > 1 ? title+"等" : title;
			goodsDeclare.setMainGName(mainGName);	//主要商品名称
			goodsDeclare.setPackNo(packNo);	//件数
			
			JkfSign sign = getJkfSign(EplinkHeadTypeEnum.BUSINESS_TYPE_PERSONAL_GOODS_DECLAR, param.getOrderCode());
			goodsDeclareModule.setJkfSign(sign);
			goodsDeclareModule.setGoodsDeclare(goodsDeclare);
			goodsDeclareModule.setGoodsDeclareDetails(goodsDeclareDetails);
			
			goodsDeclareModuleList.add(goodsDeclareModule);
//		}
		
		com.hbcun.business.bs.entity.vo.eplink.post23.Body body = new com.hbcun.business.bs.entity.vo.eplink.post23.Body();
		body.setGoodsDeclareModuleList(goodsDeclareModuleList);
		return body;
	}
	
	@Override
	public ResponseEntity callPost23(com.hbcun.business.bs.entity.vo.eplink.post23.Body body){
		
		Head head = getHead(EplinkHeadTypeEnum.BUSINESS_TYPE_PERSONAL_GOODS_DECLAR);
		String xmlData = ToXML.toXML(head, body);
		//将preentryNumber 存起来
		String orderCode = body.getGoodsDeclareModuleList().get(0).getJkfSign().getBusinessNo();
		String preEntryNumber = body.getGoodsDeclareModuleList().get(0).getGoodsDeclare().getPreEntryNumber();
		updatePreEntryNumber(orderCode, preEntryNumber);
		com.hbcun.business.bs.entity.vo.eplink.callback27.CallBack callback = postData(xmlData, EplinkHeadTypeEnum.BUSINESS_TYPE_PERSONAL_GOODS_DECLAR);
		//处理回执
		ResponseEntity entity = update_handlerCallBack(callback, false);
		return entity;
	}
	
	@Override
	public ResponseEntity callPost23(BsOrderParam param){
		
		com.hbcun.business.bs.entity.vo.eplink.post23.Body body = genPost23Body(param);
		return callPost23(body);
	}
	
	@Override
	public void update_handlerCallBack(com.hbcun.business.bs.entity.vo.eplink.callback26.CallBack callBack){
		
		String preEntryNumber = callBack.getBody().getJkfSign().getBusinessNo();
		BsWmsOrder bsOrder = bsWmsOrderService.selectByPreEntryNumber(preEntryNumber);
		String orderCode = bsOrder.getOrderCode();
		String approveResult = callBack.getBody().getJkfGoodsDeclar().getApproveResult();
		BsWmsOrder bsWmsOrder = new BsWmsOrder();
		String status = null;
		String content = null;
		//直接放行
		if("51".equals(approveResult) || "52".equals(approveResult) || "53".equals(approveResult)){
			
			status = BsStatusEnum.STATUS_WMS_CLEARED.getStatus();
			if(bsOrder.getInType() == ProviderInTypeEnum.UNDER_BOND.value()) {
				content = BsTrackLogEnum.EPLINK_CLEARED_1.content();
			} else {
				content = BsTrackLogEnum.EPLINK_CLEARED_2.content() + bsOrder.getTmsOrderCode() + ")";
			}
			orderTransitService.add(bsOrder.getUserOrderId(), bsOrder.getUserPackageId(), OrderTransitStatusEnum.CLEARED, null);
		}else if("99".equals(approveResult)){
			
//			status = BsWmsOrder.STATUS_WMS_CUSTOMS_ACCEPT;
		}else if("32".equals(approveResult)){
			String approveComment = callBack.getBody().getJkfGoodsDeclar().getApproveComment();
			status = BsStatusEnum.STATUS_WMS_CUSTOMS_REJECT.getStatus();
			content =  BsTrackLogEnum.EPLINK_EXCEPTION + approveComment;
		}
		bsWmsOrderTrackLogService.add(orderCode, content, EPLINK_OPERATOR);
		if(status == null) {
			return ;
		}
		bsWmsOrder.setOrderCode(orderCode);
		bsWmsOrder.setStatus(status);
		bsWmsOrderService.updateByPrimaryKeySelective(bsWmsOrder);
	}
	
	/**
	 * 同步处理post22 或者 post23回执 
	 * @param callBack
	 */
	@Override
	public ResponseEntity update_handlerCallBack(com.hbcun.business.bs.entity.vo.eplink.callback27.CallBack callBack, Boolean isPost22CallBack){
		
		ResponseEntity responseData = new ResponseEntity();
		if(callBack == null){
			responseData.setCode(ResultCodeBase.CODE_BAD_REQUEST);
			return responseData;
		}
		List<JkfResult> list = callBack.getBody().getList();
		if(list != null && !list.isEmpty()){
			List<JkfResultDetail> resultList = null;
			for (JkfResult jkfResult : list) {
				String orderCode = jkfResult.getBusinessNo();
				String checkMark = jkfResult.getChkMark();
				String businessType = jkfResult.getBusinessType();
				//错误
				if(!"1".equals(checkMark)){
					boolean post22 = false;
					if(EplinkHeadTypeEnum.BUSINESS_TYPE_IMPORTORDER.getType().equals(businessType)){
						post22 = true;
					}
					
					resultList = jkfResult.getResultList();
					StringBuffer sb = new StringBuffer(BsTrackLogEnum.EPLINK_EXCEPTION.content());
					sb.append(post22 ? "订单信息" : "个人申报单");
					if(resultList != null && !resultList.isEmpty()){
						for (JkfResultDetail jkfResultDetail : resultList) {
							sb.append(jkfResultDetail.getResultInfo()).append(" ");
						}
					}
					bsWmsOrderTrackLogService.add(orderCode, sb.toString(), EPLINK_OPERATOR);
					responseData.setCode(ResultCodeBase.CODE_BAD_REQUEST);
					responseData.setMsg(sb.toString());
				}else{
					
					Boolean post22Success = null;
					Boolean post23Success = null;
					String content = BsTrackLogEnum.ORDER_SEND_TO_EPLINK.content();
					if (isPost22CallBack) {
						post22Success = true;
					}else{
						post23Success = true;
						content = BsTrackLogEnum.PACKAGE_SEND_TO_EPLINK.content();
					}
					BsJobExecuteStatus jobExecuteStatus = new BsJobExecuteStatus();
					jobExecuteStatus.setOrderCode(orderCode);
					jobExecuteStatus.setIsOrderSentToEp(post22Success);
					jobExecuteStatus.setIsGoodsDeclaredToEp(post23Success);
					jobExecuteStatus.setOperator(EPLINK_OPERATOR);
					bsJobExecuteStatusService.updateByPrimaryKeySelective(jobExecuteStatus);
					bsWmsOrderTrackLogService.add(orderCode, content, EPLINK_OPERATOR);
					responseData.setCode(ResultCodeBase.CODE_SUCCESS);
					responseData.setMsg(TipConstBase.OPERATION_UPDATE_SUCCESS);
				}
			}
		}
		return responseData;
	}
	
	private void updatePreEntryNumber(String orderCode, String preEntryNumber) {
		BsWmsOrder record = new BsWmsOrder();
		record.setOrderCode(orderCode);
		record.setPreEntryNumber(preEntryNumber);
		bsWmsOrderService.updateByPrimaryKeySelective(record);
	}
	
	
	/**
	 * 向电子口岸发送数据
	 * @param xmlData
	 * @param type
	 * @return
	 */
	private com.hbcun.business.bs.entity.vo.eplink.callback27.CallBack postData(String xmlData, EplinkHeadTypeEnum type) {
		String post = type == EplinkHeadTypeEnum.BUSINESS_TYPE_IMPORTORDER ? "post22" : "post23";
		logger.info("eplink "+post+"\r\n"+xmlData);
		String result = ReceivedDeclareService_ReceivedDeclareServiceImplPort_Client.sendRequestData(xmlData, type.getType(), null);
		logger.info("eplink "+post+" callback\r\n"+result);
		com.hbcun.business.bs.entity.vo.eplink.callback27.CallBack callBack = null;
		try {
			callBack = com.hbcun.business.bs.entity.vo.eplink.callback27.CallBack.fromXML(result);
		} catch (Exception e) {
			throw new InvalidRequestException("推送电子口岸"+post+"信息失败" + e.getMessage());
		}
		return callBack;
	}
	
	
	/**
	 * 生成jkfsign
	 * @param businessType 业务类型
	 * @param orderId 业务编号
	 * @return
	 */
	private JkfSign getJkfSign(EplinkHeadTypeEnum type, String businessNo){
	
		JkfSign jkfSign = new JkfSign(EplinkConfig.companyCode, businessNo, type.getType());
		return jkfSign;
	}
	
	/**
	 * 获取head信息
	 * @param type
	 * @return
	 */
	private Head getHead(EplinkHeadTypeEnum type){
		Head head = new Head(type.getType());
		return head;
	}
	
	/**
	 * 根据产销国进行分组
	 * @param ops
	 * @return
	 */
//	private Map<String, List<OrderProductSimpleVo>> groupByOrigin(Map<String, String> product2Origin, List<OrderProductSimpleVo> ops){
//		
//		Map<String, List<OrderProductSimpleVo>> map = new HashMap<String, List<OrderProductSimpleVo>>();
//		//所有产地
//		Set<String> originList = new HashSet<String>(product2Origin.values());
//		String[] originArray = originList.toArray(new String[0]);
//		//只有一个产地
//		if(originArray.length == 1) {
//			
//			map.put(originArray[0], ops);
//			return map;
//		}
//		
//		List<OrderProductSimpleVo> list = null;
//		for (String origin : originArray) {
//			
//			list = new ArrayList<OrderProductSimpleVo>();
//			for (OrderProductSimpleVo orderProductSimpleVo : list) {
//				
//				//一个产地
//				if(origin.equals(product2Origin.get(orderProductSimpleVo.getProductId()))) {
//					list.add(orderProductSimpleVo);
//				}
//			}
//			map.put(origin, list);
//		}
//		return map;
//	}
	
	/**
	 * 生成预录入号
	 * @param suborderno
	 * @param orderId
	 * @return
	 */
	private static String genPreEntryNumber(String packageId){
		StringBuilder sb = new StringBuilder();
		sb.append("HBCN").append(packageId);
		return sb.toString();
	}
	
//	private Nation getNationByOrigin(String origin) {
//		
//		NationCondition condition = new NationCondition();
//		condition.createCriteria().andNationNameEqualTo(origin);
//		List<Nation> list = nationMapper.selectByExample(condition);
//		return list.get(0);
//	}
}
