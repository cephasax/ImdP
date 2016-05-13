package br.ufrn.imd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	
	private static String dbURL = "jdbc:postgresql://localhost/IMDBio";
	private static String dbUser = "postgres";
	private static String dbPwd = "postgres";
	private static Connection connect;
	
	public static Connection getConnection() throws SQLException{
		if(connect == null){
			connect = connect();
		}
		return connect;
	}
	
	private static Connection connect() throws SQLException{
		try{
			connect = DriverManager.getConnection(dbURL, dbUser, dbPwd);
			System.out.println("Database connected.");
		}
		catch(SQLException e){
			System.out.println("Database not connected.");
		}
		return connect;
	}
	
	public static void closeConnection() throws SQLException{
		connect.close();
		System.out.println("Database closed.");
	}
}
