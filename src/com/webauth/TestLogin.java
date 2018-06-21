/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年6月12日 下午10:08:29
 */
package com.webauth;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruomm.base.http.ResponseData;
import com.ruomm.base.http.okhttp.DataOKHttp;
import com.ruomm.base.tools.FileUtils;
import com.ruomm.base.tools.StringUtils;
import com.webauth.tools.WebAuthBaseTest;

public class TestLogin extends WebAuthBaseTest {
	public static String pwdEncrypt = "3DESMD5";
	public static String oldPwdEncrypt = "RSAMD5";
	public static String newPwdEncrypt = "RSAMD5";

	public static void main(String[] args) {
		// doIndexJsp();
		doLogin();
		// doTest();
		// doRegister();
		// doModifyPwd();
		// doModifyMobie();
		// doModifyName();
		// doModifyEmail();
	}

	public static void doTest() {
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl("http://localhost:8080/webauth/app/appInfo/doTest").setRequestBody("12").doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doIndexJsp() {
		ResponseData data = new DataOKHttp().setPost(false).setDebug(true).setCookieReadPath(BASE_COOKIE_PATH)
				.setUrl(BASE_URL).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doRegister() {
		HashMap<String, Object> map = new HashMap<>();
		// /13656655336
		map.put("account", "13355667777");
		map.put("accountType", "1");
		map.put("email", "yangmi@126.com");
		map.put("uuid", BASE_UUID);
		map.put("appid", "66778899");
		map.put("pwdEncrypt", pwdEncrypt);
		map.put("pwd", getPassWord("123456", pwdEncrypt));
		map.put("msgVerifyCode", "61280659");
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/userAccount/doRegister").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doLogin() {
		HashMap<String, Object> map = new HashMap<>();
		// /13656655336
		map.put("account", "13355667777");
		map.put("accountType", "1");
		map.put("appId", "1000");
		map.put("uuid", BASE_UUID);
		map.put("pwdEncrypt", pwdEncrypt);
		map.put("pwd", getPassWord("123456", pwdEncrypt));
		map.put("termType", "1");
		// map.put("msgVerifyCode", "21750736");
		// .setCookieSavePath(BASE_COOKIE_PATH)
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "app/userAccount/doLogin")
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
		JSONObject jsonObject = JSON.parseObject((String) data.getResultObject());
		String loginData = jsonObject.getString("data");
		if (!StringUtils.isEmpty(loginData)) {
			FileUtils.writeFile(BASE_LOGIN_USER, jsonObject.getString("data"), false);
		}
	}

	public static void doModifyPwd() {
		HashMap<String, Object> map = new HashMap<>();
		// /13656655336
		map.put("userId", "100000");
		map.put("uuid", "66778899");
		map.put("oldPwdEncrypt", oldPwdEncrypt);
		map.put("newPwdEncrypt", newPwdEncrypt);

		map.put("oldPwd", getPassWord("123456ABC", oldPwdEncrypt));
		map.put("newPwd", getPassWord("123456", newPwdEncrypt));

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "userInfo/doModifyPwd")
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doModifyMobie() {
		HashMap<String, Object> map = new HashMap<>();
		// /13656655336
		map.put("userId", "100000");
		map.put("newMobile", "13355667799");
		map.put("verifyCode", "verifyCode");
		map.put("authToken", "authToken");

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "userInfo/doModifyMobie")
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doModifyName() {
		HashMap<String, Object> map = new HashMap<>();
		// /13656655336
		map.put("userId", "100000");
		map.put("newName", "zhangmi");
		map.put("verifyCode", "verifyCode");
		map.put("authToken", "authToken");

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "userInfo/doModifyName")
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doModifyEmail() {
		HashMap<String, Object> map = new HashMap<>();
		// /13656655336
		map.put("userId", "100000");
		map.put("newEmail", "zhangmi@163.com");
		map.put("verifyCode", "verifyCode");
		map.put("authToken", "authToken");

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "userInfo/doModifyEmail")
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

}
