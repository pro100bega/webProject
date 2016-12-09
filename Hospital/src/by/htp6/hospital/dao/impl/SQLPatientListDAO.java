package by.htp6.hospital.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.dao.PatientListDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLPatientListDAO implements PatientListDAO{

	@Override
	public List<Patient> getPatients(int doctorId) throws DAOException {
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
			Connection connection = connectionPool.take();
			try {
				String query = "CALL select_patient_by_doctor_id(?)";
				CallableStatement callableStatement = connection.prepareCall(query);
				String doctorIdString = "" + doctorId;
				callableStatement.setString(1, doctorIdString);
				
				ResultSet resultSet = callableStatement.executeQuery();
				List<Patient> patients = new ArrayList<>();
				while (resultSet.next()){
					int id = resultSet.getInt("id");
					String name = resultSet.getString("name");
					String surname = resultSet.getString("surname");
					String diagnosis = resultSet.getString("diagnosis");
					Patient patient = new Patient(id, name, surname, diagnosis, doctorId);
					patients.add(patient);
				}
				connectionPool.free(connection);
				return patients;
			} catch (SQLException e) {
				connectionPool.free(connection);
				throw new DAOException(e);
			}
		} catch (InterruptedException e) {
			throw new DAOException(e);
		}
	}

}