package by.htp6.hospital.dao;

import by.htp6.hospital.dao.exception.DAOException;

public interface AddNewPatientDAO {
	void addNewPatient(String name, String surname, String diagnosis, int doctorId)
		throws DAOException;
}
