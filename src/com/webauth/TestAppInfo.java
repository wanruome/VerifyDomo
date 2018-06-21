/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年6月19日 上午10:35:41
 */
package com.webauth;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.ruomm.base.http.ResponseData;
import com.ruomm.base.http.okhttp.DataOKHttp;
import com.ruomm.base.tools.EncryptUtils;
import com.webauth.dal.AppInfoModify;
import com.webauth.tools.WebAuthBaseTest;

public class TestAppInfo extends WebAuthBaseTest {
	public static String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCburMmm/OZD1xO4dwLZK5Mon65SWze66Y5z30fxy1M8lIUrR5s3ZzO7YJfjRRZQdCjrwRENhqe0aBdmYypN/tocHZ5nvL9HHZ1fmb7X2j1PGgAP559XVAOlh2waQwPkXn5vAUXSkFoRRNlBaWAocrmLPyi5ZdgxmUXE6jspG4EaQIDAQAB";
	public static String privateKeyStr = "MIICdAIBADANBgkqhkiG9w0BAQEFAASCAl4wggJaAgEAAoGBAJu6syab85kPXE7h3AtkrkyifrlJbN7rpjnPfR/HLUzyUhStHmzdnM7tgl+NFFlB0KOvBEQ2Gp7RoF2ZjKk3+2hwdnme8v0cdnV+ZvtfaPU8aAA/nn1dUA6WHbBpDA+Refm8BRdKQWhFE2UFpYChyuYs/KLll2DGZRcTqOykbgRpAgMBAAECfy7W+Quj3KEs/Za8WHMU1Vg3h+YzL4vdVSIORpRIVFfQit+tMnmMLp4O0ifrRzORmC+jdKUPoPIkGxgMF0LOk89bgc2wsU0lSuT/UnW9QawHvcF7r0lQg2UBwrPiAMR0YXP2DbMeWoKNv/ynBMg7sAjSyh/wZ2Em7uKjTBEhEcUCQQDzv1oySAPj5H5+HWYu+qJJ56J1U9rSzMGTm+GZSs7BFCVa1JJk/HwzSEkxi3JlCE/QvhCPEKr8zlM01gqN9NE/AkEAo46xDe1+vuhF1FivlR0qGQYQk6HXvQyUbGGG2rUeC7/nMPXNd2DBKDOzbQSpqOmX/Gn2+wCp9xhNm7lEaSMYVwJBAJ3XMQpUdihyQ7NZSF1tsgAXvq0pkw9kxonWrL1+ouqHKDVsaCx7D9aJndQ2j+p6+mybV8+9JGy3E0youob4nTMCQDjEvymBZDuo7QvOGGteKw1ycHB9fR8N7bpMg30H3jzfx8PTnRQxTfnBMCbHtod9RJaExQfL7DJqig1h2Z/QYZMCQHlU7SvrEmiIvhSer8DeeTEPyk71ciDa2s8eo5qkPHzlVE8Q/SPtxVnr2o235bX8VELqPYo+zzE575ffLQ/VFnY=";

	public static void main(String[] args) {
		doModifyAppInfo();
		// doAddAppInfo();
	}

	public static void doAddAppInfo() {
		HashMap<String, Object> map = new HashMap<>();
		// /13656655336
		map.put("appName", "中国人民解放军");
		map.put("publicKey", "11331132312");
		map.put("termLimit", "20");
		map.put("termAndroidLimit", "9");
		map.put("termIphoneLimit", "6");
		map.put("termWebLimit", "3");
		map.put("newKillOut", "1");
		map.put("appPwd", EncryptUtils.encodingMD5("123456"));
		map.put("notifyUrl", "1");
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "app/appInfo/doAddAppInfo")
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doModifyAppInfo() {
		AppInfoModify appInfoModify = new AppInfoModify();
		appInfoModify.setAppId("100009");
		appInfoModify.setAppPwd(EncryptUtils.encodingMD5("12345678"));
		// appInfoModify.setAppNewPwd(EncryptUtils.EncodingMD5("12345678"));
		appInfoModify.setPublicKey(publicKeyStr);
		// appInfoModify.setAppName("112332");
		// appInfoModify.setNotifyUrl("dsakljfdasjjdflsa");
		appInfoModify.doSignInfo(privateKeyStr);
		System.out.println(JSON.toJSONString(appInfoModify));
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/appInfo/doModifyAppInfo").setRequestBody(JSON.toJSONString(appInfoModify))
				.doHttp(String.class);
		System.out.println(data.toString());
	}
}
