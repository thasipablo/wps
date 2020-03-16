package wps.tools;

/*
 * this class contains tools methods that others depend to
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import wps.db.DBConnection;

public class Tools {
	private static String sql = "";
	private static Connection connect = DBConnection.getConnection();
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;
	
	public static String getStudentFullName(String rfID) {
		String msg = "";
		try {
			sql = "SELECT name, middle_name, last_name FROM students WHERE rf_id = '" + rfID + "'";
			pst = connect.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString("name");
				String middleName = rs.getString("middle_name");
				String lastName = rs.getString("last_name");
				
				msg = name + " " + middleName + " " + lastName;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return msg;
	}
	
	public static boolean studentExist(String rfId) {
		try {
			sql = "SELECT rf_id FROM students WHERE rf_id = '" + rfId + "'";
			pst = connect.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static int getSceanceId() {
		int idSceance = 0;
		try {
			sql = "SELECT id FROM sceances ORDER BY id DESC";
			pst = connect.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				idSceance = rs.getInt("id");
			}
			
			return idSceance;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static int getPresenceState(String rfID) {
		int presenceState = 0;
		
		try {
			sql = "SELECT presence_state FROM presences WHERE rf_id_student = '" + rfID + "' ORDER BY id DESC";
			pst = connect.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				presenceState = rs.getInt("presence_state");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return presenceState;
	}
	
}
