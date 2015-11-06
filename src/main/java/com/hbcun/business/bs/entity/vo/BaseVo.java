package com.hbcun.business.bs.entity.vo;

import java.io.Serializable;

import com.hbcun.common.sys.util.JacksonMapper;
import com.thoughtworks.xstream.XStream;

public abstract class BaseVo implements Serializable {

	
	private static final long serialVersionUID = 3611273337704996762L;

	private static XStream stream;
	
	protected XStream getXStream(){
		if(stream == null){
			stream = new XStream();
			stream.autodetectAnnotations(true);
		}
		return stream;
	}
	
	public String toXML(){
		return getXStream().toXML(this);
	}
	
	@Override
	public String toString() {
		
		return JacksonMapper.nonEmptyMapper().toJson(this);
	}
}
