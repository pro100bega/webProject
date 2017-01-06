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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.dao.GetUserListDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLGetUserListDAO implements GetUserListDAO{
	private static final Logger log = LogManager.getLogger(SQLGetUserListDAO.class);

	@Override
	public List<User> getUserList(int offset, int count) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		try {
			Connection connection = connectionPool.take();
			
			String sql = "SELECT * FROM user LIMIT ?, ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
			
			preparedStatement.close();
			connectionPool.free(connection);
			
			return users;
			
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		}
	}

}
