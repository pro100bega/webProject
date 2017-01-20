package by.htp6.hospital.dao.pool;

import java.io.Closeable;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.htp6.hospital.constant.ErrorMessage;

public class ConnectionPool implements Closeable {
	private static final Logger log = LogManager.getLogger(ConnectionPool.class);
	
	private static final String DRIVER_NAME = "org.gjt.mm.mysql.Driver";
	
	static final int POOL_SIZE = 5;
	
	private BlockingQueue<Connection> freeConnections;
	private BlockingQueue<Connection> busyConnections;
	
	private final static ConnectionPool instance = new ConnectionPool();

	private ConnectionPool() {

	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	public void init() throws SQLException{
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			log.error(ErrorMessage.DATABASE_ERROR, e);
			throw new SQLException(e);//!!!!!!!! не надо выбрасывать не свои исключения, это ошибка
		}
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.dbResource");// константные неименованные строки. именовать
		String dbUrl = resourceBundle.getString("db.localConnectionString");
		String dbUsername = resourceBundle.getString("db.username");
		String dbPassword = resourceBundle.getString("db.localPassword");
		
		freeConnections = new ArrayBlockingQueue<>(POOL_SIZE);
		busyConnections = new ArrayBlockingQueue<>(POOL_SIZE);
		
		for (int i = 0; i < POOL_SIZE; i++){
			freeConnections.add(DriverManager.getConnection(dbUrl, dbUsername, dbPassword));
		}
	}
	
	public Connection take() throws InterruptedException{
		Connection connection = freeConnections.take();
		busyConnections.put(connection);
		return connection;
	}

	public void free(Connection connection) throws InterruptedException{
		if (connection == null){
			log.error(ErrorMessage.CONNECTION_IS_NULL);
			throw new RuntimeException(ErrorMessage.CONNECTION_IS_NULL);// если выбрасываешь Runtime, то лучше выбрасывай свой Runtime
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
					log.error(ErrorMessage.DATABASE_ERROR, e);
				} catch (SQLException e) {
					log.error(ErrorMessage.DATABASE_ERROR, e);
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
					log.error(ErrorMessage.DATABASE_ERROR, e);
				} catch (SQLException e) {
					log.error(ErrorMessage.DATABASE_ERROR, e);
				}	
			}
		}
		
	}
}
