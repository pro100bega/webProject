package by.htp6.hospital.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.htp6.hospital.dao.pool.ConnectionPool;
import by.htp6.hospital.service.ChangeUserTypeService;
import by.htp6.hospital.service.exception.ServiceException;

public class ChangeUserType {

	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	private ChangeUserTypeService changeUserType;

	@Before
	public void before() {
		try {
			connectionPool.init();
			changeUserType = new by.htp6.hospital.service.impl.ChangeUserType();
			
		} catch (SQLException e) {
			fail("Couldn`t initialize connection pool");
		}
	}

	@Test(expected = ServiceException.class)
	public void testChangeUserType() throws ServiceException {
		changeUserType.changeUserType("doctor", "doctor", 6);
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
