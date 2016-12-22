package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import by.htp6.hospital.bean.Log;
import by.htp6.hospital.dao.GetLogDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLGetLogDAO implements GetLogDAO{

	@Override
	public List<Log> getLog() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();
			String query = "SELECT * FROM log";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
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
			preparedStatement.close();
			connectionPool.free(connection);
			return logList;
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
