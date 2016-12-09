package by.htp6.hospital.dao;

import by.htp6.hospital.dao.exception.DAOException;

public interface AssignProcedureDAO {
	void assignProcedure(int patientId, int doctorId, String type, String name)
		throws DAOException;
}
