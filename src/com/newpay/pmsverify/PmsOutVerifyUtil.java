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
import com.ruomm.base.tools.Base64;
import com.ruomm.base.tools.RSAUtils;

public class PmsOutVerifyUtil {
	// 商户自行产生公私钥对，使用私钥进行加密，公钥发送给我司
	// private String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDqOJ4L4ljh78ump4n0rk1uugqj"
	// + "ky1u3D+xmbJcsFKx8B5lMmk4zwGM7jDva1HxGvgHf2O9OH0JrVVMX7YkciacdMj2"
	// + "TSZY/i5EyUD1YA6WndjFhToEKq8FDHWFPsPkWFVctk56ue5HRC/FyEnhSjtvpJTE" +
	// "fT5WTtWq2NArC8ttbQIDAQAB";
	// private String privateKey =
	// "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOo4ngviWOHvy6an"
	// + "ifSuTW66CqOTLW7cP7GZslywUrHwHmUyaTjPAYzuMO9rUfEa+Ad/Y704fQmtVUxf"
	// + "tiRyJpx0yPZNJlj+LkTJQPVgDpad2MWFOgQqrwUMdYU+w+RYVVy2Tnq57kdEL8XI"
	// + "SeFKO2+klMR9PlZO1arY0CsLy21tAgMBAAECgYEA02JTqTAXvZlYd8je5dflhFgd"
	// + "P/GVYGcgiN3IroRnrbWjCPvNIPsaUCGsQnfeFTacwM+EqYJwvqoTwIDk9tGa64En"
	// + "3kNZHq/91CscD1Ix0coUu8IbaoFJRhfqgL91bVmtaj41BR2oeI25KZ6vozLKFO05"
	// + "mKiBSVUFXn2tTHfV/MECQQD36q3Yj0DWl7u2t6DeWAS2518sbnY8kCJntL8/kV/J"
	// + "RcGHUk+8KVgb2sNXY1dRRdxpFXXdUoW2W7au8lMr1oVxAkEA8dufqu705qZatGWp"
	// + "8/FAcQcPDVYoc9ZE/PNS7FavYJO9++BjXhLQne9O8DIzYXVGiiswZ57D0l8lonkG"
	// + "oKD5vQJAdfAdfb1AYhXSxex7Serja0OMYi22b789rsoshQgaYjyeAUsmBWRBtxaO"
	// + "AR+QJWivnwoHhR9B3uuob3d2G4+08QJBANoi0MaV0TJ6ldKg4wFO9WU8DZpkvAWG"
	// + "GyDdwUvB/1mmxzJ2toixhZD8twCy9l6PLLkPrQ+4RTvnbwU0DQMxTS0CQBdHX2zp"
	// + "O32gjU4Pt2nElSz5mobeIdgCYfiCMdsNK3XM/j+QS1RMCjFUQ/jq70r1AYw8TLTF" + "ZFDIZHvH11QfFxE=";
	// // private String BaseUrl = "http://localhost:8080/bdss/";
	// private String BaseUrl = "http://192.168.100.196:8083/bdss/";
	private String publicKey = null;
	private String privateKey = null;
	private String BaseUrl = null;

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getBaseUrl() {
		return BaseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		BaseUrl = baseUrl;
	}

	public ResponseData testPmsOut(RequestPmsOut pmsOut) {
		if (null == pmsOut) {
			System.out.println("没有请求参数");
			return null;
		}

		String objectSignStr = PmsMerchantTools.getObjectSignStr(pmsOut);
		System.out.println(objectSignStr);
		String sign = encrptyRequestMessageFormat(objectSignStr);
		pmsOut.setSign(sign);
		System.out.println(JSON.toJSONString(pmsOut));
		ResponseData responseData = new DataOKHttp().setUrl(BaseUrl + "servlet/pms/doPmsOut").setDebug(true)
				.setRequestBody(JSON.toJSONString(pmsOut)).setPost(true).doHttp(String.class);
		System.out.println(responseData.toString());
		return responseData;
	}

	public ResponseData testPmsOut(Map<String, Object> pmsOutMap) {
		if (null == pmsOutMap || pmsOutMap.size() <= 0) {
			System.out.println("没有请求参数");
			return null;
		}

		String objectSignStr = ObjectToMapTools.getFieldsSignStr(pmsOutMap);
		System.out.println(objectSignStr);
		String sign = encrptyRequestMessageFormat(objectSignStr);
		pmsOutMap.put("sign", sign);
		System.out.println(JSON.toJSONString(pmsOutMap));
		ResponseData responseData = new DataOKHttp().setUrl(BaseUrl + "servlet/pms/doPmsOut").setDebug(true)
				.setRequestBody(JSON.toJSONString(pmsOutMap)).setPost(true).doHttp(String.class);
		System.out.println(responseData.toString());
		return responseData;
	}

	public ResponseData testPmsQuery(RequestPmsQuery pmsQuery) {
		if (null == pmsQuery) {
			System.out.println("没有请求参数");
			return null;
		}
		String objectSignStr = PmsMerchantTools.getObjectSignStr(pmsQuery);
		System.out.println(objectSignStr);
		String sign = encrptyRequestMessageFormat(objectSignStr);
		pmsQuery.setSign(sign);
		System.out.println(JSON.toJSONString(pmsQuery));
		ResponseData responseData = new DataOKHttp().setUrl(BaseUrl + "servlet/pms/pmsQuery").setDebug(true)
				.setRequestBody(JSON.toJSONString(pmsQuery)).setPost(true).doHttp(String.class);
		System.out.println(responseData.toString());
		return responseData;
	}

	public ResponseData testPmsQuery(Map<String, Object> pmsQueryMap) {
		if (null == pmsQueryMap || pmsQueryMap.size() <= 0) {
			System.out.println("没有请求参数");
			return null;
		}
		String objectSignStr = ObjectToMapTools.getFieldsSignStr(pmsQueryMap);
		System.out.println(objectSignStr);
		String sign = encrptyRequestMessageFormat(objectSignStr);
		pmsQueryMap.put("sign", sign);
		System.out.println(JSON.toJSONString(pmsQueryMap));
		ResponseData responseData = new DataOKHttp().setUrl(BaseUrl + "servlet/pms/pmsQuery").setDebug(true)
				.setRequestBody(JSON.toJSONString(pmsQueryMap)).setPost(true).doHttp(String.class);
		System.out.println(responseData.toString());
		return responseData;
	}

	private String encrptyRequestMessageFormat(String data) {
		try {
			PrivateKey mchtPrivateKey = RSAUtils.getPrivateKey(Base64.decode(privateKey));
			return RSAUtils.getSignData(data, mchtPrivateKey);

		}
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

}
