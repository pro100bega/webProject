package by.htp6.hospital.dao;

import java.io.IOException;
import java.sql.SQLException;

import by.htp6.hospital.dao.pool.ConnectionPool;

public class SourceInit {
	private static final SourceInit instance = new SourceInit();

	private SourceInit() {}
	
	public static SourceInit getInstance(){
		return instance;
	}

	public void init() throws SQLException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.init();
	}

	public void destroy() throws IOException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.close();
	}
}
