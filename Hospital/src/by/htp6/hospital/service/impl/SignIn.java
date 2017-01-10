package by.htp6.hospital.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.dao.SignInDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.SignInService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.tools.MD5Encryptor;
import by.htp6.hospital.tools.PatternContainer;

public class SignIn implements SignInService{
	private static final Logger log = LogManager.getLogger(SignIn.class);

	@Override
	public User signIn(String username, String password) throws ServiceException {
		if (null == username || null == password){
			throw new ServiceException("Username or password can`t be empty");
		}
		
		if ("".equals(username) || "".equals(password)){
			throw new ServiceException("Username or password can`t be empty");
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
		
		SignInDAO logInDao = daoFactory.getSignInDAO();
		
		User user;
		
		try {
			String encryptedPassword = MD5Encryptor.getHashCode(password);
			user = logInDao.signIn(username, encryptedPassword);
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
