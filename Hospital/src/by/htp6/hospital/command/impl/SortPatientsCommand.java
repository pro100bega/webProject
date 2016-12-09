package by.htp6.hospital.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.SortPatientsService;
import by.htp6.hospital.service.factory.ServiceFactory;

public class SortPatientsCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		@SuppressWarnings("unchecked")
		List<Patient> patients = (List<Patient>)(session.getAttribute("patients"));
		
		String sortBy = request.getParameter("sortBy");
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		SortPatientsService sortPatientsService = serviceFactory.getSortPatients();
		sortPatientsService.sortPatientsService(patients, sortBy);
		
		session.setAttribute("patients", patients);
		
		response.sendRedirect("doctor/redirection.jsp");
	}

}
