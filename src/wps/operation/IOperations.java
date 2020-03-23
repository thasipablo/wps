package wps.operation;

import wps.sceance.Presence;
import wps.sceance.Sceance;
import wps.students.Student;

public interface IOperations {
	
	/*
	 * Student
	 */
	abstract boolean recordStudent(Student student);
	abstract boolean updateStudentInfos(Student student);
		
	/*
	 * Presences
	 */
	abstract boolean recordPresence(Presence presence, Sceance sceance);
	abstract String attendSceanceStart(String rfID);
	abstract String attendSceanceEnd(String rfID);
}
