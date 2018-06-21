/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年6月12日 下午7:55:46
 */
package com.webauth.tools;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ruomm.base.tools.Base64;
import com.ruomm.base.tools.DesUtil;
import com.ruomm.base.tools.EncryptUtils;
import com.ruomm.base.tools.FileUtils;
import com.ruomm.base.tools.RSAUtils;
import com.webauth.dal.LoginUserInfo;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class WebAuthBaseTest {
	// private static String BASE_URL = "http://127.0.0.1:8090/";
	private static LoginUserInfo loginUserInfo = null;
	public static String BASE_URL = "http://localhost:8080/mchtAppUserApi/";
	public static Map<String, String> headerMaps = new HashMap<>();
	public static String BASE_COOKIE_PATH = "D:\\temp\\webauth\\logincookie.txt";
	public static String BASE_UUID = "111122223333444455556666777788889999";
	public static String BASE_LOGIN_USER = "D:\\temp\\webauth\\loginuserinfo.txt";
	public static String PUBLIC_KEY_3DES = "0xMc9ATsAZi/48LZPXpUN5uzjLM9FjGr";
	public static String PUBLIC_KEY_RSA = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBU/U2REh26VkVS5J80Ct6V5vDBS2h0xKzE41Y26V2Ygd6VNmQNcq8A21zy0IfwOysH3OZqFvN0lymz0T9OPchKpr4WIdQ7PkExU/J9NLurZIfx9l2T5smz7J58XbehsL2b5k1veUR4CZWmPXBQ0C3hkUvrb+Gtz7GQmt1IAd4+QIDAQAB";

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

	public static Map<String, String> createRequestMap(boolean isLogin) {
		Map<String, String> requestMap = new HashMap<>();

		if (isLogin) {
			requestMap.put("appId", getLoginUserInfo().getAppId());
			requestMap.put("userId", getLoginUserInfo().getUserId());
			requestMap.put("tokenId", getLoginUserInfo().getTokenId());
			requestMap.put("uuid", BASE_UUID);
		}
		else {
			requestMap.put("uuid", BASE_UUID);
		}
		return requestMap;
	}

	public static LoginUserInfo getLoginUserInfo() {
		if (null == loginUserInfo) {
			String data = FileUtils.readFile(BASE_LOGIN_USER);
			loginUserInfo = JSON.parseObject(data, LoginUserInfo.class);
		}

		return loginUserInfo;
	}

	public static String getPassWord(String pwdStr, String pwdEncrypt) {
		if (pwdEncrypt.equals("NONE")) {
			return pwdStr;
		}
		else if (pwdEncrypt.equals("RSA")) {
			PublicKey publicKey = RSAUtils.loadPublicKey(PUBLIC_KEY_RSA);
			byte[] dataEnt = RSAUtils.encryptData(pwdStr.getBytes(), publicKey);
			return Base64.encode(dataEnt);
		}
		else if (pwdEncrypt.equals("RSAMD5")) {
			PublicKey publicKey = RSAUtils.loadPublicKey(PUBLIC_KEY_RSA);
			byte[] dataEnt = RSAUtils.encryptData(EncryptUtils.EncodingMD5(pwdStr).getBytes(), publicKey);
			return Base64.encode(dataEnt);
		}
		else if (pwdEncrypt.equals("3DES")) {
			return DesUtil.encryptString(pwdStr, PUBLIC_KEY_3DES);
		}
		else if (pwdEncrypt.equals("3DESMD5")) {
			return DesUtil.encryptString(EncryptUtils.EncodingMD5(pwdStr), PUBLIC_KEY_3DES);
		}
		else if (pwdEncrypt.equals("MD5")) {
			return EncryptUtils.EncodingMD5(pwdStr);
		}
		else {
			return null;
		}
	}
}
