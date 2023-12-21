package com.PatientDetails.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalreport", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
