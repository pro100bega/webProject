package by.htp6.hospital.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.htp6.hospital.dao.pool.ConnectionPool;
import by.htp6.hospital.service.GetPatientService;
import by.htp6.hospital.service.exception.ServiceException;

public class GetPatient {

	ConnectionPool connectionPool = ConnectionPool.getInstance();

	GetPatientService getPatient;
	
	@Before
	public void before() {
		try {
			connectionPool.init();
			getPatient = new by.htp6.hospital.service.impl.GetPatient();
		} catch (SQLException e) {
			fail("Couldn`t initialize connection pool");
		}
	}

	@Test(expected = ServiceException.class)
	public void testGetPatient() throws ServiceException {
		getPatient.getPatient(0);
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
