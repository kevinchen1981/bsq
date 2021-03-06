package com.hbcun.business.bs.entity.vo.ems.util;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.net.URL;

import javax.xml.namespace.QName;

/**
 * This class was generated by Apache CXF 3.0.4 2015-04-16T14:17:25.565+08:00
 * Generated source version: 3.0.4
 * 
 */
public final class ILoadDataPortType_ILoadDataHttpPort_Client {

	private static final QName SERVICE_NAME = new QName("http://wsserver.hzws.routdata.com", "ILoadData");

	private static ILoadDataPortType getILoadDataPortType(){
		
		URL wsdlURL = ILoadData.WSDL_LOCATION;
		ILoadData ss = new ILoadData(wsdlURL, SERVICE_NAME);
		ILoadDataPortType port = ss.getILoadDataHttpPort();
		return port;
	}
	
	/**
	 * 2.2.2 电子运单信息接口
	 * @param xml
	 * @return
	 */
	public static String send22(String xml) {

		ILoadDataPortType port = getILoadDataPortType();
		String _callService__return = port.callService(xml);
		return _callService__return;
	}

	/**
	 * 2.2.3 运单回执信息接口
	 * @param xml
	 * @return
	 */
	public static String send23(String xml) {

		ILoadDataPortType port = getILoadDataPortType();
		String _callService__return = port.callServiceReturn(xml);
		return _callService__return;
	}

}
