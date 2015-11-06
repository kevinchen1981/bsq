package com.hbcun.business.bs.service;

import java.io.InputStream;
import java.util.List;

import com.hbcun.business.bs.entity.vo.BsBhListVo;
import com.hbcun.business.bs.entity.vo.BsZyListVo;
import com.hbcun.business.bs.entity.vo.TaskBsOrderListVo;
import com.hbcun.business.bs.entity.vo.param.ListParam;
import com.hbcun.business.bs.enume.BsStatusEnum;
import com.hbcun.common.entity.vo.QueryResult;
import com.hbcun.common.entity.vo.ResultVo;

public interface BsService {

	/**
	 * 保税列表
	 * @param param
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	QueryResult<BsBhListVo> getBhScrollData(ListParam param, int pageIndex, int pageSize);

	/**
	 * 备货列表
	 * @param param
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	QueryResult<BsZyListVo> getZyScrollData(ListParam param, int pageIndex, int pageSize);

	/**
	 * 导入excel中的发货订单信息，如有更新失败的，将其返回。没有关闭流
	 * @param is 
	 * @return
	 */
	ResultVo updateImportOrder(InputStream is);

	/**
	 * 保税备货订单还未发送到网仓
	 * @return
	 */
	List<TaskBsOrderListVo> getOrderNotSendToWms();

	/**
	 * ems单号还未获取的订单。<br>
	 * 直邮的条件：status:create&intype:2&is_ems_no_got:0
	 * @return
	 */
	List<TaskBsOrderListVo> getEMSNumNotGetList();

	/**
	 * 订单信息还没发送到支付公司。<br>
	 * ((保税备货的条件：status:accept&intype:1) || (保税直邮的条件：status:(create||shiped)&intype:2))
	 * &is_payinfo_sent:0 & is_ems_no_got:1
	 * @return
	 */
	List<TaskBsOrderListVo> getOrderNotSendToPayList();
	
	/**
	 * 订单信息还没发送到EMS。<br>
	 *  (保税直邮的条件：status:(create||shiped)&intype:2)
	 * &is_logistics_sent_to_ems:0 &is_payinfo_sent:1 & is_ems_no_got:1
	 * @return
	 */
	List<TaskBsOrderListVo> getOrderNotSendToEmsList();
	
	/**
	 * 订单还没发送到电子口岸。<br>
	 *  ((保税备货的条件：status:accept&intype:1) || (保税直邮的条件：status:(create||shiped)&intype:2))
	 * &is_order_sent_to_ep:0 &is_logistics_sent_to_ems:1 &is_payinfo_sent:1 & is_ems_no_got:1
	 * @return
	 */
	List<TaskBsOrderListVo> getOrderNotSentToEpList();

	/**
	 * 按订单状态获取订单
	 * @param status
	 * @return
	 */
	List<TaskBsOrderListVo> getOrderListByStatus(BsStatusEnum status);

}
