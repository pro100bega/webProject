package by.htp6.hospital.service.impl;

import java.security.NoSuchAlgorithmException;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.dao.UserLogInDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.LogInService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.utility.MD5Encryptor;

public class LogIn implements LogInService{

	@Override
	public User logIn(String username, String password) throws ServiceException {
		if (null == username || null == password){
			throw new ServiceException("Username or password can`t be empty");
		}
		
		if ("".equals(username) || "".equals(password)){
			throw new ServiceException("Username or password can`t be empty");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		
		UserLogInDAO userLoginationDao = daoFactory.getUserLoginationDAO();
		
		User user;
		
		try {
			String encryptedPassword = MD5Encryptor.getHashCode(password);
			user = userLoginationDao.logination(username, encryptedPassword);
			return user;
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException(e);
		}
	}
}
