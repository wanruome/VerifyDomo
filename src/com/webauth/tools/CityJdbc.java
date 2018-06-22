/**
 *	@copyright wanruome-2018
 * 	@author wanruome
 * 	@create 2018年6月22日 下午9:51:05
 */
package com.webauth.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import com.ruomm.base.tools.FileUtils;

public class CityJdbc {
	public static void main(String[] args) {
		List<String> listStr = FileUtils.readFileToList("D:\\temp\\RepaymentCitys.txt");
		// int i = 0;
		// for (String str : listStr) {
		// String[] data = listStr.get(i).split("\\|");
		// if (null == data || data.length != 2) {
		// continue;
		// }
		// i++;
		// String province = data[0];
		// String city = data[1];
		// System.out.println(i + ":" + province + ":" + city);
		// }
		String URL = "jdbc:mysql://127.0.0.1:3306/webauth?useUnicode=true&amp;characterEncoding=utf-8";
		String USER = "root";
		String PASSWORD = "xiaoniu";

		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			int i = 0;
			for (String str : listStr) {
				String[] data = str.split("\\|");
				if (null == data || data.length != 2) {
					continue;
				}
				i++;
				String province = data[0];
				String city = data[1];
				System.out.println(i + ":" + province + ":" + city);
				PreparedStatement preparedStatement = conn
						.prepareStatement("INSERT INTO tbl_repayment_citys(ID,PROVINCE,CITY) VALUES (?,?,?)");
				preparedStatement.setString(1, String.format("%03d", i));
				preparedStatement.setString(2, province);
				preparedStatement.setString(3, city);
				preparedStatement.execute();
				preparedStatement.close();
			}

		}
		catch (Exception e) {
			// TODO: handle exception
		}

	}
}
