package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.GetUnreadReportsCountDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLGetUnreadReportsCountDAO implements GetUnreadReportsCountDAO {
	private static final Logger log = LogManager.getLogger(SQLGetUnreadReportsCountDAO.class);

	private static final String SQL_GET_UNREAD_REPORTS_COUNT = 
			"SELECT COUNT(*) FROM report WHERE status = 'unread'";
	
	@Override
	public int getUnreadReportsCount() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.take();

			preparedStatement = connection.prepareStatement(SQL_GET_UNREAD_REPORTS_COUNT);
			ResultSet resultSet = preparedStatement.executeQuery();
			int reportsCount = 0;
			if (resultSet.next()) {
				reportsCount = resultSet.getInt(1);
			}

			return reportsCount;

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
