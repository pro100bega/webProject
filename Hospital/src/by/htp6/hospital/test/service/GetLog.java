package by.htp6.hospital.test.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.htp6.hospital.dao.pool.ConnectionPool;
import by.htp6.hospital.service.GetLogService;
import by.htp6.hospital.service.exception.ServiceException;

public class GetLog {

	ConnectionPool connectionPool = ConnectionPool.getInstance();

	GetLogService getLog;
	
	@Before
	public void before() {
		try {
			connectionPool.init();
			getLog = new by.htp6.hospital.service.impl.GetLog();
		} catch (SQLException e) {
			fail("Couldn`t initialize connection pool");
		}
	}

	@Test(expected = ServiceException.class)
	public void testGetLog() throws ServiceException {

		getLog.getLog("doctor");
	}
	
	@Test(expected = ServiceException.class)
	public void testGetLog2() throws ServiceException {

		getLog.getLog("doctor");
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
