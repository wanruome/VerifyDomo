/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年5月15日 下午5:23:12
 */
package com.newpay.pmsverify;

import java.security.PrivateKey;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.newpay.pmsverify.tools.ObjectToMapTools;
import com.newpay.pmsverify.tools.PmsMerchantTools;
import com.newpay.pmsverify.tools.RequestPmsOut;
import com.newpay.pmsverify.tools.RequestPmsQuery;
import com.ruomm.base.http.ResponseData;
import com.ruomm.base.http.okhttp.DataOKHttp;
import com.ruomm.base.http.okhttp.OkHttpConfig;
import com.ruomm.base.tools.Base64;
import com.ruomm.base.tools.RSAUtils;

public class PmsOutVerify {
	// 商户自行产生公私钥对，使用私钥进行加密，公钥发送给我司
	private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDqOJ4L4ljh78ump4n0rk1uugqj"
			+ "ky1u3D+xmbJcsFKx8B5lMmk4zwGM7jDva1HxGvgHf2O9OH0JrVVMX7YkciacdMj2"
			+ "TSZY/i5EyUD1YA6WndjFhToEKq8FDHWFPsPkWFVctk56ue5HRC/FyEnhSjtvpJTE" + "fT5WTtWq2NArC8ttbQIDAQAB";
	private static String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOo4ngviWOHvy6an"
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
	// private static final String BaseUrl = "http://localhost:8080/bdss/";
	private static final String BaseUrl = "http://192.168.100.196:8083/bdss/";

	public static void testModeString() {
		RequestPmsOut pmsOut = new RequestPmsOut();
		pmsOut.setCrttime("20180518110001");
		pmsOut.setMchtno("ZX0000010010089");
		pmsOut.setGroupFlag("0");
		pmsOut.setOutamt("6");
		pmsOut.setOutbatch("ZX2018021000000000393505");
		pmsOut.setOutdate("20180518");
		pmsOut.setOutRemarks("出款");
		pmsOut.setSettleAcctNm("张三");
		pmsOut.setSettleAcctNo("6226388000000095");
		pmsOut.setSettleBankNm("中国农业银行股份有限公司苍南龙翔支行");
		pmsOut.setSettleBankNo("103301010719");
		String objectSignStr = PmsMerchantTools.getObjectSignStr(pmsOut);
		System.out.println(objectSignStr);
		String sign = encrptyRequestMessageFormat(objectSignStr);
		pmsOut.setSign(sign);

		ResponseData responseData2 = new DataOKHttp().setUrl(BaseUrl + "servlet/pms/doPmsOut").setDebug(true)
				.setRequestBody(JSON.toJSONString(pmsOut)).setPost(true).doHttp(String.class);
		System.out.println(responseData2.toString());
	}

	public static void testPmsQueryString() {
		RequestPmsQuery pmsOut = new RequestPmsQuery();
		pmsOut.setMchtno("ZX0000010010089");
		pmsOut.setOutbatch("ZX0000010010089");
		pmsOut.setOutdate("20180518");
		String objectSignStr = PmsMerchantTools.getObjectSignStr(pmsOut);
		System.out.println(objectSignStr);
		String sign = encrptyRequestMessageFormat(objectSignStr);
		pmsOut.setSign(sign);
		System.out.println(JSON.toJSONString(pmsOut));

		ResponseData responseData2 = new DataOKHttp().setUrl(BaseUrl + "servlet/pms/pmsQuery").setDebug(true)
				.setRequestBody(JSON.toJSONString(pmsOut)).setPost(true).doHttp(String.class);
		System.out.println(responseData2.toString());
	}

	public static void testModeHashMap() {
		RequestPmsOut pmsOut = new RequestPmsOut();
		pmsOut.setCrttime("20180516120001");
		pmsOut.setMchtno("AB0000010010089");
		pmsOut.setGroupFlag("0");
		pmsOut.setOutamt("8");
		pmsOut.setOutbatch("AB2018021000000000393503");
		pmsOut.setOutdate("20180516");
		pmsOut.setOutRemarks("出款");
		pmsOut.setSettleAcctNm("张三");
		pmsOut.setSettleAcctNo("6226388000000095");
		pmsOut.setSettleBankNm("中国农业银行股份有限公司苍南龙翔支行");
		pmsOut.setSettleBankNo("103301010719");
		String objectSignStr = PmsMerchantTools.getObjectSignStr(pmsOut);
		System.out.println(objectSignStr);
		String sign = encrptyRequestMessageFormat(objectSignStr);
		pmsOut.setSign(sign);
		Map<String, Object> mapObject = ObjectToMapTools.getFiedlsMap(pmsOut);
		ResponseData responseData2 = new DataOKHttp().setUrl(BaseUrl + "servlet/pms/doPmsout").setDebug(true)
				.setRequestBody(OkHttpConfig.attachFormRequestForamtBody(mapObject)).setPost(true).doHttp(String.class);
		System.out.println(responseData2.toString());
	}

	public static String encrptyRequestMessageFormat(String data) {
		try {
			PrivateKey mchtPrivateKey = RSAUtils.getPrivateKey(Base64.decode(privateKey));
			return RSAUtils.getSignData(data, mchtPrivateKey);

		}
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}
	// public static String encrptyRequestMessageFormat(String md5, String privateKey) {
	// try {
	// byte[] data = RSAUtils.encryptDataByPrivateKey(md5.getBytes(encoding),
	// Base64.decode(privateKey));
	// return Base64.encode(data);
	//
	// }
	// catch (Exception e) {
	// e.printStackTrace();
	// return "";
	// }
	//
	// }
}
