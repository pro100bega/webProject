package by.htp6.hospital.dao.factory;

import by.htp6.hospital.dao.PatientListDAO;
import by.htp6.hospital.dao.UserLogInDAO;
import by.htp6.hospital.dao.UserLogUpDAO;
import by.htp6.hospital.dao.impl.SQLPatientListDAO;
import by.htp6.hospital.dao.impl.SQLUserLoginationDAO;
import by.htp6.hospital.dao.impl.SQLUserRegistrationDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private UserLogInDAO userLoginationDAO = new SQLUserLoginationDAO();

	private UserLogUpDAO userRegistrationDAO = new SQLUserRegistrationDAO();
	
	private PatientListDAO patientListDAO = new SQLPatientListDAO();

	public UserLogUpDAO getUserRegistrationDAO() {
		return userRegistrationDAO;
	}

	public UserLogInDAO getUserLoginationDAO() {
		return userLoginationDAO;
	}
	
	public PatientListDAO getPatientListDAO() {
		return patientListDAO;
	}

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
	}
}
