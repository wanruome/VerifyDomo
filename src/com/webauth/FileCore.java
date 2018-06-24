/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年6月24日 下午2:48:12
 */
package com.webauth;

import java.io.File;
import java.util.Map;

import com.ruomm.base.http.ResponseData;
import com.ruomm.base.http.okhttp.DataOKHttp;
import com.webauth.tools.WebAuthBaseTest;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FileCore extends WebAuthBaseTest {
	public static void main(String[] args) {
		uploadFile();
		// doAddAppInfo();
	}

	public static void uploadFile() {
		Map<String, String> map = createRequestMap(true);
		signRequest(map);
		MultipartBody.Builder builder = new MultipartBody.Builder();
		// 设置类型
		builder.setType(MultipartBody.FORM);
		File file = new File("D:/temp/temp.pdf");
		System.out.println(file.getName());
		builder.addFormDataPart("file", file.getName(), RequestBody.create(null, file));
		RequestBody body = builder.build();
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL + "app/fileCore/uploadFile")
				.setHeadersMap(map).setRequestBody(body).doHttp(String.class);
		System.out.println(data.toString());

		// 创建RequestBody
		// RequestBody body = builder.build();
		// File file = new File("D:/temp/temp.pdf");
		// RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"),
		// file);
		// RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
		// .addFormDataPart("application/octet-stream", "file", fileBody).build();
		// ResponseData data = new DataOKHttp().setPost(true).setDebug(true).setUrl(BASE_URL +
		// "file/fileCore/uploadFile")
		// .setRequestBody(requestBody).doHttp(String.class);
		// System.out.println(data.toString());
	}
}
