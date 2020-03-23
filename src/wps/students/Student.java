package wps.students;

public class Student {
	
	private int id;
	private String name;
	private String middleName;
	private String lastName;
	private String gender;
	private int matricule;
	private String rfId;
	private String promotion;
	private String departement;
	private String faculty;
	
	public Student() {
		super();
	}

	public Student(String name, String middleName, String lastName, String gender, int matricule, String rfId, String promotion,
			String departement, String faculty) {
		super();
		this.name = name;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.matricule = matricule;
		this.rfId = rfId;
		this.promotion = promotion;
		this.departement = departement;
		this.faculty = faculty;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
 
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getMatricule() {
		return matricule;
	}

	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}

	public String getRfId() {
		return rfId;
	}

	public void setRfId(String rfId) {
		this.rfId = rfId;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public int getId() {
		return id;
	}

	
}
