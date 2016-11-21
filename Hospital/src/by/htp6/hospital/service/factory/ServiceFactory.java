package by.htp6.hospital.service.factory;

import by.htp6.hospital.service.LogUpService;
import by.htp6.hospital.service.LogInService;
import by.htp6.hospital.service.impl.LogUp;
import by.htp6.hospital.service.impl.LogIn;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private LogInService logIn = new LogIn();
	
	private LogUpService logUp = new LogUp();
	
	public LogUpService getLogUpUser() {
		return logUp;
	}

	public LogInService getLoginUserService(){
		return this.logIn;
	}
	
	private ServiceFactory(){}
	
	public static ServiceFactory getInstance(){
		return instance;
	}
}
