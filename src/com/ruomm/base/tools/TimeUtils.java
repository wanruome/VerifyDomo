package com.ruomm.base.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 项目名称：工具类 类名称：TimeUtils 类描述： 时间常用操作类 创建人：王龙能 联系方式：563208883 http://www.wanglongneng.cn 创建时间：2014-3-11 下午3:43:58
 * 修改人：王龙能 修改时间：2014-3-11 下午3:43:58 修改备注：
 *
 * @version
 */
public class TimeUtils {
	public static final long VALUE_DAYTimeMillis = 1000l * 3600l * 24l;
	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat TIME_FORMAT_DATE_12 = new SimpleDateFormat("a hh:mm");
	public static final SimpleDateFormat TIME_FORMAT_DATE_24 = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat NO_SEC_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	/**
	 * 长的时间转换为字符串
	 *
	 * @param timeInMillis
	 * @param dateFormat
	 * @return
	 */
	public static String formatTime(long timeInMillis, SimpleDateFormat dateFormat) {
		return dateFormat.format(new Date(timeInMillis));
	}

	/**
	 * 时间长字符串，格式为DEFAULT_DATE_FORMAT
	 *
	 * @param timeInMillis
	 * @return
	 */
	public static String formatTime(long timeInMillis) {
		return formatTime(timeInMillis, DEFAULT_DATE_FORMAT);
	}

}
