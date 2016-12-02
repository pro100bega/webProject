package by.htp6.hospital.service;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.service.exception.ServiceException;

public interface LogUpService {
	User logUp(String username, String password, String userType) throws ServiceException;
}
