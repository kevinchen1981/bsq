package com.hbcun.business.bs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class Test {

	private static final String JKS_CA_FILENAME = "tenpay_cacert.jks";
	
	private static final String JKS_CA_ALIAS = "tenpay";
	
	private static final String JKS_CA_PASSWORD = "";
	
	/**pem文件位置**/
	private static final String CA_PATH = File.separator + "hbc" + File.separator + "customs" + File.separator + "tenpay" + File.separator + "tenpay_customs.pem";

	/**cert文件位置**/
	private static final String CERT_PATH = File.separator + "hbc" + File.separator + "customs" + File.separator + "tenpay" + File.separator + "tenpay_customs.pfx";
	
	/**cert文件密码**/
	private static final String CERT_PASSWORD = "916149";

	
	public static void main(String[] args) throws Exception {
		
		File caFile = new File(CA_PATH);
		// ca目录
		String caPath = caFile.getParent();
		File jksCAFile = new File(caPath + "/" + JKS_CA_FILENAME);
		
		X509Certificate cert = (X509Certificate) getCertificate(caFile);

		FileOutputStream out = new FileOutputStream(jksCAFile);
		// store jks file
		storeCACert(cert, JKS_CA_ALIAS, JKS_CA_PASSWORD, out);
		out.close();
	}

	public static Certificate getCertificate(File cafile) throws CertificateException, IOException {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		FileInputStream in = new FileInputStream(cafile);
		Certificate cert = cf.generateCertificate(in);
		in.close();
		return cert;
	}
	
	public static void storeCACert(Certificate cert, String alias, String password, OutputStream out)
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore ks = KeyStore.getInstance("JKS");

		ks.load(null, null);

		ks.setCertificateEntry(alias, cert);

		// store keystore
		ks.store(out, str2CharArray(password));

	}
	
	private static char[] str2CharArray(String str) {
		if(null == str) return null;
		
		return str.toCharArray();
	}
}
