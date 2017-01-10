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

import by.htp6.hospital.bean.Appointment;
import by.htp6.hospital.dao.GetAppointmentListDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLGetAppointmentListDAO implements GetAppointmentListDAO{
	private static final Logger log = LogManager.getLogger(SQLGetAppointmentListDAO.class);

	private static final String SQL_GET_APPOINTMENT_LIST = 
			"SELECT * FROM appointment WHERE patient_id = ? AND status = ?"
			+ " ORDER BY appointment_term DESC";
	
	@Override
	public List<Appointment> getAppointmentList(int patientId, String status) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = connectionPool.take();
			
			preparedStatement = connection.prepareStatement(SQL_GET_APPOINTMENT_LIST);
			preparedStatement.setInt(1, patientId);
			preparedStatement.setString(2, status);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
			Timestamp date;
			Appointment appointment;
			List<Appointment> appointments = new ArrayList<>();
			while (resultSet.next()){
				int id = resultSet.getInt("id");
				int doctorId = resultSet.getInt("doctor_id");
				String type = resultSet.getString("type");
				String name = resultSet.getString("name");
				date = resultSet.getTimestamp("appointment_date");
				String appointmentDate = dateFormat.format(date);
				date = resultSet.getTimestamp("appointment_term");
				String appointmentTerm = dateFormat.format(date);
				date = resultSet.getTimestamp("perform_date");
				String performDate = (date == null) ? null : dateFormat.format(date);
				appointment = new Appointment(id, patientId, doctorId, type, name,
						status, appointmentDate, appointmentTerm, performDate);
				appointments.add(appointment);
			}
			
			if (appointments.isEmpty()){
				appointments = Collections.emptyList();
			}
			
			return appointments;
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
