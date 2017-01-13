package by.htp6.hospital.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.dao.GetUserListDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.GetUserListService;
import by.htp6.hospital.service.exception.ServiceException;

public class GetUserList implements GetUserListService{
	private static final Logger log = LogManager.getLogger(GetUserList.class);
	
	@Override
	public List<User> getUserList(String userType, int offset, int count) throws ServiceException {
		if (!"admin".equals(userType)) {
			throw new ServiceException("Insufficient permission");
		}
		
		if (offset < 0 || count <= 0) {
			throw new ServiceException("Illegal arguments");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		GetUserListDAO getUserListDAO = daoFactory.getGetUserListDAO();
		
		try {
			List<User> users = getUserListDAO.getUserList(offset, count);
			return users;
			
		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

}
