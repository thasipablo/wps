package wps.statistic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import wps.db.DBConnection;
import wps.tools.Tools;

/*
 * This class contains statistic methods as :
 * - get students by names
 * - get students by faculty
 * - return the student participation rate
 * - etc.
 */

public class Stat implements IStat {

	private static String sql = "";
	private static Connection connect = DBConnection.getConnection();
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;

	@Override
	public ArrayList<HashMap<String, String>> getResultByPromotion(String promotion, String faculty, int semester,
			String year) {

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		try {

			sql = "SElECT * FROM students WHERE promotion = '" + promotion + "' " + "AND faculty = '" + faculty + "'";
			pst = connect.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {

				HashMap<String, String> data = new HashMap<String, String>();

				data.put("matricule", String.valueOf(rs.getInt("matricule")));
				data.put("promotion", rs.getString("promotion"));
				data.put("departement", rs.getString("departement"));
				data.put("pourcentage", String.valueOf(Tools.getStPourcentBySemester(rs.getString("rf_id"), semester, year)));

				list.add(data);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public ArrayList<HashMap<String, String>> getGeneralResult(int semester, String year) {
		
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		try {

			sql = "SElECT * FROM students";
			pst = connect.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {

				HashMap<String, String> data = new HashMap<String, String>();

				data.put("matricule", String.valueOf(rs.getInt("matricule")));
				data.put("promotion", rs.getString("promotion"));
				data.put("departement", rs.getString("departement"));
				data.put("faculty", rs.getString("faculty"));
				data.put("name", rs.getString(Tools.getStudentFullName(rs.getString("rf_id"))));
				data.put("gender", rs.getString("gender"));
				data.put("pourcentage", String.valueOf(Tools.getStPourcentBySemester(rs.getString("rf_id"), semester, year)));

				list.add(data);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
