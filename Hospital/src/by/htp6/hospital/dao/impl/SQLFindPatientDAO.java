package by.htp6.hospital.dao.impl;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.constant.ErrorMessage;
import by.htp6.hospital.dao.FindPatientDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLFindPatientDAO implements FindPatientDAO{
	private static final Logger log = LogManager.getLogger(SQLFindPatientDAO.class);
	
	private static final String SQL_FIND_PATIENT_BY_DOCTOR_ID = 
			"CALL find_patients_by_doctor_id(?,?,?,?,'receipt_date')";
	
	private static final String SQL_FIND_PATIENT = 
			"CALL find_patients(?,?,?,'receipt_date')";			
	
	@Override
	public List<Patient> findPatientsByDoctorId(String searchData,
			int doctorId, int offset, int count, String orderBy) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		CallableStatement callableStatement = null;
		
		try {
			String query;
			if (null == orderBy) {
				query = SQL_FIND_PATIENT_BY_DOCTOR_ID;
			} else {
				query = "CALL find_patients_by_doctor_id(?,?,?,?," + orderBy + ")";
			}
			connection = connectionPool.take();
			callableStatement = connection.prepareCall(query);
			callableStatement.setString(1, searchData);
			callableStatement.setInt(2, doctorId);
			callableStatement.setInt(3, offset);
			callableStatement.setInt(4, count);
			ResultSet resultSet = callableStatement.executeQuery();
			
			// Patients list
			List<Patient> patients = new ArrayList<Patient>();
			// Single patient object
			
			SimpleDateFormat birthDateFormat = new SimpleDateFormat("dd.MM.yyyy");
			SimpleDateFormat receiptDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			Patient patient = null;
			while (resultSet.next()){
				int patientId = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String sex = resultSet.getString("sex");
				String birthDate = birthDateFormat.format(resultSet.getDate("birth_date"));
				String diagnosis = resultSet.getString("diagnosis");
				String receiptDate = receiptDateFormat.format(resultSet.getDate("receipt_date"));
				String note = resultSet.getString("note");
				patient = new Patient(patientId, name, surname, sex, birthDate,
						diagnosis, doctorId, receiptDate, note);
				patients.add(patient);
			
			}

			return patients;
		} catch (InterruptedException e) {
			log.error(ErrorMessage.UNABLE_TO_TAKE_CONNECTION, e);
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new DAOException(e);
		} finally {
			try {
				if (callableStatement != null && !callableStatement.isClosed()) {
					callableStatement.close();
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

	@Override
	public List<Patient> findPatients(String searchData, int offset, int count,
			String orderBy) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		CallableStatement callableStatement = null;
		
		try {
			String query;
			if (null == orderBy) {
				query = SQL_FIND_PATIENT;
			} else {
				query = "CALL find_patients(?,?,?," + orderBy + ")";
			}
			
			connection = connectionPool.take();
			callableStatement = connection.prepareCall(query);
			callableStatement.setString(1, searchData);
			callableStatement.setInt(2, offset);
			callableStatement.setInt(3, count);
			ResultSet resultSet = callableStatement.executeQuery();
			
			// Patients list
			List<Patient> patients = new ArrayList<Patient>();
			// Single patient object
			
			SimpleDateFormat birthDateFormat = new SimpleDateFormat("dd.MM.yyyy");
			SimpleDateFormat receiptDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			Patient patient = null;
			while (resultSet.next()){
				int patientId = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String sex = resultSet.getString("sex");
				String birthDate = birthDateFormat.format(resultSet.getDate("birth_date"));
				String diagnosis = resultSet.getString("diagnosis");
				String receiptDate = receiptDateFormat.format(resultSet.getDate("receipt_date"));
				int doctorId = resultSet.getInt("doctor_id");
				String note = resultSet.getString("note");
				patient = new Patient(patientId, name, surname, sex, birthDate,
						diagnosis, doctorId, receiptDate, note);
				patients.add(patient);
			}
			
			return patients;
			
		} catch (InterruptedException e) {
			log.error(ErrorMessage.UNABLE_TO_TAKE_CONNECTION, e);
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
			throw new DAOException(e);
		} finally {
			try {
				if (callableStatement != null && !callableStatement.isClosed()) {
					callableStatement.close();
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
