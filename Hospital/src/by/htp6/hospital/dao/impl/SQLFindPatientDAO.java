package by.htp6.hospital.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.dao.FindPatientDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLFindPatientDAO implements FindPatientDAO{
	
	/**
	 * @author Бегенч
	 * @param searchData - string that is needed to find by
	 * @param doctorId - identifier of doctor whose patients need to be found
	 * @return ArrayList that contains found patients
	 * @throws DAOException
	 */
	@Override
	public List<Patient> findPatientsByDoctorId(String searchData,
			int doctorId, int offset, int count, String orderBy) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			String query;
			if (null == orderBy) {
				query = "CALL find_patients_by_doctor_id(?,?,?,?,receipt_date)";
			} else {
				query = "CALL find_patients_by_doctor_id(?,?,?,?," + orderBy + ")";
			}
			Connection connection = connectionPool.take();
			CallableStatement callableStatement = connection.prepareCall(query);
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
				char sex = resultSet.getString("sex").charAt(0);
				String birthDate = birthDateFormat.format(resultSet.getDate("birth_date"));
				String diagnosis = resultSet.getString("diagnosis");
				String receiptDate = receiptDateFormat.format(resultSet.getDate("receipt_date"));
				String note = resultSet.getString("note");
				patient = new Patient(patientId, name, surname, sex, birthDate,
						diagnosis, doctorId, receiptDate, note);
				patients.add(patient);
			
			}
			callableStatement.close();
			connectionPool.free(connection);
			return patients;
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<Patient> findPatients(String searchData, int offset, int count,
			String orderBy) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			String query;
			if (null == orderBy) {
				query = "CALL find_patients_by_doctor_id(?,?,?,receipt_date)";
			} else {
				query = "CALL find_patients_by_doctor_id(?,?,?," + orderBy + ")";
			}
			
			Connection connection = connectionPool.take();
			CallableStatement callableStatement = connection.prepareCall(query);
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
				char sex = resultSet.getString("sex").charAt(0);
				String birthDate = birthDateFormat.format(resultSet.getDate("birth_date"));
				String diagnosis = resultSet.getString("diagnosis");
				String receiptDate = receiptDateFormat.format(resultSet.getDate("receipt_date"));
				int doctorId = resultSet.getInt("doctor_id");
				String note = resultSet.getString("note");
				patient = new Patient(patientId, name, surname, sex, birthDate,
						diagnosis, doctorId, receiptDate, note);
				patients.add(patient);
			}
			callableStatement.close();
			connectionPool.free(connection);
			return patients;
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
