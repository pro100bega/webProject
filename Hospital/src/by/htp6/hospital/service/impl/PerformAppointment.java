package by.htp6.hospital.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.PerformAppointmentDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.PerformAppointmentService;
import by.htp6.hospital.service.exception.ServiceException;

public class PerformAppointment implements PerformAppointmentService{
	private static final Logger log = LogManager.getLogger(PerformAppointment.class);
	
	@Override
	public void performAppointment(int appointmentId, String userType, String appointmentType)
			throws ServiceException {
		if (appointmentId == 0 || null == userType || null == appointmentType){
			throw new ServiceException("Illegal arguments");
		}
		
		if ("".equals(userType) || "".equals(appointmentType)){
			throw new ServiceException("Illegal arguments");
		}
		
		if (!"doctor".equals(userType) && !"nurse".equals(userType)){
			throw new ServiceException("Insufficient permission");
		}
		
		if ("nurse".equals(userType) && "операция".equals(appointmentType)){
			throw new ServiceException("Insufficient permission");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		PerformAppointmentDAO performAppointmentDAO = daoFactory.getPerformAppointmentDAO();
		
		try {
			performAppointmentDAO.performAppointment(appointmentId);
		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

}
