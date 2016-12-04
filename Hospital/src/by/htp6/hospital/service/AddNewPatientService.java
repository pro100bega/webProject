package by.htp6.hospital.service;

import by.htp6.hospital.service.exception.ServiceException;

public interface AddNewPatientService {
	void addNewPatient(String name, String surname, String diagnosis, int doctorId)
		throws ServiceException;
}
