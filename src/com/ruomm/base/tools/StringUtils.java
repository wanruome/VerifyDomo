package com.ruomm.base.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 项目名称：工具类 类名称：StringUtils 类描述： 可用于常见字符串操作 创建人：王龙能 创建时间：2014-3-11 下午2:07:11 修改人：王龙能 修改时间：2014-3-11
 * 下午2:07:11 修改备注：
 *
 * @version
 */
public class StringUtils {
	/**
	 * 公共静态布尔ISBLANK 为空或长度为0或它是由空间中进行
	 *
	 * <pre>
	 * isBlank(null) = true;
	 * isBlank(&quot;&quot;) = true;
	 * isBlank(&quot;  &quot;) = true;
	 * isBlank(&quot;a&quot;) = false;
	 * isBlank(&quot;a &quot;) = false;
	 * isBlank(&quot; a&quot;) = false;
	 * isBlank(&quot;a b&quot;) = false;
	 * </pre>
	 *
	 * @param str
	 * @return 如果字符串为null或它的大小为0，或它是由空间中进行的，返回true，否则返回false。
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 为空或长度为0
	 *
	 * <pre>
	 * isEmpty(null) = true;
	 * isEmpty(&quot;&quot;) = true;
	 * isEmpty(&quot;  &quot;) = false;
	 * </pre>
	 *
	 * @param str
	 * @return 如果字符串为null或它的大小为0，返回真，否则返回假。
	 */
	public static boolean isEmpty(CharSequence str) {
		return str == null || str.length() == 0;
	}

	public static boolean isNotEmpty(CharSequence str) {
		return !isEmpty(str);
	}

	public static boolean isZhCNString(String chs) {
		if (isEmpty(chs)) {
			return false;
		}
		else {
			boolean isChs = false;
			for (int i = 0; i < chs.length(); i++) {
				String key = chs.substring(i, i + 1);
				if (key.getBytes().length >= 2) {
					isChs = true;
					break;
				}
			}
			return isChs;
		}
	}

	public static String firstToUpperCase(String str) {
		if (isEmpty(str)) {
			return str;
		}
		else if (str.length() == 1) {
			return str.toUpperCase();
		}
		else {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
	}

	public static String firstToLowerCase(String str) {
		if (isEmpty(str)) {
			return str;
		}
		else if (str.length() == 1) {
			return str.toLowerCase();
		}
		else {
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		}
	}

	public static int getLength(String str) {
		return null == str ? 0 : str.length();
	}

	/**
	 * 随机获取一串UUID
	 *
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().trim().replace("-", "");
	}

	/**
	 * 取得从0开始长度为length的子字符串，如果str的长度小于length的长度则取得整个字符串 <功能详细描述>
	 *
	 * @param str
	 * @param length
	 * @return [参数说明]
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 * @author lKF13186 2009-7-23
	 */
	public static String substr(String str, int length) {
		if (null == str) {
			return "";
		}
		else {
			return (str.length() > length) ? str.substring(0, length) : str;
		}

	}

	public static List<String> getListString(String arg, String split) {
		List<String> list = new ArrayList<String>();
		if (isEmpty(arg)) {
			return list;
		}
		else {
			String[] strings = arg.split(split);
			for (String string : strings) {
				if (!isEmpty(string)) {
					list.add(string);
				}
			}
		}
		return list;
	}

}
