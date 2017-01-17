package by.htp6.hospital.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.htp6.hospital.bean.Log;
import by.htp6.hospital.constant.ErrorMessage;
import by.htp6.hospital.dao.GetLogDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLGetLogDAO implements GetLogDAO{
	private static final Logger log = LogManager.getLogger(SQLGetLogDAO.class);

	private static final String SQL_GET_LOG = "SELECT * FROM log";
	
	@Override
	public List<Log> getLog() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = connectionPool.take();
	
			preparedStatement = connection.prepareStatement(SQL_GET_LOG);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			Timestamp sqlDate;
			Log log;
			List<Log> logList = new ArrayList<>();			
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String message = resultSet.getString("message");
				String tableName = resultSet.getString("table_name");
				int rowId = resultSet.getInt("row_id");
				sqlDate = resultSet.getTimestamp("time");
				String time = dateFormat.format(sqlDate);
				log = new Log(id, message, tableName, rowId, time);
				logList.add(log);
			}
			
			return logList;
		} catch (InterruptedException e) {
			log.error(ErrorMessage.UNABLE_TO_TAKE_CONNECTION, e);
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new DAOException(e);
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}

			} catch (SQLException e) {
				log.error(ErrorMessage.UNABLE_TO_CLOSE_STATEMENT, e);
			}
			
			try {
				if (connection != null) {
					connectionPool.free(connection);
				}
			} catch (InterruptedException e) {
				log.error(ErrorMessage.UNABLE_TO_FREE_CONNECTION, e);
			}

		}
	}

}
