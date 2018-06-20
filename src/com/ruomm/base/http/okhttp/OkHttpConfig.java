/**
 *	@copyright wanruome-2017
 * 	@author wanruome
 * 	@create 2017年3月23日 下午4:54:13
 */
package com.ruomm.base.http.okhttp;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.newpay.bankcardverify.VerifyConfig;
import com.ruomm.base.tools.StringUtils;

import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class OkHttpConfig {
	private static OkHttpClient mOkHttpClient;

	public static OkHttpClient getOkHttpClient() {
		if (null == mOkHttpClient) {

			mOkHttpClient = new OkHttpClient();

			mOkHttpClient.newBuilder().connectTimeout(VerifyConfig.getOkhttp_connect_timeout(), TimeUnit.SECONDS)
					.writeTimeout(VerifyConfig.getOkhttp_write_timeout(), TimeUnit.SECONDS)
					.readTimeout(VerifyConfig.getOkhttp_read_timeout(), TimeUnit.SECONDS);
		}
		else {
			mOkHttpClient.newBuilder().connectTimeout(VerifyConfig.getOkhttp_connect_timeout(), TimeUnit.SECONDS)
					.writeTimeout(VerifyConfig.getOkhttp_write_timeout(), TimeUnit.SECONDS)
					.readTimeout(VerifyConfig.getOkhttp_read_timeout(), TimeUnit.SECONDS);
		}
		return mOkHttpClient;
	}

	public static RequestBody attachFormRequestForamtBody(Map<String, Object> hashMap) {

		Builder mBuilder = new FormBody.Builder();
		Set<String> sets = hashMap.keySet();
		for (String key : sets) {
			Object value = hashMap.get(key);
			String val = null == value ? null : String.valueOf(value);
			if (StringUtils.isEmpty(val)) {
				mBuilder.add(key, "");
			}
			else {
				mBuilder.add(key, val);
			}

		}
		return mBuilder.build();
	}

}
