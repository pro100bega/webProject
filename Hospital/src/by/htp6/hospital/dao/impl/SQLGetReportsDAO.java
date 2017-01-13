package by.htp6.hospital.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.htp6.hospital.bean.Report;
import by.htp6.hospital.dao.GetReportsDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLGetReportsDAO implements GetReportsDAO {
	private static final Logger log = LogManager.getLogger(SQLGetPatientsCountDAO.class);

	private static final String SQL_GET_REPORTS = 
			"SELECT * FROM report ORDER BY time DESC limit ?, ?";
	
	@Override
	public List<Report> getReports(int offset, int count) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = connectionPool.take();
			
			preparedStatement = connection.prepareStatement(SQL_GET_REPORTS);
			
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, count);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			List<Report> reports = new ArrayList<>();
			
			
			SimpleDateFormat timeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			while (resultSet.next()){
				int id = resultSet.getInt("id");
				String header = resultSet.getString("header");
				String message = resultSet.getString("message");
				Timestamp date = resultSet.getTimestamp("time");
				String time = timeFormat.format(date);
				String status = resultSet.getString("status");
				reports.add(new Report(id, header, message, time, status));
			}
			
			if (reports.size() == 0){
				reports = Collections.emptyList();
			}

			return reports;
			
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
