package by.htp6.hospital.dao;

import by.htp6.hospital.dao.exception.DAOException;

public interface LogUpDAO {
	void logUp(String username, String password, String userType) throws DAOException;
}
