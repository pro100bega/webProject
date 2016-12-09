package by.htp6.hospital.service;

import java.util.List;

import by.htp6.hospital.bean.Patient;

public interface SortPatientsService {
	void sortPatientsService(List<Patient> patients, String sortBy);
}
