package com.hbcun.business.bs.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hbcun.business.bs.entity.vo.BsBhListVo;
import com.hbcun.business.bs.entity.vo.BsZyListVo;
import com.hbcun.business.bs.entity.vo.TaskBsOrderListVo;
import com.hbcun.business.bs.entity.vo.param.ListParam;
import com.hbcun.business.bs.service.BsService;

public interface BusinessBsMapper {

	List<BsBhListVo> getBhScrollData(@Param("param") ListParam param, @Param("start") int start, @Param("pageSize") int pageSize);

	int getBhScrollDataCounts(@Param("param") ListParam param);

	List<BsZyListVo> getZyScrollData(@Param("param") ListParam param, @Param("start") int start, @Param("pageSize") int pageSize);

	int getZyScrollDataCounts(@Param("param") ListParam param);

	/**
	 * {@link BsService#getOrderNotSendToWms()}
	 * @return
	 */
	List<TaskBsOrderListVo> getOrderNotSendToWms();

	/**
	 * {@link BsService#getEMSNumNotGetList()}
	 * @return
	 */
	List<TaskBsOrderListVo> getEMSNumNotGetList();

	/**
	 * {@link BsService#getOrderNotSentToEpList()}
	 * @return
	 */
	List<TaskBsOrderListVo> getOrderNotSentToEpList();

	/**
	 * {@link BsService#getOrderNotSendToPayList()}
	 * @return
	 */
	List<TaskBsOrderListVo> getOrderNotSendToPayList();

	/**
	 * {@link BsService#getOrderNotSendToEmsList()}
	 * @return
	 */
	List<TaskBsOrderListVo> getOrderNotSendToEmsList();

	/**
	 * 按订单状态获取订单列表
	 * @return
	 */
	List<TaskBsOrderListVo> getOrderListByStatus(@Param("status") String status);


}
