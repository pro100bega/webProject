package by.htp6.hospital.service.factory;

import by.htp6.hospital.service.LogUpService;
import by.htp6.hospital.service.GetPatientListService;
import by.htp6.hospital.service.GetPatientService;
import by.htp6.hospital.service.GetPatientsCountService;
import by.htp6.hospital.service.PerformAppointmentService;
import by.htp6.hospital.service.AddNewPatientService;
import by.htp6.hospital.service.AddAppointmentService;
import by.htp6.hospital.service.DischargePatientService;
import by.htp6.hospital.service.FindLogService;
import by.htp6.hospital.service.FindPatientService;
import by.htp6.hospital.service.GetAppointmentListService;
import by.htp6.hospital.service.GetLogService;
import by.htp6.hospital.service.LogInService;
import by.htp6.hospital.service.impl.LogUp;
import by.htp6.hospital.service.impl.GetPatientList;
import by.htp6.hospital.service.impl.GetPatientsCount;
import by.htp6.hospital.service.impl.PerformAppointment;
import by.htp6.hospital.service.impl.AddNewPatient;
import by.htp6.hospital.service.impl.AddAppointment;
import by.htp6.hospital.service.impl.DischargePatient;
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
	
	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}
}
