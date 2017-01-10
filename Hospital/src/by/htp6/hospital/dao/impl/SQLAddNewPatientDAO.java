package by.htp6.hospital.dao.impl;

import java.sql.Connection;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import by.htp6.hospital.dao.AddNewPatientDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLAddNewPatientDAO implements AddNewPatientDAO {
	private static final Logger log = LogManager.getLogger(SQLAddNewPatientDAO.class);

	private static final String SQL_ADD_NEW_PATIENT = 
			"INSERT INTO patient(`name`, `surname`, `sex`, `birth_date`,"
					+ " `diagnosis`, `doctor_id`, `note`)" + " VALUES (?,?,?,?,?,?,?)";
	
	@Override
	public void addNewPatient(String name, String surname, char sex, Date birthDate, String diagnosis, int doctorId,
			String note) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.take();

			preparedStatement = connection.prepareStatement(SQL_ADD_NEW_PATIENT);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, "" + sex);
			preparedStatement.setDate(4, new java.sql.Date(birthDate.getTime()));
			preparedStatement.setString(5, diagnosis);
			preparedStatement.setInt(6, doctorId);
			preparedStatement.setString(7, note);
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
