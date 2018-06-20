/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年6月12日 下午7:55:46
 */
package com.webauth.tools;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class WebAuthBaseTest {
	// private static String BASE_URL = "http://127.0.0.1:8090/";
	public static String BASE_URL = "http://localhost:8080/webauth/";
	public static Map<String, String> headerMaps = new HashMap<>();
	public static String BASE_COOKIE_PATH = "D:\\temp\\webauth\\logincookie.txt";
	public static String BASE_UUID = "111122223333444455556666777788889999";

	public static RequestBody getRequestBody(Object bodyParameters) {
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
