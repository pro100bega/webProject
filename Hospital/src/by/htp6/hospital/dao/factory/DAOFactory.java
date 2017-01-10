package by.htp6.hospital.dao.factory;

import by.htp6.hospital.dao.AddNewPatientDAO;
import by.htp6.hospital.dao.ChangeUserTypeDAO;
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
import by.htp6.hospital.dao.GetReportsCountDAO;
import by.htp6.hospital.dao.GetReportsDAO;
import by.htp6.hospital.dao.GetSingleReportDAO;
import by.htp6.hospital.dao.GetUnreadReportsCountDAO;
import by.htp6.hospital.dao.GetUserListDAO;
import by.htp6.hospital.dao.GetUsersCountDAO;
import by.htp6.hospital.dao.PerformAppointmentDAO;
import by.htp6.hospital.dao.SendReportDAO;
import by.htp6.hospital.dao.SignInDAO;
import by.htp6.hospital.dao.SignUpDAO;
import by.htp6.hospital.dao.impl.SQLAddNewPatientDAO;
import by.htp6.hospital.dao.impl.SQLChangeUserTypeDAO;
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
import by.htp6.hospital.dao.impl.SQLGetReportsCountDAO;
import by.htp6.hospital.dao.impl.SQLGetReportsDAO;
import by.htp6.hospital.dao.impl.SQLGetSingleReportDAO;
import by.htp6.hospital.dao.impl.SQLGetUnreadReportsCountDAO;
import by.htp6.hospital.dao.impl.SQLGetUserListDAO;
import by.htp6.hospital.dao.impl.SQLGetUsersCountDAO;
import by.htp6.hospital.dao.impl.SQLPerformAppointmentDAO;
import by.htp6.hospital.dao.impl.SQLSendReportDAO;
import by.htp6.hospital.dao.impl.SQLSignInDAO;
import by.htp6.hospital.dao.impl.SQLSignUpDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private SignInDAO signInDAO = new SQLSignInDAO();

	private SignUpDAO signUpDAO = new SQLSignUpDAO();

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

	private SendReportDAO sendReportDAO = new SQLSendReportDAO();

	private GetReportsDAO getReportsDAO = new SQLGetReportsDAO();

	private GetReportsCountDAO getReportsCountDAO = new SQLGetReportsCountDAO();

	private GetUnreadReportsCountDAO getUnreadReportsCountDAO = new SQLGetUnreadReportsCountDAO();
	
	private GetSingleReportDAO getSingleReportDAO = new SQLGetSingleReportDAO();
	
	private GetUserListDAO getUserListDAO = new SQLGetUserListDAO();
	
	private GetUsersCountDAO getUsersCountDAO = new SQLGetUsersCountDAO();
	
	private ChangeUserTypeDAO changeUserTypeDAO = new SQLChangeUserTypeDAO();

	public SignInDAO getSignInDAO() {
		return this.signInDAO;
	}

	public SignUpDAO getSignUpDAO() {
		return this.signUpDAO;
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

	public SendReportDAO getSendReportDAO() {
		return this.sendReportDAO;
	}

	public GetReportsDAO getGetReportsDAO() {
		return this.getReportsDAO;
	}
	
	public GetReportsCountDAO getGetReportsCountDAO() {
		return this.getReportsCountDAO;
	}
	
	public GetUnreadReportsCountDAO getGetUnreadReportsCountDAO() {
		return this.getUnreadReportsCountDAO;
	}
	
	public GetSingleReportDAO getGetSingleReportDAO() {
		return this.getSingleReportDAO;
	}

	public GetUserListDAO getGetUserListDAO() {
		return this.getUserListDAO;
	}
	
	public GetUsersCountDAO getGetUsersCountDAO() {
		return this.getUsersCountDAO;
	}

	public ChangeUserTypeDAO getChangeUserTypeDAO() {
		return this.changeUserTypeDAO;
	}
	
	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
	}

}
