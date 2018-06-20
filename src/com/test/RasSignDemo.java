package com.test;

import java.security.PrivateKey;
import java.security.PublicKey;

import com.ruomm.base.tools.Base64;
import com.ruomm.base.tools.RSAUtils;

public class RasSignDemo {
	/**
	 * 签名可用的方法 MD2withRSA MD5andSHA1withRSA MD5withRSA NONEwithDSA NONEwithECDSA NONEwithRSA
	 * SHA1withDSA SHA1withECDSA SHA1withRSA SHA224withDSA SHA224withECDSA SHA224withRSA
	 * SHA256withDSA SHA256withECDSA SHA256withRSA SHA384withECDSA SHA384withRSA SHA512withECDSA
	 * SHA512withRSA
	 */
	private static String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDqOJ4L4ljh78ump4n0rk1uugqj"
			+ "ky1u3D+xmbJcsFKx8B5lMmk4zwGM7jDva1HxGvgHf2O9OH0JrVVMX7YkciacdMj2"
			+ "TSZY/i5EyUD1YA6WndjFhToEKq8FDHWFPsPkWFVctk56ue5HRC/FyEnhSjtvpJTE" + "fT5WTtWq2NArC8ttbQIDAQAB";
	private static String privateKeyStr = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOo4ngviWOHvy6an"
			+ "ifSuTW66CqOTLW7cP7GZslywUrHwHmUyaTjPAYzuMO9rUfEa+Ad/Y704fQmtVUxf"
			+ "tiRyJpx0yPZNJlj+LkTJQPVgDpad2MWFOgQqrwUMdYU+w+RYVVy2Tnq57kdEL8XI"
			+ "SeFKO2+klMR9PlZO1arY0CsLy21tAgMBAAECgYEA02JTqTAXvZlYd8je5dflhFgd"
			+ "P/GVYGcgiN3IroRnrbWjCPvNIPsaUCGsQnfeFTacwM+EqYJwvqoTwIDk9tGa64En"
			+ "3kNZHq/91CscD1Ix0coUu8IbaoFJRhfqgL91bVmtaj41BR2oeI25KZ6vozLKFO05"
			+ "mKiBSVUFXn2tTHfV/MECQQD36q3Yj0DWl7u2t6DeWAS2518sbnY8kCJntL8/kV/J"
			+ "RcGHUk+8KVgb2sNXY1dRRdxpFXXdUoW2W7au8lMr1oVxAkEA8dufqu705qZatGWp"
			+ "8/FAcQcPDVYoc9ZE/PNS7FavYJO9++BjXhLQne9O8DIzYXVGiiswZ57D0l8lonkG"
			+ "oKD5vQJAdfAdfb1AYhXSxex7Serja0OMYi22b789rsoshQgaYjyeAUsmBWRBtxaO"
			+ "AR+QJWivnwoHhR9B3uuob3d2G4+08QJBANoi0MaV0TJ6ldKg4wFO9WU8DZpkvAWG"
			+ "GyDdwUvB/1mmxzJ2toixhZD8twCy9l6PLLkPrQ+4RTvnbwU0DQMxTS0CQBdHX2zp"
			+ "O32gjU4Pt2nElSz5mobeIdgCYfiCMdsNK3XM/j+QS1RMCjFUQ/jq70r1AYw8TLTF" + "ZFDIZHvH11QfFxE=";
	private static String signStr = "mchtno=AB0000010010089,outbatch=AB2018021000000000393503,outdate=20180518"
			+ privateKeyStr;
	private static String signMethod = "SHA256withRSA";

	public static void main(String[] args) throws Exception {
		PrivateKey privateKey = RSAUtils.getPrivateKey(Base64.decode(privateKeyStr));
		PublicKey publicKey = RSAUtils.getPublicKey(Base64.decode(publicKeyStr));
		String signData = RSAUtils.getSignData(signStr, privateKey, signMethod, null);
		System.out.println(signData);
		boolean flag = RSAUtils.verifySignData(signStr, signData, publicKey, signMethod, null);
		System.out.println(flag);

	}

}
