package com.hbcun.business.bs.omstrans;


import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import com.hbcun.business.bs.constant.OmsTranConfig;

import com.hbcun.business.bs.omstrans.entity.Authors;
import com.hbcun.business.bs.omstrans.entity.GoodsPurchaser;
import com.hbcun.business.bs.omstrans.entity.SubmitOrderRQ;
import com.hbcun.business.bs.omstrans.entity.SubmitOrderRQData;
import com.hbcun.business.bs.omstrans.entity.SubmitOrderRS;
import com.hbcun.business.bs.omstrans.entity.ServiceResult;

//import com.hbcun.business.bs.service.customs.impl.InvalidRequestException;
//import com.hbcun.business.bs.service.customs.impl.ResultVo;

import com.hbcun.business.bs.service.impl.BaseService;
import com.thoughtworks.xstream.XStream;

public class OmsTranServiceImpl extends BaseService implements OmsTranService {

	@Override
	public String AuthorIDConfirm(Authors author) {
		Map<String, String> declareMap = new HashMap<String, String>();
		declareMap.put("app_id", author.getAppID());
		declareMap.put("auth_code", author.getAuthorCode());

		String result = "";
		String token = "";
		try {
			//logger.info("AuthorIDConfirm request :" + declareMap);
			System.out.println(declareMap);
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(OmsTranConfig.WEB_SERVICE_URL
					+ OmsTranConfig.AuthorUrl);
			if (declareMap != null && !declareMap.isEmpty()) {
				UrlEncodedFormEntity urlEncodedFormEntity = assembleRequestParams(declareMap);
				httpPost.setEntity(urlEncodedFormEntity);
			}
			httpPost.setHeader("Content-type", "application/json; charset=UTF-8");
			CloseableHttpResponse response;			
			response = httpclient.execute(httpPost);
			response.setHeader("Content-type", "application/json; charset=UTF-8");
	
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				System.out.println(entity.toString());				
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
				}
			} else {
				throw new IllegalArgumentException(
						"Unexpected response status: " + status);
			}

			response.close();
			httpPost.releaseConnection();
			System.out.println(result);
			//logger.info("AuthorIDConfirm response :" + result);
		} catch (Exception e) {
			//logger.error(e);
			// throw new InvalidRequestException(e.getMessage());
		}
		Map<String, String> xmlData;
		JSONObject obj = null;
		try {
			//xmlData = parseXmlData(result);
			
			//Object[] val = xmlData.values().toArray();
			//token = (String) val[0];
			obj = JSONObject.fromObject(result);
			Object arr = obj.get("result");
			token = arr.toString();
		} catch (Exception e) {
			//logger.error(e);
			// throw new InvalidRequestException(e.getMessage());
			System.out.println(e.getMessage());
		}

		// URL urlTemp = new URL(
		// "auth.htm");
		// URLConnection connection = urlTemp.openConnection();
		// connection.setRequestProperty("content-type",
		// "application/x-www-form-urlencoded;charset=UTF-8");
		// connection.setDoOutput(true);
		// out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		// StringBuffer sb = new StringBuffer();
		// sb.append("app_id=");
		// sb.append(author.appID);
		// sb.append("&");
		// sb.append("auth_code=");
		// sb.append(author.authorCode);
		// out.write(sb.toString());
		// out.flush();
		// //out.close();
		// InputStream l_urlStream;
		// l_urlStream = connection.getInputStream();
		// } catch (Exception e) {
		// //e.printStackTrace();
		// } finally {
		// if (null != out) {
		// try {
		// out.close();
		// } catch (IOException e) {
		// //e.printStackTrace();
		// }
		// }
		// }

		return token;
	}

	/**
	 * 组装http请求参数
	 * 
	 * @param params
	 * @param menthod
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static UrlEncodedFormEntity assembleRequestParams(
			Map<String, String> data) throws UnsupportedEncodingException {
		final List<BasicNameValuePair> nameValueList = new ArrayList<BasicNameValuePair>();

		Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it
					.next();
			nameValueList.add(new BasicNameValuePair((String) entry.getKey(),
					(String) entry.getValue()));
		}
		return new UrlEncodedFormEntity(nameValueList, "UTF-8");
	}

	/**
	 * 解析xml
	 * 
	 * @param xml
	 * @return
	 * @throws DocumentException
	 */
	public static Map<String, String> parseXmlData(String xml)
			throws DocumentException {

		Map<String, String> data = new HashMap<String, String>();
		SAXReader saxReader = new SAXReader();
		InputSource source = new InputSource(new StringReader(xml));
		Document doc = saxReader.read(source);
		return concreteParse(data, doc);
	}

	private static Map<String, String> concreteParse(Map<String, String> data,
			Document doc) {
		Element root = doc.getRootElement();
		List childList = root.elements();
		for (Iterator iter = root.elementIterator(); iter.hasNext();) {
			Element e = (Element) iter.next();
			data.put(e.getName(), String.valueOf(e.getData()));
		}
		return data;
	}

	@Override
	public SubmitOrderRS SubmitOrder(SubmitOrderRQ request) {

		String result = "";
		SubmitOrderRS rs = null;
		try {
			//String xmlData = request.toXML();
			JSONObject jo = JSONObject.fromObject(request);
			String xmlData = jo.toString(); 
			System.out.println(xmlData);
			//logger.info("SubmitOrder request :" + xmlData);
			// System.out.println(declareMap);
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(OmsTranConfig.WEB_SERVICE_URL
					+ OmsTranConfig.SubmitOrderURL);
			// 指定请求内容的类型
			httpPost.setHeader("Content-type", "application/json; charset=UTF-8");
			StringEntity xmlentity = new StringEntity(xmlData);
			xmlentity.setContentType("text/json");
			xmlentity.setContentEncoding(new BasicHeader("Content-type", "application/json"));
			httpPost.setEntity(xmlentity);
			CloseableHttpResponse response = httpclient.execute(httpPost);

			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
				}
			} else {
				throw new IllegalArgumentException(
						"Unexpected response status: " + status);
			}

			response.close();
			httpPost.releaseConnection();

			System.out.println(result);
			//logger.info("AuthorIDConfirm response :" + result);
		} catch (Exception e) {
			//logger.error(e);
			// throw new InvalidRequestException(e.getMessage());
			System.out.println(e.getMessage());
		}

		try {
			JSONObject obj = JSONObject.fromObject(result);
			JSONArray arr = obj.getJSONArray("service_result");
			//token = arr.toString();
			ArrayList<ServiceResult> results = new ArrayList<ServiceResult>(); 
			for(int i=0;i<arr.size();i++)
			{
				ServiceResult sor = new ServiceResult();
				sor.setBusiness_no(arr.getJSONObject(i).getString("business_no"));
				sor.setBusiness_type(arr.getJSONObject(i).getString("business_type"));
				sor.setChk_mark(arr.getJSONObject(i).getString("chk_mark"));
				sor.setNotice_content(arr.getJSONObject(i).getString("notice_content"));
				sor.setNotice_time(arr.getJSONObject(i).getString("notice_time"));
				sor.setWay_bills(arr.getJSONObject(i).getString("way_bills"));
				results.add(sor);
			}
			rs = new SubmitOrderRS();
			rs.setResults(results);
		/*	XStream xstream = new XStream();
			xstream.alias("service_Result", SubmitOrderRS.class);
			// xstream.addImplicitCollection(SubmitOrderRS. class , " persons "
			// );
			rs = (SubmitOrderRS) xstream.fromXML(result);*/

		} catch (Exception e) {
			//logger.error(e);
			// throw new InvalidRequestException(e.getMessage());
			System.out.println(e.getMessage());
		}

		return rs;
	}
	public static void main(String[] args) {
		Authors author = new Authors();
		author.setAppID("jx");
		author.setAuthorCode("12344");
		OmsTranServiceImpl imp = new OmsTranServiceImpl();
		String re = imp.AuthorIDConfirm(author);
		SubmitOrderRQ rq = new SubmitOrderRQ();
		rq.setApp_id("jx");
		SubmitOrderRQData data = new SubmitOrderRQData();
		GoodsPurchaser good = new GoodsPurchaser();
		good.setEmail("jx@xx.com");
		data.setGoodsPurchaser(good);
		rq.setData(data);
		imp.SubmitOrder(rq);
	}

}
