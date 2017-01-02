package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.EditPatientDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLEditPatientDAO implements EditPatientDAO {
	private static final Logger log = LogManager.getLogger(SQLEditPatientDAO.class);

	@Override
	public void editPatient(int patientId, String name, String surname,
			String sex, Date birthDate, String diagnosis, String note) 
					throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();
			String sql = "UPDATE patient SET name = ?, surname = ?, sex = ?, birth_date = ?,"
					+ "diagnosis = ?, note = ? WHERE id = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, sex);
			preparedStatement.setDate(4, new java.sql.Date(birthDate.getTime()));
			preparedStatement.setString(5, diagnosis);
			preparedStatement.setString(6, note);
			preparedStatement.setInt(7, patientId);
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connectionPool.free(connection);
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		} catch (SQLException e) {
			log.error(e.getMessage());
			throw new DAOException(e);
		}
	}

}
