package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.htp6.hospital.bean.Appointment;
import by.htp6.hospital.dao.GetAppointmentListDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLGetAppointmentListDAO implements GetAppointmentListDAO{

	@Override
	public List<Appointment> getAppointmentList(int patientId, String status) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		try {
			Connection connection = connectionPool.take();
			
			String sql = "SELECT * FROM appointment WHERE patient_id = ? AND status = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, patientId);
			preparedStatement.setString(2, status);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			Date date;
			Appointment appointment;
			List<Appointment> appointments = new ArrayList<>();
			while (resultSet.next()){
				int id = resultSet.getInt("id");
				int doctorId = resultSet.getInt("doctor_id");
				String type = resultSet.getString("type");
				String name = resultSet.getString("name");
				String appointmentDate = dateFormat.format(resultSet.getDate("appointment_date"));
				date = resultSet.getDate("appointment_term");
				String appointmentTerm = (date == null) ? null : dateFormat.format(date);
				date = resultSet.getDate("perform_date");
				String performDate = (date == null) ? null : dateFormat.format(date);
				appointment = new Appointment(id, patientId, doctorId, type, name,
						status, appointmentDate, appointmentTerm, performDate);
				appointments.add(appointment);
			}
			
			if (appointments.isEmpty()){
				appointments = Collections.emptyList();
			}
			
			preparedStatement.close();
			connectionPool.free(connection);
			return appointments;
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
