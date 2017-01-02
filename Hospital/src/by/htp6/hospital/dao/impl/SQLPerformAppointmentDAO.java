package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.PerformAppointmentDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.pool.ConnectionPool;

public class SQLPerformAppointmentDAO implements PerformAppointmentDAO{
	private static final Logger log = LogManager.getLogger(SQLPerformAppointmentDAO.class);

	@Override
	public void performAppointment(int appointmentId) throws DAOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			Connection connection = connectionPool.take();
			
			String sql = "UPDATE appointment SET status = 'done', "
					+ "perform_date = CURRENT_TIMESTAMP WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, appointmentId);
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
