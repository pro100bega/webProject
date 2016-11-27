package by.htp6.hospital.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.dao.UserLogInDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLUserLoginationDAO implements UserLogInDAO {

	@Override
	public User logination(String username, String password) throws DAOException {
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.take();
			String query = "SELECT * FROM user WHERE username = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (password.equals(resultSet.getString("password"))) {
					User user = new User(resultSet.getInt("id"), resultSet.getString("username"),
							resultSet.getString("password"), resultSet.getString("type"));
					connectionPool.free(connection);
					return user;
				} else {
					connectionPool.free(connection);
					throw new DAOException("Incorrect password or username");
				}
			} else {
				connectionPool.free(connection);
				throw new DAOException("Incorrect password or username");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} 		
	}
}
