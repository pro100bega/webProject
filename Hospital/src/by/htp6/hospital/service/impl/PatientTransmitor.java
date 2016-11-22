package by.htp6.hospital.service.impl;

import java.util.List;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.dao.PatientListDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.PatientTransmitorService;
import by.htp6.hospital.service.exception.ServiceException;

public class PatientTransmitor implements PatientTransmitorService{

	@Override
	public List<Patient> transmitPatient(int doctorId) throws ServiceException {
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			PatientListDAO patientListDAO = daoFactory.getPatientListDAO();
			List<Patient> patients = patientListDAO.getPatients(doctorId);
			if (!patients.isEmpty()){
				return patients;
			} else {
				throw new ServiceException("You have no patients");
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
