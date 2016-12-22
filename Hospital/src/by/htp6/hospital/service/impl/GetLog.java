package by.htp6.hospital.service.impl;

import java.util.List;

import by.htp6.hospital.bean.Log;
import by.htp6.hospital.dao.GetLogDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.GetLogService;
import by.htp6.hospital.service.exception.ServiceException;

public class GetLog implements GetLogService{

	@Override
	public List<Log> getLog(String userType) throws ServiceException {
		if (null == userType){
			throw new ServiceException("Insufficient permission");
		}
		
		if (!"admin".equals(userType)){
			throw new ServiceException("Insufficient permission");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		GetLogDAO getLogDAO = daoFactory.getGetLogDAO();
		List<Log> logs;
		try {
			logs = getLogDAO.getLog();
			return logs;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
