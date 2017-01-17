package by.htp6.hospital.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.htp6.hospital.dao.pool.ConnectionPool;
import by.htp6.hospital.service.FindLogService;
import by.htp6.hospital.service.exception.ServiceException;

public class FindLog {

	ConnectionPool connectionPool = ConnectionPool.getInstance();

	FindLogService findLog;
	
	@Before
	public void before() {
		try {
			connectionPool.init();
			findLog = new by.htp6.hospital.service.impl.FindLog();
		} catch (SQLException e) {
			fail("Couldn`t initialize connection pool");
		}
	}

	@Test(expected = ServiceException.class)
	public void testFindLog() throws ServiceException {
		findLog.findLog("ins", "doctor");
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
