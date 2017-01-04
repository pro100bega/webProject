package by.htp6.hospital.service.factory;

import by.htp6.hospital.service.LogUpService;
import by.htp6.hospital.service.GetPatientListService;
import by.htp6.hospital.service.GetPatientService;
import by.htp6.hospital.service.GetPatientsCountService;
import by.htp6.hospital.service.GetReportsCountService;
import by.htp6.hospital.service.GetReportsService;
import by.htp6.hospital.service.GetSingleReportService;
import by.htp6.hospital.service.GetUnreadReportsCountService;
import by.htp6.hospital.service.PerformAppointmentService;
import by.htp6.hospital.service.SendReportService;
import by.htp6.hospital.service.AddNewPatientService;
import by.htp6.hospital.service.AddAppointmentService;
import by.htp6.hospital.service.DischargePatientService;
import by.htp6.hospital.service.EditPatientService;
import by.htp6.hospital.service.FindLogService;
import by.htp6.hospital.service.FindPatientService;
import by.htp6.hospital.service.GetAppointmentListService;
import by.htp6.hospital.service.GetLogService;
import by.htp6.hospital.service.LogInService;
import by.htp6.hospital.service.impl.LogUp;
import by.htp6.hospital.service.impl.GetPatientList;
import by.htp6.hospital.service.impl.GetPatientsCount;
import by.htp6.hospital.service.impl.GetReports;
import by.htp6.hospital.service.impl.GetReportsCount;
import by.htp6.hospital.service.impl.GetSingleReport;
import by.htp6.hospital.service.impl.GetUnreadReportsCount;
import by.htp6.hospital.service.impl.PerformAppointment;
import by.htp6.hospital.service.impl.SendReport;
import by.htp6.hospital.service.impl.AddNewPatient;
import by.htp6.hospital.service.impl.AddAppointment;
import by.htp6.hospital.service.impl.DischargePatient;
import by.htp6.hospital.service.impl.EditPatient;
import by.htp6.hospital.service.impl.FindLog;
import by.htp6.hospital.service.impl.FindPatient;
import by.htp6.hospital.service.impl.GetAppointmentList;
import by.htp6.hospital.service.impl.GetLog;
import by.htp6.hospital.service.impl.GetPatient;
import by.htp6.hospital.service.impl.LogIn;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private LogInService logIn = new LogIn();

	private LogUpService logUp = new LogUp();

	private GetPatientListService getPatientList = new GetPatientList();

	private GetPatientsCountService getPatientsCount = new GetPatientsCount();

	private DischargePatientService dischargePatien = new DischargePatient();

	private FindPatientService findPatient = new FindPatient();

	private AddNewPatientService addNewPatient = new AddNewPatient();

	private AddAppointmentService addAppointment = new AddAppointment();

	private GetLogService getLog = new GetLog();

	private FindLogService findLog = new FindLog();

	private GetAppointmentListService getAppointmentList = new GetAppointmentList();

	private PerformAppointmentService performAppointment = new PerformAppointment();

	private GetPatientService getPatient = new GetPatient();

	private EditPatientService editPatient = new EditPatient();

	private SendReportService sendReport = new SendReport();

	private GetReportsService getReports = new GetReports();

	private GetReportsCountService getReportsCount = new GetReportsCount();

	private GetUnreadReportsCountService getUnreadReportsCount = new GetUnreadReportsCount();
	
	private GetSingleReportService getSingleReport = new GetSingleReport();

	public LogInService getLoginUser() {
		return this.logIn;
	}

	public LogUpService getLogUpUser() {
		return this.logUp;
	}

	public GetPatientListService getGetPatientList() {
		return this.getPatientList;
	}

	public GetPatientsCountService getGetPatientsCount() {
		return this.getPatientsCount;
	}

	public DischargePatientService getDischargePatient() {
		return this.dischargePatien;
	}

	public FindPatientService getFindPatient() {
		return this.findPatient;
	}

	public AddNewPatientService getAddNewPatient() {
		return this.addNewPatient;
	}

	public AddAppointmentService getAddAppointment() {
		return this.addAppointment;
	}

	public GetLogService getGetLog() {
		return this.getLog;
	}

	public FindLogService getFindLog() {
		return this.findLog;
	}

	public GetAppointmentListService getGetAppointmentList() {
		return this.getAppointmentList;
	}

	public PerformAppointmentService getPerformAppointment() {
		return this.performAppointment;
	}

	public GetPatientService getGetPatient() {
		return this.getPatient;
	}

	public EditPatientService getEditPatient() {
		return this.editPatient;
	}

	public SendReportService getSendReport() {
		return this.sendReport;
	}
	
	public GetReportsService getGetReports() {
		return this.getReports;
	}
	
	public GetReportsCountService getGetReportsCount() {
		return this.getReportsCount;
	}
	
	public GetUnreadReportsCountService getGetUnreadReportsCount() {
		return this.getUnreadReportsCount;
	}
	
	public GetSingleReportService getGetSingleReport() { 
		return this.getSingleReport;
	}

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}
}
