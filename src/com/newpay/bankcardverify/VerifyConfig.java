/**
 *	@copyright wanruome-2017
 * 	@author wanruome
 * 	@create 2017年9月19日 下午4:37:18
 */
package com.newpay.bankcardverify;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class VerifyConfig {
	private static String verify_url = null;
	private static String verify_merchantId = null;
	private static String verify_privateKey = null;
	private static String verify_publicKey = null;
	private static int okhttp_connect_timeout = 0;
	private static int okhttp_read_timeout = 0;
	private static int okhttp_write_timeout = 0;
	private static String properties_Path = "class:sjzfconfig/verify.properties";
	private static Boolean isDebug = null;

	public static void setConfigPath(String propertiesPath) {
		properties_Path = propertiesPath;
	}

	public static void loadConfig() {
		verify_url = null;
		verify_merchantId = null;
		verify_privateKey = null;
		verify_publicKey = null;
		okhttp_connect_timeout = 0;
		okhttp_read_timeout = 0;
		okhttp_write_timeout = 0;
		isDebug = null;
		Properties property = null;
		InputStream fis = null;
		try {
			if (null == properties_Path || properties_Path.length() == 0) {
				fis = VerifyConfig.class.getClassLoader().getResourceAsStream("sjzfconfig/verify.properties");
			}
			else if (properties_Path.toLowerCase().startsWith("class://")) {
				fis = VerifyConfig.class.getClassLoader().getResourceAsStream(properties_Path.substring(8));
			}
			else if (properties_Path.toLowerCase().startsWith("class:")) {
				fis = VerifyConfig.class.getClassLoader().getResourceAsStream(properties_Path.substring(6));
			}
			else {
				fis = new FileInputStream(properties_Path);
			}
			property = new Properties();
			property.load(fis);
		}
		catch (Exception e) {
			e.printStackTrace();
			property = null;
		}
		finally {
			try {
				if (null != fis) {
					fis.close();
				}
			}
			catch (Exception fex) {
				fex.printStackTrace();
			}
		}

		try {
			verify_url = property.getProperty("verify_url");
			verify_merchantId = property.getProperty("verify_merchantId");
			verify_privateKey = property.getProperty("verify_privateKey");
			verify_publicKey = property.getProperty("verify_publicKey");
		}
		catch (Exception e) {
			e.printStackTrace();
			verify_url = null;
			verify_merchantId = null;
			verify_privateKey = null;
			verify_publicKey = null;
		}
		if (null != property) {
			try {
				okhttp_connect_timeout = Integer.valueOf(property.getProperty("okhttp_connect_timeout"));
				okhttp_read_timeout = Integer.valueOf(property.getProperty("okhttp_read_timeout"));
				okhttp_write_timeout = Integer.valueOf(property.getProperty("okhttp_write_timeout"));
				isDebug = Boolean.valueOf(property.getProperty("isDebug"));
			}
			catch (Exception e) {
				e.printStackTrace();
				okhttp_connect_timeout = 60;
				okhttp_read_timeout = 60;
				okhttp_write_timeout = 60;
			}
		}
		if (null == verify_url) {
			verify_url = "http://192.168.100.196:8083/bdss/bankcardVerify/";
		}
		if (okhttp_connect_timeout < 5 || okhttp_connect_timeout > 600) {
			okhttp_connect_timeout = 60;
		}
		if (okhttp_read_timeout < 5 || okhttp_read_timeout > 600) {
			okhttp_read_timeout = 60;
		}
		if (okhttp_write_timeout < 5 || okhttp_write_timeout > 600) {
			okhttp_write_timeout = 60;
		}
		if (null == isDebug) {
			isDebug = true;
		}

	}

	public static String getVerify_url() {
		if (null == verify_url) {
			loadConfig();
		}
		return verify_url;
	}

	public static String getVerify_merchantId() {
		if (null == verify_merchantId) {
			loadConfig();
		}
		return verify_merchantId;
	}

	public static String getVerify_privateKey() {
		if (null == verify_privateKey) {
			loadConfig();
		}
		return verify_privateKey;
	}

	public static String getVerify_publicKey() {
		if (null == verify_publicKey) {
			loadConfig();
		}
		return verify_publicKey;
	}

	public static int getOkhttp_connect_timeout() {
		if (okhttp_connect_timeout <= 0) {
			loadConfig();
		}
		return okhttp_connect_timeout;
	}

	public static int getOkhttp_read_timeout() {
		if (okhttp_read_timeout <= 0) {
			loadConfig();
		}
		return okhttp_read_timeout;
	}

	public static int getOkhttp_write_timeout() {
		if (okhttp_write_timeout <= 0) {
			loadConfig();
		}
		return okhttp_write_timeout;
	}

	public static boolean isDebug() {
		if (null == isDebug) {
			loadConfig();
		}
		return isDebug;
	}

}
