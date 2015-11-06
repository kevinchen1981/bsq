package com.hbcun.business.bs.entity.vo.eplink;

public class ToXML {

	/**
	 * 拼接head、body 成xml
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static String toXML(Head head, Object obj2){
		
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
		xml.append("<mo version=\"1.0.0\">\r\n");
		xml.append(head.toXML()).append("\r\n");
		if(obj2 instanceof com.hbcun.business.bs.entity.vo.eplink.post22.Body){
			
			com.hbcun.business.bs.entity.vo.eplink.post22.Body body = (com.hbcun.business.bs.entity.vo.eplink.post22.Body) obj2;
			xml.append(body.toXML()).append("\r\n");
		} else if(obj2 instanceof com.hbcun.business.bs.entity.vo.eplink.post23.Body){
			
			com.hbcun.business.bs.entity.vo.eplink.post23.Body body = (com.hbcun.business.bs.entity.vo.eplink.post23.Body) obj2;
			xml.append(body.toXML()).append("\r\n");
		}
		return xml.append("</mo>").toString();
	}
}
