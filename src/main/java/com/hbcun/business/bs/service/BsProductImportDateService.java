package com.hbcun.business.bs.service;

public interface BsProductImportDateService {

	String getImportDateStr(String productId);

	void add(String itemId, String arrivalBeginTime, String arrivalEndTime);

}
