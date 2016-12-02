package by.htp6.hospital.service.impl;

import java.util.List;
import java.util.regex.Matcher;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.dao.FindPatientDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.FindPatientService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.tools.PatternContainer;

public class FindPatient implements FindPatientService{

	@Override
	public List<Patient> findPatients(String searchData, int doctorId) throws ServiceException {
		if (null == searchData || 0 == doctorId){
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
			List<Patient> patients = findPatientDAO.findPatients(searchData, doctorId);
			return patients;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
}
