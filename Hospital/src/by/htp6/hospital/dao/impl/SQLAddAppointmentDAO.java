package by.htp6.hospital.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.htp6.hospital.dao.AddAppointmentDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLAddAppointmentDAO implements AddAppointmentDAO {
	private static final Logger log = LogManager.getLogger(SQLAddAppointmentDAO.class);
	
	private static final String SQL_ADD_APPOINTMENT = 
			"INSERT INTO appointment(patient_id, doctor_id, type, name, status,"
					+ " appointment_term) VALUES (?,?,?,?,?,?)";
	
	@Override
	public void addAppointment(int patientId, int doctorId, String type, String name, Timestamp term)
			throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.take();

			preparedStatement = connection.prepareStatement(SQL_ADD_APPOINTMENT);
			preparedStatement.setInt(1, patientId);
			preparedStatement.setInt(2, doctorId);
			preparedStatement.setString(3, type);
			preparedStatement.setString(4, name);
			preparedStatement.setString(5, "undone");
			preparedStatement.setTimestamp(6, term);
			preparedStatement.executeUpdate();

		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} finally {
			try {
				if (preparedStatement != null && 
						!preparedStatement.isClosed()) {
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
