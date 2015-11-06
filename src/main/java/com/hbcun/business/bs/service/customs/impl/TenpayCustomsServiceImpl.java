package com.hbcun.business.bs.service.customs.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import com.hbcun.business.bs.constant.TenpayCustomsConfig;
import com.hbcun.business.bs.entity.vo.tenpay.SSLHttpClient;
import com.hbcun.common.entity.po.OrderInfo;
import com.hbcun.common.entity.vo.ResultVo;
import com.hbcun.common.sys.exception.InvalidRequestException;
import com.hbcun.common.sys.util.MD5;


public class TenpayCustomsServiceImpl {

	private static final String DECLARE_URL = "https://mch.tenpay.com/cgi-bin/mch_custom_declare.cgi";
	
	private static final Logger logger = Logger.getLogger(TenpayCustomsServiceImpl.class);
	
	public static ResultVo update_sendOrderData(OrderInfo info) {
		
		Map<String, String> declareMap = new HashMap<String, String>();
		declareMap.put("sign_type", "MD5");
		declareMap.put("service_version", "1.0");
		declareMap.put("input_charset", "GBK");
		declareMap.put("sign_key_index", "1");
		declareMap.put("partner", TenpayCustomsConfig.PARTNER);
		declareMap.put("transaction_id", info.getPayNumber());
		declareMap.put("customs", "2");	//杭州
		declareMap.put("mch_customs_no", TenpayCustomsConfig.MCH_CUSTOMS_NO);
		declareMap.put("action_type", "1");
		
		String preSign = createLinkString(declareMap);
		String sign = MD5.getMd5(preSign + "&key="+TenpayCustomsConfig.PARTNER_KEY, declareMap.get("input_charset")).toUpperCase();
		declareMap.put("sign", sign);
		
		String result = "";
		try {
			logger.info("sendDeclare request :" + declareMap);
			System.out.println(declareMap);
			result = SSLHttpClient.postData(DECLARE_URL, declareMap);
			System.out.println(result);
			logger.info("sendDeclare response :" + result);
		} catch (Exception e) {
			logger.error(e);
			throw new InvalidRequestException(e.getMessage());
		}
		Map<String, String> xmlData;
		try {
			xmlData = parseXmlData(result);
		} catch (DocumentException e) {
			logger.error(e);
			throw new InvalidRequestException(e.getMessage());
		}
		if(!"0".equals(xmlData.get("retcode"))) {
			return new ResultVo(xmlData.get("retmsg"));
		}
		//判断签名
		String returnSign = xmlData.get("sign");
		xmlData.remove("sign");
		String queryString = createLinkString(xmlData);
		if(MD5.getMd5(queryString + "&key="+TenpayCustomsConfig.PARTNER_KEY, xmlData.get("input_charset")).equalsIgnoreCase(returnSign)) {
			return new ResultVo(true);
		}
		throw new InvalidRequestException("签名验证失败");
	}
	
	private static String createLinkString(Map<String, String> params) {
		
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if(value != null && value != "") {
				
				if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
					prestr = prestr + key + "=" + value.trim();
				} else {
					prestr = prestr + key + "=" + value.trim() + "&";
				}
			}
		}

		return prestr;
	}
	

	/**
	 * 解析xml
	 * @param xml
	 * @return
	 * @throws DocumentException
	 */
	public static Map<String, String> parseXmlData(String xml) throws DocumentException{
		
		Map<String, String> data = new HashMap<String, String>();
		SAXReader saxReader = new SAXReader();
		InputSource source = new InputSource(new StringReader(xml));
		Document doc = saxReader.read(source);
		return concreteParse(data, doc);
	}
	
	private static Map<String, String> concreteParse(Map<String, String> data, Document doc) {
		Element root = doc.getRootElement();
		List childList = root.elements();
		for (Iterator iter = root.elementIterator(); iter.hasNext();) {
			Element e = (Element) iter.next();
			data.put(e.getName(), String.valueOf(e.getData()));
		}
		return data;
	}

}
