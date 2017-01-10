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
	
	private static final String SQL_GET_PATIENTS_COUNT_FOR_NURSE = 
			"CALL get_patient_count(?)";

	private static final String SQL_GET_PATIENTS_COUNT_FOR_DOCTOR = 
			"CALL get_patient_count_by_doctor_id(?,?)";
	
	private static final String SQL_GET_ALL_PATIENTS_COUNT_FOR_NURSE =
			"SELECT COUNT(*) FROM patient";
	
	private static final String SQL_GET_ALL_PATIENTS_COUNT_FOR_DOCTOR =
			"SELECT COUNT(*) FROM patient WHERE doctor_id = ?";
	
	@Override
	public int getPatientsCountByDoctorId(int doctorId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = connectionPool.take();

			preparedStatement = connection.prepareStatement(
					SQL_GET_ALL_PATIENTS_COUNT_FOR_DOCTOR);
			preparedStatement.setInt(1, doctorId);
			ResultSet resultSet = preparedStatement.executeQuery();
			int patientsCount = 0;
			if (resultSet.next()) {
				patientsCount = resultSet.getInt(1);
			}

			return patientsCount;
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

	@Override
	public int getPatientsCount() throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.take();

			preparedStatement = connection.prepareStatement(
					SQL_GET_ALL_PATIENTS_COUNT_FOR_NURSE);
			ResultSet resultSet = preparedStatement.executeQuery();
			int patientsCount = 0;
			if (resultSet.next()) {
				patientsCount = resultSet.getInt(1);
			}

			return patientsCount;
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

	@Override
	public int getPatientsCountByDoctorId(String searchData, int doctorId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		CallableStatement callableStatement = null;
		
		try {
			connection = connectionPool.take();

			callableStatement = connection.prepareCall(
					SQL_GET_PATIENTS_COUNT_FOR_DOCTOR);
			callableStatement.setString(1, searchData);
			callableStatement.setInt(2, doctorId);
			ResultSet resultSet = callableStatement.executeQuery();
			int patientsCount = 0;
			if (resultSet.next()) {
				patientsCount = resultSet.getInt(1);
			}

			return patientsCount;
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} finally {
			try {
				if (callableStatement != null && !callableStatement.isClosed()) {
					callableStatement.close();
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

	@Override
	public int getPatientsCount(String searchData) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		CallableStatement callableStatement = null;
		
		try {
			connection = connectionPool.take();

			callableStatement = connection.prepareCall(
					SQL_GET_PATIENTS_COUNT_FOR_NURSE);
			callableStatement.setString(1, searchData);
			ResultSet resultSet = callableStatement.executeQuery();
			int patientsCount = 0;
			if (resultSet.next()) {
				patientsCount = resultSet.getInt(1);
			}

			return patientsCount;
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} finally {
			try {
				if (callableStatement != null && !callableStatement.isClosed()) {
					callableStatement.close();
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
