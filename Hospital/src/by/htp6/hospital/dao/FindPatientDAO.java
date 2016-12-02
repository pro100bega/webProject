package by.htp6.hospital.dao;

import java.util.List;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.dao.exception.DAOException;

public interface FindPatientDAO {
	List<Patient> findPatients(String searchData, int doctorId) throws DAOException;
}
