package by.htp6.hospital.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.AddAppointmentDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.AddAppointmentService;
import by.htp6.hospital.service.exception.ServiceException;

public class AddAppointment implements AddAppointmentService{
	private static final Logger log = LogManager.getLogger(AddAppointment.class);

	@Override
	public void addAppointment(int patientId, int doctorId, String type, String name)
			throws ServiceException {
		if (patientId == 0 || doctorId == 0 || null == type || null == name){
			throw new ServiceException("Illegal arguments");
		}
		
		if ("".equals(type) || "".equals(name)){
			throw new ServiceException("Illegal arguments");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		AddAppointmentDAO assignProcedureDAO = daoFactory.getAddAppointmentDAO();
		
		try {
			assignProcedureDAO.addAppointment(patientId, doctorId, type, name);
		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

}
