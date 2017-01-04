package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.bean.Report;
import by.htp6.hospital.dao.GetSingleReportDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLGetSingleReportDAO implements GetSingleReportDAO{
	private static final Logger log = LogManager.getLogger(SQLGetSingleReportDAO.class);
		
	@Override
	public Report getReport(int reportId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		try {
			Connection connection = connectionPool.take();
			
			String sql = "SELECT * FROM report WHERE id = ? limit 1";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, reportId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			SimpleDateFormat timeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			Report report = null;
			if (resultSet.next()) {
				changeStatus(connection, reportId);
				
				int id = resultSet.getInt("id");
				String header = resultSet.getString("header");
				String message = resultSet.getString("message");
				Timestamp date = resultSet.getTimestamp("time");
				String time = timeFormat.format(date);
				String status = resultSet.getString("status");
				report = new Report(id, header, message, time, status);
			} else {
				throw new DAOException("Wrong report id");
			}
			
			preparedStatement.close();
			connectionPool.free(connection);
			return report;
			
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		}
	}
	
	private void changeStatus(Connection connection, int reportId) 
			throws DAOException{
		String sql = "UPDATE report SET status = 'read' WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reportId);
			
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		}
	}

}
