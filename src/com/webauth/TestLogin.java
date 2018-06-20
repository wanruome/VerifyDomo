/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年6月12日 下午10:08:29
 */
package com.webauth;

import java.security.PublicKey;
import java.util.HashMap;

import com.ruomm.base.http.ResponseData;
import com.ruomm.base.http.okhttp.DataOKHttp;
import com.ruomm.base.tools.Base64;
import com.ruomm.base.tools.DesUtil;
import com.ruomm.base.tools.EncryptUtils;
import com.ruomm.base.tools.RSAUtils;
import com.webauth.tools.WebAuthBaseTest;

public class TestLogin extends WebAuthBaseTest {
	public static String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtGQvj57TW+zPcHcLFRf90XT4N20PXvufEqcCNjT3+GZoCbuidDrbQWbx/ZIob0RYZ7pLwzOAJjQ6Ace1gAdCAPLrrglGxAcy3ZP1vMipsSSTpdMdGW7n8llqJ4OLeg+ZjZdiB+0AaBH6vigDhSRwRd4PR/zDoWJyqkkbncxe7NQIDAQAB";
	public static String privateKeyStr = "MIICdAIBADANBgkqhkiG9w0BAQEFAASCAl4wggJaAgEAAoGBAJu6syab85kPXE7h3AtkrkyifrlJbN7rpjnPfR/HLUzyUhStHmzdnM7tgl+NFFlB0KOvBEQ2Gp7RoF2ZjKk3+2hwdnme8v0cdnV+ZvtfaPU8aAA/nn1dUA6WHbBpDA+Refm8BRdKQWhFE2UFpYChyuYs/KLll2DGZRcTqOykbgRpAgMBAAECfy7W+Quj3KEs/Za8WHMU1Vg3h+YzL4vdVSIORpRIVFfQit+tMnmMLp4O0ifrRzORmC+jdKUPoPIkGxgMF0LOk89bgc2wsU0lSuT/UnW9QawHvcF7r0lQg2UBwrPiAMR0YXP2DbMeWoKNv/ynBMg7sAjSyh/wZ2Em7uKjTBEhEcUCQQDzv1oySAPj5H5+HWYu+qJJ56J1U9rSzMGTm+GZSs7BFCVa1JJk/HwzSEkxi3JlCE/QvhCPEKr8zlM01gqN9NE/AkEAo46xDe1+vuhF1FivlR0qGQYQk6HXvQyUbGGG2rUeC7/nMPXNd2DBKDOzbQSpqOmX/Gn2+wCp9xhNm7lEaSMYVwJBAJ3XMQpUdihyQ7NZSF1tsgAXvq0pkw9kxonWrL1+ouqHKDVsaCx7D9aJndQ2j+p6+mybV8+9JGy3E0youob4nTMCQDjEvymBZDuo7QvOGGteKw1ycHB9fR8N7bpMg30H3jzfx8PTnRQxTfnBMCbHtod9RJaExQfL7DJqig1h2Z/QYZMCQHlU7SvrEmiIvhSer8DeeTEPyk71ciDa2s8eo5qkPHzlVE8Q/SPtxVnr2o235bX8VELqPYo+zzE575ffLQ/VFnY=";
	public static String desKeyStr = "kXywNN83L8jIp5h640ZR0OMCcyrTUldn";
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
		map.put("account", "13355667780");
		map.put("accountType", "1");
		map.put("email", "yangmi@163.com");
		map.put("uuid", "66778899");
		map.put("pwdEncrypt", pwdEncrypt);
		map.put("pwd", getPassWord("123456", pwdEncrypt));

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/userAccount/doRegister").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doLogin() {
		HashMap<String, Object> map = new HashMap<>();
		// /13656655336
		map.put("account", "13355667766");
		map.put("accountType", "1");
		map.put("appId", "1000");
		map.put("uuid", BASE_UUID);
		map.put("pwdEncrypt", oldPwdEncrypt);
		map.put("pwd", getPassWord("123456", oldPwdEncrypt));
		map.put("termType", "1");
		// map.put("msgVerifyCode", "21750736");
		// .setCookieSavePath(BASE_COOKIE_PATH)
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "app/userAccount/doLogin")
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
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

	public static String getPassWord(String pwdStr, String pwdEncrypt) {
		if (pwdEncrypt.equals("NONE")) {
			return pwdStr;
		}
		else if (pwdEncrypt.equals("RSA")) {
			PublicKey publicKey = RSAUtils.loadPublicKey(publicKeyStr);
			byte[] dataEnt = RSAUtils.encryptData(pwdStr.getBytes(), publicKey);
			return Base64.encode(dataEnt);
		}
		else if (pwdEncrypt.equals("RSAMD5")) {
			PublicKey publicKey = RSAUtils.loadPublicKey(publicKeyStr);
			byte[] dataEnt = RSAUtils.encryptData(EncryptUtils.EncodingMD5(pwdStr).getBytes(), publicKey);
			return Base64.encode(dataEnt);
		}
		else if (pwdEncrypt.equals("3DES")) {
			return DesUtil.encryptString(pwdStr, desKeyStr);
		}
		else if (pwdEncrypt.equals("3DESMD5")) {
			return DesUtil.encryptString(EncryptUtils.EncodingMD5(pwdStr), desKeyStr);
		}
		else if (pwdEncrypt.equals("MD5")) {
			return EncryptUtils.EncodingMD5(pwdStr);
		}
		else {
			return null;
		}
	}
}
