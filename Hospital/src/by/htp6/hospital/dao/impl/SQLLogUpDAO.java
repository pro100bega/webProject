package by.htp6.hospital.dao.impl;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.LogUpDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLLogUpDAO implements LogUpDAO {
	private static final Logger log = LogManager.getLogger(SQLLogUpDAO.class);
	
	@Override
	public void logUp(String username, String password, String userType) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();
			if (!checkUsernameInBase(username, connection)) {
				try {
					String query = "INSERT INTO user(username, password, type) VALUES(?,?,?)";
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, password);
					preparedStatement.setString(3, userType);
					preparedStatement.executeUpdate();
					
					preparedStatement.close();
					connectionPool.free(connection);

				} catch (SQLException e) {
					log.error(e.getMessage());
					connectionPool.free(connection);
					throw new DAOException(e);
				} catch (InterruptedException e) {
					log.error(e.getMessage());
					connectionPool.free(connection);
					throw new DAOException(e);
				}
			} else {
				throw new DAOException("Username \"" + username + "\" is already exists");
			}
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} 
	}

	// checks if user name already exists in database
	/**
	 * @param username
	 *            Returns {@code true} if user name already exists in database
	 *            or {@code false} in another case.
	 */
	private boolean checkUsernameInBase(String username, Connection connection) throws DAOException {
		try {
			String sql = "CALL check_username(?)";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(1, username);
			ResultSet resultSet = callableStatement.executeQuery();
			if (resultSet.next()) {
				String answer = resultSet.getString(1);
				callableStatement.close();
				return (answer.equals("yes")) ? true : false;
			} else {
				callableStatement.close();
				throw new DAOException("Database error");
			}
		} catch (SQLException e) {
			log.info(e.getMessage());
			throw new DAOException(e);
		}
	}
}
