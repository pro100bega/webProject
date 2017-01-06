package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.GetUsersCountDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLGetUsersCountDAO implements GetUsersCountDAO{
	private static final Logger log = LogManager.getLogger(SQLGetUsersCountDAO.class);

	@Override
	public int getUsersCount() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			Connection connection = connectionPool.take();

			String query = "SELECT COUNT(*) FROM user";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			int usersCount = 0;
			if (resultSet.next()) {
				usersCount = resultSet.getInt(1);
			}
			preparedStatement.close();
			connectionPool.free(connection);

			return usersCount;
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		}
	}

}
