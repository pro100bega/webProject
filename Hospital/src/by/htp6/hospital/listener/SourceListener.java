package by.htp6.hospital.listener;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.htp6.hospital.dao.SourceInit;

public class SourceListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		SourceInit sourceInit = SourceInit.getInstance();
		try {
			sourceInit.init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		SourceInit sourceInit = SourceInit.getInstance();
		try {
			sourceInit.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
