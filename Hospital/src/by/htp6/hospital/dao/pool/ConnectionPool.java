package by.htp6.hospital.dao.pool;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool implements Closeable {
	static final int POOL_SIZE = 5;
	private BlockingQueue<Connection> freeConnections;
	private BlockingQueue<Connection> busyConnections;

	public static ConnectionPool getInstance() {
		return instance;
	}

	public void init() throws SQLException{
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.dbResource");
		String url = "jdbc:mysql://127.0.0.1/hospital?useSSL=false";
		String dbUsername = resourceBundle.getString("db.username");
		String dbPassword = resourceBundle.getString("db.password");
		
		freeConnections = new ArrayBlockingQueue<>(POOL_SIZE);
		busyConnections = new ArrayBlockingQueue<>(POOL_SIZE);
		
		for (int i = 0; i < POOL_SIZE; i++){
			freeConnections.add(DriverManager.getConnection(url, dbUsername, dbPassword));
		}
	}
	
	public Connection take() throws InterruptedException{
		Connection connection = freeConnections.take();
		busyConnections.put(connection);
		return connection;
	}

	public void free(Connection connection) throws InterruptedException{
		if (connection == null){
			throw new RuntimeException("Connection is NULL");
		}
		Connection temporaryConnection = connection;
		connection = null;
		busyConnections.remove(temporaryConnection);
		freeConnections.put(temporaryConnection);
		
	}
	@Override
	public void close() throws IOException {
		if (busyConnections.size() != 0){
			for (int i = 0; i < busyConnections.size(); i++){
				try {
					Connection temporaryConnection = busyConnections.take();
					if (temporaryConnection.isClosed() == false){
						temporaryConnection.close();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
		}
		
		if (freeConnections.size() != 0){
			for (int i = 0; i < freeConnections.size(); i++){
				try {
					Connection temporaryConnection = freeConnections.take();
					if (temporaryConnection.isClosed() == false){
						temporaryConnection.close();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
		}
		
	}

	private final static ConnectionPool instance = new ConnectionPool();

	private ConnectionPool() {

	}

}
