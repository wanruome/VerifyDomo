/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年6月12日 下午7:55:26
 */
package com.webauth;

import java.util.HashMap;

import com.ruomm.base.http.ResponseData;
import com.ruomm.base.http.okhttp.DataOKHttp;
import com.webauth.tools.WebAuthBaseTest;

public class TestUuidKeyPair extends WebAuthBaseTest {
	public static void main(String[] args) {
		getPublicKeyByUuid();
	}

	public static void getPublicKeyByUuid() {
		HashMap<String, Object> map = new HashMap<>();
		// /13656655336
		map.put("uuid", BASE_UUID);
		map.put("keyType", "RSA");
		// map.put("accountNo", "6226090000000048");

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/keypair/getPublicKeyByUuid").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());

	}
}
