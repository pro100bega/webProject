package by.htp6.hospital.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.AddNewPatientService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class AddNewPatientCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String diagnosis = request.getParameter("diagnosis");
		
		User doctor = (User)session.getAttribute("authorisedUser");
		int doctorId = doctor.getId();
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AddNewPatientService addNewPatientService = serviceFactory.getAddNewPatient();
		
		try {
			addNewPatientService.addNewPatient(name, surname, diagnosis, doctorId);
			session.removeAttribute("patients");
			response.sendRedirect("doctor/redirection.jsp");
		} catch (ServiceException e) {
			request.setAttribute("error", "true");
			String url = "doctor/doctorPanel.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
	}

}
