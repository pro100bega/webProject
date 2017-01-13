package by.htp6.hospital.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.htp6.hospital.dao.ChangeUserTypeDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLChangeUserTypeDAO implements ChangeUserTypeDAO{
	private static final Logger log = LogManager.getLogger(SQLChangeUserTypeDAO.class);

	private static final String SQL_CHANGE_USER_TYPE = 
			"UPDATE user SET type = ? WHERE id = ?";
	
	@Override
	public void changeUserType(String type, int userId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = connectionPool.take();
			
			preparedStatement = connection.prepareStatement(SQL_CHANGE_USER_TYPE);
			
			preparedStatement.setString(1, type);
			preparedStatement.setInt(2, userId);
			preparedStatement.executeUpdate();
			
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
