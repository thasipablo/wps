package wps.sceance;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Sceance {
	private int idSceance;
	private String date;
	
	public Sceance() {
		
		Date dateTime = new Date();
		DateFormat format = DateFormat.getDateInstance(DateFormat.LONG, new Locale("FR", "fr"));
		String date = format.format(dateTime);
		
		this.setDate(date);
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
		
}
