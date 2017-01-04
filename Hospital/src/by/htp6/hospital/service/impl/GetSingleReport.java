package by.htp6.hospital.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.bean.Report;
import by.htp6.hospital.dao.GetSingleReportDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.GetSingleReportService;
import by.htp6.hospital.service.exception.ServiceException;

public class GetSingleReport implements GetSingleReportService{
	private static final Logger log = LogManager.getLogger(GetSingleReport.class);

	@Override
	public Report getReport(String userType, int reportId) throws ServiceException {
		if (!"admin".equals(userType)) {
			throw new ServiceException("Insufficient permission");
		}
		
		if (reportId <= 0) {
			throw new ServiceException("Illegal arguments");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		GetSingleReportDAO getSingleReportDAO = daoFactory.getGetSingleReportDAO();
		
		try {
			Report report = getSingleReportDAO.getReport(reportId);
			
			return report;
			
		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

}
