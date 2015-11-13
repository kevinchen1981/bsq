package com.hbcun.business.bs.omstrans.entity;

import java.io.Serializable;
import java.util.ArrayList;

import com.hbcun.business.bs.entity.vo.BaseVo;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class SubmitOrderRQData extends BaseVo  {
	public SubmitOrderRQData(){
		
	}	
	@XStreamAlias("orderHead")
	private OrderHead head;
	@XStreamAlias("orderDetailList")
	private ArrayList<OrderDetail> orderDetailList;
	@XStreamAlias("goodsPurchaser")
	private GoodsPurchaser goodsPurchaser;
	public OrderHead getHead() {
		return head;
	}
	public void setHead(OrderHead head) {
		this.head = head;
	}
	public ArrayList<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}
	public void setOrderDetailList(ArrayList<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	public GoodsPurchaser getGoodsPurchaser() {
		return goodsPurchaser;
	}
	public void setGoodsPurchaser(GoodsPurchaser goodsPurchaser) {
		this.goodsPurchaser = goodsPurchaser;
	}
}
