package by.htp6.hospital.bean;

import java.io.Serializable;

public class Patient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4117081979441761136L;
	
	private int id;
	
	private String name;
	
	private String surname;
	
	private String diagnosis;
	
	private int doctorId;

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Patient(){
	}

	/**
	 * @param id:
	 * user identification
	 * @param name:
	 * user first name
	 * @param surname
	 * user last name 
	 */
	public Patient(String name, String surname, String diagnosis, int doctorId){
		this.name = name;
		this.surname = surname;
		this.diagnosis = diagnosis;
		this.doctorId = doctorId;
	}
	
	public Patient(int id, String name,
			String surname, String diagnosis, int doctorId) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.diagnosis = diagnosis;
		this.doctorId = doctorId;
	}
}
