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

import by.htp6.hospital.bean.User;
import by.htp6.hospital.constant.ErrorMessage;
import by.htp6.hospital.dao.GetUserListDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLGetUserListDAO implements GetUserListDAO{
	private static final Logger log = LogManager.getLogger(SQLGetUserListDAO.class);

	private static final String SQL_GET_USER_LIST = 
			"SELECT * FROM user LIMIT ?, ?";
	
	@Override
	public List<User> getUserList(int offset, int count) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = connectionPool.take();
			
			preparedStatement = connection.prepareStatement(SQL_GET_USER_LIST);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, count);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			List<User> users = new ArrayList<>();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String username = resultSet.getString("username");
				String password = "********";
				String type = resultSet.getString("type");
				
				Timestamp dbTime = resultSet.getTimestamp("create_time");
				String createTime = dateFormat.format(dbTime);
				
				users.add(new User(id, username, password, type, createTime));
			}
			
			if (users.isEmpty()) {
				users = Collections.emptyList();
			}
			
			return users;
			
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
