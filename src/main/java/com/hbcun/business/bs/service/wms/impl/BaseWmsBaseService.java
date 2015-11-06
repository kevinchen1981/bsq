package com.hbcun.business.bs.service.wms.impl;

import com.hbcun.business.bs.constant.WmsConfig;
import com.hbcun.business.bs.entity.vo.wms.RequestDataSend2WMS;
import com.hbcun.business.bs.entity.vo.wms.ResponseData;
import com.hbcun.business.bs.enume.WmsNotifyTypeEnum;
import com.hbcun.business.bs.service.impl.BaseService;
import com.hbcun.common.sys.exception.InvalidRequestException;
import com.hbcun.common.sys.http.HttpRequest;
import com.hbcun.common.sys.util.DateUtils;
import com.hbcun.common.sys.util.JacksonMapper;

public abstract class BaseWmsBaseService extends BaseService {

	/**
	 * 向wms 发送请求数据
	 * @param entity		报文数据
	 * @param notifyType	通知类型
	 * @return	返回wms 同步数据
	 */
	protected static <T> T sendData2Wms(Object entity, WmsNotifyTypeEnum notifyType, Class<T> responseClass) {
		
		T responseData = null;
		RequestDataSend2WMS requestData = new RequestDataSend2WMS(entity, notifyType.toString(),
				String.valueOf(System.currentTimeMillis()), DateUtils.getCurrentDateTimeString(), WmsConfig.store_code, WmsConfig.seller_user_id, WmsConfig.platform_id);
		requestData.genSign();
		logger.info("wms request data:"+requestData.toString());
		String result = null;
		try {
			result = HttpRequest.postData(WmsConfig.url, requestData.requestDataMap());
			responseData = JacksonMapper.nonEmptyMapper().fromJson(result, responseClass);
		} catch (Exception e) {
			logger.error("wms responseData:"+result, e);
			throw new InvalidRequestException("网仓请求失败"+e.getMessage());
		}
		logger.info("wms response data:"+result);
		return responseData;
	}
	
	public static void main(String[] args) {
		
		String str = "{\"errorCode\":\"S07\",\"errorMsg\":\"data json解析错误\",\"success\":false}";
		System.out.println(JacksonMapper.nonEmptyMapper().fromJson(str, ResponseData.class));
	}
}
