package wps.students;

import wps.operation.Operations;

public class App {
	
	public static void main(String[] args) {
		
				
//		Connection connect = DBConnection.getConnection();
		
//		Student st = new Student("Melanie", "Mumbere", "Matofe", "F", 4377, "rf-09qwef4940", "L3", "Electron-Mec Eng", "Applied Sciences");
//		st.setName("Kayis");
//		
		Operations op = new Operations();
		
//		op.recordPresence(new Presence(), new Sceance());
		
//		System.out.println(op.attendSceanceStart("rf-09qwef4940"));
		System.out.println(op.attendSceanceEnd("rf-09qwef4940"));
	
		
	}
}
