package by.htp6.hospital.service.impl;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.dao.UserDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.LogInUserService;
import by.htp6.hospital.service.exception.ServiceException;

public class LogInUser implements LogInUserService{

	@Override
	public User logIn(String username, String password) throws ServiceException {
		if (null == username || null == password){
			throw new ServiceException("Username or password can`t be empty");
		}
		
		if ("".equals(username) || "".equals(password)){
			throw new ServiceException("Username or password can`t be empty");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		
		UserDAO userDao = daoFactory.getUserDao();
		
		User user;
		
		try {
			user = userDao.logination(username, password);
			return user;
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
