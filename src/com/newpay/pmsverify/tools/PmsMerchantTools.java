/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年5月14日 下午2:01:20
 */
package com.newpay.pmsverify.tools;

import java.util.Map;

import com.ruomm.base.tools.StringUtils;

public class PmsMerchantTools {
	public static String convertToPmsMerchantId(String merchantId, String headerName) {
		int fillSize = 15 - StringUtils.getLength(merchantId) - StringUtils.getLength(headerName);
		StringBuilder sbBuilder = new StringBuilder();
		sbBuilder.append(headerName);
		for (int i = 0; i < fillSize; i++) {
			sbBuilder.append("0");
		}
		sbBuilder.append(merchantId);
		String strResult = sbBuilder.toString();
		if (strResult.length() > 15) {

			return strResult.substring(0, 15);
		}
		else {
			return strResult;
		}
	}

	public static String getPmsMerchantHeaderName(String pmsMerchantId) {
		if (StringUtils.isEmpty(pmsMerchantId)) {
			return null;
		}
		StringBuilder sb = null;
		for (int i = 0; i < 3; i++) {
			char a = pmsMerchantId.charAt(i);
			if (a >= 'a' && a <= 'z') {
				if (null == sb) {
					sb = new StringBuilder();
				}
				sb.append(a);
			}
			else if (a >= 'A' && a <= 'Z') {
				if (null == sb) {
					sb = new StringBuilder();
				}
				sb.append(a);
			}
			else {
				break;
			}

		}
		return null == sb ? null : sb.toString();
	}

	public static String getObjectSignStr(Object object) {
		Map<String, Object> mapObject = ObjectToMapTools.getFiedlsMap(object);
		String signStr = ObjectToMapTools.getFieldsSignStr(mapObject);
		return signStr;
	}
}
