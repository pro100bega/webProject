package by.htp6.hospital.dao;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.dao.exception.DAOException;

public interface LogInDAO {
	User logIn(String username, String password) throws DAOException;
}
