package by.htp6.hospital.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.EditPatientDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.EditPatientService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.tools.PatternContainer;

public class EditPatient implements EditPatientService{
	private static final Logger log = LogManager.getLogger(EditPatient.class);

	@Override
	public void editPatitnt(int patientId, String name, String surname,
			String sex, String birthDate, String diagnosis,
			String note) throws ServiceException {
		if (patientId == 0 || null == name || null == surname || null == sex ||
				null == birthDate || null == diagnosis){
			throw new ServiceException("Illegal arguments");
		}
		
		if ("".equals(name) || "".equals(surname) || "".equals(sex) ||
				"".equals(birthDate) || "".equals(diagnosis)){
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
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
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
		EditPatientDAO editPatientDAO = daoFactory.getEditPatientDAO();
		
		try {
			editPatientDAO.editPatient(patientId, name, surname, sex, date,
					diagnosis, note);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
