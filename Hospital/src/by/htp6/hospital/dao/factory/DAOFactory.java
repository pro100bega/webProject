package by.htp6.hospital.dao.factory;

import by.htp6.hospital.dao.AddNewPatientDAO;
import by.htp6.hospital.dao.GetAppointmentListDAO;
import by.htp6.hospital.dao.AddAppointmentDAO;
import by.htp6.hospital.dao.DischargePatientDAO;
import by.htp6.hospital.dao.EditPatientDAO;
import by.htp6.hospital.dao.FindLogDAO;
import by.htp6.hospital.dao.FindPatientDAO;
import by.htp6.hospital.dao.GetLogDAO;
import by.htp6.hospital.dao.GetPatientDAO;
import by.htp6.hospital.dao.GetPatientListDAO;
import by.htp6.hospital.dao.GetPatientsCountDAO;
import by.htp6.hospital.dao.PerformAppointmentDAO;
import by.htp6.hospital.dao.UserLogInDAO;
import by.htp6.hospital.dao.UserLogUpDAO;
import by.htp6.hospital.dao.impl.SQLAddNewPatientDAO;
import by.htp6.hospital.dao.impl.SQLGetAppointmentListDAO;
import by.htp6.hospital.dao.impl.SQLAddAppointmentDAO;
import by.htp6.hospital.dao.impl.SQLDischargePatientDAO;
import by.htp6.hospital.dao.impl.SQLEditPatientDAO;
import by.htp6.hospital.dao.impl.SQLFindLogDAO;
import by.htp6.hospital.dao.impl.SQLFindPatientDAO;
import by.htp6.hospital.dao.impl.SQLGetLogDAO;
import by.htp6.hospital.dao.impl.SQLGetPatientDAO;
import by.htp6.hospital.dao.impl.SQLGetPatientListDAO;
import by.htp6.hospital.dao.impl.SQLGetPatientsCountDAO;
import by.htp6.hospital.dao.impl.SQLPerformAppointmentDAO;
import by.htp6.hospital.dao.impl.SQLUserLoginationDAO;
import by.htp6.hospital.dao.impl.SQLUserRegistrationDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private UserLogInDAO userLoginationDAO = new SQLUserLoginationDAO();

	private UserLogUpDAO userRegistrationDAO = new SQLUserRegistrationDAO();
	
	private GetPatientListDAO getPatientListDAO = new SQLGetPatientListDAO();
	
	private GetPatientsCountDAO getPatientsCountDAO = new SQLGetPatientsCountDAO();
	
	private DischargePatientDAO dischargePatientDAO = new SQLDischargePatientDAO();
	
	private FindPatientDAO findPatientDAO = new SQLFindPatientDAO();
	
	private AddNewPatientDAO addNewPatientDAO = new SQLAddNewPatientDAO();
	
	private AddAppointmentDAO addAppointmentDAO = new SQLAddAppointmentDAO();
	
	private GetLogDAO getLogDAO = new SQLGetLogDAO();
	
	private FindLogDAO findLogDAO = new SQLFindLogDAO();
	
	private GetAppointmentListDAO getAppointmentListDAO = new SQLGetAppointmentListDAO();
	
	private PerformAppointmentDAO performAppointmentDAO = new SQLPerformAppointmentDAO();
	
	private GetPatientDAO getPatientDAO = new SQLGetPatientDAO();
	
	private EditPatientDAO editPatientDAO = new SQLEditPatientDAO();

	public UserLogInDAO getUserLoginationDAO() {
		return this.userLoginationDAO;
	}
	
	public UserLogUpDAO getUserRegistrationDAO() {
		return this.userRegistrationDAO;
	}

	public GetPatientListDAO getGetPatientListDAO() {
		return this.getPatientListDAO;
	}
	
	public GetPatientsCountDAO getGetPatientsCountDAO() {
		return this.getPatientsCountDAO;
	}

	public DischargePatientDAO getDischargePatientDAO() {
		return this.dischargePatientDAO;
	}
	
	public FindPatientDAO getFindPatientDAO() {
		return this.findPatientDAO;
	}
	
	public AddNewPatientDAO getAddNewPatientDAO() {
		return this.addNewPatientDAO;
	}
	
	public AddAppointmentDAO getAddAppointmentDAO() {
		return this.addAppointmentDAO;
	}
	
	public GetLogDAO getGetLogDAO() {
		return this.getLogDAO;
	}
	
	public FindLogDAO getFindLogDAO() {
		return this.findLogDAO;
	}
	
	public GetAppointmentListDAO getGetAppointmentListDAO() {
		return this.getAppointmentListDAO;
	}
	
	public PerformAppointmentDAO getPerformAppointmentDAO() {
		return this.performAppointmentDAO;
	}
	
	public GetPatientDAO getGetPatientDAO() {
		return this.getPatientDAO;
	}
	
	public EditPatientDAO getEditPatientDAO() {
		return this.editPatientDAO;
	}
	
	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
	}
	
}
