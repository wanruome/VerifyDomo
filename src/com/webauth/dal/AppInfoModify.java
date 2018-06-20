/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年6月15日 下午9:25:32
 */
package com.webauth.dal;

import java.security.PrivateKey;
import java.security.PublicKey;

import com.ruomm.base.tools.Base64;
import com.ruomm.base.tools.EncryptUtils;
import com.ruomm.base.tools.RSAUtils;
import com.ruomm.base.tools.StringUtils;

public class AppInfoModify {

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppPwd() {
		return appPwd;
	}

	public void setAppPwd(String appPwd) {
		this.appPwd = appPwd;
	}

	public String getTermLimit() {
		return termLimit;
	}

	public void setTermLimit(String termLimit) {
		this.termLimit = termLimit;
	}

	public String getTermAndroidLimit() {
		return termAndroidLimit;
	}

	public void setTermAndroidLimit(String termAndroidLimit) {
		this.termAndroidLimit = termAndroidLimit;
	}

	public String getTermIphoneLimit() {
		return termIphoneLimit;
	}

	public void setTermIphoneLimit(String termIphoneLimit) {
		this.termIphoneLimit = termIphoneLimit;
	}

	public String getTermWebLimit() {
		return termWebLimit;
	}

	public void setTermWebLimit(String termWebLimit) {
		this.termWebLimit = termWebLimit;
	}

	public String getNewKillOut() {
		return newKillOut;
	}

	public void setNewKillOut(String newKillOut) {
		this.newKillOut = newKillOut;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getRsaSignInfo() {
		return rsaSignInfo;
	}

	public void setRsaSignInfo(String rsaSignInfo) {
		this.rsaSignInfo = rsaSignInfo;
	}

	public String getAppNewPwd() {
		return appNewPwd;
	}

	public void setAppNewPwd(String appNewPwd) {
		this.appNewPwd = appNewPwd;
	}

	private String appId;
	private String appPwd;
	private String appName;
	private String appNewPwd;
	private String termLimit;
	private String termAndroidLimit;
	private String termIphoneLimit;
	private String termWebLimit;
	private String newKillOut;
	private String publicKey;
	private String notifyUrl;
	private String rsaSignInfo;

	public boolean doVerifySignInfo(String appPublicKey) {
		try {
			PublicKey appKey = RSAUtils.getPublicKey(Base64.decode(appPublicKey));
			String signInfoMd5 = new String(RSAUtils.decryptData(Base64.decode(rsaSignInfo), appKey));
			if (doGetSignInfo().equals(signInfoMd5)) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public String doGetSignInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append(appId);
		sb.append(appPwd);
		if (!StringUtils.isEmpty(appNewPwd)) {
			sb.append(appNewPwd);
		}
		if (!StringUtils.isEmpty(appName)) {
			sb.append(appName);
		}
		if (!StringUtils.isEmpty(termLimit)) {
			sb.append(termLimit);
		}
		if (!StringUtils.isEmpty(termAndroidLimit)) {
			sb.append(termAndroidLimit);
		}
		if (!StringUtils.isEmpty(termIphoneLimit)) {
			sb.append(termIphoneLimit);
		}
		if (!StringUtils.isEmpty(termWebLimit)) {
			sb.append(termWebLimit);
		}
		if (!StringUtils.isEmpty(newKillOut)) {
			sb.append(newKillOut);
		}
		if (!StringUtils.isEmpty(publicKey)) {
			sb.append(publicKey);
		}
		if (!StringUtils.isEmpty(notifyUrl)) {
			sb.append(notifyUrl);
		}

		return EncryptUtils.EncodingMD5(sb.toString());
	}

	public String doSignInfo(String appPrivateKey) {
		try {
			PrivateKey appKey = RSAUtils.getPrivateKey(Base64.decode(appPrivateKey));
			String sign = Base64.encode(RSAUtils.encryptData(doGetSignInfo().getBytes(), appKey));
			this.rsaSignInfo = sign;
			return sign;

		}
		catch (Exception e) {
			e.printStackTrace();
			this.rsaSignInfo = null;
			return null;
		}

	}
}
