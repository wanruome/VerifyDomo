/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年5月25日 上午11:10:24
 */
package com.webauth;

import java.util.Map;

import com.ruomm.base.http.ResponseData;
import com.ruomm.base.http.okhttp.DataOKHttp;
import com.webauth.tools.WebAuthBaseTest;

public class TestRepatmentPayInfo extends WebAuthBaseTest {
	public static String oldPwdEncrypt = "RSAMD5";
	public static String newPwdEncrypt = "RSA";

	public static void main(String[] args) {
		// testBindCard();
		// testUnBindCard();
		// testCallBindCardResult();
		// testUnBindCard();
		// testQueryProvinceCity();
		// testCallQrCode();
		// testQueryOrders();
		// doGetPayInfo();
		// doSetPayPwd();
		// doModifyPayPwd();
		// doModifyNoPwdFlag();
		doFindPayPwd();
		// testQueryAllCitys();
	}

	public static void doGetPayInfo() {
		Map<String, String> map = createRequestMap(true);

		// map.put("accountNo", "6212261202037568699");
		// map.put("accountNo", "62260900000000482");
		// map.put("mobileNo", "181000000002");
		// map.put("idcardNo", "5102657901283032");
		// map.put("name", "张三2");
		// map.put("area", "河南省-洛阳市2");
		signRequest(map);

		// new DataOKHttp().setPost(true).setDebug(true)
		// .setUrl("http://127.0.0.1:8090/mobile/repayment/bindCard/15166669999")
		// .setRequestBody(OkHttpConfig.attachFormRequestForamtBody(map)).doHttp(String.class);

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/repaymentPayInfo/doGetPayInfo").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doSetPayPwd() {
		Map<String, String> map = createRequestMap(true);
		map.put("uuid", BASE_UUID);
		map.put("payPwd", getPassWord("123456", newPwdEncrypt));
		map.put("payPwdEncrypt", newPwdEncrypt);
		// map.put("accountNo", "6212261202037568699");
		// map.put("accountNo", "62260900000000482");
		// map.put("mobileNo", "181000000002");
		// map.put("idcardNo", "5102657901283032");
		// map.put("name", "张三2");
		// map.put("area", "河南省-洛阳市2");
		signRequest(map);

		// new DataOKHttp().setPost(true).setDebug(true)
		// .setUrl("http://127.0.0.1:8090/mobile/repayment/bindCard/15166669999")
		// .setRequestBody(OkHttpConfig.attachFormRequestForamtBody(map)).doHttp(String.class);

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/repaymentPayInfo/doSetPayPwd").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doModifyPayPwd() {
		Map<String, String> map = createRequestMap(true);

		map.put("oldPayPwdEncrypt", oldPwdEncrypt);
		map.put("newPayPwdEncrypt", newPwdEncrypt);

		map.put("oldPayPwd", getPassWord("123456", oldPwdEncrypt));
		map.put("newPayPwd", getPassWord("123456", newPwdEncrypt));
		// map.put("msgVerifyCode", "54091940");
		signRequest(map);
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/repaymentPayInfo/doModifyPayPwd").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doFindPayPwd() {
		Map<String, String> map = createRequestMap(true);
		map.put("newPayPwdEncrypt", newPwdEncrypt);
		map.put("newPayPwd", getPassWord("111111", newPwdEncrypt));
		map.put("msgVerifyCode", "61798656");
		signRequest(map);
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/repaymentPayInfo/doFindPayPwd").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());
	}

	public static void doModifyNoPwdFlag() {
		Map<String, String> map = createRequestMap(true);
		map.put("payPwdEncrypt", newPwdEncrypt);
		map.put("payPwd", getPassWord("123456", newPwdEncrypt));
		map.put("noPwdFlag", "0");
		signRequest(map);
		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/repaymentPayInfo/doModifyNoPwdFlag").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());
	}

}
