/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年6月12日 下午10:08:29
 */
package com.webauth;

import java.util.HashMap;

import com.ruomm.base.http.ResponseData;
import com.ruomm.base.http.okhttp.DataOKHttp;
import com.webauth.tools.WebAuthBaseTest;

public class TestMsg extends WebAuthBaseTest {
	public static String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCPEl44/utLAJ1hwl0h7XcU5tOqHCb6Yd3iWYx9LujG5xSWXwwzfK3/vi9US3Z2nueJno8FMlRTQOixgMBUvBXl6l/gGziu7KSXgNFvuQLe8C2Qvck+djHStc2QVKxztbJx51zijBzNZPd7so84G+oPvn89fauX63/k2zjxPjUG8wIDAQAB";
	public static String desKeyStr = "kXywNN83L8jIp5h640ZR0OMCcyrTUldn";
	public static String pwdEncrypt = "RSA";
	public static String oldPwdEncrypt = "RSAMD5";
	public static String newPwdEncrypt = "RSA";

	public static void main(String[] args) {
		// doRegister();
		// doModifyPwd();
		// doMsgSend();
		// doModifyName();
		// doModifyEmail();
		doMsgSend1();
		// doShiroDemo();
	}

	public static void doMsgSend1() {
		HashMap<String, Object> map = new HashMap<>();
		// /13656655336
		// map.put("userId", "13355667788");
		// map.put("userId", );
		map.put("msgFunction", "1");
		map.put("uuid", BASE_UUID);

		map.put("msgAddr", "13738055782");
		// HashMap<String, String> headers = new HashMap<>();
		// headers.put("JSESSIONID", "cc6ae670-db67-449d-a094-bfc96ef7c352");
		// headers.put("cookie", "JSESSIONID=851d812e-5cb2-48c1-9629-4f76a9e2043a");

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "app/msg/doMsgSend")
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doMsgSend() {
		HashMap<String, Object> map = new HashMap<>();
		// /13656655336
		map.put("userId", "13355667788");
		map.put("token", "tokentest");
		map.put("userId", "123213");
		map.put("msgFunction", "1");
		map.put("uuid", BASE_UUID);

		map.put("msgAddr", "13738085782");
		// HashMap<String, String> headers = new HashMap<>();
		// headers.put("JSESSIONID", "cc6ae670-db67-449d-a094-bfc96ef7c352");
		// headers.put("cookie", "JSESSIONID=851d812e-5cb2-48c1-9629-4f76a9e2043a");

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "msg/doMsgSend")
				.setRequestBody(getRequestBody(map)).setCookieReadPath(BASE_COOKIE_PATH).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doShiroDemo() {
		HashMap<String, Object> map = new HashMap<>();
		// /13656655336
		map.put("userId", "13355667788");
		map.put("token", "tokentest");
		map.put("userId", "123213");
		map.put("msgFunction", "1");

		map.put("uuid", "66778899");

		map.put("msgAddr", "13738085782");

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "mydemo/login")
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

}
