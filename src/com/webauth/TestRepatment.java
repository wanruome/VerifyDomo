/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年5月25日 上午11:10:24
 */
package com.webauth;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ruomm.base.http.ResponseData;
import com.ruomm.base.http.okhttp.DataOKHttp;
import com.webauth.tools.WebAuthBaseTest;

public class TestRepatment extends WebAuthBaseTest {
	private static JSONObject loginJson = null;
	private static Map<String, String> headersMap = new HashMap<>();
	// private static String BASE_URL = "http://127.0.0.1:8090/";
	// private static String BASE_URL = "http://183.129.219.202:8091/";
	//
	// private static String BASE_MOBILE = "18100000000";
	// private static String BASE_PWD = "123456";

	public static void main(String[] args) {
		// testBindCard();
		// testUnBindCard();
		// testCallBindCardResult();
		// testUnBindCard();
		// testQueryProvinceCity();
		// testCallQrCode();
		// testQueryOrders();
		testDoQueryBindCards();
		// testQueryAllCitys();
	}

	public static void testBindCard() {
		Map<String, String> map = createRequestMap(true);

		// map.put("accountNo", "6212261202037568699");
		map.put("accountNo", "62260900000000482");
		map.put("mobileNo", "181000000002");
		map.put("idcardNo", "5102657901283032");
		map.put("name", "张三2");
		map.put("area", "河南省-洛阳市2");
		signRequest(map);

		// new DataOKHttp().setPost(true).setDebug(true)
		// .setUrl("http://127.0.0.1:8090/mobile/repayment/bindCard/15166669999")
		// .setRequestBody(OkHttpConfig.attachFormRequestForamtBody(map)).doHttp(String.class);

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "app/repayment/doBindCard")
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void testCallBindCardResult() {
		String dataStr = "SQXH64CCz9eRuXrws75uGB/rx1pKNTZhMPMFIboteE9TkHlKJnZdCx6kNL8YoyAD1nMwyM1IIjWmPdxXCkeMtFVGIBel7U/a4ICOmDB3CdIqUxbn5ODijldfM/vPhd584YgEeiESpCr3BtZDahifpNRlbxAixkzwsOBbXUBBNmI=|e4fSsFIzfWiQXPtUGQOsgB0TNkVhQA4grhrI+k+SzN70EAvvHGDHoVWlu5H5gaG89LwB3OARgZz0yj9S+y7M3ijTsgCgmtzrb7gwgo113p88WHE1wAw8Euc/U0/MKlrdXCN974qCFSssTe2mPX/VqXFukptage5FRQ3noLdErOYi1Wsp/V5UOCq3Aup+ZDTIZlIeFAaIOizyd5GAR8HiPFvVsDlqkL1b5panNTSj8jA0Dewf22aN8kvoVBIO78BpxeT1+tqCI0Gi/ZR+l5G8745xJ+RO2BguVuA9BGKKRmQoR/9Sdk4f7riLhA4c1h0xy6kd9BQkizpXZZ0BTgr7iCN3hXQRlkgWbYl5JCfLv8AG8/NyyC/J6PH2C91wYfzmKxgiIjn46hmvRdlvAMDrNHGuL6Wg8CZtekCCDScTXD1XeD03vRdkiYGzju35NXugqAVXkz8O8JYVz3/s8b4L4Ve7vw5lzUvWHnpV1yL+Libv5Q+tqSxtN9rb1M5q3v2pVQmA7cJOaSPq2FMEF1t8WJW+z1+iKyhUkyw1UWIaJMpM9B5xlY+yXg==";
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl("http://192.168.1.99:8080/mchtAppUserApi/app/repayment/callBindCardResult")
				.setRequestBody(dataStr).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void testUnBindCard() {
		Map<String, String> map = createRequestMap(true);
		map.put("sequenceNo", "100010");
		signRequest(map);
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/repayment/doUnBindCard").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void testCallQrCode() {
		Map<String, String> map = createRequestMap(true);
		map.put("cardIndex", "100010");
		signRequest(map);
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/repayment/doCallQrcode").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void testQueryOrders() {
		Map<String, String> map = createRequestMap(true);
		map.put("cardIndex", "100010");
		// map.put("mobileNo", "18100000000");
		map.put("orderDate", "20180529-20180531");
		signRequest(map);
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/repayment/doQueryOrders").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void testDoQueryBindCards() {
		Map<String, String> map = createRequestMap(true);
		// map.put("cardIndex", "100010");
		// map.put("mobileNo", "18100000000");
		signRequest(map);
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/repayment/doQueryBindCards").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void testQueryAllCitys() {
		Map<String, String> map = createRequestMap(true);
		// map.put("cardIndex", "100010");
		// map.put("mobileNo", "18100000000");
		map.put("version", "1");
		map.put("format", "dict");
		signRequest(map);
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/repayment/doQueryAllCitys").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());
	}

}
