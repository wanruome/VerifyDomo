/**
 *	@copyright 盛炬支付-2017
 * 	@author wanruome
 * 	@create 2017年4月1日 下午6:36:43
 */
package com.newpay.bankcardverify;

import java.text.SimpleDateFormat;

import com.alibaba.fastjson.JSON;
import com.newpay.bankcardverify.entry.CardName;
import com.newpay.bankcardverify.entry.CardNameCid;
import com.newpay.bankcardverify.entry.CardPhone;
import com.newpay.bankcardverify.entry.FourFactors;
import com.newpay.bankcardverify.entry.MessageFormat;
import com.newpay.bankcardverify.entry.RequestMessageFormat;
import com.newpay.bankcardverify.entry.VerifyResponse;
import com.ruomm.base.http.ResponseData;
import com.ruomm.base.http.okhttp.DataOKHttp;
import com.ruomm.base.tools.Base64;
import com.ruomm.base.tools.DesUtil;
import com.ruomm.base.tools.EncryptUtils;
import com.ruomm.base.tools.RSAUtils;
import com.ruomm.base.tools.StringUtils;
import com.ruomm.base.tools.TimeUtils;

public class VerifyUtil {
	private static String encoding = "utf-8";
	private static int strLenth = 16;
	private static SimpleDateFormat debugTimeFormat = null;
	// API地址
	private static String requestUrl = null;
	// 分配的商户号
	private static String merchId = null;
	private static String privateKey = null;
	private static String publicKey = null;

	public static void loadConfig(String propertiesPath) {
		VerifyConfig.setConfigPath(propertiesPath);
		requestUrl = VerifyConfig.getVerify_url();
		merchId = VerifyConfig.getVerify_merchantId();
		privateKey = VerifyConfig.getVerify_privateKey();
		publicKey = VerifyConfig.getVerify_publicKey();
	}

	private static String getDataTkey(String sign, String uuid) {
		if (StringUtils.isEmpty(uuid) || StringUtils.isEmpty(sign)) {
			return null;
		}

		String dataTkey = StringUtils.substr(sign, strLenth) + StringUtils.substr(uuid, strLenth);

		return dataTkey;
	}

	private static byte[] getEncryptData(String data, String dataTkey) {
		byte[] enData = null;
		if (StringUtils.isEmpty(data) || StringUtils.isEmpty(dataTkey)) {
			return null;
		}
		try {
			enData = DesUtil.encrypt(data.getBytes(encoding), dataTkey.getBytes(encoding));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return enData;
	}

	private static byte[] getDecryptData(byte[] dataByte, String dataTkey) {
		byte[] enData = null;
		try {
			enData = DesUtil.decrypt(dataByte, dataTkey.getBytes(encoding));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return enData;
	}

	private static byte[] getEncryptDataTKey(String dataTkey, String pKey) {
		byte[] enData = null;
		if (StringUtils.isEmpty(dataTkey) || StringUtils.isEmpty(pKey)) {
			return null;
		}
		try {
			enData = RSAUtils.encryptDataByPrivateKey(dataTkey.getBytes(encoding), Base64.decode(pKey));

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return enData;
	}

	// 二要素验证：卡号 + 姓名
	public static MessageFormat verifyCardName(CardName cardName) {

		return verifyProgress(cardName, "cardName");
	}

	// 二要素验证2：卡号 + 手机号
	public static MessageFormat verifyCardPhone(CardPhone verifyValue) {

		return verifyProgress(verifyValue, "cardPhone");
	}

	public static MessageFormat verifyCardNameCid(CardNameCid verifyValue) {
		return verifyProgress(verifyValue, "cardNameCid");
	}

	public static MessageFormat verifyFourFators(FourFactors verifyValue) {
		return verifyProgress(verifyValue, "fourFators");
	}

	private static MessageFormat verifyProgress(Object verifyValue, String verifyApi) {

		if (StringUtils.isBlank(requestUrl) || StringUtils.isBlank(merchId) || StringUtils.isBlank(privateKey)) {
			logDebug("requestError 请求参数设置不正确，请正确配置properties参数");
			logDebug("responseBean === " + "MessageFormat IS NULL");
			return null;
		}
		logDebug("requestUrl === " + requestUrl + verifyApi);

		MessageFormat messageFormat = null;
		try {
			// 1.组装原始报文

			// 2.产生随机工作密钥
			String data = JSON.toJSONString(verifyValue);
			logDebug("data === " + data);
			logDebug("new String(data.getBytes(encoding)) === " + new String(data.getBytes(encoding)));
			String sign = EncryptUtils.EncodingMD5(data);
			String uuid = StringUtils.getUUID();
			String dataTkey = getDataTkey(sign, uuid);
			logDebug("dataTkey === " + dataTkey);
			// 3.对原报文进行对称加密
			byte[] enData = getEncryptData(data, dataTkey);

			logDebug("enData(Base64) === " + Base64.encode(enData));

			byte[] k = getDecryptData(enData, dataTkey);
			String tk = new String(k);

			logDebug("tk === " + tk);

			// 4.对工作密钥进行私钥加密
			byte[] enDataTKey = getEncryptDataTKey(dataTkey, privateKey);

			logDebug("enDataTKey(Base64) === " + Base64.encode(enDataTKey));

			// 如果公钥不为空则测试工作密钥公钥解密
			if (!StringUtils.isBlank(publicKey)) {
				byte[] kd = RSAUtils.decryptDataByPublicKey(enDataTKey, Base64.decode(publicKey));
				String tdk = new String(kd);
				logDebug("tdk === " + tdk);
			}

			// 5.组装最终报文，转成json字符串
			RequestMessageFormat format = new RequestMessageFormat();
			format.setM(Base64.encode(merchId.getBytes(encoding)));
			format.setK(Base64.encode(enDataTKey));
			format.setD(Base64.encode(enData));
			String reqData = JSON.toJSONString(format);

			logDebug("requestData === " + reqData);

			// 6.发送http请求

			boolean isOkHttpDebug = VerifyConfig.isDebug();
			ResponseData responseData = new DataOKHttp().setUrl(requestUrl + verifyApi).setDebug(isOkHttpDebug)
					.setRequestBody(reqData).setPost(true).doHttp(MessageFormat.class);
			messageFormat = (MessageFormat) responseData.getResultObject();
			if (null != messageFormat && "1".equals(messageFormat.getF())) {
				String verifyResponseString = new String(
						DesUtil.decrypt(Base64.decode(messageFormat.getR()), dataTkey.getBytes(encoding)), encoding);

				logDebug("responseData === " + verifyResponseString);

				try {
					VerifyResponse verifyResponse = JSON.parseObject(verifyResponseString, VerifyResponse.class);
					messageFormat.setVerifyResponse(verifyResponse);

				}
				catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		if (null == messageFormat) {
			logDebug("responseBean === " + "MessageFormat IS NULL");
		}
		else {
			logDebug("responseBean === " + messageFormat.toString());
		}

		return messageFormat;
	}

	private static void logDebug(String message) {
		if (VerifyConfig.isDebug()) {
			if (null == debugTimeFormat) {
				debugTimeFormat = new SimpleDateFormat("HH:mm:ss");
			}
			System.out.println(
					TimeUtils.formatTime(System.currentTimeMillis(), debugTimeFormat) + " VerifyUtil " + message);
		}
		else {
			if (null != debugTimeFormat) {
				debugTimeFormat = null;
			}
		}
	}
}
