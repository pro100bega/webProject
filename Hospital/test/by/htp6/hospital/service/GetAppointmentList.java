package by.htp6.hospital.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.htp6.hospital.dao.pool.ConnectionPool;
import by.htp6.hospital.service.GetAppointmentListService;
import by.htp6.hospital.service.exception.ServiceException;

public class GetAppointmentList {

	ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	GetAppointmentListService getAppointmentList;

	@Before
	public void before() {
		try {
			connectionPool.init();
			getAppointmentList = new by.htp6.hospital.service.impl.GetAppointmentList();
		} catch (SQLException e) {
			fail("Couldn`t initialize connection pool");
		}
	}

	@Test(expected = ServiceException.class)
	public void testGetAppointmentList() throws ServiceException {
		getAppointmentList.getAppointmentList(10, null);
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
