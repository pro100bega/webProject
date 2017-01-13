package by.htp6.hospital.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.htp6.hospital.bean.Appointment;
import by.htp6.hospital.dao.GetAppointmentListDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.GetAppointmentListService;
import by.htp6.hospital.service.exception.ServiceException;

public class GetAppointmentList implements GetAppointmentListService{
	private static final Logger log = LogManager.getLogger(GetAppointmentList.class);

	@Override
	public List<Appointment> getAppointmentList(int patientId, String status) throws ServiceException {
		if (patientId == 0 || null == status || "".equals(status)){
			throw new ServiceException("Illegal arguments");
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		GetAppointmentListDAO getAppointmentLitDAO = daoFactory.getGetAppointmentListDAO();
		
		try {
			List<Appointment> appointments = getAppointmentLitDAO.
					getAppointmentList(patientId, status);
			return appointments;
		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}
}
