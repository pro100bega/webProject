package by.htp6.hospital.service.impl;

import java.util.regex.Matcher;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.htp6.hospital.dao.SendReportDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.SendReportService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.tools.PatternContainer;

public class SendReport implements SendReportService{
	private static final Logger log = LogManager.getLogger(SendReport.class);

	@Override
	public void sendReport(String header, String message) throws ServiceException {
		if (null == header || null == message || 
				"".equals(header) || "".equals(message)){
			throw new ServiceException("Illegal arguments");
		}
		
		Matcher matcher = PatternContainer.headerPattern.matcher(header);
		
		if (!matcher.find()){
			throw new ServiceException("Wrong header format");
		}
		
		matcher = PatternContainer.messagePattern.matcher(message);
		
		if (!matcher.find()){
			throw new ServiceException("Wrong message format");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		SendReportDAO sendReportDAO = daoFactory.getSendReportDAO();
		
		try {
			sendReportDAO.sendReport(header, message);
		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		
	}

}
