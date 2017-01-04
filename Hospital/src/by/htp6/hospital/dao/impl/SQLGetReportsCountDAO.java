package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.GetReportsCountDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLGetReportsCountDAO implements GetReportsCountDAO{
	private static final Logger log = LogManager.getLogger(SQLGetReportsCountDAO.class);

	@Override
	public int getReportsCount() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();

			String query = "SELECT COUNT(*) FROM report";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			int reportsCount = 0;
			if (resultSet.next()) {
				reportsCount = resultSet.getInt(1);
			}
			preparedStatement.close();
			connectionPool.free(connection);

			return reportsCount;
			
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		}
	}

}
