package by.htp6.hospital.service.impl;

import java.security.NoSuchAlgorithmException;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.dao.UserLogUpDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.LogUpService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.utility.MD5Encryptor;

public class LogUp implements LogUpService{

	@Override
	public User book(String username, String password) throws ServiceException {
		if (null == username || null == password){
			throw new ServiceException("Username or password cant be empty");
		}
		
		if ("".equals(username) || "".equals(password)){
			throw new ServiceException("Username or password cant be empty");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		
		UserLogUpDAO registrationDAO = daoFactory.getUserRegistrationDAO();
		
		User user;
		
		try {
			String encryptedPassword = MD5Encryptor.getHashCode(password);
			user = registrationDAO.registration(username, encryptedPassword);
			return user;
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException(e);
		}	
	}

	
}
