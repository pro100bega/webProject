package by.htp6.hospital.dao;

import java.util.List;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.dao.exception.DAOException;

public interface PatientListDAO {
	List<Patient> getPatients(int doctorId) throws DAOException;
}
