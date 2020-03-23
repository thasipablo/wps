package wps.students;

import wps.statistic.Stat;

public class App {
	
	public static void main(String[] args) {
		
				
//		Connection connect = DBConnection.getConnection();
		
//		Student st = new Student("Melanie", "Mumbere", "Matofe", "F", 4377, "rf-09qwef4940", "L3", "Electron-Mec Eng", "Applied Sciences");
//		st.setName("Kayis");
//		
//		Operations op = new Operations();
		
//		op.recordPresence(new Presence(), new Sceance(1,"2020"));
		
//		System.out.println(op.attendSceanceStart("rf-09qwef4940"));
//		System.out.println(op.attendSceanceEnd("rf-09qwef4940"));
		
//		System.out.println(Stat.getStPourcentByCursus("rf-09qwef4940"));
		
		Stat stat = new Stat();
		
		System.out.println(stat.getResultByPromotion("L3", "Applied Sciences", 1, "2019-2020"));
	
		
	}
}
