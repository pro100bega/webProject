package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.dao.GetPatientListDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLGetPatientListDAO implements GetPatientListDAO{
	private static final Logger log = LogManager.getLogger(SQLGetPatientListDAO.class);
	
	private static final String SLQ_GET_PATIENT_LIST_FOR_DOCTOR =
			"SELECT * FROM patient WHERE doctor_id = ?"
							+ " ORDER BY receipt_date limit ?, ?";
	
	private static final String SQL_GET_ALL_PATIENTS_LIST = 
			"SELECT * FROM patient ORDER BY receipt_date limit ?, ?";

	@Override
	public List<Patient> getPatientListForDoctor(int doctorId,
			int offset, int patientsPerPage, String orderBy) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();
			try {
				String query;
				if (null == orderBy) {
					query = SLQ_GET_PATIENT_LIST_FOR_DOCTOR;
				} else {
					query = "SELECT * FROM patient WHERE doctor_id = ?"
							+ " ORDER BY " + orderBy + " limit ?, ?";
				}
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, doctorId);
				preparedStatement.setInt(2, offset);
				preparedStatement.setInt(3, patientsPerPage);
				
				SimpleDateFormat birthDateFormat = new SimpleDateFormat("dd.MM.yyyy");
				SimpleDateFormat receiptDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				ResultSet resultSet = preparedStatement.executeQuery();
				List<Patient> patients = new ArrayList<>();
				while (resultSet.next()){
					int id = resultSet.getInt("id");
					String name = resultSet.getString("name");
					String surname = resultSet.getString("surname");
					String diagnosis = resultSet.getString("diagnosis");
					String sex = resultSet.getString("sex");
					Date birthDate = resultSet.getDate("birth_date");
					String stringBirthDate = birthDateFormat.format(birthDate);
					Date receiptDate = resultSet.getDate("receipt_date");
					String stringReceiptDate = receiptDateFormat.format(receiptDate);
					String note = resultSet.getString("note");
					Patient patient = new Patient(id, name, surname, sex, stringBirthDate,
							diagnosis, doctorId, stringReceiptDate, note);
					patients.add(patient);
				}
				
				preparedStatement.close();
				connectionPool.free(connection);
				return patients;
			} catch (SQLException e) {
				log.error(e.getMessage());
				connectionPool.free(connection);
				throw new DAOException(e);
			}
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		}
	}

	@Override
	public List<Patient> getAllPatientList(int offset, int patientsPerPage,
			String orderBy) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();
			try {
				String query;
				if (null == orderBy) {
					query = SQL_GET_ALL_PATIENTS_LIST;
				} else {
					query = "SELECT * FROM patient ORDER BY " + orderBy 
							+ " limit ?, ?";
				}
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, offset);
				preparedStatement.setInt(2, patientsPerPage);
				
				SimpleDateFormat birthDateFormat = new SimpleDateFormat("dd.MM.yyyy");
				SimpleDateFormat receiptDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				ResultSet resultSet = preparedStatement.executeQuery();
				List<Patient> patients = new ArrayList<>();
				while (resultSet.next()){
					int id = resultSet.getInt("id");
					String name = resultSet.getString("name");
					String surname = resultSet.getString("surname");
					String diagnosis = resultSet.getString("diagnosis");
					String sex = resultSet.getString("sex");
					Date birthDate = resultSet.getDate("birth_date");
					int doctorId = resultSet.getInt("doctor_id");
					String stringBirthDate = birthDateFormat.format(birthDate);
					Date receiptDate = resultSet.getDate("receipt_date");
					String stringReceiptDate = receiptDateFormat.format(receiptDate);
					String note = resultSet.getString("note");
					Patient patient = new Patient(id, name, surname, sex, stringBirthDate,
							diagnosis, doctorId, stringReceiptDate, note);
					patients.add(patient);
				}
				
				preparedStatement.close();
				connectionPool.free(connection);
				return patients;
			} catch (SQLException e) {
				log.error(e.getMessage());
				connectionPool.free(connection);
				throw new DAOException(e);
			}
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		}
	}

}
