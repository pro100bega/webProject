package by.htp6.hospital.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import by.htp6.hospital.bean.Log;
import by.htp6.hospital.dao.FindLogDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.FindLogService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.tools.PatternContainer;

public class FindLog implements FindLogService{

	@Override
	public List<Log> findLog(String searchData, String userType) throws ServiceException {
		if (!"admin".equals(userType)){
			throw new ServiceException("Insufficient permission");
		}
		
		if (null == searchData){
			throw new ServiceException("Incorrect arguments");
		}
		
		if ("".equals(searchData)){
			throw new ServiceException("Incorrect arguments");
		}
		
		Matcher matcher = PatternContainer.logSearchPattern.matcher(searchData);
		if (!matcher.find()){
			throw new ServiceException();
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		FindLogDAO findPatientDAO = daoFactory.getFindLogDAO();
		try {
			List<Log> logList = findPatientDAO.findLog(searchData);
			return logList;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
