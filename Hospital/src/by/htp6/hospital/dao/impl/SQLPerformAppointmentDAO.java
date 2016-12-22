package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.htp6.hospital.dao.PerformAppointmentDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLPerformAppointmentDAO implements PerformAppointmentDAO{

	@Override
	public void performAppointment(int appointmentId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();
			
			String sql = "UPDATE appointment SET status = 'done' WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, appointmentId);
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
