package wps.sceance;

public class Presence {
	private int id;
	private String rfIdStudent;
	private int sceanceId;
	private int presenceState;
	
	public Presence() {
		super();
	}

	public Presence(String rfIdStudent, int sceanceId, int presenceState) {
		super();
		this.rfIdStudent = rfIdStudent;
		this.sceanceId = sceanceId;
		this.presenceState = presenceState;
	}

	public String getRfIdStudent() {
		return rfIdStudent;
	}

	public void setRfIdStudent(String rfIdStudent) {
		this.rfIdStudent = rfIdStudent;
	}

	public int getSceanceId() {
		return sceanceId;
	}

	public void setSceanceId(int sceanceId) {
		this.sceanceId = sceanceId;
	}

	public int getPresenceState() {
		return presenceState;
	}

	public void setPresenceState(int presenceState) {
		this.presenceState = presenceState;
	}

	public int getId() {
		return id;
	}
	
}
