package wps.tools;

/*
 * this class contains tools methods that others depend to
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import wps.db.DBConnection;
import wps.students.Student;

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

			if (rs.next()) {
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

			if (rs.next()) {
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

			if (rs.next()) {
				presenceState = rs.getInt("presence_state");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return presenceState;
	}

	public static Student getStudentByMtr(int matricule) {
		Student student = new Student();

		try {
			sql = "SELECT * FROM student WHERE matricule = " + matricule;
			pst = connect.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {
				student.setName(rs.getString("name"));
				student.setMiddleName(rs.getString("middle_name"));
				student.setLastName(rs.getString("last_name"));
				student.setGender(rs.getString("gender"));
				student.setMatricule(rs.getInt("matricule"));
				student.setPromotion(rs.getString("promotion"));
				student.setDepartement(rs.getString("departement"));
				student.setFaculty(rs.getString("faculty"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	public static double getStPourcentBySemester(String rfID, int semester, String year) {

		if (Tools.studentExist(rfID)) {

			try {

				sql = "SELECT count(presences.presence_state) AS nb_presence FROM presences "
						+ "INNER JOIN sceances ON sceances.id = presences.id_sceance "
						+ "WHERE presences.presence_state = 1 AND " + "sceances.year = '" + year
						+ "' AND sceances.semester = " + semester + " AND presences.rf_id_student = '" + rfID + "'";

				pst = connect.prepareStatement(sql);
				rs = pst.executeQuery();

				if (rs.next()) {

					double nb_presence = rs.getDouble("nb_presence");

					sql = "SELECT count(id) AS nb_sceance FROM sceances WHERE semester = " + semester;
					pst = connect.prepareStatement(sql);
					rs = pst.executeQuery();

					if (rs.next()) {
						double nb_sceance = rs.getDouble("nb_sceance");

						return Tools.round(nb_presence * 100 / nb_sceance, 2);
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return 0.0;
	}

	public static double getStPourcentByCursus(String rfID) {

		if (Tools.studentExist(rfID)) {

			try {

				sql = "SELECT count(presences.presence_state) AS nb_presence FROM presences "
						+ "WHERE presences.presence_state = 1" + " AND presences.rf_id_student = '" + rfID + "'";

				pst = connect.prepareStatement(sql);
				rs = pst.executeQuery();

				if (rs.next()) {

					double nb_presence = rs.getDouble("nb_presence");

					sql = "SELECT count(id) AS nb_sceance FROM sceances";
					pst = connect.prepareStatement(sql);
					rs = pst.executeQuery();

					if (rs.next()) {
						double nb_sceance = rs.getDouble("nb_sceance");

						return Tools.round(nb_presence * 100 / nb_sceance, 2);
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return 0.0;
	}

	public double getStPourcentByYear(String rfID, String year) {

		if (Tools.studentExist(rfID)) {

			try {

				sql = "SELECT count(presences.presence_state) AS nb_presence FROM presences "
						+ "INNER JOIN sceances ON sceances.id = presences.id_sceance "
						+ "WHERE presences.presence_state = 1 AND sceances.year = '" + year + "'"
						+ "AND presences.rf_id_student = '" + rfID + "'";

				pst = connect.prepareStatement(sql);
				rs = pst.executeQuery();

				if (rs.next()) {

					double nb_presence = rs.getDouble("nb_presence");

					sql = "SELECT count(id) AS nb_sceance FROM sceances WHERE year = '" + year + "'";
					pst = connect.prepareStatement(sql);
					rs = pst.executeQuery();

					if (rs.next()) {
						double nb_sceance = rs.getDouble("nb_sceance");

						return Tools.round(nb_presence * 100 / nb_sceance, 2);
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return 0.0;
	}
	
	public static double round(double value, int precision) {
		
		return (double)((int) (value * Math.pow(10, precision) + .5)) / Math.pow(10, precision);
		
	}

}
