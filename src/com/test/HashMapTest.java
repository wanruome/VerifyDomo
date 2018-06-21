/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年5月18日 下午12:55:39
 */
package com.test;

import java.util.HashMap;
import java.util.Map;

import com.newpay.pmsverify.tools.ObjectToMapTools;
import com.ruomm.base.tools.EncryptUtils;

public class HashMapTest {
	public static void main(String[] args) {
		System.out.println(
				"6IcaSytZ+crCNWLMj8a9H9gMqdkSD79U5QNEWOj8uL3rs1NxJ8uSzwbGOFpPBK6Yhd+p6nQay7IlwbaGvoCGVNcMuDyjUne28d6qvRzDXrLwh0uOBikMVQOrxKHmWVlUqIhdh4Nd14kndYR8jP7JjWrv80NijkVs+MrSpIJobNs="
						.length());
		System.out.println(EncryptUtils.encodingMD5("crttime=20180506091133,mchtno=ZY555666,outBacth=ZY66669999"));
		Map<String, Object> map = new HashMap<>();
		map.put("Hidfa", "1");
		map.put("hidasf", "2");
		map.put("gf", "2");
		map.put("Gg", "2");
		map.put("Li", "2");
		map.put("li", "2");
		String str = ObjectToMapTools.getFieldsSignStr(map);
		System.out.println(str);
	}
}
