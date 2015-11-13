package com.hbcun.business.bs.omstrans.entity;

import java.io.Serializable;
import java.util.ArrayList;

import com.hbcun.business.bs.entity.vo.BaseVo;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class SubmitOrderRS extends BaseVo {
	
	@XStreamAlias("service_Result")
	private ArrayList<ServiceResult> results;

	public ArrayList<ServiceResult> getResults() {
		return results;
	}

	public void setResults(ArrayList<ServiceResult> results) {
		this.results = results;
	}
}
