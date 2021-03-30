package com.jdbc.jdbc.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnectionProvider {

	private static String JDBC_DRIVER;
	static {
		try {
			JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
			Class.forName(JDBC_DRIVER);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public static Connection getConnection() {

		Connection con = null;
		String JDBC_URL = "jdbc:mysql://localhost:3306/SQLExceptionDemo";
		String USERNAME = "root";
		String PASSWORD = "root";

		// Validating Username and Password of MySQL DB

		if (USERNAME.equals("root") && PASSWORD.equals("root")) {
			System.out.println("Valid!!");
		} else {
			System.out.println("Invalid!!");
		}

		// Validating and Establishing Connection between JDBC and Database
		try {
			if (con == null || con != null) {
				con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				System.out.println("Connection Done!!");
			} else {
				System.out.println("Invalid Connection!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;

	}

	public static void closeResources(PreparedStatement ps, Connection con) {
		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (con != null && !con.isClosed()) {
				con.close();
			}
			System.out.println("Connection Closed!!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
