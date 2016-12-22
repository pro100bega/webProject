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
import by.htp6.hospital.service.FindLogService;
import by.htp6.hospital.service.GetLogService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class FindLogCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String searchData = request.getParameter("searchData");
		User user = (User)session.getAttribute("authorisedUser");
		String userType = user.getType();
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		
		if ("".equals(searchData) || null == searchData){
			GetLogService getLog = serviceFactory.getGetLog();
			try {
				List<Log> logList = getLog.getLog(userType);
				session.setAttribute("logList", logList);
				String url = "administrator/administrationPanel.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				return;
			} catch (ServiceException e) {
				String url = "error.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}
		}		
		
		FindLogService findLog = serviceFactory.getFindLog();
		
		try {
			List<Log> logList = findLog.findLog(searchData, userType);
			request.setAttribute("searchData", searchData);
			session.setAttribute("logList", logList);
			String url = "administrator/administrationPanel.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			String url = "error.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		
	}
	
}
