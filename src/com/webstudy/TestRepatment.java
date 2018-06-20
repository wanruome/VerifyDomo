/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年5月25日 上午11:10:24
 */
package com.webstudy;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ruomm.base.http.ResponseData;
import com.ruomm.base.http.okhttp.DataOKHttp;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TestRepatment {
	// private static String BASE_URL = "http://127.0.0.1:8090/";
	private static String BASE_URL = "http://localhost:8080/webstudy/";
	private static Map<String, String> headerMaps = new HashMap<>();

	public static void main(String[] args) {
		headerMaps.put("aasa", "123123");
		headerMaps.put("dafs", "adsf");
		// login();

		// login();
		getLogin();
		// postLogin();

	}

	public static void login() {
		HashMap<String, Object> map = new HashMap<>();
		// /13656655336
		map.put("loginIx", "1132");
		// map.put("accountNo", "6226090000000048");

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "login/doLogin")
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());

	}

	public static void getLogin() {
		HashMap<String, String> map = new HashMap<>();
		// /13656655336
		map.put("loginUser", "1231");
		map.put("loginName", "张蜜");
		// map.put("accountNo", "6226090000000048");

		ResponseData data = new DataOKHttp().setPost(false).setDebug(true)
				.setUrl(BASE_URL + "login/getLogin?userId=12323&userName=zhangmi").setHeadersMap(headerMaps)
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());

	}

	public static void postLogin() {
		HashMap<String, String> map = new HashMap<>();
		// /13656655336
		map.put("loginUser", "1231");
		map.put("loginName", "张蜜");
		// map.put("accountNo", "6226090000000048");

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "login/postLogin")
				.setHeadersMap(headerMaps).setRequestBody(getRequestBody(map)).doHttp(String.class);
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
