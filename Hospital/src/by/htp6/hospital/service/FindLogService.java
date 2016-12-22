package by.htp6.hospital.service;

import java.util.List;

import by.htp6.hospital.bean.Log;
import by.htp6.hospital.service.exception.ServiceException;

public interface FindLogService {
	List<Log> findLog(String searchData, String userType) throws ServiceException;
}
