package by.htp6.hospital.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.htp6.hospital.dao.pool.ConnectionPool;
import by.htp6.hospital.service.AddNewPatientService;
import by.htp6.hospital.service.exception.ServiceException;

public class AddNewPatient {

	private ConnectionPool connectionPool;

	private AddNewPatientService addNewPatient;

	@Before
	public void before() {
		connectionPool = ConnectionPool.getInstance();
		try {
			connectionPool.init();
			addNewPatient= new by.htp6.hospital.service.impl.AddNewPatient();
		} catch (SQLException e) {
			fail("Couldn`t initialize connection pool");
		}
	}

	@Test(expected = ServiceException.class)
	public void testAddNewPatient() throws ServiceException {
		
		addNewPatient.addNewPatient("Вася", "Иванов", "м", "awf", "111", 0, null);
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
