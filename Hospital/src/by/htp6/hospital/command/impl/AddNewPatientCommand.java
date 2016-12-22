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
		HttpSession session = request.getSession(true);
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		char sex = request.getParameter("sex").charAt(0);
		String birthDate = request.getParameter("birthDate");
		String note = request.getParameter("note");
		String diagnosis = request.getParameter("diagnosis");
		
		User doctor = (User)session.getAttribute("authorisedUser");
		int doctorId = doctor.getId();
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		AddNewPatientService addNewPatientService = serviceFactory.getAddNewPatient();
		
		try {
			addNewPatientService.addNewPatient(name, surname, sex, birthDate,
					diagnosis, doctorId, note);
			session.removeAttribute("patients");
			String url = "doctor/success.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			String url = "doctor/error.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
	}

}
