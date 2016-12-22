package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.htp6.hospital.dao.AddAppointmentDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLAddAppointmentDAO implements AddAppointmentDAO {

	@Override
	public void addAppointment(int patientId, int doctorId, String type,
			String name) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();
			String sql = "INSERT INTO procedure(`patient_id`, `doctor_id`, `type`, `name`, `status`)"
					+ " VALUES (?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, patientId);
			preparedStatement.setInt(2, doctorId);
			preparedStatement.setString(3, type);
			preparedStatement.setString(4, name);
			preparedStatement.setString(5, "undone");
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
