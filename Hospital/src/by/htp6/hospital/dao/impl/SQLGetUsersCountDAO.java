package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.htp6.hospital.dao.GetUsersCountDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLGetUsersCountDAO implements GetUsersCountDAO{
	private static final Logger log = LogManager.getLogger(SQLGetUsersCountDAO.class);

	private static final String SQL_GET_USERS_COUNT = 
			"SELECT COUNT(*) FROM user";
	
	@Override
	public int getUsersCount() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = connectionPool.take();

			preparedStatement = connection.prepareStatement(SQL_GET_USERS_COUNT);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			int usersCount = 0;
			if (resultSet.next()) {
				usersCount = resultSet.getInt(1);
			}

			return usersCount;
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

}
