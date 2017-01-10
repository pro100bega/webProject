package by.htp6.hospital.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.AddNewPatientDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.AddNewPatientService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.tools.PatternContainer;

public class AddNewPatient implements AddNewPatientService{
	private static final Logger log = LogManager.getLogger(AddNewPatient.class);

	@Override
	public void addNewPatient(String name, String surname, char sex,
			String birthDate, String diagnosis, int doctorId, String note) 
					throws ServiceException {
		if (null == name || null == surname || null == diagnosis ||
				null == birthDate || 0 == sex || 0 == doctorId){
			throw new ServiceException("Illegal arguments");
		}
		
		if ("".equals(name) || "".equals(surname) || "".equals(diagnosis) || 
				"".equals(birthDate)) {
			throw new ServiceException("Illegal arguments");
		}
		
		Matcher matcher = PatternContainer.patientNamePattern.matcher(name);
		if (!matcher.find()){
			throw new ServiceException("Invalid name format");
		}
		
		matcher = PatternContainer.patientNamePattern.matcher(surname);
		if (!matcher.find()){
			throw new ServiceException("Invalid surname format");
		}
		
		matcher = PatternContainer.diagnosisPattern.matcher(diagnosis);
		if (!matcher.find()){
			throw new ServiceException("Invalid diagnosis format");
		}
		
		Date date = null;
		matcher = PatternContainer.enDateFormatPattern.matcher(birthDate);
		if (!matcher.find()){
			matcher = PatternContainer.ruDateFormatPattern.matcher(birthDate);
			if (!matcher.find()){
				throw new ServiceException("Invalid date format");
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
			try {
				date = dateFormat.parse(birthDate);
			} catch (ParseException e) {
				log.error(e.getMessage());
				throw new ServiceException(e);
			}
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = dateFormat.parse(birthDate);
			} catch (ParseException e) {
				log.error(e.getMessage());
				throw new ServiceException(e);
			}
		}
		
		if (note != null) {
			matcher = PatternContainer.notePatern.matcher(note);
			if (!matcher.find()){
				throw new ServiceException("Invalid note format");
			}
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		AddNewPatientDAO addNewPatientDAO = daoFactory.getAddNewPatientDAO();
		
		try {
			addNewPatientDAO.addNewPatient(name, surname, sex, date, diagnosis,
					doctorId, note);
		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

}
