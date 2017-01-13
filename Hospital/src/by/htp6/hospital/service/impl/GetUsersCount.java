package by.htp6.hospital.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.htp6.hospital.dao.GetUsersCountDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.GetUsersCountService;
import by.htp6.hospital.service.exception.ServiceException;

public class GetUsersCount implements GetUsersCountService {
	private static final Logger log = LogManager.getLogger(GetUsersCount.class);

	@Override
	public int getUsersCount() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		GetUsersCountDAO getUsersCountDAO = daoFactory.getGetUsersCountDAO();
		
		try {
			int usersCount = getUsersCountDAO.getUsersCount();
			return usersCount;
			
		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

}
