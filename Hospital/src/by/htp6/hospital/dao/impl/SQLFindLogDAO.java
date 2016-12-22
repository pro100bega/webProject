package by.htp6.hospital.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import by.htp6.hospital.bean.Log;
import by.htp6.hospital.dao.FindLogDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLFindLogDAO implements FindLogDAO {

	/**
	 * @author Бегенч
	 * @param searchData - string that is needed to find by
	 * @return ArrayList that contains found log rows
	 * @throws DAOException
	 */
	@Override
	public List<Log> findLog(String searchData) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();
			String sql = "CALL find_log(?)";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(1, searchData);
			ResultSet resultSet = callableStatement.executeQuery();
			
			Timestamp sqlTime;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			// Log list
			List<Log> logList = new ArrayList<>();
			// Single log object
			Log log;
			while (resultSet.next()){
				int id = resultSet.getInt("id");
				String message = resultSet.getString("message");
				String tableName = resultSet.getString("table_name");
				int rowId = resultSet.getInt("row_id");
				sqlTime = resultSet.getTimestamp("time");
				String time = dateFormat.format(sqlTime);
				log = new Log(id, message, tableName, rowId, time);
				logList.add(log);
			}
			callableStatement.close();
			connectionPool.free(connection);
			
			if (logList.size() == 0){
				logList = Collections.emptyList();
			}
			
			return logList;
			
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
