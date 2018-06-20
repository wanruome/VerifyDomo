/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年5月18日 下午5:00:32
 */
package com.test;

import java.util.HashMap;
import java.util.Map;

import com.newpay.pmsverify.PmsOutVerifyUtil;

public class TestPmsOut {
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
	private static PmsOutVerifyUtil pmsoutUtil = new PmsOutVerifyUtil();
	static {
		pmsoutUtil.setPrivateKey(privateKey);
		pmsoutUtil.setPublicKey(publicKey);
		pmsoutUtil.setBaseUrl(BaseUrl);
	}

	public static void main(String[] args) {
		// pmsoutUtil.setPrivateKey(privateKey);
		// pmsoutUtil.setPublicKey(publicKey);
		// pmsoutUtil.setBaseUrl(BaseUrl);
		// 测试PMS出款
		testPmsOut();
		// 测试PMS查询
		testPmsQuery();

	}

	// 测试出款
	private static void testPmsOut() {
		Map<String, Object> map = new HashMap<>();
		map.put("crttime", "20180518170001");
		map.put("mchtno", "ZX0000010010089");
		map.put("groupFlag", "0");
		map.put("outamt", "6");
		map.put("outbatch", "ZX2018021000000000393505");
		map.put("outdate", "20180518");
		map.put("outRemarks", "出款");
		map.put("settleAcctNm", "张三");
		map.put("settleAcctNo", "6226388000000095");
		map.put("settleBankNm", "中国农业银行股份有限公司苍南龙翔支行");
		map.put("settleBankNo", "103301010719");
		pmsoutUtil.testPmsOut(map);
	}

	// 测试查询
	private static void testPmsQuery() {
		Map<String, Object> map = new HashMap<>();
		map.put("mchtno", "ZX0000010010089");
		map.put("outbatch", "ZX2018021000000000393505");
		map.put("outdate", "20180518");
		pmsoutUtil.testPmsQuery(map);
	}
}
