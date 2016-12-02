package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.htp6.hospital.dao.DischargePatientDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLDischargePatientDAO implements DischargePatientDAO{

	@Override
	public boolean dishcargePatient(int patientId, String finalDiagnosis) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();
			setFinalDiagnosis(connection, patientId, finalDiagnosis);
			String sql = "DELETE FROM patient WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, patientId);
			int resultOfUpdate = preparedStatement.executeUpdate();
			return (resultOfUpdate == 0) ? false : true;
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	private void setFinalDiagnosis(Connection connection, int patientId, String finalDiagnosis)
			throws DAOException{
		String sql = "UPDATE patient SET diagnosis = ? WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, finalDiagnosis);
			preparedStatement.setInt(2, patientId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
}
