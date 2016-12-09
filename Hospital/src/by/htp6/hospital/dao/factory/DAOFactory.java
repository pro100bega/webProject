package by.htp6.hospital.dao.factory;

import by.htp6.hospital.dao.AddNewPatientDAO;
import by.htp6.hospital.dao.AssignProcedureDAO;
import by.htp6.hospital.dao.DischargePatientDAO;
import by.htp6.hospital.dao.FindPatientDAO;
import by.htp6.hospital.dao.PatientListDAO;
import by.htp6.hospital.dao.UserLogInDAO;
import by.htp6.hospital.dao.UserLogUpDAO;
import by.htp6.hospital.dao.impl.SQLAddNewPatientDAO;
import by.htp6.hospital.dao.impl.SQLAssignProcedureDAO;
import by.htp6.hospital.dao.impl.SQLDischargePatientDAO;
import by.htp6.hospital.dao.impl.SQLFindPatientDAO;
import by.htp6.hospital.dao.impl.SQLPatientListDAO;
import by.htp6.hospital.dao.impl.SQLUserLoginationDAO;
import by.htp6.hospital.dao.impl.SQLUserRegistrationDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private UserLogInDAO userLoginationDAO = new SQLUserLoginationDAO();

	private UserLogUpDAO userRegistrationDAO = new SQLUserRegistrationDAO();
	
	private PatientListDAO patientListDAO = new SQLPatientListDAO();
	
	private DischargePatientDAO dischargePatientDAO = new SQLDischargePatientDAO();
	
	private FindPatientDAO findPatientDAO = new SQLFindPatientDAO();
	
	private AddNewPatientDAO addNewPatientDAO = new SQLAddNewPatientDAO();
	
	private AssignProcedureDAO assignProcedureDAO = new SQLAssignProcedureDAO();

	public UserLogInDAO getUserLoginationDAO() {
		return this.userLoginationDAO;
	}
	
	public UserLogUpDAO getUserRegistrationDAO() {
		return this.userRegistrationDAO;
	}

	public PatientListDAO getPatientListDAO() {
		return this.patientListDAO;
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
	
	public AssignProcedureDAO getAssignProcedureDAO() {
		return this.assignProcedureDAO;
	}
	
	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
	}
	
}
