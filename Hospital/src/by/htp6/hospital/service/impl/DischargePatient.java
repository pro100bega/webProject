package by.htp6.hospital.service.impl;

import java.util.regex.Matcher;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.htp6.hospital.dao.DischargePatientDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.DischargePatientService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.tools.PatternContainer;

public class DischargePatient implements DischargePatientService{
	private static final Logger log = LogManager.getLogger(DischargePatient.class);

	@Override
	public boolean dischargePatient(int patientId, String finalDiagnosis) throws ServiceException {
		if (patientId == 0 || finalDiagnosis == null){
			throw new ServiceException("Illegal arguments");
		}
		
		if ("".equals(finalDiagnosis)){
			throw new ServiceException("Illegal arguments");
		}
		
		Matcher matcher = PatternContainer.diagnosisPattern.matcher(finalDiagnosis);
		if (!matcher.find()){
			throw new ServiceException("Diagnosis must contain only"
					+ " russian or english symbols, \"-\" and spaces");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		DischargePatientDAO dischargePatientDAO = daoFactory.getDischargePatientDAO();
		try {
			return dischargePatientDAO.dishcargePatient(patientId, finalDiagnosis);
		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}
	
}
