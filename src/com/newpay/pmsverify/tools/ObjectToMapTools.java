/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年5月16日 下午4:31:41
 */
package com.newpay.pmsverify.tools;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class ObjectToMapTools {

	public static Map<String, Object> getFiedlsMap(Object object, String... noCollectKeys) {
		if (null == object) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();

		Field fields[] = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				System.out.println(field.getName());
				map.put(field.getName(), field.get(object));
				System.out.println(map.size());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (null != noCollectKeys && noCollectKeys.length > 0) {
			for (String key : noCollectKeys) {
				if (null != key && key.length() > 0) {
					map.remove(key);
				}
			}
		}
		return map;
	}

	public static String getFieldsSignStr(Map<String, Object> mapObject) {
		return getFieldsSignStr(mapObject, false);
	}

	public static String getFieldsSignStr(Map<String, Object> mapObject, boolean isAppendEmpty) {
		if (null == mapObject || mapObject.size() <= 0) {
			return "";
		}
		// 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
		StringBuilder basestring = new StringBuilder();
		boolean isAppendDouhao = false;

		if (null != mapObject && mapObject.size() > 0) {
			// 先将参数以其参数名的字典序升序进行排序
			TreeMap<String, Object> sortedParams = new TreeMap<String, Object>(mapObject);
			Set<Entry<String, Object>> entrys = sortedParams.entrySet();
			for (Entry<String, Object> entryItem : entrys) {
				String key = entryItem.getKey();
				String valStr = null;
				try {
					if (null != entryItem.getValue()) {
						if (entryItem.getValue() instanceof String) {
							valStr = (String) entryItem.getValue();
						}
						else {
							valStr = String.valueOf(entryItem.getValue());
						}
					}
				}
				catch (Exception e) {
					e.printStackTrace();
					valStr = null;
				}
				if (isAppendEmpty) {
					if (isAppendDouhao) {
						basestring.append(",");
					}
					else {
						isAppendDouhao = true;
					}
					if (null != valStr && valStr.length() > 0) {
						basestring.append(valStr);
					}
				}
				else {
					if (null != valStr && valStr.length() > 0) {
						if (isAppendDouhao) {
							basestring.append(",");
						}
						else {
							isAppendDouhao = true;
						}
						basestring.append(key).append("=").append(valStr);
					}
				}
			}
		}
		return basestring.length() > 0 ? basestring.toString() : "";
	}
}
