package by.htp6.hospital.dao.factory;

import by.htp6.hospital.dao.UserDAO;
import by.htp6.hospital.dao.UserRegistrationDAO;
import by.htp6.hospital.dao.impl.SQLUserDAO;
import by.htp6.hospital.dao.impl.SQLUserRegistrationDAO;

public class DAOFactory {
	public static final DAOFactory instance = new DAOFactory();

	private UserDAO userDAO = new SQLUserDAO();

	private UserRegistrationDAO userRegistrationDAO = new SQLUserRegistrationDAO();

	public UserRegistrationDAO getUserRegistrationDAO() {
		return userRegistrationDAO;
	}

	public UserDAO getUserDao() {
		return userDAO;
	}

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
	}
}
