package com.hbcun.business.bs.service.eplink;

import com.hbcun.business.bs.entity.vo.eplink.callback27.CallBack;
import com.hbcun.business.bs.entity.vo.param.BsOrderParam;
import com.hbcun.common.entity.vo.ResponseEntity;

public interface EplinkService {

	String EPLINK_OPERATOR = "eplink";

	/**
	 * 获取Post22的报文
	 * @param param
	 * @return
	 */
	com.hbcun.business.bs.entity.vo.eplink.post22.Body genPost22Body(BsOrderParam param);

	/**
	 * 获取post23的报文
	 * @param param
	 * @return
	 */
	com.hbcun.business.bs.entity.vo.eplink.post23.Body genPost23Body(BsOrderParam param);

	/**
	 * 推送订单到电子口岸
	 * @param body
	 * @return
	 */
	ResponseEntity callPost22(com.hbcun.business.bs.entity.vo.eplink.post22.Body body);

	/**
	 * 推送订单到电子口岸
	 * @param param
	 * @return
	 */
	ResponseEntity callPost22(BsOrderParam param);

	/**
	 * 推送个人物品申报单
	 * @param param
	 * @return
	 */
	ResponseEntity callPost23(BsOrderParam param);

	/**
	 * 推送个人物品申报单
	 * @param param
	 * @return
	 */
	ResponseEntity callPost23(com.hbcun.business.bs.entity.vo.eplink.post23.Body body);

	/**
	 * 处理callback26的回执
	 * @param callBack
	 */
	void update_handlerCallBack(com.hbcun.business.bs.entity.vo.eplink.callback26.CallBack callBack);

	/**
	 * 处理post22 或post23回执
	 * @param callBack
	 * @param isPost22CallBack
	 * @return
	 */
	ResponseEntity update_handlerCallBack(CallBack callBack, Boolean isPost22CallBack);

	
}
