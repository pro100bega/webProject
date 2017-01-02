package by.htp6.hospital.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.dao.UserLogUpDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.LogUpService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.tools.MD5Encryptor;
import by.htp6.hospital.tools.PatternContainer;

public class LogUp implements LogUpService{
	private static final Logger log = LogManager.getLogger(LogUp.class);

	@Override
	public User logUp(String username, String password, String userType) throws ServiceException {
		if (null == username || null == password || null == userType){
			throw new ServiceException("Username or password cant be empty");
		}
		
		if ("".equals(username) || "".equals(password)){
			throw new ServiceException("Username or password cant be empty");
		}
		
		if (!"doctor".equals(userType) && !"guest".equals(userType)){
			throw new ServiceException("Invalid user type");
		}
		
		Matcher matcher = PatternContainer.usernamePattern.matcher(username);
		if (!matcher.find()){
			throw new ServiceException("Username must contain only [A-Z],[a-z],[0-9] symbols");
		}
		
		matcher = PatternContainer.passwordPattern.matcher(password);
		if (!matcher.find()){
			throw new ServiceException("Password must contain only [A-Z],[a-z],[0-9] symbols");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		
		UserLogUpDAO registrationDAO = daoFactory.getUserRegistrationDAO();
		
		User user;
		
		try {
			String encryptedPassword = MD5Encryptor.getHashCode(password);
			user = registrationDAO.registration(username, encryptedPassword, userType);
			return user;
		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}	
	}

	
}
