package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.constant.ErrorMessage;
import by.htp6.hospital.dao.SignInDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLSignInDAO implements SignInDAO {
	private static final Logger log = LogManager.getLogger(SQLSignInDAO.class);

	private static final String SQL_SIGN_IN = 
			"SELECT * FROM user WHERE username = ?";
	
	@Override
	public User signIn(String username, String password) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = connectionPool.take();

			preparedStatement = connection.prepareStatement(SQL_SIGN_IN);
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
					
					return user;
				} else {
					throw new DAOException("Incorrect password or username");
				}
			} else {
				throw new DAOException("Incorrect password or username");
			}
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
