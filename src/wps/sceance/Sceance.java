package wps.sceance;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


public class Sceance {
	private int idSceance;
	private String date;
	private int semester;
	private String  year;
	
	public Sceance(int semester, String year) {
		
		Date dateTime = new Date();
		DateFormat format = DateFormat.getDateInstance(DateFormat.LONG, new Locale("FR", "fr"));
		String date = format.format(dateTime);
		
		this.setDate(date);
		this.setSemester(semester);
		this.setYear(year);
	}
 
	public int getIdSceance() {
		return idSceance;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public int getSemester() {
		return semester;
	}
	
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
		
}
