package by.htp6.hospital.service.impl;

import java.util.regex.Matcher;

import by.htp6.hospital.dao.AddNewPatientDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.AddNewPatientService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.tools.PatternContainer;

public class AddNewPatient implements AddNewPatientService{

	@Override
	public void addNewPatient(String name, String surname, String diagnosis, int doctorId) throws ServiceException {
		if (null == name || null == surname || null == diagnosis || 0 == doctorId){
			throw new ServiceException("Illegal arguments");
		}
		
		if ("".equals(name) || "".equals(surname) || "".equals(diagnosis)) {
			throw new ServiceException("Illegal arguments");
		}
		
		Matcher matcher = PatternContainer.patientNamePattern.matcher(name);
		if (!matcher.find()){
			throw new ServiceException("Name must contain only russian or only english symbols");
		}
		
		matcher = PatternContainer.patientNamePattern.matcher(surname);
		if (!matcher.find()){
			throw new ServiceException("Surname must contain only russian or only english symbols");
		}
		
		matcher = PatternContainer.diagnosisPattern.matcher(diagnosis);
		if (!matcher.find()){
			throw new ServiceException("Diagnosis must contain only"
					+ " russian or english symbols, \"-\" and spaces");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		AddNewPatientDAO addNewPatientDAO = daoFactory.getAddNewPatientDAO();
		
		try {
			addNewPatientDAO.addNewPatient(name, surname, diagnosis, doctorId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
