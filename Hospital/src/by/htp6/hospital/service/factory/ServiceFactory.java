package by.htp6.hospital.service.factory;

import by.htp6.hospital.service.BookUserService;
import by.htp6.hospital.service.LogInUserService;
import by.htp6.hospital.service.impl.BookUser;
import by.htp6.hospital.service.impl.LogInUser;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private LogInUserService logInUser = new LogInUser();
	
	private BookUserService bookUser = new BookUser();
	
	public BookUserService getBookUser() {
		return bookUser;
	}

	public LogInUserService getLoginUserService(){
		return this.logInUser;
	}
	
	private ServiceFactory(){}
	
	public static ServiceFactory getInstance(){
		return instance;
	}
}
