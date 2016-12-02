package by.htp6.hospital.service.factory;

import by.htp6.hospital.service.LogUpService;
import by.htp6.hospital.service.PatientTransmitorService;
import by.htp6.hospital.service.DischargePatientService;
import by.htp6.hospital.service.FindPatientService;
import by.htp6.hospital.service.LogInService;
import by.htp6.hospital.service.impl.LogUp;
import by.htp6.hospital.service.impl.PatientTransmitor;
import by.htp6.hospital.service.impl.DischargePatient;
import by.htp6.hospital.service.impl.FindPatient;
import by.htp6.hospital.service.impl.LogIn;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private LogInService logIn = new LogIn();

	private LogUpService logUp = new LogUp();

	private PatientTransmitorService patientTransmitor = new PatientTransmitor();

	private DischargePatientService dischargePatien = new DischargePatient();

	private FindPatientService findPatient = new FindPatient();

	public LogInService getLoginUser() {
		return this.logIn;
	}

	public LogUpService getLogUpUser() {
		return this.logUp;
	}

	public PatientTransmitorService getPatientTransmitor() {
		return this.patientTransmitor;
	}

	public DischargePatientService getDischargePatient() {
		return this.dischargePatien;
	}

	public FindPatientService getFindPatientService() {
		return this.findPatient;
	}

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}
}
