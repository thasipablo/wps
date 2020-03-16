package wps.operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import wps.db.DBConnection;
import wps.sceance.Presence;
import wps.sceance.Sceance;
import wps.students.Student;
import wps.tools.Tools;

public class Operations implements IOperations{
	
	Connection connect = DBConnection.getConnection();
	PreparedStatement pst = null;
	ResultSet rs = null;
	String sql = "";
	boolean flag = false;
	static int idSceance = 0;
 
	@Override
	public boolean recordStudent(Student student) {
		
		String name = student.getName();
		String middleName = student.getMiddleName();
		String lastName = student.getLastName();
		String gender = student.getGender();
		int matricule = student.getMatricule();
		String rfId = student.getRfId();
		String promotion = student.getPromotion();
		String departement = student.getDepartement();
		String faculty = student.getFaculty();
		
		sql = "INSERT INTO students("
				+ "name, middle_name, last_name, gender, matricule, rf_id, promotion, departement, faculty) "
				+ "VALUES("
					+ "'" + name + "',"
					+ "'" + middleName + "',"
					+ "'" + lastName + "',"
					+ "'" + gender + "',"
					+ "'" + matricule + "',"
					+ "'" + rfId + "',"
					+ "'" + promotion + "',"
					+ "'" + departement + "',"
					+ "'" + faculty + "'"
				+ ");";
		
		try {
			pst = connect.prepareStatement(sql);
			
			if (pst.execute()) {
				return true;	
			}
			
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateStudentInfos(Student student) {
		
		// TODO Auto-generated method stub
		return false;		
		
	}

	private boolean recordSceance(Sceance sceance) {
		sql = "INSERT INTO sceances (date) VALUES('"+ sceance.getDate() +"')";
		try {
			pst = connect.prepareStatement(sql);
			if (pst.execute()) {
				System.out.println("Sceance reccorded succefuly");
				return true;
			}
			
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean recordPresence(Presence presence, Sceance sceance) {
		// first step: record a new Sceance
		this.recordSceance(sceance);
		
		// second step: fetch sceance_id from Sceance table and insert it into Presence table
		int idSceance = 0;
		try {
			sql = "SELECT id FROM sceances ORDER BY id DESC";
			pst = connect.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				idSceance = rs.getInt("id");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
		// third step: copy rfids from Students table to Presences table
		sql = "SELECT rf_id FROM students";
		try {
			pst = connect.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				String rfId = rs.getString("rf_id");
				
				sql = "INSERT INTO presences (rf_id_student, id_sceance) VALUES('" + rfId + "'," + idSceance + ")";
				pst = connect.prepareStatement(sql);
				pst.execute();	
			}
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	@Override
	public String attendSceanceStart(String rfID) {
		// first step: fetch id sceance
		idSceance = Tools.getSceanceId();
		final int startState = 0;
		
		if (Tools.studentExist(rfID)) {
			// second step: if student exist
			try {
				sql = "UPDATE presences SET presence_state = " + startState + " WHERE rf_id_student = '" + rfID
						+ "' AND id_sceance = " + idSceance + "";
				pst = connect.prepareStatement(sql);
				pst.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			
			return Tools.getStudentFullName(rfID);
			
		}else {
			return "Vous n'êtes pas enregistré(e)...!";
		}
	}
	
	@Override
	public String attendSceanceEnd(String rfID) {
		final int endState = 1;
		idSceance = Tools.getSceanceId();
		
		// step two: if student attended the start service 
		if(Tools.studentExist(rfID)) {
			if (Tools.getPresenceState(rfID) == 0) {
				try {
					sql = "UPDATE presences SET presence_state = " + endState + " WHERE rf_id_student = '" + rfID
							+ "' AND id_sceance = " + idSceance + "";
					pst = connect.prepareStatement(sql);
					pst.execute();
					
				} catch (SQLException e) {
					e.printStackTrace();
				} 
				
				return Tools.getStudentFullName(rfID);
				
			}else {
				return "Vous avez pas signer l'entrée...!";
			}
		}else {
			return "Vous n'êtes pas enregistré(e)...!";
		} 
	}

}
