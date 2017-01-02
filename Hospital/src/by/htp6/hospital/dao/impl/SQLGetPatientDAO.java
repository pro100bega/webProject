package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.dao.GetPatientDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLGetPatientDAO implements GetPatientDAO{
	private static final Logger log = LogManager.getLogger(SQLGetPatientDAO.class);

	@Override
	public Patient getPatient(int patientId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		
		try {
			Connection connection = connectionPool.take();
			
			String query = "SELECt * FROM patient WHERE id = ? limit 1";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, patientId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			
			SimpleDateFormat birthDateFormat = new SimpleDateFormat("dd.MM.yyyy");
			SimpleDateFormat receiptDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String surname = resultSet.getString("surname");
			String diagnosis = resultSet.getString("diagnosis");
			String sex = resultSet.getString("sex");
			Date birthDate = resultSet.getDate("birth_date");
			String stringBirthDate = birthDateFormat.format(birthDate);
			Date receiptDate = resultSet.getDate("receipt_date");
			int doctorId = resultSet.getInt("doctor_id");
			String stringReceiptDate = receiptDateFormat.format(receiptDate);
			String note = resultSet.getString("note");
			Patient patient = new Patient(id, name, surname, sex, stringBirthDate,
					diagnosis, doctorId, stringReceiptDate, note);
			
			preparedStatement.close();
			connectionPool.free(connection);
			
			return patient;
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		}
	}
	
}
