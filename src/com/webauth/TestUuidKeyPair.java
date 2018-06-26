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
	private static String key_pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCL4fdfChuLXHKFsTArBUh/33thO/eN2ArAE8Xu8E0pssFTHUNstjXZsKUVqIlDlyczFGbsOzpzYpWJvpkZVe5z8oyBoeSHYYR4K9hGgHI9zvX792ohhEPnaJhbW2qFdnZ0O4hEC5c+CdIDQuiP1lz4I1paTqXQrGCMhPhaefPITwIDAQAB";
	private static String key_pri = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIvh918KG4tccoWxMCsFSH/fe2E7943YCsATxe7wTSmywVMdQ2y2NdmwpRWoiUOXJzMUZuw7OnNilYm+mRlV7nPyjIGh5IdhhHgr2EaAcj3O9fv3aiGEQ+domFtbaoV2dnQ7iEQLlz4J0gNC6I/WXPgjWlpOpdCsYIyE+Fp588hPAgMBAAECgYAvxkJFTF9x7mYSsRyBZPGI8tvhrqhy4nlxdo9gduPzvOB4MYNoqqajrcgEKKaQ1hwGPw6T2fqxk9b8z4Lce1PP9pFniMykBy75aGaB19ewxiQqGerFkTmEx+abbUeof9K4AAQMPXX2P7T5Ui9y/nLF/yrva2/xy/xZFgRKUEKfiQJBAMMe2EtHKqodlBICLXBS75XvvjbKktdjEbuZ013Ho9THCyvkpnpry7ZLWvealOI3mVpgL8GWZT7VIy+J6wCc6gsCQQC3hwczl4IeRL56wa37wgQrIPqOwiBop3FGVyqJzH4cTS2Igegwdycv9B99ypBwkxI/Zjf5XekrezoX/HWe9QlNAkA95/ZmA+FUcef9wrUZ8yZSourtxV3LeoIwzEBUe7fOFYzE01nCc5sBbm0hK+la0JsWInhkakwbIFJ2jcKCwkY7AkBg7/llzsjzo+vYeySirb/1591wOilUHd0/Aht93X1fBYTfbX5u2wDf21om3y+bxpME2cEG/guC4/uzX63g4ByhAkAk8MZEas2mNSinjaO/PM9WjXgqQVxr88IeB3Ylt13TK8rY1HAYFVWrgjeP1G2Qa6sZPBk+YQLEkCrr+JDp46GP";

	public static void main(String[] args) {
		getPublicKeyByUuid();
		// byte[] data = Base64.decode(
		// "FZFQTb3MpKkyPH8EsejzPwN/oTSI36R+YF5gOcsJtn117wcZcn73TCbVfXx8iqeWY66tU6nz7ZP0sM81sR6ST77CeTd8/+7ZFCm2OY8ivoBPhelj8dq2WxDvpy516i1K1a1DmxlD/WlUQncKRzsYFPzGPcOxTeqSa7PXarhxPmFIN9z79UQ9aGJHBoqDX/Brh7XHLU92vFrnFlejG8mcyn7NH9/hmyI4RgqwLuJ/1jVCKHm0Nvt83Ohc/IDYPauyhzqntv+Lfj8dGn/4I12gXl+LnyQYP5Q48yhO2I3xcPtqR5JPs0DWNUX2KZAGT+DEJTRtSbOSmHjonCFkOAj/Wg==");
		// byte[] result = RSAUtils.decryptDataBig(data, RSAUtils.loadPrivateKey(key_pri));
		// System.out.println(new String(result));

	}

	public static void getPublicKeyByUuid() {
		HashMap<String, Object> map = new HashMap<>();
		// /13656655336
		map.put("uuid", BASE_UUID + 1);
		map.put("keyType", "RSA");
		map.put("rasPublicKey", key_pub);
		map.put("timeStamp", System.currentTimeMillis() + "");
		// map.put("accountNo", "6226090000000048");

		ResponseData data = new DataOKHttp().setPost(true).setDebug(true)
				.setUrl(BASE_URL + "app/keypair/getPublicKeyByUuid").setRequestBody(getRequestBody(map))
				.doHttp(String.class);
		System.out.println(data.toString());

	}
}
