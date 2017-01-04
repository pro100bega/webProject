package by.htp6.hospital.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.SendReportService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class SendReportCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String header = request.getParameter("header");
		String message = request.getParameter("message");
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		SendReportService sendReportService = serviceFactory.getSendReport();
		
		try {
			sendReportService.sendReport(header, message);
			session.setAttribute("successMessage", "sendReport");
			response.sendRedirect("success.jsp");
			
		} catch (ServiceException e) {
			request.setAttribute("errorMessage", e.getMessage());
			response.sendRedirect("error.jsp");
		}
		
	}

}
