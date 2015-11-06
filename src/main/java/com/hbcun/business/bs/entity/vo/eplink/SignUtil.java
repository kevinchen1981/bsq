package com.hbcun.business.bs.entity.vo.eplink;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.hbcun.business.bs.constant.EplinkConfig;
import com.hbcun.common.sys.util.AES;
import com.hbcun.common.sys.util.Base64;
import com.hbcun.common.sys.util.RSA;

public class SignUtil {

	private static final Logger logger = Logger.getLogger(SignUtil.class);
	
	/**
	 * @param content
	 * @return 正常返回解密后的content，否则返回<code>null</code>
	 */
	public static String getContent(String content) {
		
		String returnVal = null;
		if(StringUtils.isBlank(content)){
			return returnVal;
		}
		byte[] input_content = Base64.decode(content);
		byte[] aes_key = Base64.decode(EplinkConfig.AES_KEY);
		try {
			returnVal = new String(AES.decrypt(input_content, aes_key), "utf-8");
		} catch (Exception e) {
			logger.error("content:"+content, e);
		} 
		return returnVal;
	}
	
	/**
	 * 验证eplink 的签名数据
	 * @param content
	 * @param sign
	 * @return
	 */
	public static boolean verifySign(String content, String sign){
		
		if(StringUtils.isEmpty(content)){
			return false;
		}
		return RSA.verify(content, sign, EplinkConfig.RSA_PUBLIC_KEY, "utf-8");
	}
}
