package by.htp6.hospital.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp6.hospital.dao.ChangeUserTypeDAO;
import by.htp6.hospital.dao.exception.DAOException;
import by.htp6.hospital.dao.factory.DAOFactory;
import by.htp6.hospital.service.ChangeUserTypeService;
import by.htp6.hospital.service.exception.ServiceException;

public class ChangeUserType implements ChangeUserTypeService{
	private static final Logger log = LogManager.getLogger(ChangeUserType.class);

	@Override
	public void changeUserType(String authorisedUserType, String type, int userId) throws ServiceException {
		if (!"admin".equals(authorisedUserType)) {
			throw new ServiceException("Insufficient permission");
		}
		
		if (null == type || "".equals(type) || userId <=0) {
			throw new ServiceException("Illegal arguments");
		}
		
		if (!"admin".equals(type) && !"doctor".equals(type) 
				&& !"nurse".equals(type) && !"guest".equals(type)) {
			throw new ServiceException("Illegal arguments");
		}
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		ChangeUserTypeDAO changeUserTypeDao = daoFactory.getChangeUserTypeDAO();
		
		try {
			changeUserTypeDao.changeUserType(type, userId);
			
		} catch (DAOException e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

}
