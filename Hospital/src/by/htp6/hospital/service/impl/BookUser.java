package by.htp6.hospital.service.impl;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.dao.UserRegistrationDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.BookUserService;
import by.htp6.hospital.service.exception.ServiceException;

public class BookUser implements BookUserService{

	@Override
	public User book(String username, String password) throws ServiceException {
		if (null == username || null == password){
			throw new ServiceException("Username or password cant be empty");
		}
		
		if ("".equals(username) || "".equals(password)){
			throw new ServiceException("Username or password cant be empty");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserRegistrationDAO registrationDAO = daoFactory.getUserRegistrationDAO();
		User user;
		try {
			user = registrationDAO.registration(username, password);
			return user;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
	}

	
}
