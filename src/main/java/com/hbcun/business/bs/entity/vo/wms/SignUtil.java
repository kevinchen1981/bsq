package com.hbcun.business.bs.entity.vo.wms;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.hbcun.business.bs.constant.WmsConfig;
import com.hbcun.common.sys.util.Base64;
import com.hbcun.common.sys.util.MD5;
/**
 * 和网仓对接签名<br>
 * 将data+secret拼接为字符串进行md5加密后，再转化为16进制，最终进行Base64编码
 * @author liuxy
 */
public class SignUtil {

	private static final Logger logger = Logger.getLogger(SignUtil.class);
	
	protected static String getSign(String data){
		
		String md5Value = MD5.getMd5(data + WmsConfig.secret);
		try {
			return Base64.encode(md5Value.getBytes(WmsConfig.charset));
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
		return null;
	}
	
	public static boolean verifySign(String data, String sign){
		
		if(StringUtils.isEmpty(sign)){
			return false;
		}
		String md5Value = MD5.getMd5(data + WmsConfig.secret);
		String result = null;
		try {
			result = Base64.encode(md5Value.getBytes(WmsConfig.charset));
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
		return sign.equals(result);
	}
}
