package by.htp6.hospital.dao.impl;

import java.sql.Connection;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import by.htp6.hospital.dao.AddNewPatientDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLAddNewPatientDAO implements AddNewPatientDAO {

	@Override
	public void addNewPatient(String name, String surname, char sex,
			Date birthDate , String diagnosis, int doctorId, String note) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();
			String sql = "INSERT INTO patient(`name`, `surname`, `sex`, `birth_date`,"
					+ " `diagnosis`, `doctor_id`, `note`)"
					+ " VALUES (?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, "" + sex);
			preparedStatement.setDate(4, new java.sql.Date(birthDate.getTime()));
			preparedStatement.setString(5, diagnosis);
			preparedStatement.setInt(6, doctorId);
			preparedStatement.setString(7, note);
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connectionPool.free(connection);
		} catch (InterruptedException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
