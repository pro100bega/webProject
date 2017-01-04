package by.htp6.hospital.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.GetReportsCountDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.GetReportsCountService;
import by.htp6.hospital.service.exception.ServiceException;

public class GetReportsCount implements GetReportsCountService {
	private static final Logger log = LogManager.getLogger(GetReportsCount.class);

	@Override
	public int getReportsCountService() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		GetReportsCountDAO getReportsCountDAO = daoFactory.getGetReportsCountDAO();

		try {
			int ReportsCount = getReportsCountDAO.getReportsCount();

			return ReportsCount;

		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}
}
