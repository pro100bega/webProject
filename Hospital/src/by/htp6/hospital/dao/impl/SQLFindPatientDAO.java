package by.htp6.hospital.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public List<Patient> findPatients(String searchData, int doctorId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();
			String sql = "CALL find_patients(?,?)";
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(1, searchData);
			callableStatement.setInt(2, doctorId);
			ResultSet resultSet = callableStatement.executeQuery();
			
			// Patients list
			List<Patient> patients = new ArrayList<Patient>();
			// Single patient object
			Patient patient = null;
			while (resultSet.next()){
				int patientId = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String diagnosis = resultSet.getString("diagnosis");
				patient = new Patient(patientId, name, surname, diagnosis, doctorId);
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
