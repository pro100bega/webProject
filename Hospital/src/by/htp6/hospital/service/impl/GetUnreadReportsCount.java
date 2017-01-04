package by.htp6.hospital.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.GetUnreadReportsCountDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.GetUnreadReportsCountService;
import by.htp6.hospital.service.exception.ServiceException;

public class GetUnreadReportsCount implements GetUnreadReportsCountService{
	private static final Logger log = LogManager.getLogger(GetUnreadReportsCount.class);
	
	@Override
	public int getUnreadReportsCountService() throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		GetUnreadReportsCountDAO getUnreadReportsCountDAO = 
				daoFactory.getGetUnreadReportsCountDAO();
		
		try {
			int unreadReportsCount = getUnreadReportsCountDAO.getUnreadReportsCount();
			
			return unreadReportsCount;
			
		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

}
