package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.dao.LogInDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLLogInDAO implements LogInDAO {
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public User logIn(String username, String password) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();
			String query = "SELECT * FROM user WHERE username = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (password.equals(resultSet.getString("password"))) {
					int id = resultSet.getInt("id");
					String type = resultSet.getString("type");
					Timestamp dbTime = resultSet.getTimestamp("create_time");
					String createTime = dateFormat.format(dbTime);
					
					User user = new User(id, username, password, type, createTime);
					
					resultSet.close();
					connectionPool.free(connection);
					return user;
				} else {
					connectionPool.free(connection);
					throw new DAOException("Incorrect password or username");
				}
			} else {
				connectionPool.free(connection);
				throw new DAOException("Incorrect password or username");
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