package by.htp6.hospital.service.impl;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.dao.GetPatientDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.GetPatientService;
import by.htp6.hospital.service.exception.ServiceException;

public class GetPatient implements GetPatientService{

	@Override
	public Patient getPatient(int patientId) throws ServiceException {
		if (patientId < 1){
			throw new ServiceException("Illegal arguments");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		GetPatientDAO getPatientDAO = daoFactory.getGetPatientDAO();
		
		try {
			Patient patient = getPatientDAO.getPatient(patientId);
			return patient;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
}
