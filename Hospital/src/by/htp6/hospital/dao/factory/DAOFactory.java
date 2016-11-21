package by.htp6.hospital.dao.factory;

import by.htp6.hospital.dao.UserLogInDAO;
import by.htp6.hospital.dao.UserLogUpDAO;
import by.htp6.hospital.dao.impl.SQLUserLoginationDAO;
import by.htp6.hospital.dao.impl.SQLUserRegistrationDAO;

public class DAOFactory {
	public static final DAOFactory instance = new DAOFactory();

	private UserLogInDAO userLoginationDAO = new SQLUserLoginationDAO();

	private UserLogUpDAO userRegistrationDAO = new SQLUserRegistrationDAO();

	public UserLogUpDAO getUserRegistrationDAO() {
		return userRegistrationDAO;
	}

	public UserLogInDAO getUserLoginationDao() {
		return userLoginationDAO;
	}

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
	}
}
