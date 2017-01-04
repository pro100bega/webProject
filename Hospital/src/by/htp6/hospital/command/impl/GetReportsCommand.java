package by.htp6.hospital.command.impl;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.bean.Report;
import by.htp6.hospital.bean.User;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.GetReportsCountService;
import by.htp6.hospital.service.GetReportsService;
import by.htp6.hospital.service.GetUnreadReportsCountService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class GetReportsCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		User user = (User) session.getAttribute("authorisedUser");
		String userType = user.getType();

		String requestCurrentPage = request.getParameter("currentPage");
		int currentPage;
		if (requestCurrentPage == null) {
			String sessionCurrentPage = "" + session.getAttribute("currentPage");
			currentPage = (sessionCurrentPage == null) 
					? 1 
					: Integer.parseInt(sessionCurrentPage);
		} else {
			currentPage = Integer.parseInt(requestCurrentPage);
		}

		String requestReportsPerPage = request.getParameter("reportsPerPage");
		int reportsPerPage;
		if (requestReportsPerPage == null) {
			String sessionReportsPerPage = "" + 
		session.getAttribute("reportsPerPage");
			reportsPerPage = (sessionReportsPerPage == null) 
					? 10 
					: Integer.parseInt(sessionReportsPerPage);
		} else {
			reportsPerPage = Integer.parseInt(requestReportsPerPage);
		}

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		GetReportsCountService getReportsCount = serviceFactory.getGetReportsCount();
		GetReportsService getReports = serviceFactory.getGetReports();
		GetUnreadReportsCountService getUnreadReportsCount = 
				serviceFactory.getGetUnreadReportsCount();

		try {
			int reportsCount = getReportsCount.getReportsCountService();
			int offset = reportsPerPage * (currentPage - 1);
			List<Report> reports = getReports.getReports(userType, offset, 
					reportsPerPage);
			int unreadReportsCount = 
					getUnreadReportsCount.getUnreadReportsCountService();

			request.setAttribute("unreadReportsCount", unreadReportsCount);			
			session.setAttribute("currentPage", currentPage);
			session.setAttribute("reportsPerPage", reportsPerPage);
			request.setAttribute("reports", reports);
			request.setAttribute("reportsCount", reportsCount);
			
			String url = "administrator/reports.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			String url = "error";
			response.sendRedirect(url);
		}
	}

}
