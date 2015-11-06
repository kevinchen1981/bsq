package com.hbcun.business.bs.entity.vo.wms;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import com.hbcun.business.bs.entity.vo.BaseVo;
import com.hbcun.common.sys.util.DateUtils;

/**
 * 网仓向我们请求数据格式
 * @author liuxy
 */
public class RequestDataSend2Us extends BaseVo{

	/****/
	private static final long serialVersionUID = 1L;
	
	private Object data;
	
	@NotEmpty(message="签名不能为空")
	private String sign;
	/** 消息类型 **/
	@NotEmpty(message="消息类型不能为空")
	private String notify_type;
	
	@NotEmpty(message="消息ID不能为空")
	private String notify_id;
	
	/** 请求的时间戳 **/
	@NotEmpty(message="请求的时间戳不能为空")
	private String notify_time;
	
	/** 平台定义的仓库编码 **/
	@NotEmpty(message="仓库编码不能为空")
	private String store_code;

	/** 平台定义的商家编码 **/
	@NotEmpty(message="商家编码不能为空")
	private String seller_user_id;
	private String wms_id;

	public RequestDataSend2Us() {
	}

	public RequestDataSend2Us(Object data, String sign, String notify_type,
			String notify_id, String notify_time, String store_code,
			String seller_user_id, String wms_id) {
		this.data = data;
		this.sign = sign;
		this.notify_type = notify_type;
		this.notify_id = notify_id;
		this.notify_time = notify_time;
		this.store_code = store_code;
		this.seller_user_id = seller_user_id;
		this.wms_id = wms_id;
	}

	public String getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}

	public void setNotify_time(Date notify_time) {
		this.notify_time = DateUtils.formatToDateTimeString(notify_time);
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNotify_type() {
		return notify_type;
	}

	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

	public String getStore_code() {
		return store_code;
	}

	public void setStore_code(String store_code) {
		this.store_code = store_code;
	}

	public String getSeller_user_id() {
		return seller_user_id;
	}

	public void setSeller_user_id(String seller_user_id) {
		this.seller_user_id = seller_user_id;
	}

	public String getWms_id() {
		return wms_id;
	}

	public void setWms_id(String wms_id) {
		this.wms_id = wms_id;
	}

}
