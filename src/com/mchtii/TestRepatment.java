/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年5月25日 上午11:10:24
 */
package com.mchtii;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruomm.base.http.ResponseData;
import com.ruomm.base.http.okhttp.DataOKHttp;
import com.ruomm.base.tools.EncryptUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TestRepatment {
	private static JSONObject loginJson = null;
	private static Map<String, String> headersMap = new HashMap<>();
	// private static String BASE_URL = "http://127.0.0.1:8090/";
	// private static String BASE_URL = "http://183.129.219.202:8091/";
	private static String BASE_URL = "http://192.168.100.66:8090/";
	private static String BASE_MOBILE = "18100000000";
	private static String BASE_PWD = "123456";

	public static void main(String[] args) {
		login();

		if (headersMap.size() <= 0) {
			headersMap.put("token", "fe75b7945fb0476aa7b0dcc11ca11ebb");
			headersMap.put("mobile", BASE_MOBILE);
		}
		// logout();
		// sendSmsCode();
		// testBindCard();
		// testGetPayInfo();
		// testUnBindCard();
		// testBindCardResult();
		// testGetQrcode();
		// testQueryOrders();
		// testSetPayPassword();

		// testQueryBindcards();

		// testQueryProvinceCity();
		// testFindPayPassword();
		// testModifyPayPassword();
		// testModifyNoPwdFlag();
	}

	public static void sendSmsCode() {
		ResponseData data = new DataOKHttp().setPost(false).setDebug(false)
				.setUrl(BASE_URL + "mobile/sms/send/13656655336/5").doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void login() {
		HashMap<String, Object> map = new HashMap<>();
		// /13656655336
		map.put("mobile", BASE_MOBILE);
		// map.put("accountNo", "6226090000000048");
		map.put("password", EncryptUtils.EncodingMD5(BASE_PWD));
		map.put("appType", "1");
		map.put("deviceInfo",
				"{\"appCode\":\"9\",\"appType\":\"1\",\"appVersion\":\"1.2.0\",\"osVersion\":\"Android 7.0\",\"phoneName\":\"HONOR KNT-AL20\"}");
		ResponseData data = new DataOKHttp().setPost(true).setDebug(false).setUrl(BASE_URL + "mobile/user/login")
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());

		loginJson = JSON.parseObject((String) data.getResultObject());
		headersMap.put("token", loginJson.getJSONObject("data").getString("token"));
		headersMap.put("mobile", BASE_MOBILE);

	}

	public static void logout() {
		// HashMap<String, Object> map = new HashMap<>();
		// /13656655336
		// map.put("mobile", "13738055782");
		// // map.put("accountNo", "6226090000000048");
		// map.put("password", EncryptUtils.EncodingMD5("123456"));
		// map.put("appType", "1");
		// map.put("deviceInfo",
		// "{\"appCode\":\"9\",\"appType\":\"1\",\"appVersion\":\"1.2.0\",\"osVersion\":\"Android
		// 7.0\",\"phoneName\":\"HONOR KNT-AL20\"}");
		ResponseData data = new DataOKHttp().setPost(false).setDebug(true).setUrl(BASE_URL + "mobile/user/logout/1")
				.setHeadersMap(headersMap).doHttp(String.class);
		System.out.println(data.toString());

		// loginJson = JSON.parseObject((String) data.getResultObject());
		// headersMap.put("token", loginJson.getJSONObject("data").getString("token"));
		// headersMap.put("mobile", "13738055782");

	}

	public static void testBindCard() {
		HashMap<String, Object> map = new HashMap<>();

		// map.put("accountNo", "6212261202037568699");
		map.put("accountNo", "6226090000000048");
		map.put("mobileNo", "18100000000");
		map.put("idcardNo", "510265790128303");
		map.put("name", "张三");
		map.put("area", "河南省-洛阳市");

		// new DataOKHttp().setPost(true).setDebug(true)
		// .setUrl("http://127.0.0.1:8090/mobile/repayment/bindCard/15166669999")
		// .setRequestBody(OkHttpConfig.attachFormRequestForamtBody(map)).doHttp(String.class);

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "mobile/repayment/callBindCard/" + BASE_MOBILE).setHeadersMap(headersMap)
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void testUnBindCard() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("sequenceNo", "000000000185");

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "mobile/repayment/callUnBindCard/" + BASE_MOBILE).setHeadersMap(headersMap)
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void testBindCardResult() {

		String dataStr = "IZqY9D/BbOw4fEytawbYO5OB7fIzEhaXNoUZ9rX09OaQB68uik1WI8F+Z5QGaUMowQrM2drVZwuDLzq+k0cwbbH53dwETeHlqTesRhPvTnxPYHTcGEHRhbtT4WbCI9Y3eO0MPVgVyIY0bqJj1xz2DokG1KSw7Lvjf2o8rpnx61k=|AY0htftgFltggVzQVWroAr6uHw4ibTx7sPUcZaUV/xzgUXeE8Mq0ll7FwwpCjjz98TPeurtgOvFsmesXpqxcd8NRz9551r3iAOmcbax8cyl7o1nHe3/XR8fdvMAm2q8i3Dxg4j5kLU4+atKtlbT1hHMbaIwKj5AmxADJvI/39lBgNzelQR78DGcAvXGAdTNEH+MOGz09Uh9OcQn8NoPe68ltMf+/eoskmfoiVIMZwnqv9tFK8pvlDFzntmQ5yeg4L0DylK29kr2+jaJ9vw7ZZ4nYbqKJ3ar5Jg+NB4WALjTMn++J7UytWZhkHy8TmNVQwGhqcmueR24gLY4h2yxKPUr183z+nnUSuP28H3n8OQ2G3gRDDmOzcVQMcC9+JH3cYEQ8h/VWukz3Vsoid1+j1cEdldPNqTb33C0vQmv+pdfll6/wiiyrSA789VSnpKxmUtRI5hD2rn5njYR+o3/34oCZoMIza1uN+kYMFBjUth484GtNV4en2b3bjxFLfhg0jOLEU5iu/FKLFvycozuq5t65YtHhRz0y4EMsp+im9QHyYXdyCM2zPKdL7LKIYyErlRQSlr/4Na/uLChsxXwSRvW/iFSQSBDi4PAsWUQWCDI1eFn57cwS4gf4nmWRihxZO2MZ4gCvRWmq6e8yOEzGw3pZMlmx4ueasEn7bnvO7jYOsEr2b+KGeBLUs0J1j9/ZED2a4TKY/5zHu3se6+n85xDaZkXjTOq0rfosHLWy32oBgfVlJSd5trWq1VCD0NsxkHF8pefdHfQYaGHhU9LzSjexvjJKIjTKG6O26gxFvgM3sb4ySiI0yt37vVntuj694r6zpdpvnQkXNfpW2TGKtX9YfFKb/tqhnRCJwjrRv4g6CBUqPUVqOOrNDLT+nbpwaub40eJ3aFWKjBpg5/jT6oJqdYeVWwoz3TsPbz1opuxeBuwkNwsEzVj9pUp70A/1EYfAW4Rm7Tz6N8OWQeBzDitinCcOEE/YPqeTuOV1KNAMLskIFC58hNsdcZGs4dHSdqnd8b+EB9C6bK7/MZJt0JjIjhuIswXMFQ/sVe0j/vc5tYI2EqV37N/ft9CD1c2GG6O26gxFvgNOoEkiXs/EK8e7ex7r6fzn4+9xrCCJShuZ6jljSyV//wf4nmWRihxZO2MZ4gCvRWmq6e8yOEzGw3pZMlmx4uea4k25uxZ88JITqQotWB2h/rFAI9VWCuL+vOZQ6l6ybO491Tfw44CfURUxmtDEsz2xL6rQxVylLbDTHtT8XifD9VhvDlMxI/xr9pDhlpguiNDrAHyUWE/Qa87V9b7i/t40NrsoKcSOM47vi8dHekVW2VaBOEgYJHUknTfI6hkL8lfJ1fjJSBXTu15GhWDAXAQ2eXCvybobIkYBgfVlJSd5trWq1VCD0NsxkHF8pefdHfQYaGHhU9LzSgHFb7RhtwtAkNHEVHnJMkn7zHVVJwfMfxCDiIn78jQh3fu9We26Pr1/nUmYiOmm2k1qcvbXoO2PhUgOTn26XkwqtDiGTANkg0PoMmv4W6mZ5sYOE6MlytH48yPbM85tCYWdjud4ricYf9bYaOlUQkJLo7HL+0o8LqUbhMDw3yHd73/ae4cqoJfGjBd7hYw6/ibHxOYNkVssKyVzHE2E17GQMNvwc3fEWti+BGgxE5Mna/XcNzWLstA3eewvujtcHomvSPuH8V+gZsjmGGCCTAJDbJJofPSAUPRIeT0ZTAFRaQI36RlN+dn6fxCs6EHIYigq/fu4A+Ab9wLQfjx6XjLxdJ559Ml4BrYxlV2dKWrHhqN01NrrQzLMTCj8Xkx3KTDG11n3pibydjXJUeOgXFzyun4ci0y4U3ILtdc1EvW3nEpC80NICY8p/gaRd2zf4wx8S3kOVIBXfH48mO5NNAW44oMnVRgTDLXI6hZ4pZJiyDWjCdh1RE1f4ZX/r9aOpO+gRE7FmjY9T2KPDiH5g71n01aUZERwMIcRuGFqrasd/voBbwcvIYY0/Lu5l1B+wZB75j7cAS9N+W0aD8ONZAkbLhsCHWTwR2nOidIM64W35mz9Tn37PmL6olSxDi7Y3ylKC5QuLX0IA3opgGfGhIOrrbP7xCK5CdIVAhEeb7gzKdrwQYEUqzE8k1BCnTi9Y3mDcO7qwndHUXkvRzW4c3pLR7/gwXX4C8Y7Upl3tFXLEJNyptfXD15Ao+KislxoHAC5osyePhEvSOorrVgjUI/ttSRr4z2wjQf4nmWRihxZO2MZ4gCvRWmq6e8yOEzGw3pZMlmx4ueaydr7nqhs3iAbo7bqDEW+A6lqgvRMyzdP17hsS4CRuZCTE4ZAaIJpgQf4nmWRihxZO2MZ4gCvRWmq6e8yOEzGw3pZMlmx4ueaYKFbPEeeQuobo7bqDEW+Ay3/OKgfvQ+317hsS4CRuZBW+JigkISXtl4G7CQ3CwTNWP2lSnvQD/URh8BbhGbtPNdR2Uai/ikJsiXNfRlL++WjCzSzKAqxh937vVntuj69a43fS5B1J2IxXUIlPOnXsoOTl12+Ph1oMmyUX+wazxvEhbsicxSBLJeSexBDEe3oHQHlkwmJTWnOd3mC3c03fxNbw7jd/YLpFHBYFHVHmwGiYvklstDeDShOwqD/CDBPw4bC3XqYuG+u9ptRTrNfTViqg3CLAp/FbsUEgLv7ZGcBxbxC/wgJ2yN7g6dhnVb4qdEXrGeE4WKgMTfPbM5wHriAc5kPt/W7tw06lLvQMwGFzzXolIslydR5Lm7a2iBIimWoiyngxYOSX9NYLQYfjR537LO8b2Pwhk5pfTF8pJHlnFeTd2/GyWfKf7bFnVLQMU786t3t0oXFAZGHOAHDNEZD3dsgBhElzqcLALtY2bYrS9sr+gc86KrWxWI5BIZZa/JDm3UH3dVDQ4Gl/QFLNOXcL0LB5iU7hxsFsc3pu28urThL4JQl4/X+paRJ//XWbXETeq3LF0uuse22rMXP4esAfJRYT9BrztX1vuL+3jQ2uygpxI4zjhovn5l/71yz8azRtpe4rAGCHNAWNJ9PjsjCfz7G54h84pei5dhNun6h5EueFP7OWQGB9WUlJ3m2tarVUIPQ2zGQcXyl590d9BhoYeFT0vNK36+kmMFBFLqJNe6lE0hOLSJTKLglWLPWw7Eb4sNL8Z0Wd+VB/KO94xomEIXz0INNm6hgARn24GPn3IGFw9ZUVgFlIRdHMcFwkKiA8WpD31L3Kg3iNGW89AcdrooFopH+7Kq0OwGJPwQrMAFB4826wc01xBnQ9Z4wg/aZb18QT5DyN1u6YR0tuGmS+mr/EeusG6O26gxFvgNAB1du9in3ycOxG+LDS/GdpgzDrOIniKgse9bO+6F0ql4G7CQ3CwTNWP2lSnvQD/URh8BbhGbtPDL4Z0DzWyyWA9V9h0K9PV9EV08DBOUVbgPVfYdCvT1fw7Eb4sNL8Z1xHWjhvOPW/gGB9WUlJ3m2tarVUIPQ2zGQcXyl590d9BhoYeFT0vNKRtYcDf7IdxKJNe6lE0hOLYB4AZ5tAj0dw7Eb4sNL8Z3o1OFk0Hja4gf4nmWRihxZO2MZ4gCvRWmq6e8yOEzGw3pZMlmx4ueaWD1k+0NYPaliohp+b2cSv0RXTwME5RVuWA93ji7Jb23DMR3adaQ5Xs5gCEvaRj52MUrozpiZ6fcpHSTVN2hLupjIjhuIswXMFQ/sVe0j/vc5tYI2EqV37FMRqrazwTOL+bmze+KeP/Tjswt4CAPlUTIpoBtsgfM5x7t7Huvp/OcNu3ctkoxy3jOexz7R0FtcUBDOxrlI8yNilOS/rxPKXUwN+fRePhqpUuUSlwm+fIBt3BfxZ0JMCnVnnMDVzO1VYh7rjzN6MOBURcAYQjeI2VBfjPy6DGRqUbqvaaOmhS2GrIWIrJb2epHaq2sJUd6j7nvokbNysHYDq24O6ibSm8T9mfdp4yuuZc0Z8l6I+ePw0P+VZmTQ5iEFifBnxxLj2F57x8L3TnxVFl7kYtYwuXnLJh+awSthLmOBT9ycCgWVnq7yMfBJ1RlF9Cl5n4EIASzQlVHqx7INLAKF3Jd39HMVLsqirNqZOZYp8r81lVfxgxNjWiWjPizHgYSQhHHFEKEP4XGQqEKIK5aJfr9HZLPQl/iQpxucQVtrmrl9ghXu0Pu23DMyXVtpp9Y8tFH3gAOP46BNClacYl2AhCAiLw7V5sz3hVX/Whv2vJiRJC+WKv4hMPGeQSswAUHjzbrBzTXEGdD1njCD9plvXxBPkPI3W7phHS24HuXDKejcvkZG02E0VWqtwgSFuRDuU/KH3fu9We26Pr2jcChdA0/9W6B/nZgPBjS0a7csQqDKvKEpHSTVN2hLupjIjhuIswXMFQ/sVe0j/vc5tYI2EqV37E4x2bk63S3fm9uhVCeO4mE8+EZtgJR/2V5GhWDAXAQ2PKD4sUBe3Etqybutg91v02PmpjLjtuhi4EMsp+im9QHyYXdyCM2zPKdL7LKIYyErlrYje+74pX/RSWCHgeIx8pywc6Pt5fn2YSh8s6q4jNDd+71Z7bo+vQ6E/UzeEenEWI0WDc9pYVJkgrp7lti52n8ACRARFvaTWJ2AqctLa9upMl2kRB1TVPpo8h9MNfPSozKMaQbahpeDYFHiqwFgZDSvUvM43uUUiX+kjvAHCUiEu4rZIgv8cRWvUUJ93bJekZcbsrL7pyYX6bimmNM3P6owtjo5G65EDHzHawX2hRfSugF7LS3Szh8hHieG9ilH7SkJgm56mfaTqCD/3Qw5aB7iG4LdEPRrAFSnDEnTC1/1F886DUdpbeKTZuKavrgr3TO87VGONaDmIF62GrQjbYrTxF1fAFz2rvshoadTvaRPl6NQS+t4lUIUAj30kJeIJQ1X16GxsX/Ge0OkPCGLVyDRktfPo7czbYp+QSfzTIKsCMeG5tYSc3u1vnpV3V/O";
		// HashMap<String, Object> map = new HashMap<>();
		// map.put("accountNo", "a");
		// map.put("mobileNo", "a");
		// map.put("idcardNo", "a");
		// map.put("name", "a");
		// OkHttpConfig.attachFormRequestForamtBody(map);
		// new DataOKHttp().setPost(true).setDebug(true)
		// .setUrl("http://127.0.0.1:8090/mobile/repayment/bindCard/15166669999")
		// .setRequestBody(OkHttpConfig.attachFormRequestForamtBody(map)).doHttp(String.class);

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "mobile/repayment/syncBindCardResult").setHeadersMap(headersMap)
				.setRequestBody(dataStr).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void testGetQrcode() {

		HashMap<String, Object> map = new HashMap<>();
		map.put("sequenceNo", "000000000082");

		// OkHttpConfig.attachFormRequestForamtBody(map);13656655336

		// new DataOKHttp().setPost(true).setDebug(true)
		// .setUrl("http://127.0.0.1:8090/mobile/repayment/bindCard/15166669999")
		// .setRequestBody(OkHttpConfig.attachFormRequestForamtBody(map)).doHttp(String.class);

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "mobile/repayment/callQrcode/" + BASE_MOBILE).setHeadersMap(headersMap)
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void testQueryOrders() {

		HashMap<String, Object> map = new HashMap<>();
		// map.put("sequenceNo", "000000000045");
		map.put("mobileNo", "18100000000");
		map.put("orderDate", "20180529-20180530");

		// OkHttpConfig.attachFormRequestForamtBody(map);13656655336

		// new DataOKHttp().setPost(true).setDebug(true)
		// .setUrl("http://127.0.0.1:8090/mobile/repayment/bindCard/15166669999")
		// .setRequestBody(OkHttpConfig.attachFormRequestForamtBody(map)).doHttp(String.class);

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "mobile/repayment/callQueryOrders/13738055782").setHeadersMap(headersMap)
				.setRequestBody(getRequestBody(map)).doHttp(String.class);

		JSONObject jsonObject = JSON.parseObject((String) data.getResultObject());
		System.out.println(data.toString());
		System.out.println(jsonObject);
	}

	public static void testQueryBindcards() {

		// OkHttpConfig.attachFormRequestForamtBody(map);13656655336

		// new DataOKHttp().setPost(true).setDebug(true)
		// .setUrl("http://127.0.0.1:8090/mobile/repayment/bindCard/15166669999")
		// .setRequestBody(OkHttpConfig.attachFormRequestForamtBody(map)).doHttp(String.class);

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "mobile/repayment/callQueryBindcards/" + BASE_MOBILE).setHeadersMap(headersMap)
				.setRequestBody("").doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void testQueryProvinceCity() {

		HashMap<String, Object> map = new HashMap<>();
		// map.put("province", "河南");
		// map.put("orderDate", "20180527-20150529");
		// map.put("version", "472036969");

		// OkHttpConfig.attachFormRequestForamtBody(map);13656655336

		// new DataOKHttp().setPost(true).setDebug(true)
		// .setUrl("http://127.0.0.1:8090/mobile/repayment/bindCard/15166669999")
		// .setRequestBody(OkHttpConfig.attachFormRequestForamtBody(map)).doHttp(String.class);

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "mobile/repayment/queryProvinceCity").setHeadersMap(headersMap)
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void testGetPayInfo() {

		HashMap<String, Object> map = new HashMap<>();
		// map.put("province", "河南");
		// map.put("orderDate", "20180527-20150529");
		// map.put("version", "472036969");

		// OkHttpConfig.attachFormRequestForamtBody(map);13656655336

		// new DataOKHttp().setPost(true).setDebug(true)
		// .setUrl("http://127.0.0.1:8090/mobile/repayment/bindCard/15166669999")
		// .setRequestBody(OkHttpConfig.attachFormRequestForamtBody(map)).doHttp(String.class);

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "mobile/repaymentPayinfo/getUserPayinfo/13738055782").setHeadersMap(headersMap)
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void testSetPayPassword() {

		HashMap<String, Object> map = new HashMap<>();
		map.put("payPassword", EncryptUtils.EncodingMD5("123456"));
		map.put("smsCode", "472036");

		// OkHttpConfig.attachFormRequestForamtBody(map);13656655336

		// new DataOKHttp().setPost(true).setDebug(true)
		// .setUrl("http://127.0.0.1:8090/mobile/repayment/bindCard/15166669999")
		// .setRequestBody(OkHttpConfig.attachFormRequestForamtBody(map)).doHttp(String.class);

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "mobile/repaymentPayinfo/setPayPassword/13738055782").setHeadersMap(headersMap)
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void testModifyPayPassword() {

		HashMap<String, Object> map = new HashMap<>();
		map.put("payPassword", EncryptUtils.EncodingMD5("111111"));
		map.put("oldPassword", EncryptUtils.EncodingMD5("654321"));

		// OkHttpConfig.attachFormRequestForamtBody(map);13656655336

		// new DataOKHttp().setPost(true).setDebug(true)
		// .setUrl("http://127.0.0.1:8090/mobile/repayment/bindCard/15166669999")
		// .setRequestBody(OkHttpConfig.attachFormRequestForamtBody(map)).doHttp(String.class);

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "mobile/repaymentPayinfo/modifyPayPassword/13738055782").setHeadersMap(headersMap)
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void testFindPayPassword() {

		HashMap<String, Object> map = new HashMap<>();
		map.put("payPassword", EncryptUtils.EncodingMD5("654321"));
		map.put("smsCode", "208610");

		// OkHttpConfig.attachFormRequestForamtBody(map);13656655336

		// new DataOKHttp().setPost(true).setDebug(true)
		// .setUrl("http://127.0.0.1:8090/mobile/repayment/bindCard/15166669999")
		// .setRequestBody(OkHttpConfig.attachFormRequestForamtBody(map)).doHttp(String.class);

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "mobile/repaymentPayinfo/findPayPassword/13738055782").setHeadersMap(headersMap)
				.doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void testModifyNoPwdFlag() {

		HashMap<String, Object> map = new HashMap<>();
		map.put("noPwdFlag", "0");

		// OkHttpConfig.attachFormRequestForamtBody(map);13656655336

		// new DataOKHttp().setPost(true).setDebug(true)
		// .setUrl("http://127.0.0.1:8090/mobile/repayment/bindCard/15166669999")
		// .setRequestBody(OkHttpConfig.attachFormRequestForamtBody(map)).doHttp(String.class);

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "mobile/repaymentPayinfo/modifyNoPwdFlag/13738055782").setHeadersMap(headersMap)
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

	private static RequestBody getRequestBody(Object bodyParameters) {
		RequestBody requestBody = null;

		String stringBody = "";
		if (bodyParameters != null) {

			if (bodyParameters instanceof String) {
				stringBody = (String) bodyParameters;
			}
			else {
				stringBody = JSON.toJSONString(bodyParameters);
			}
		}
		requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf8"), stringBody);

		return requestBody;
	}
}
