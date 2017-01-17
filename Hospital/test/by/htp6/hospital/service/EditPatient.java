package by.htp6.hospital.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.htp6.hospital.dao.pool.ConnectionPool;
import by.htp6.hospital.service.EditPatientService;
import by.htp6.hospital.service.exception.ServiceException;

public class EditPatient {

	ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	EditPatientService editPatient;

	@Before
	public void before() {
		try {
			connectionPool.init();
			editPatient = new by.htp6.hospital.service.impl.EditPatient();
		} catch (SQLException e) {
			fail("Couldn`t initialize connection pool");
		}
	}

	@Test(expected = ServiceException.class)
	public void testEditPatient() throws ServiceException {
		editPatient.editPatitnt(0, "Вася", "Иванов", "м", "awf", "111", null);
		
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
