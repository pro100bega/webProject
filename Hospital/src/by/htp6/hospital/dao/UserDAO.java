package by.htp6.hospital.dao;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.dao.exception.DAOException;

public interface UserDAO {
	User logination(String username, String password) throws DAOException;
}
