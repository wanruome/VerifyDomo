/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年6月12日 下午10:08:29
 */
package com.webauth;

import java.util.HashMap;
import java.util.Map;

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
		// doLogout();
		// doTest();
		// doRegister();
		// doModifyUserInfo();
		// doGetUserInfo();
		// doModifyPwd();
		// doModifyMobie();
		// doModifyName();
		// doModifyEmail();
		// doFindPwd();

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
		map.put("account", "13355667799");
		map.put("accountType", "1");
		map.put("email", "yang@126.com");
		map.put("uuid", BASE_UUID);
		map.put("appid", "66778899");
		map.put("pwdEncrypt", pwdEncrypt);
		map.put("pwd", getPassWord("123456", pwdEncrypt));
		map.put("msgVerifyCode", "60760159");
		map.put("idCardName", "jianli");
		map.put("idCardNo", "410327198310091454");
		map.put("nickName", "jianli");
		map.put("headImg", "jianli");
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/userAccount/doRegister").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doLogin() {
		HashMap<String, String> map = new HashMap<>();
		// /13656655336
		map.put("account", "13355667799");
		map.put("accountType", "1");
		map.put("appId", "1000");
		map.put("uuid", BASE_UUID);
		map.put("uuidEncrypt", "3DES");
		map.put("pwdEncrypt", pwdEncrypt);
		map.put("pwd", getPassWord("111111", pwdEncrypt));
		map.put("termType", "1");
		map.put("timeStamp", System.currentTimeMillis() + "");
		signRequest(map, PUBLIC_KEY_3DES);
		// map.put("msgVerifyCode", "32456606");
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
		Map<String, String> map = createRequestMap(true);

		map.put("oldPwdEncrypt", oldPwdEncrypt);
		map.put("newPwdEncrypt", newPwdEncrypt);

		map.put("oldPwd", getPassWord("12345678", oldPwdEncrypt));
		map.put("newPwd", getPassWord("111111", newPwdEncrypt));
		map.put("msgVerifyCode", "54091940");
		signRequest(map);
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/userAccount/doModifyPwd").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
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

	public static void doFindPwd() {
		Map<String, String> map = createRequestMap(false);
		map.put("account", "13355667799");
		map.put("accountType", "1");
		map.put("newPwdEncrypt", oldPwdEncrypt);

		map.put("newPwd", getPassWord("111111", oldPwdEncrypt));
		map.put("msgVerifyCode", "32456606");
		// signRequest(map);
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "app/userAccount/doFindPwd")
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doGetUserInfo() {
		Map<String, String> map = createRequestMap(true);
		// /13656655336
		// map.put("idCardName", "jianli");
		// map.put("idCardNo", "410327198310091454");
		// map.put("nickName", "yang");
		// map.put("headImg", "http://www.baidu.com/");
		signRequest(map);
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/userAccount/doGetUserInfo").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doModifyUserInfo() {
		Map<String, String> map = createRequestMap(true);
		// /13656655336
		map.put("idCardName", "jianli");
		map.put("idCardNo", "410327198310091454");
		// map.put("nickName", "yang");
		// map.put("headImg", "http://www.baidu.com/");
		signRequest(map);
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/userAccount/doModifyUserInfo").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doLogout() {
		Map<String, String> map = createRequestMap(true);
		signRequest(map);
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "app/userAccount/doLogout")
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

}
