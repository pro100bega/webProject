package by.htp6.hospital.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.htp6.hospital.bean.Report;
import by.htp6.hospital.dao.GetReportsDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.GetReportsService;
import by.htp6.hospital.service.exception.ServiceException;

public class GetReports implements GetReportsService {
	private static final Logger log = LogManager.getLogger(GetReports.class);

	@Override
	public List<Report> getReports(String userType, int offset, int count) throws ServiceException {
		if (!"admin".equals(userType)) {
			throw new ServiceException("Insufficient permission");
		}
		if (offset < 0 || count <= 0) {
			throw new ServiceException("Illegal arguments");
		}

		DAOFactory daoFactory = DAOFactory.getInstance();
		GetReportsDAO getReportsDAO = daoFactory.getGetReportsDAO();

		try {
			List<Report> reports = getReportsDAO.getReports(offset, count);
			return reports;

		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

}
