package by.htp6.hospital.service.impl;

import java.util.List;
import java.util.regex.Matcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.dao.FindPatientDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.FindPatientService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.tools.PatternContainer;

public class FindPatient implements FindPatientService{
	private static final Logger log = LogManager.getLogger(FindPatient.class);

	@Override
	public List<Patient> findPatientsByDoctorId(String searchData, int doctorId,
			int offset, int count, String orderBy) throws ServiceException {
		if (null == searchData || 0 == doctorId ||
				offset < 0 || count == 0){
			throw new ServiceException();
		}
		
		if ("".equals(searchData)){
			throw new ServiceException();
		}
		
		Matcher matcher = PatternContainer.diagnosisPattern.matcher(searchData);
		if (!matcher.find()){
			throw new ServiceException();
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		FindPatientDAO findPatientDAO = daoFactory.getFindPatientDAO();
		try {
			List<Patient> patients = findPatientDAO.findPatientsByDoctorId(searchData,
					doctorId, offset, count, orderBy);
			return patients;
		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Patient> findPatients(String searchData, int offset, int count,
			String orderBy) throws ServiceException {
		if (null == searchData || offset < 0 ||
				count == 0){
			throw new ServiceException();
		}
		
		if ("".equals(searchData)){
			throw new ServiceException();
		}
		
		Matcher matcher = PatternContainer.diagnosisPattern.matcher(searchData);
		if (!matcher.find()){
			throw new ServiceException();
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		FindPatientDAO findPatientDAO = daoFactory.getFindPatientDAO();
		try {
			List<Patient> patients = findPatientDAO.findPatients(searchData,
					offset, count, orderBy);
			return patients;
		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}
	
}
