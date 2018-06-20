package com.test;

import java.text.SimpleDateFormat;

import com.newpay.bankcardverify.VerifyConfig;
import com.newpay.bankcardverify.VerifyUtil;
import com.newpay.bankcardverify.entry.CardName;
import com.newpay.bankcardverify.entry.CardNameCid;
import com.newpay.bankcardverify.entry.CardPhone;
import com.newpay.bankcardverify.entry.FourFactors;
import com.newpay.bankcardverify.entry.MessageFormat;
import com.ruomm.base.tools.TimeUtils;

public class verifyDemo {
	/*
	 * codeArray = { 000, 100, 101, 102, 111, 112, 113, 120, 130, 400, 901, 910, 911, 920, 921, 922,
	 * 923, 930, 999, 119, 991 }; msgArray = { "验证成功", "库无", "身份证姓名不匹配", "实人认证失败", "卡号姓名不匹配",
	 * "银行卡号手机号不匹配", "卡异常", "要素验证不匹配", "卡状态未知", "系统不支持", "网络异常", "验签失败", "参数错误", "客户不可用", "预存款不足",
	 * "没有此接口访问权限", "交易限制", "产品已停用", "系统异常", "认证信息错误", "银联验证时候出错" };
	 * 依据测试银行卡去掉最后1位后的字符串的最后3为数字的值判定验证状态(如“629915600781”获取的值为“078”)，
	 * 该值最接近codeArray里面的那个code值则返回该Code值作为验证结果。
	 */
	private static String encoding = "utf-8";
	private static int strLenth = 16;
	// 成功的测试信息
	public static final String TEST_NAME = "测试";
	public static final String TEST_CARDNO = "6229110000080001";
	public static final String TEST_IDCARD = "310001190001050608";
	public static final String TEST_PHONE = "18990616000";

	// 商户自行产生公私钥对，使用私钥进行加密，公钥发送给我司
	private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDqOJ4L4ljh78ump4n0rk1uugqj"
			+ "ky1u3D+xmbJcsFKx8B5lMmk4zwGM7jDva1HxGvgHf2O9OH0JrVVMX7YkciacdMj2"
			+ "TSZY/i5EyUD1YA6WndjFhToEKq8FDHWFPsPkWFVctk56ue5HRC/FyEnhSjtvpJTE" + "fT5WTtWq2NArC8ttbQIDAQAB";
	private static String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOo4ngviWOHvy6an"
			+ "ifSuTW66CqOTLW7cP7GZslywUrHwHmUyaTjPAYzuMO9rUfEa+Ad/Y704fQmtVUxf"
			+ "tiRyJpx0yPZNJlj+LkTJQPVgDpad2MWFOgQqrwUMdYU+w+RYVVy2Tnq57kdEL8XI"
			+ "SeFKO2+klMR9PlZO1arY0CsLy21tAgMBAAECgYEA02JTqTAXvZlYd8je5dflhFgd"
			+ "P/GVYGcgiN3IroRnrbWjCPvNIPsaUCGsQnfeFTacwM+EqYJwvqoTwIDk9tGa64En"
			+ "3kNZHq/91CscD1Ix0coUu8IbaoFJRhfqgL91bVmtaj41BR2oeI25KZ6vozLKFO05"
			+ "mKiBSVUFXn2tTHfV/MECQQD36q3Yj0DWl7u2t6DeWAS2518sbnY8kCJntL8/kV/J"
			+ "RcGHUk+8KVgb2sNXY1dRRdxpFXXdUoW2W7au8lMr1oVxAkEA8dufqu705qZatGWp"
			+ "8/FAcQcPDVYoc9ZE/PNS7FavYJO9++BjXhLQne9O8DIzYXVGiiswZ57D0l8lonkG"
			+ "oKD5vQJAdfAdfb1AYhXSxex7Serja0OMYi22b789rsoshQgaYjyeAUsmBWRBtxaO"
			+ "AR+QJWivnwoHhR9B3uuob3d2G4+08QJBANoi0MaV0TJ6ldKg4wFO9WU8DZpkvAWG"
			+ "GyDdwUvB/1mmxzJ2toixhZD8twCy9l6PLLkPrQ+4RTvnbwU0DQMxTS0CQBdHX2zp"
			+ "O32gjU4Pt2nElSz5mobeIdgCYfiCMdsNK3XM/j+QS1RMCjFUQ/jq70r1AYw8TLTF" + "ZFDIZHvH11QfFxE=";

	// 测试验证地址内网
	// private static String requestUrl = "http://192.168.100.196:8083/bdss/bankcardVerify/";
	// 测试验证地址公网
	private static String requestUrl = "http://localhost/bdss/bankcardVerify/";
	// 正式验证地址公网(需要正式计费，请适当控制测试数据量)
	// private static String requestUrl = "http://www.esicash.com/bdss/bankcardVerify/";
	//
	// 分配的商户号
	private static String merchId = "10010089";

	public static void main(String[] args) throws Exception {
		String filePath = verifyDemo.class.getResource("/").getPath() + "sjzfconfig/verify.properties";
		System.out.println(verifyDemo.class.getResource("/").getPath() + "sjzfconfig/verify.properties");
		// VerifyUtil.loadConfig(String propertiesPath)使用properties文件配置,配置的参数及意义如下
		// 参数verify_url:银联验证服务地址
		// verify_merchantId:分配的商户号
		// verify_privateKey:RSA(1024)位PKCS8格式私钥的纯密钥部分
		// verify_publicKey:RSA(1024)位PKCS8格式公钥的纯密钥部分
		// okhttp_connect_timeout:网络请求连接超时时间，单位秒，有效范围5-600
		// okhttp_read_timeout:网络请求读取流超时时间，单位秒，有效范围5-600
		// okhttp_write_timeout:网络请求写入流超时时间，单位秒，有效范围5-600
		// isDebug:是否调试，若是调试则打印验证时候的信息
		// 以上参数为properties所需的参数，公钥参数不是必须参数。敬请知晓。
		// 加载properties使用VerifyUtil.loadConfig(String propertiesPath)来加载。
		// propertiesPath以class://或class:开头则是classLoader模式：使用getClassLoader().getResourceAsStream()方式来加载
		// 此方式填写配置文件在java class资源里面的路径，若本demo使用"class://sjzfconfig/verify.properties"
		// 其他情况，fileLoader模式。propertiesPath作为文件路径，调用new FileInputStream(propertiesPath)来加载，需要文件全路径。
		//
		// classLoader模式:
		VerifyConfig.setConfigPath("class://sjzfconfig/verify.properties");
		// fileLoader模式:
		String configFilePath = verifyDemo.class.getResource("/").getPath() + "sjzfconfig/verify.properties";
		VerifyConfig.setConfigPath(configFilePath);

		VerifyUtil.loadConfig(filePath);

		// VerifyConfig.setConfigPath(verifyDemo.class.getResource("/"));
		// 若是需要做Jar包，VerifyDemo不需要包含，注意修改VerifyUtil中的信息为你们商户的信息
		// 修改VerifyUtil中的信息为你们商户的信息后，以下设置则不需要加载了，可以直接验证。
		// fastjson,okhttp,okoi包为依赖包，需要包含，注意防止冲突

		// 二要素验证：卡号 + 姓名
		// verifyCardName();

		// 二要素验证2：卡号 + 手机号
		// verifyCardPhone();

		// 三要素验证：卡号 + 姓名 + 身份号
		// verifyCardNameCid();

		// 四要素验证：卡号 + 姓名 + 身份号 + 手机号
		verifyFourFators();

	}

	// 二要素验证：卡号 + 姓名
	public static void verifyCardName() {
		CardName verifyValue = new CardName();
		verifyValue.setOrderNo(
				TimeUtils.formatTime(System.currentTimeMillis(), new SimpleDateFormat("yyyyMMddHHmmssSSS")));
		verifyValue
				.setOrderTime(TimeUtils.formatTime(System.currentTimeMillis(), new SimpleDateFormat("yyyyMMddHHmmss")));
		verifyValue.setCardNo(TEST_CARDNO);
		verifyValue.setName(TEST_NAME);
		MessageFormat merFormat = VerifyUtil.verifyCardName(verifyValue);
		if (null != merFormat) {
			System.out.println(merFormat.toString());
		}
		else {
			System.out.println("NULL验证错误");
		}
	}

	// 二要素验证2：卡号 + 手机号
	public static void verifyCardPhone() {
		final CardPhone verifyValue = new CardPhone();
		verifyValue.setOrderNo(
				TimeUtils.formatTime(System.currentTimeMillis(), new SimpleDateFormat("yyyyMMddHHmmssSSS")));
		verifyValue
				.setOrderTime(TimeUtils.formatTime(System.currentTimeMillis(), new SimpleDateFormat("yyyyMMddHHmmss")));
		verifyValue.setCardNo(TEST_CARDNO);
		verifyValue.setPhone(TEST_PHONE);
		MessageFormat merFormat = VerifyUtil.verifyCardPhone(verifyValue);
		if (null != merFormat) {
			System.out.println(merFormat.toString());
		}
		else {
			System.out.println("NULL验证错误");
		}
	}

	public static void verifyCardNameCid() {
		final CardNameCid verifyValue = new CardNameCid();
		verifyValue.setOrderNo(
				TimeUtils.formatTime(System.currentTimeMillis(), new SimpleDateFormat("yyyyMMddHHmmssSSS")));
		verifyValue
				.setOrderTime(TimeUtils.formatTime(System.currentTimeMillis(), new SimpleDateFormat("yyyyMMddHHmmss")));
		verifyValue.setCardNo(TEST_CARDNO);
		verifyValue.setName(TEST_NAME);
		verifyValue.setCid(TEST_IDCARD);
		MessageFormat merFormat = VerifyUtil.verifyCardNameCid(verifyValue);
		if (null != merFormat) {
			System.out.println(merFormat.toString());
		}
		else {
			System.out.println("NULL验证错误");
		}
	}

	public static void verifyFourFators() {
		final FourFactors verifyValue = new FourFactors();
		verifyValue.setOrderNo(
				TimeUtils.formatTime(System.currentTimeMillis(), new SimpleDateFormat("yyyyMMddHHmmssSSS")));
		verifyValue
				.setOrderTime(TimeUtils.formatTime(System.currentTimeMillis(), new SimpleDateFormat("yyyyMMddHHmmss")));
		verifyValue.setCardNo(TEST_CARDNO);
		verifyValue.setName(TEST_NAME);
		verifyValue.setCid(TEST_IDCARD);
		verifyValue.setPhone(TEST_PHONE);
		MessageFormat merFormat = VerifyUtil.verifyFourFators(verifyValue);
		if (null != merFormat) {
			System.out.println(merFormat.toString());
		}
		else {
			System.out.println("NULL验证错误");
		}
	}

}
