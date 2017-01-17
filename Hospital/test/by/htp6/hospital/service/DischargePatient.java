package by.htp6.hospital.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.htp6.hospital.dao.pool.ConnectionPool;
import by.htp6.hospital.service.DischargePatientService;
import by.htp6.hospital.service.exception.ServiceException;

public class DischargePatient {
	ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	DischargePatientService dischargePatient;
	
	@Before
	public void before() {
		try {
			connectionPool.init();
			dischargePatient = new by.htp6.hospital.service.impl.DischargePatient();
		} catch (SQLException e) {
			fail("Couldn`t initialize connection pool");
		}
	}
	
	@Test(expected = ServiceException.class)
	public void testDischargePatient() throws ServiceException {
		dischargePatient.dischargePatient(0, "");
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
