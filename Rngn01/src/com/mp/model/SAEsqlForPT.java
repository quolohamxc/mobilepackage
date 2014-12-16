package com.mp.model;

import java.sql.*;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 
 * @author Administrator used in TController.fetchZol, QueryTask.run,
 *         TimerManager.contextInitialized, solveBean
 */
public class SAEsqlForPT{

	private String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/";
	private String dbname = "app_floion";
	private String drivername = "com.mysql.jdbc.Driver";
	private String username = "l5onwl2oj3";
	private String password = "kk35iyll4214j4w5ihy3x0mwy5yy02w4l25kkjij";

	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(drivername);
			con = DriverManager.getConnection(url + dbname, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public void closeConnection(Connection con) {
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public HashMap<String, PackageTariff> getPT() {
		HashMap<String, PackageTariff> hM = new HashMap<String, PackageTariff>();
		PackageTariff pT;
		String key = new String();
		try {
			Connection con = this.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from package;");
			while(rs.next()) {
				int i = 2;
				key = rs.getString(i++);
				pT = new PackageTariff(rs.getInt(i++),
						rs.getString(i++),
						rs.getDouble(i++),
						rs.getDouble(i++),
						rs.getInt(i++),
						rs.getDouble(i++),
						rs.getInt(i++),
						rs.getInt(i++),
						rs.getInt(i++),
						rs.getDouble(i++),
						rs.getDouble(i++),
						rs.getDouble(i++),
						rs.getDouble(i++),
						rs.getDouble(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getInt(i++),
						rs.getString(i++));
				hM.put(key, pT);
			}
			this.closeConnection(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hM;
	}
	
	public void insertPT(HashMap<String, PackageTariff> hM) {
		if (hM.size() == 0) {
			return;
		}
		PackageTariff pT = new PackageTariff();
		String key = new String();
		try {
			Connection con = this.getConnection();
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO package VALUES(1,?,?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?)");
			
			for (Entry<String, PackageTariff> entry : hM.entrySet()) {
				pT = entry.getValue();
				key = entry.getKey();
				if(null != pT) {
					int i = 1;
					ps.setString(i++, key);
					ps.setInt(i++, pT.getmType());
					ps.setString(i++, pT.getmTitle());
					ps.setDouble(i++, pT.getmMonthlyFee());
					ps.setDouble(i++, pT.getmMonthlyCons());
					ps.setInt(i++, pT.getmDomCallDur());
					
					ps.setDouble(i++, pT.getmDomInfoFlow());
					ps.setInt(i++, pT.getmDomMsg());
					ps.setInt(i++, pT.getmDomMmMsg());
					ps.setInt(i++, pT.getmWifi());
					
					ps.setDouble(i++, pT.getmDomCall());
					ps.setDouble(i++, pT.getmDomListen());
					ps.setDouble(i++, pT.getmDomCallBey());
					ps.setDouble(i++, pT.getmDomLisBey());
					ps.setDouble(i++, pT.getmDomInfoBey());
					
					ps.setString(i++, pT.getmAddition());
					ps.setString(i++, pT.getmDescription());
					
					ps.setInt(i++, pT.ismAutoUpdateIF() == true ? 1 : 0);
					ps.setString(i++, pT.getmLocation());
					ps.executeUpdate();
				}
			}
			this.closeConnection(con);
		} catch (SQLException e) {
			debug();
			e.printStackTrace();
		}
	}

	public void debug() {
		try {
			Connection con = this.getConnection();
			Statement st = con.createStatement();
			st.executeUpdate("use " + dbname);
			st.executeUpdate("drop table if exists package;");
			st.executeUpdate("create table if not exists debug("
					+ "id int(3),"
					+ "keyvalue char(25)," + "type int(6)," + "title char(25),"
					+ "fee double(6,2)," + "cons double(6,2),"
					+ "calldur int(6)," + "info double(8,2)," + "msg int(6),"
					+ "mmsg int(6)," + "wifi int(6)," + "callin double(6,2),"
					+ "lisin double(6,2)," + "callbey double(6,2),"
					+ "lisbey double(6,2)," + "infobey double(6,2),"
					+ "addition char(40)," + "description varchar(20),"
					+ "autoupdate int(1)," + "location char(25)" + ")");
			this.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initdb() {
		try {
			Connection con = this.getConnection();
			Statement st = con.createStatement();
			st.executeUpdate("use " + dbname);
			st.executeUpdate("create table if not exists package("
					+ "id int(3),"
					+ "keyvalue char(25)," + "type int(6)," + "title char(25),"
					+ "fee double(6,2)," + "cons double(6,2),"
					+ "calldur int(6)," + "info double(8,2)," + "msg int(6),"
					+ "mmsg int(6)," + "wifi int(6)," + "callin double(6,2),"
					+ "lisin double(6,2)," + "callbey double(6,2),"
					+ "lisbey double(6,2)," + "infobey double(6,2),"
					+ "addition char(40)," + "description varchar(20),"
					+ "autoupdate int(1)," + "location char(25)" + ")");
			this.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		SAEsqlForPT sFPT = new SAEsqlForPT();
		// HashMap<String, PackageTariff> hM = csab.getPT();
		// System.out.println(hM.size());
		sFPT.initdb();
		System.out.println("1");
	}
}