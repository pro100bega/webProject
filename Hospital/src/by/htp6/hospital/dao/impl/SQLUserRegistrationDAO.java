package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.dao.UserLogUpDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLUserRegistrationDAO implements UserLogUpDAO {

	@Override
	public User registration(String username, String password) throws DAOException {
		if (!checkUsernameInBase(username)) {
			try {
				ConnectionPool connectionPool = ConnectionPool.getInstance();
				Connection connection = connectionPool.take();
				try {
					String query = "INSERT INTO user(username, password, type) VALUES(?,?,?)";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, password);
					preparedStatement.setString(3, "guest"); // default user
																// type
					preparedStatement.executeUpdate();

					User user = getUserFromDatabase(connection, username);
					connectionPool.free(connection);

					return user;
				} catch (SQLException e) {
					connectionPool.free(connection);
					throw new DAOException(e);
				} catch (InterruptedException e) {
					connectionPool.free(connection);
					throw new DAOException(e);
				}
			} catch (InterruptedException e) {
				throw new DAOException(e);
			}

		} else {
			throw new DAOException("Uername \"" + username + "\" is already exists");
		}
	}

	// checks if user name already exists in database
	/**
	 * @param username
	 *            Returns {@code true} if user name already exists in database
	 *            or {@code false} in another case.
	 */
	private boolean checkUsernameInBase(String username) throws DAOException {
		boolean exists = false;
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.take();
			String query = "SELECT * FROM user WHERE username = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				exists = true;
				connectionPool.free(connection);
				return exists;
			}
			connectionPool.free(connection);
			return exists;
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (InterruptedException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * @param username
	 *            Creates and returns {@code User} object from database.
	 */
	private User getUserFromDatabase(Connection connection, String username) throws DAOException {
		try {
			String query = "SELECT * FROM user WHERE username = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			User user = new User(resultSet.getInt("id"), resultSet.getString("username"),
					resultSet.getString("password"), resultSet.getString("type"));
			connection.close();
			return user;

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
