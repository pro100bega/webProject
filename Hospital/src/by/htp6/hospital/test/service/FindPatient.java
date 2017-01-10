package by.htp6.hospital.test.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.htp6.hospital.dao.pool.ConnectionPool;
import by.htp6.hospital.service.FindPatientService;
import by.htp6.hospital.service.exception.ServiceException;

public class FindPatient {

	ConnectionPool connectionPool = ConnectionPool.getInstance();

	FindPatientService findPatient;
	
	@Before
	public void before() {
		try {
			connectionPool.init();
			findPatient = new by.htp6.hospital.service.impl.FindPatient();
		} catch (SQLException e) {
			fail("Couldn`t initialize connection pool");
		}
	}

	@Test(expected = ServiceException.class)
	public void testFindPatients() throws ServiceException {
		findPatient.findPatients("Вася", -1, 5, null);
	}

	@Test(expected = ServiceException.class)
	public void testFindPatientByDoctorId() throws ServiceException {
		findPatient.findPatientsByDoctorId("Вася",21, -1, 5, null);
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
