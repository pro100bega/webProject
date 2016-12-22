package by.htp6.hospital.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.bean.Log;
import by.htp6.hospital.bean.User;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.GetLogService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class GetAdministratorInfoCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		HttpSession session = request.getSession(false);
		User user = (User)session.getAttribute("authorisedUser");
		String userType = user.getType();
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		if (null == session.getAttribute("logList")){
			GetLogService getLog = serviceFactory.getGetLog();
			try {
				List<Log> logList = getLog.getLog(userType);
				session.setAttribute("logList", logList);
			} catch (ServiceException e) {
				response.sendRedirect("error.jsp");
			}
		}
		
		String url = "administrator/administrationPanel.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
}
