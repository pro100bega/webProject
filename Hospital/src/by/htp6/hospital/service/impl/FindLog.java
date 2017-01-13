package by.htp6.hospital.service.impl;

import java.util.List;
import java.util.regex.Matcher;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.htp6.hospital.bean.Log;
import by.htp6.hospital.dao.FindLogDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.FindLogService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.tools.PatternContainer;

public class FindLog implements FindLogService{
	private static final Logger log = LogManager.getLogger(FindLog.class);

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
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

}
