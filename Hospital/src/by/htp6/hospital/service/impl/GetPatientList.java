package by.htp6.hospital.service.impl;

import java.util.List;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.dao.GetPatientListDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.GetPatientListService;
import by.htp6.hospital.service.exception.ServiceException;

public class GetPatientList implements GetPatientListService {

	@Override
	public List<Patient> getPatientsListForDoctor(int doctorId, int offset,
			int patientsPerPage, String orderBy) throws ServiceException {
		if (offset < 0 || patientsPerPage < 0) {
			throw new ServiceException("Illegal arguments");
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		GetPatientListDAO getPatientListDAO = daoFactory.getGetPatientListDAO();
		try {
			List<Patient> patients = getPatientListDAO.getPatientListForDoctor(doctorId,
					offset, patientsPerPage, orderBy);
			return patients;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Patient> getAllPatientsList(int offset, int patientsPerPage,
			String orderBy) throws ServiceException {
		if (offset < 0 || patientsPerPage < 0) {
			throw new ServiceException("Illegal arguments");
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		GetPatientListDAO getPatientListDAO = daoFactory.getGetPatientListDAO();
		try {
			List<Patient> patients = getPatientListDAO.getAllPatientList(offset,
					patientsPerPage, orderBy);
			return patients;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
