package com.hbcun.business.bs.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hbcun.business.bs.entity.vo.WmsPurchaseOrderVo;

public interface BusinessBsWmsPurchaseOrderMapper {

	List<WmsPurchaseOrderVo> getScrollData(@Param("start") int start, @Param("pageSize") int pageSize);

	int getScrollDataCounts();

}
