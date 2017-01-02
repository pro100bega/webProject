package by.htp6.hospital.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.GetPatientsCountDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLGetPatientsCountDAO implements GetPatientsCountDAO {
	private static final Logger log = LogManager.getLogger(SQLGetPatientsCountDAO.class);

	@Override
	public int getPatientsCountByDoctorId(int doctorId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			Connection connection = connectionPool.take();

			String query = "SELECT COUNT(*) FROM patient WHERE doctor_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, doctorId);
			ResultSet resultSet = preparedStatement.executeQuery();
			int patientsCount = 0;
			if (resultSet.next()) {
				patientsCount = resultSet.getInt(1);
			}
			preparedStatement.close();
			connectionPool.free(connection);

			return patientsCount;
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		}
	}

	@Override
	public int getPatientsCount() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();

			String query = "SELECT COUNT(*) FROM patient";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			int patientsCount = 0;
			if (resultSet.next()) {
				patientsCount = resultSet.getInt(1);
			}
			preparedStatement.close();
			connectionPool.free(connection);

			return patientsCount;
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		}
	}

	@Override
	public int getPatientsCountByDoctorId(String searchData, int doctorId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			Connection connection = connectionPool.take();

			String query = "CALL get_patient_count_by_doctor_id(?,?)";
			CallableStatement callableStatement = connection.prepareCall(query);
			callableStatement.setString(1, searchData);
			callableStatement.setInt(2, doctorId);
			ResultSet resultSet = callableStatement.executeQuery();
			int patientsCount = 0;
			if (resultSet.next()) {
				patientsCount = resultSet.getInt(1);
			}
			callableStatement.close();
			connectionPool.free(connection);

			return patientsCount;
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		}
	}

	@Override
	public int getPatientsCount(String searchData) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			Connection connection = connectionPool.take();

			String query = "CALL get_patient_count(?)";
			CallableStatement callableStatement = connection.prepareCall(query);
			callableStatement.setString(1, searchData);
			ResultSet resultSet = callableStatement.executeQuery();
			int patientsCount = 0;
			if (resultSet.next()) {
				patientsCount = resultSet.getInt(1);
			}
			callableStatement.close();
			connectionPool.free(connection);

			return patientsCount;
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		}
	}
}
