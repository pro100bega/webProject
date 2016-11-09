package by.htp6.hospital.dao.factory;

import by.htp6.hospital.dao.UserDAO;
import by.htp6.hospital.dao.impl.SQLUserDAO;

public class DAOFactory {
	public static final DAOFactory instance = new DAOFactory();
	
	private UserDAO userDAO = new SQLUserDAO();
	
	private DAOFactory(){}
	
	public static DAOFactory getInstance(){
		return instance;
	}
	
	public UserDAO getUserDao(){
		return userDAO;
	}
}
