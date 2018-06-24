/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年6月24日 下午8:56:14
 */
package com.webauth;

import java.util.Map;

import com.ruomm.base.http.ResponseData;
import com.ruomm.base.http.okhttp.DataOKHttp;
import com.webauth.tools.WebAuthBaseTest;

public class FeedBackTest extends WebAuthBaseTest {
	public static void main(String[] args) {
		// doFeedBack();
		doGetAppVersion();
	}

	private static void doFeedBack() {
		Map<String, String> map = createRequestMap(true);

		// map.put("accountNo", "6212261202037568699");
		map.put("contact", "62260900000000482");
		map.put("feedBackTitle", "181000000002");
		map.put("feedBackContent", "5102657901283032");
		signRequest(map);

		// new DataOKHttp().setPost(true).setDebug(true)
		// .setUrl("http://127.0.0.1:8090/mobile/repayment/bindCard/15166669999")
		// .setRequestBody(OkHttpConfig.attachFormRequestForamtBody(map)).doHttp(String.class);

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "/app/feedBack/doFeedBack")
				.setRequestBody(getRequestBody(map)).doHttp(String.class);
		System.out.println(data.toString());
	}

	private static void doGetAppVersion() {
		Map<String, String> map = createRequestMap(true);

		// map.put("accountNo", "6212261202037568699");
		map.put("appName", "商户服务APP");
		map.put("appType", "1");
		map.put("appVersion", "bata 4 3.69.3");
		signRequest(map);

		// new DataOKHttp().setPost(true).setDebug(true)
		// .setUrl("http://127.0.0.1:8090/mobile/repayment/bindCard/15166669999")
		// .setRequestBody(OkHttpConfig.attachFormRequestForamtBody(map)).doHttp(String.class);

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "/app/appVersion/doGetAppVersion").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());
	}
}
