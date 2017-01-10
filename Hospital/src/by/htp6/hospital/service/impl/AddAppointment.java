package by.htp6.hospital.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.AddAppointmentDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.AddAppointmentService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.tools.PatternContainer;

public class AddAppointment implements AddAppointmentService {
	private static final Logger log = LogManager.getLogger(AddAppointment.class);

	@Override
	public void addAppointment(int patientId, int doctorId, String type,
			String name, String termDate, String termTime) throws ServiceException {
		if (patientId == 0 || doctorId == 0 || null == type || null == name
				|| null == termDate || null == termTime) {
			throw new ServiceException("Illegal arguments");
		}

		if ("".equals(type) || "".equals(name) || "".equals(termDate)
				|| "".equals(termTime)) {
			throw new ServiceException("Illegal arguments");
		}

		Matcher matcher = PatternContainer.appointmentNamePattern.matcher(name);
		
		if (!matcher.find()) {
			throw new ServiceException("Invalid appointment name format");
		}
		
		SimpleDateFormat termDateFormat;
		matcher = PatternContainer.enDateFormatPattern.matcher(termDate);
		if (!matcher.find()){
			matcher = PatternContainer.ruDateFormatPattern.matcher(termDate);
			if (!matcher.find()){
				throw new ServiceException("Invalid term date format");
			}
			
			termDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			
		} else {
			termDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		}
		
		String fullDate = termDate + " " + termTime;

		DAOFactory daoFactory = DAOFactory.getInstance();
		AddAppointmentDAO assignProcedureDAO = daoFactory.getAddAppointmentDAO();

		try {
			
			Timestamp termDateTimestamp = new Timestamp(termDateFormat.parse(fullDate).getTime());
			
			assignProcedureDAO.addAppointment(patientId, doctorId, type, name,
					termDateTimestamp);
			
		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		} catch (ParseException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}

	}

}
