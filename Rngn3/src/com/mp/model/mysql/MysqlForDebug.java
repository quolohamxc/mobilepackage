package com.mp.model.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MysqlForDebug {

	protected String url = "jdbc:mysql://localhost:3306/";
	protected String dbname = "packagetariffdb";
	protected String drivername = "com.mysql.jdbc.Driver";
	protected String username = "root";
	protected String password = "0000";

	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(drivername);
			con = DriverManager.getConnection(url + dbname, username, password);
		} catch (Exception e) {
			// e.printStackTrace();
			return con;
		}
		return con;
	}

	public void closeConnection(Connection con) {
		if (con != null)
			try {
				con.close();
			} catch (SQLException | NullPointerException e) {
				// e.printStackTrace();
				return;
			}
	}

	public void debug(String bugInfo) throws NullPointerException, SQLException {
		debug(bugInfo,new Date().toString().substring(11,19) );
	}
	
	public void debug(String bugInfo, String bugTime) throws SQLException, NullPointerException {
		try {
			Connection con = this.getConnection();
			Statement st = con.createStatement();
			st.executeUpdate("create database if not exists " + dbname);
			st.executeUpdate("use " + dbname);
			st.executeUpdate("create table if not exists bugreport("
					+ "buginfo varchar(100)" + ", bugtime varchar(20)" + ")");
			st.executeUpdate("insert into bugreport values('" + bugInfo + "', '"
					+ bugTime + "');");
			this.closeConnection(con);
		} catch (SQLException | NullPointerException e) {
			// e.printStackTrace();
			throw(e);
		}
	}

	public static void main(String[] args) {
		try {
			new MysqlForDebug().debug("first test", new Date().toString().substring(11,19));
		} catch (NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println( new Date().toString().substring(11,19));
	}
}
