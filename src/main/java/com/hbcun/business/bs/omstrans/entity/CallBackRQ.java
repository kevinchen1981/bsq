package com.hbcun.business.bs.omstrans.entity;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class CallBackRQ {
	@XStreamAlias("service_Result")
	private ArrayList<ServiceResult> result;

	public ArrayList<ServiceResult> getResults() {
		return result;
	}

	public void setResults(ArrayList<ServiceResult> results) {
		this.result = results;
	}

	
}
