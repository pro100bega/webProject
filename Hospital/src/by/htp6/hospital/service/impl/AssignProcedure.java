package by.htp6.hospital.service.impl;

import by.htp6.hospital.dao.AssignProcedureDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.AssignProcedureService;
import by.htp6.hospital.service.exception.ServiceException;

public class AssignProcedure implements AssignProcedureService{

	@Override
	public void assignProcedure(int patientId, int doctorId, String type, String name)
			throws ServiceException {
		if (patientId == 0 || doctorId == 0 || null == type || null == name){
			throw new ServiceException("Illegal arguments");
		}
		
		if ("".equals(type) || "".equals(name)){
			throw new ServiceException("Illegal arguments");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		AssignProcedureDAO assignProcedureDAO = daoFactory.getAssignProcedureDAO();
		
		try {
			assignProcedureDAO.assignProcedure(patientId, doctorId, type, name);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
