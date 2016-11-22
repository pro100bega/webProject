package by.htp6.hospital.listener;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import by.htp6.hospital.dao.SourceInit;

@WebListener
public class SourceListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		SourceInit sourceInit = SourceInit.getInstance();
		try {
			sourceInit.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		SourceInit sourceInit = SourceInit.getInstance();
		try {
			sourceInit.init();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
