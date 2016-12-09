package by.htp6.hospital.service;

import by.htp6.hospital.service.exception.ServiceException;

public interface AssignProcedureService {
	void assignProcedure(int patientId, int doctorId, String type, String name)
		throws ServiceException;
}
