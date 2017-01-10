package by.htp6.hospital.dao.impl;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.SignUpDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLSignUpDAO implements SignUpDAO {
	private static final Logger log = LogManager.getLogger(SQLSignUpDAO.class);

	private static final String SQL_CHECK_USERNAME = "CALL check_username(?)";
	
	private static final String SQL_SIGN_UP = 
			"INSERT INTO user(username, password, type) VALUES(?,?,?)";
	
	@Override
	public void signUp(String username, String password, String userType) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = connectionPool.take();
			if (!checkUsernameInBase(username, connection)) {
				preparedStatement = connection.prepareStatement(SQL_SIGN_UP);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, userType);
				preparedStatement.executeUpdate();

			} else {
				throw new DAOException("Username \"" + username + "\" is already exists");
			}

		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}

				if (connection != null) {
					connectionPool.free(connection);
				}

			} catch (SQLException e) {
				log.error(e.getMessage());
				throw new DAOException(e);
			} catch (InterruptedException e) {
				log.error(e.getMessage());
				throw new DAOException(e);
			}

		}
	}

	// checks if user name already exists in database
	/**
	 * @param username
	 *            Returns {@code true} if user name already exists in database
	 *            or {@code false} in another case.
	 */
	private boolean checkUsernameInBase(String username, Connection connection) throws DAOException {
		CallableStatement callableStatement = null;
		try {
			callableStatement = connection.prepareCall(SQL_CHECK_USERNAME);
			callableStatement.setString(1, username);
			ResultSet resultSet = callableStatement.executeQuery();
			if (resultSet.next()) {
				String answer = resultSet.getString(1);
				return (answer.equals("yes")) ? true : false;
			} else {
				throw new DAOException("Database error");
			}
		} catch (SQLException e) {
			log.info(e.getMessage());
			throw new DAOException(e);
		} finally {
			try {
				if (callableStatement != null && !callableStatement.isClosed()) {
					callableStatement.close();
				}

			} catch (SQLException e) {
				log.error(e.getMessage());
				throw new DAOException(e);
			}
		}
	}
}
