package by.htp6.hospital.service;

import java.util.List;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.service.exception.ServiceException;

public interface PatientTransmitorService {
	List<Patient> transmitPatient(int doctorId) throws ServiceException;
}
