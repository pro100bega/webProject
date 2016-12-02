package by.htp6.hospital.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.bean.User;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.FindPatientService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class FindPatientCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchData = request.getParameter("searchData");
		if (searchData == null || "".equals(searchData)) {
			response.sendRedirect("doctor/redirection.jsp");
		} else {
			HttpSession session = request.getSession(false);
			User user = (User) session.getAttribute("authorisedUser");
			int doctorId = user.getId();

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			FindPatientService findPatientService = serviceFactory.getFindPatientService();

			try {
				List<Patient> patients = findPatientService.findPatients(searchData, doctorId);
				session.setAttribute("foundPatients", patients);
				String url = "doctor/foundPatients.jsp?searchData=" + searchData;
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			} catch (ServiceException e) {
				String url = "error.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}
		}

	}

}
