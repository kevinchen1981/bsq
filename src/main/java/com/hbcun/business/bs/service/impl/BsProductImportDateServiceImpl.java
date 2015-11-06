package com.hbcun.business.bs.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hbcun.business.bs.service.BsProductImportDateService;
import com.hbcun.common.dao.mapper.BsProductImportDateMapper;
import com.hbcun.common.entity.po.BsProductImportDate;
import com.hbcun.common.sys.util.DateUtils;

public class BsProductImportDateServiceImpl implements BsProductImportDateService {

	@Autowired
	private BsProductImportDateMapper bsProductImportDateMapper;
	
	@Override
	public String getImportDateStr(String productId) {
		
		BsProductImportDate bsProductImportDate = bsProductImportDateMapper.selectByPrimaryKey(productId);
		if(bsProductImportDate == null){
			return DateUtils.getCurrentDateString();
		}else if(bsProductImportDate.getArrivalBeginTime() != null){
			
			String arrivalBeginTime = DateUtils.formatToDateString(bsProductImportDate.getArrivalBeginTime());
			return arrivalBeginTime;
		}else{
			return DateUtils.formatToDateString(bsProductImportDate.getCreateDate());
		}
	}

	@Override
	public void add(String productId, String arrivalBeginTime, String arrivalEndTime){
		
		if(StringUtils.isEmpty(productId)){
			return ;
		}
		BsProductImportDate bsProductImportDate = new BsProductImportDate();
		bsProductImportDate.setProductId(productId);
		bsProductImportDate.setArrivalBeginTime(DateUtils.converStringToDateTime(arrivalBeginTime));
		bsProductImportDate.setArrivalEndTime(DateUtils.converStringToDateTime(arrivalEndTime));
		bsProductImportDateMapper.insertSelective(bsProductImportDate);
	}
}
