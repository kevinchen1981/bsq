package com.hbcun.business.bs.entity.vo.wms;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hbcun.business.bs.entity.vo.BaseVo;
import com.hbcun.common.sys.util.DateUtils;

/**
 * 向网仓发送数据格式
 * @author liuxy
 */
public class RequestDataSend2WMS extends BaseVo {

	/****/
	private static final long serialVersionUID = 1L;
	private Object data;
	private String sign;
	/** 消息类型 **/
	private String notify_type;
	private String notify_id;
	/** 请求的时间戳 **/
	private String notify_time;
	/** 平台定义的仓库编码 **/
	private String store_code;
	/** 平台定义的商家编码 **/
	private String seller_user_id;
	private String format = "json";
	private String charset = "utf-8";
	private String platform_id;

	public RequestDataSend2WMS() {
	}

	public RequestDataSend2WMS(Object data, String notify_type,
			String notify_id, String notify_time, String store_code,
			String seller_user_id, String platform_id) {
		this.data = data;
		this.notify_type = notify_type;
		this.notify_id = notify_id;
		this.notify_time = notify_time;
		this.store_code = store_code;
		this.seller_user_id = seller_user_id;
		this.platform_id = platform_id;
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

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(String platform_id) {
		this.platform_id = platform_id;
	}

	/**生成签名**/
	public void genSign(){
		
		String sign = SignUtil.getSign(this.data.toString());
		setSign(sign);
	}
	
	public Map<String, String> requestDataMap(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("data", this.data.toString());
		map.put("sign", this.sign);
		map.put("notify_type", this.notify_type);
		map.put("notify_id", this.notify_id);
		map.put("notify_time", this.notify_time);
		map.put("store_code", this.store_code);
		map.put("seller_user_id", this.seller_user_id);
		map.put("format", this.format);
		map.put("charset", this.charset);
		map.put("platform_id", this.platform_id);
		return map;
	}
	/*
	@Override
	public String toString() {
		return "?data=" + data + "&sign=" + sign
				+ "&notify_type=" + notify_type + "&notify_id=" + notify_id
				+ "&notify_time=" + notify_time + "&store_code=" + store_code
				+ "&seller_user_id=" + seller_user_id + "&format=" + format
				+ "&charset=" + charset + "&platform_id=" + platform_id;
	}
	*/
	
}
