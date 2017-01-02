package by.htp6.hospital.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.dao.GetPatientListDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.GetPatientListService;
import by.htp6.hospital.service.exception.ServiceException;

public class GetPatientList implements GetPatientListService {
	private static final Logger log = LogManager.getLogger(GetPatientList.class);

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
			log.error(e.getMessage());
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
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

}
