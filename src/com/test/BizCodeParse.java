/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年5月17日 下午2:00:00
 */
package com.test;

public class BizCodeParse {
	public static final String BizeCode = "abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void main(String[] args) {
		// StringBuilder sb = new StringBuilder();
		// for (int i = 0; i < unitSize; i++) {
		// sb.append("'").append(BizeCode.charAt(i)).append("'").append(",");
		// }
		// System.out.println(sb.toString());
		System.out.println(parseIntToBusiCode(52 * 52 * 52 * 52 - 1));
		;
	}

	public static final char[] BUSICODE_BASE_CHARS = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k',
			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	public static String parseIntToBusiCode(int intVal) {
		int unitSize = BUSICODE_BASE_CHARS.length;
		int val = intVal % (unitSize * unitSize * unitSize * unitSize);
		val = val < 0 ? val + unitSize * unitSize * unitSize * unitSize : val;
		int val0 = val / (unitSize * unitSize * unitSize);
		int val1 = (val - val0 * unitSize * unitSize * unitSize) / (unitSize * unitSize);
		int val2 = (val - val0 * unitSize * unitSize * unitSize - val1 * unitSize * unitSize) / unitSize;
		int val3 = val - val0 * unitSize * unitSize * unitSize - val1 * unitSize * unitSize - val2 * unitSize;
		StringBuilder sb = new StringBuilder();
		if (val0 > 0) {
			sb.append(BUSICODE_BASE_CHARS[val0]).append(BUSICODE_BASE_CHARS[val1]).append(BUSICODE_BASE_CHARS[val2])
					.append(BUSICODE_BASE_CHARS[val3]);
		}
		else if (val1 > 0) {
			sb.append(BUSICODE_BASE_CHARS[val1]).append(BUSICODE_BASE_CHARS[val2]).append(BUSICODE_BASE_CHARS[val3]);
		}
		else {
			sb.append(BUSICODE_BASE_CHARS[val2]).append(BUSICODE_BASE_CHARS[val3]);
		}
		return sb.toString();
	}
}
