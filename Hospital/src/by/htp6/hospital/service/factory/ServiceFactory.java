package by.htp6.hospital.service.factory;

import by.htp6.hospital.service.LogUpService;
import by.htp6.hospital.service.PatientTransmitorService;
import by.htp6.hospital.service.LogInService;
import by.htp6.hospital.service.impl.LogUp;
import by.htp6.hospital.service.impl.PatientTransmitor;
import by.htp6.hospital.service.impl.LogIn;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private LogInService logIn = new LogIn();
	
	private LogUpService logUp = new LogUp();
	
	private PatientTransmitorService patientTransmitor = 
			new PatientTransmitor();
	
	public LogUpService getLogUpUser() {
		return this.logUp;
	}

	public LogInService getLoginUser(){
		return this.logIn;
	}
	
	public PatientTransmitorService getPatientTransmitor(){
		return this.patientTransmitor;
	}

	private ServiceFactory(){}
	
	public static ServiceFactory getInstance(){
		return instance;
	}
}
