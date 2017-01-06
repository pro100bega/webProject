package by.htp6.hospital.service;

import by.htp6.hospital.service.exception.ServiceException;

public interface LogUpService {
	void logUp(String username, String password, String userType) throws ServiceException;
}
