package by.htp6.hospital.service.impl;

import by.htp6.hospital.dao.PerformAppointmentDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.PerformAppointmentService;
import by.htp6.hospital.service.exception.ServiceException;

public class PerformAppointment implements PerformAppointmentService{

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
			throw new ServiceException(e);
		}
	}

}
