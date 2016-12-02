package by.htp6.hospital.service;

import java.util.List;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.service.exception.ServiceException;

public interface FindPatientService {
	List<Patient> findPatients(String searchData, int doctorId) throws ServiceException;
}
