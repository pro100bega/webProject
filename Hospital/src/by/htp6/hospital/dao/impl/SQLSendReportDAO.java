package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.SendReportDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLSendReportDAO implements SendReportDAO{
	private static final Logger log = LogManager.getLogger(SQLSendReportDAO.class);

	@Override
	public void sendReport(String header, String message) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		try {
			Connection connection = connectionPool.take();
			
			String sql = "INSERT INTO report(`header`, `message`, `status`)"
					+ " VALUES (?, ?, 'unread')";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, header);
			preparedStatement.setString(2, message);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			connectionPool.free(connection);
			
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		}
		
	}

}