package by.htp6.hospital.service.impl;

import by.htp6.hospital.dao.GetPatientsCountDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.GetPatientsCountService;
import by.htp6.hospital.service.exception.ServiceException;

public class GetPatientsCount implements GetPatientsCountService{

	@Override
	public int getPatientsCountByDoctorId(int doctorId) throws ServiceException {
		if (doctorId < 1){
			throw new ServiceException();
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		GetPatientsCountDAO getPatientsCountDAO = daoFactory.getGetPatientsCountDAO();
		
		try {
			int patientsCount = getPatientsCountDAO.getPatientsCountByDoctorId(doctorId);
			return patientsCount;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int getPatientsCount() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		GetPatientsCountDAO getPatientsCountDAO = daoFactory.getGetPatientsCountDAO();
		
		try {
			int patientsCount = getPatientsCountDAO.getPatientsCount();
			return patientsCount;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}		
	}

	@Override
	public int getPatientsCountByDoctorId(String searchData, int doctorId) throws ServiceException {
		if (null == searchData || doctorId < 1){
			throw new ServiceException("Illegal arguments");
		}
		
		if ("".equals(searchData)){
			throw new ServiceException("Illegal arguments");
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		GetPatientsCountDAO getPatientsCountDAO = daoFactory.getGetPatientsCountDAO();
		
		try {
			int patientsCount = getPatientsCountDAO.getPatientsCountByDoctorId(searchData, 
					doctorId);
			return patientsCount;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int getPatientsCount(String searchData) throws ServiceException {
		if (null == searchData || "".equals(searchData)){
			throw new ServiceException("Illegal arguments");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		GetPatientsCountDAO getPatientsCountDAO = daoFactory.getGetPatientsCountDAO();
		
		try {
			int patientsCount = getPatientsCountDAO.getPatientsCount(searchData);
			return patientsCount;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}
}
