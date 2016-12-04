package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import by.htp6.hospital.dao.AddNewPatientDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLAddNewPatientDAO implements AddNewPatientDAO {

	@Override
	public void addNewPatient(String name, String surname, String diagnosis, int doctorId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();
			String sql = "INSERT INTO patient(`name`, `surname`, `diagnosis`, `doctor_id`)"
					+ " VALUES (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, diagnosis);
			preparedStatement.setInt(4, doctorId);
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
