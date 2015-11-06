package com.hbcun.business.bs.entity.vo.tenpay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

//openssl pkcs12 -in file.p12 -out file.pem -nodes  
public class SSLHttpClient {

	private static final String JKS_CA_FILENAME = "tenpay_cacert.jks";
	private static final String JKS_CA_ALIAS = "tenpay";
	private static final String JKS_CA_PASSWORD = "";
	private static final String SunX509 = "SunX509";
	private static final String JKS = "JKS";
	private static final String PKCS12 = "PKCS12";
	private static final String TLS = "TLS";
	
	
	public static String postData(String url, Map<String, String> data){
		String result = "";
		try {
			SSLContext sslContext = getSSlContext();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			HttpPost httpPost = new HttpPost(url);
			if(data != null && !data.isEmpty()){
				UrlEncodedFormEntity urlEncodedFormEntity = assembleRequestParams(data);
				httpPost.setEntity(urlEncodedFormEntity);
			}
			result = getResult(httpclient, httpPost);
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**pem文件位置**/
	private static final String CA_PATH = File.separator + "hbc" + File.separator + "customs" + File.separator + "tenpay" + File.separator + "tenpay_customs.pem";

	/**cert文件位置**/
	private static final String CERT_PATH = File.separator + "hbc" + File.separator + "customs" + File.separator + "tenpay" + File.separator + "tenpay_customs.pfx";
	
	/**cert文件密码**/
	private static final String CERT_PASSWORD = "916149";

	private static SSLContext getSSlContext() throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {

		File caFile = new File(CA_PATH);
		File certFile = new File(CERT_PATH);
		String certPassword = CERT_PASSWORD;
		if (caFile == null || certFile == null) {
			return null;
		}

		// ca目录
		String caPath = caFile.getParent();
		File jksCAFile = new File(caPath + "/" + JKS_CA_FILENAME);
		if (!jksCAFile.isFile()) {
			X509Certificate cert = (X509Certificate) getCertificate(caFile);

			FileOutputStream out = new FileOutputStream(jksCAFile);
			// store jks file
			storeCACert(cert, JKS_CA_ALIAS, JKS_CA_PASSWORD, out);
			out.close();
		}

		FileInputStream trustStream = new FileInputStream(jksCAFile);
		FileInputStream keyStream = new FileInputStream(certFile);

		SSLContext sslContext = getSSLContext(trustStream, JKS_CA_PASSWORD, keyStream, certPassword);

		// 关闭流
		keyStream.close();
		trustStream.close();
		return sslContext;
	}
	
	public static Certificate getCertificate(File cafile) throws CertificateException, IOException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		FileInputStream in = new FileInputStream(cafile);
		Certificate cert = cf.generateCertificate(in);
		in.close();
		return cert;
	}
	
	public static void storeCACert(Certificate cert, String alias,
			String password, OutputStream out) throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore ks = KeyStore.getInstance("JKS");

		ks.load(null, null);

		ks.setCertificateEntry(alias, cert);

		// store keystore
		ks.store(out, str2CharArray(password));

	}
	public static SSLContext getSSLContext(
			FileInputStream trustFileInputStream, String trustPasswd,
			FileInputStream keyFileInputStream, String keyPasswd)
			throws NoSuchAlgorithmException, KeyStoreException,
			CertificateException, IOException, UnrecoverableKeyException,
			KeyManagementException {

		// ca
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(SunX509);
		KeyStore trustKeyStore = KeyStore.getInstance(JKS);
		trustKeyStore.load(trustFileInputStream, str2CharArray(trustPasswd));
		tmf.init(trustKeyStore);

		final char[] kp = str2CharArray(keyPasswd);
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(SunX509);
		KeyStore ks = KeyStore.getInstance(PKCS12);
		ks.load(keyFileInputStream, kp);
		kmf.init(ks, kp);

		SecureRandom rand = new SecureRandom();
		SSLContext ctx = SSLContext.getInstance(TLS);
		ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), rand);

		return ctx;
	}
	
	private static char[] str2CharArray(String str) {
		if(null == str) return null;
		
		return str.toCharArray();
	}
	
	/**
	 * 组装http请求参数
	 * 
	 * @param params
	 * @param menthod
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static UrlEncodedFormEntity assembleRequestParams(Map<String, String> data) throws UnsupportedEncodingException {
		final List<BasicNameValuePair> nameValueList = new ArrayList<BasicNameValuePair>();

		Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			nameValueList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
		}
		return new UrlEncodedFormEntity(nameValueList, "UTF-8");
	}
	
	/**
	 * 获取结果
	 * @param httpClient
	 * @param method
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private static String getResult(CloseableHttpClient httpClient, HttpRequestBase method) throws ClientProtocolException, IOException{
		String result = "";
		CloseableHttpResponse response;
		response = httpClient.execute(method);
		try { 
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();  
				if (entity != null) {  
					result = EntityUtils.toString(entity, "UTF-8");
				}
			}else{
				throw new IllegalArgumentException("Unexpected response status: " + status);
			}
		} finally {
			response.close();
		}
		return result;
	}
	
	public static void main(String[] args) {
		
//		File caFile = new File("c:\\hbc\\hbc.pem");
//		File certFile = new File("c:\\hbc\\1264179801_20150813153500.pfx");
//		String certPassword = "916149";
//		DeclareParam declareParam = new DeclareParam("1264179801", null, "1002000908201503010022825145", "3301968FA2", "410423199207182512", "刘修远", "1");
//		SortedMap<String, String> map = ParamterUtil.getSortedParam(declareParam);
//		String preSign = ParamterUtil.getPreSignStr(map) + "&key=f4d2f62498433561811783e72a7c8f31";
//		System.out.println(preSign);
//		String sign = Md5.getMd5(preSign).toLowerCase();
//		map.put("sign", sign);
//		System.out.println(sign);
//		System.out.println(map);
//		System.out.println(postData("https://mch.tenpay.com/cgi-bin/mch_custom_declare.cgi", map,
//				caFile, certFile, certPassword));
	}
}
