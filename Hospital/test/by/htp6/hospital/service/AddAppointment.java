package by.htp6.hospital.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.htp6.hospital.dao.pool.ConnectionPool;
import by.htp6.hospital.service.AddAppointmentService;
import by.htp6.hospital.service.exception.ServiceException;

public class AddAppointment {
	
	private ConnectionPool connectionPool;
	
	private AddAppointmentService addAppointment;

	@Before
	public void before() {
		connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.init();
			addAppointment = new by.htp6.hospital.service.impl.AddAppointment();
		} catch (SQLException e) {
			fail("Couldn`t initialize connection pool");
		}	
	}
	
	@Test(expected = ServiceException.class)
	public void testAddAppointment() throws ServiceException {
		
		addAppointment.addAppointment(0, 21, "укол", "ампицелин", "", "");
	}
	
	@After
	public void after() {
		try {
			connectionPool.close();
		} catch (IOException e) {
			fail("Couldn`t close connection pool");
		}
	}

}
