package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.dao.UserRegistrationDAO;
import by.htp6.hospital.dao.exception.DAOException;

public class SQLUserRegistrationDAO implements UserRegistrationDAO {

	@Override
	public User registration(String username, String password) throws DAOException {
		if (!checkUsernameInBase(username)){
			try {
				Class.forName("org.gjt.mm.mysql.Driver");
				String url = "jdbc:mysql://127.0.0.1/hospital?useSSL=false";
				String dbUser = "root";
				String dbPassword = "Bega15326";
				Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
				
				String query = "INSERT INTO user(username, password, type) VALUES(?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, "guest"); // default user type 
				preparedStatement.executeUpdate();
				
				User user = getUserFromDatabase(username);
				
				return user;
				
			} catch (ClassNotFoundException e) {
				throw new DAOException(e);
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		} else {
			throw new DAOException("Uername \"" + username + "\" is already exists");
		}
	}

	// checks if user name already exists in database
	/**
	 * @param username
	 *            Returns {@code true} if user name already exists in database or
	 *            {@code false} in another case.
	 */
	private boolean checkUsernameInBase(String username) throws DAOException {
		boolean exists = false;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			String url = "jdbc:mysql://127.0.0.1/hospital?useSSL=false";
			String dbUser = "root";
			String dbPassword = "Bega15326";
			String query = "SELECT * FROM user WHERE username = ?";

			Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				exists = true;
				connection.close();
				return exists;
			}
			return exists;
		} catch (ClassNotFoundException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	/**
	 * @param username
	 * Creates and returns {@code User} object from database.
	 */
	private User getUserFromDatabase(String username) throws DAOException {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			String url = "jdbc:mysql://127.0.0.1/hospital?useSSL=false";
			String dbUser = "root";
			String dbPassword = "Bega15326";
			String query = "SELECT * FROM user WHERE username = ?";

			Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			User user = new User(resultSet.getInt("id"), resultSet.getString("username"),
					resultSet.getString("password"), resultSet.getString("type"));
			connection.close();
			return user;

		} catch (

		ClassNotFoundException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
