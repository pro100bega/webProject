package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.DischargePatientDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLDischargePatientDAO implements DischargePatientDAO {
	private static final Logger log = LogManager.getLogger(SQLDischargePatientDAO.class);

	private static final String SQL_DISCHARGE_PATIENT = 
			"DELETE FROM patient WHERE id = ?";
	
	private static final String SQL_SET_FINAL_DIAGNOSIS = 
			"UPDATE patient SET diagnosis = ? WHERE id = ?";
	
	@Override
	public boolean dishcargePatient(int patientId, String finalDiagnosis) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = connectionPool.take();
			setFinalDiagnosis(connection, patientId, finalDiagnosis);
		
			preparedStatement = connection.prepareStatement(SQL_DISCHARGE_PATIENT);
			preparedStatement.setInt(1, patientId);
			int resultOfUpdate = preparedStatement.executeUpdate();
			return (resultOfUpdate == 0) ? false : true;
			
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

	private void setFinalDiagnosis(Connection connection, int patientId, String finalDiagnosis) throws DAOException {
		
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(SQL_SET_FINAL_DIAGNOSIS);
			preparedStatement.setString(1, finalDiagnosis);
			preparedStatement.setInt(2, patientId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} finally {
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}

			} catch (SQLException e) {
				log.error(e.getMessage());
				throw new DAOException(e);
			}
		}
	}
}