package by.htp6.hospital.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.dao.UserDAO;
import by.htp6.hospital.dao.exception.DAOException;

public class SQLUserDAO implements UserDAO {

	@Override
	public User logination(String username, String password) throws DAOException {
		try {
			String dbName = "root";
			String dbPassword = "Bega15326";
			String url = "jdbc:mysql://127.0.0.1/hospital?useSSL=false";

			Class.forName("org.gjt.mm.mysql.Driver");
			Connection connection = DriverManager.getConnection(url, dbName, dbPassword);

			String query = "SELECT * FROM user WHERE username = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (password.equals(resultSet.getString("password"))) {
					User user = new User(resultSet.getInt("id"), resultSet.getString("username"),
							resultSet.getString("password"), resultSet.getString("type"));
					return user;
				} else {
					throw new DAOException("Incorrect password or username");
				}
			} else {
				throw new DAOException("Incorrect password or username");
			}
		} catch (ClassNotFoundException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
