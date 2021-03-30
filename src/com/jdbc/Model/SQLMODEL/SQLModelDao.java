package com.jdbc.Model.SQLMODEL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.jdbc.jdbc.Connection.DBConnectionProvider;

public class SQLModelDao {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;
		String INSERT_SQL_QUERY = "insert into demo" + "(id,username,address)" + "values(?,?,?)";
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		try {
			con = DBConnectionProvider.getConnection();
			ps = con.prepareStatement(INSERT_SQL_QUERY);

			System.out.println("Enter the id:-");
			int id = sc.nextInt();
			System.out.println("Enter the name:-");
			String name = sc.next();
			System.out.println("Enter the address:-");
			String address = sc.next();

			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, address);

			int insertedRecord = ps.executeUpdate();
			if (insertedRecord == 1 || insertedRecord > 0) {
				System.out.println("Records are inserted!!");
			} else {
				System.out.println("Something went wrong!!");
			}
			DBConnectionProvider.closeResources(ps, con);
		} catch (SQLException e) {
			printSQLException(e);
		}

	}

	// Handling SQL Exceptions
	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQL State: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + ((SQLException) e).getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}

	}

}
